ALTER TABLE portfolio.post ADD available boolean default true;

select * from portfolio.post where available = 1 and board_uri = 'test';

update portfolio.post
set
    post.available = false
where board_uri = 'test' and post_writer = 'asdf';

select * from portfolio.post where board_uri = 'test' and available = false;


select * from portfolio.post
where post_content like '%p%';