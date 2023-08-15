
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