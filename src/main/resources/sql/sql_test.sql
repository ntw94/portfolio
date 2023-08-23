
select * from post;


select *
from post
where 1 = 1
and board_uri='mymymymyboard'
and available = false
and post_writer='asdf';

select * from post;

select * from comment;

update comment
set
    comment_available = false
where id = 4;

select * from comment;



/* 글 찾기 sql union을 활용해서 서로 다른 테이블에서 필요한 데이터를 가져온다. */
select a.mode,a.writer,a.content,a.regiDate , a.id
from (select 1 as mode,id as id ,comment_writer writer, comment_content content, comment_regiDate regiDate
      from comment
      where board_uri = 'test'
        and comment_writer = 'asdf'
      union all
      select 2 as mode,id as id , post_writer writer, post_content content, post_regiDate regiDate
      from post p
      where board_uri = 'test'
        and post_writer = 'asdf') a
order by a.regiDate desc;

select *
from comment
where (id,post_id) in ((1,37),(2,37));

select *
from comment


;
select count(*)
from comment c
where c.comment_available = true; #에기 맞는 구문일까?

select b.board_uri,IFNULL(sum(post_hit),0) `조회수`
from post p right join board b on p.board_uri = b.board_uri
group by b.board_uri
order by `조회수` desc;

/* */
update board b, (
    select b.board_uri,
           ifnull(sum(`게시글`) + sum(`댓글 수`) + sum(`조회수`),0) score
    from (
             select p.board_uri,ifnull(count(*),0) `게시글`,
                    ifnull( (select count(c.id)
                             from comment c
                             where c.comment_available = true
                               and c.board_uri = p.board_uri),0) `댓글 수`,ifnull(sum(p.post_hit),0) `조회수`
             from board b left join post p on (b.board_uri = p.board_uri )
             where p.available = true
             group by p.board_uri) result right join board b on (result.board_uri = b.board_uri)
    group by b.board_uri
) a1
set b.score = a1.score
where b.board_uri = a1.board_uri;


select * from board;


select board_uri, count(id)
from board
group by board_uri
;


select board_uri ,post_hit from post;

select * from board;


select board_uri,count(id) "댓글 수"
from comment
where comment_available = true
group by board_uri;



select count(*)
from board
where board_uri = 'test';

select * from board;


/* 메인카테고리 서브카테고리 전체 리스트 동시에 가져오기 */
select *
from board_main_category inner join board_sub_category bsc on board_main_category.id = bsc.board_main_category_id

/* board 가져올때 카테고리랑 같이 가져오기 */
select b.id, b.board_title, b.board_uri, b.board_description,
       b.upload_file_name, b.store_file_name, b.member_id, b.board_create_date,
       b.board_update_date, b.main_category_id, b.sub_category_id,
       b.score,
       bmc.id, bmc.category_name,
       bsc.id, bsc.board_main_category_id, bsc.category_name
from board b left join board_main_category bmc on b.main_category_id = bmc.id
    left join board_sub_category bsc on b.sub_category_id = bsc.id
where main_category_id = 1
order by score desc
# limit #{startIdx},#{perPageSize}





select * from board;
select * from board;