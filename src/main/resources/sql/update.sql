ALTER TABLE portfolio.post ADD available boolean default true; # available 컬럼 추가
ALTER TABLE portfolio.post ADD no_tag_content text; #태그 제거한 순수 내용
ALTER TABLE portfolio.board ADD score bigint default 0;

select * from board;

select * from portfolio.post;
select * from portfolio.file_board_image;

