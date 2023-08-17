
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


