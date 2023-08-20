
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
where c.comment_available = true;

select b.*,ifnull(sum(`게시글`) + sum(`댓글 수`) + sum(`조회수`),0) score
from (select p.board_uri,count(*) `게시글`,
                             (select count(id)
                               from comment c
                               where available = true
                                 and c.board_uri = p.board_uri) `댓글 수`,sum(post_hit) `조회수`
      from board b right outer join post p on (b.board_uri = p.board_uri )
      where available = true
      group by p.board_uri) result right join board b on (result.board_uri = b.board_uri)
group by b.board_uri
limit 10
;



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



select board_uri
from board;

