drop table member;
drop table image_profile;
drop table board;
drop table comment;
drop table post;
drop table post_category;
drop table board_manager;
drop table board_favor;

#회원
create table member (
    id int auto_increment primary key ,
    member_id varchar(50) unique not null,
    member_pwd varchar(100) not null,
    member_name varchar(50) not null,
    member_phone varchar(20) not null,
    member_role varchar(50),
    member_regiDate datetime
);

#차단 회원 테이블 ( 차단 사유, 차단한 매니저 아이디)
create table stop_member(
    id int auto_increment primary key,
    board_uri varchar(50) not null,
    member_id varchar(50) not null,
    stop_date datetime,
    regiDate datetime
);


insert into stop_member(board_uri, member_id, stop_date, regiDate)
values ('qweqw', 'test', now(), now());
select * from stop_member;
select * from member;

#게시판
create table board(
    id int auto_increment primary key,
    board_title varchar(50) not null unique,
    board_uri varchar(50) not null unique,
    board_description varchar(200) not null,
    upload_file_name varchar(100),
    store_file_name varchar(100),
    member_id varchar(50) not null,
    board_create_date date,
    board_update_date date
);

#게시판 즐겨찾기
create table board_favor(
    id int auto_increment primary key,
    member_id varchar(50) not null,
    board_id int not null,
    favor int default 1,
    favor_regiDate datetime
);


#게시판 매니저
create table board_role(
    id int auto_increment primary key ,
    board_uri varchar(50) not null,
    member_id varchar(50) not null,
    board_role varchar(50) not null,
    regiDate datetime
);
# duplicate를 사용하기위해 조합키로 묶었음
# on duplicate key update는 PK나 unique를 검색함
alter table board_role add unique key(board_uri,member_id);

#게시글
create table post(
    id int auto_increment primary key,
    board_uri varchar(50) not null,
    post_title varchar(100) not null,
    post_writer varchar(100) not null,
    post_hit int default 0,
    post_content text,
    post_category varchar(50),
    post_notice int default 1,
    post_regiDate datetime
);

#공지 게시글
create table post_notice(
    id int auto_increment primary key,
    board_uri varchar(50) not null,
    post_id int,
    post_regiDate datetime
);

create table post_category(
    id int auto_increment primary key,
    board_uri varchar(50) not null,
    category_menu varchar(50) not null unique,
    category_order int default 1,
    category_regiDate datetime
);

alter table post_category add unique key(board_uri,category_order);
desc post_category;
# drop index `category_order` on post_category;

#댓글
create table comment(
    id int auto_increment primary key,
    post_id int not null,
    board_uri varchar(50) not null,
    comment_writer varchar(50) not null,
    comment_content text,
    comment_parentNo int,
    comment_sequence int default 0,
    comment_level int default 0,
    comment_available int default 1,
    comment_regiDate datetime
);

#회원이미지
create table image_profile(
    id int auto_increment primary key ,
    member_id varchar(50) not null,
    upload_file_name varchar(100),
    store_file_name varchar(100),
    regi_date datetime
);

#회원 이미지
create table file_member_image(
    id int auto_increment primary key ,
    member_id varchar(50) not null,
    upload_file_name varchar(100),
    store_file_name varchar(100),
    regi_date datetime
);

#게시판 이미지
create table file_board_image(
    id int auto_increment primary key,
    board_uri varchar(50) not null,
    upload_file_name varchar(100),
    store_file_name varchar(100),
    regi_date datetime
);
alter table file_board_image add unique key(board_uri);


#게시글 임시 이미지 테이블
create table file_post_temp(
    id int auto_increment primary key ,
    uuid varchar(200) not null,
    member_id varchar(50) not null, # 글쓴 사람
    upload_file_name varchar(100),
    store_file_name varchar(100),
    regi_date datetime
);

select * from file_board_image;
select * from board_role;
# 게시글 실제 저장 테이블
create table file_post_image(
    id int auto_increment primary key,
    uuid varchar(200) not null,
    post_id int,
    upload_file_name varchar(100),
    store_file_name varchar(100),
    regi_date datetime
);


select p.id id, p.board_uri board_uri, post_title, post_writer, post_content,post_notice,post_category,p.post_regiDate post_regiDate
from post p,post_notice pn
where p.id = pn.post_id and pn.board_uri = 'test'
order by pn.post_regiDate desc;


insert into post (board_uri, post_title, post_writer, post_content, post_regiDate)
VALUES ('test', '제목1', 'hong', 'gdgd', now());
insert into post (board_uri, post_title, post_writer, post_content, post_regiDate)
VALUES ('test','제목1','hong','gdgd',now());
insert into post (board_uri, post_title, post_writer, post_content, post_regiDate)
VALUES ('test','제목1','hong','gdgd',now());
insert into post (board_uri, post_title, post_writer, post_content, post_category,post_regiDate)
VALUES ('test','제목1','hong','gdgd','메뉴1',now());
insert into post (board_uri, post_title, post_writer, post_content, post_category,post_regiDate)
VALUES ('test','제목1','hong','gdgd','메뉴2',now());
insert into post (board_uri, post_title, post_writer, post_content,post_notice,post_category,post_regiDate)
VALUES ('test','제목1','hong','gdgd',1,'공지',now());

insert into post_category (board_uri, category_menu, category_regiDate)
VALUES ('test','메뉴1',now());
insert into post_category (board_uri, category_menu, category_regiDate)
VALUES ('test','메뉴2',now());
insert into post_category (board_uri, category_menu, category_regiDate)
VALUES ('test','메뉴3',now());
insert into post_category (board_uri, category_menu, category_regiDate)
VALUES ('test','공지',now());

insert into post_notice (board_uri, post_id, post_regiDate)
values ('test',9,now());

insert into post_notice (board_uri, post_id, post_regiDate)
values ('test',12,now());

insert into post_notice (board_uri, post_id, post_regiDate)
values ('test',22,now());

select *
from board b, board_favor f
where b.id = f.board_id and f.member_id='asdf';

select * from post;

select * from post
where id in (select post_id
             from post_notice
             where post.board_uri = post_notice.board_uri);

select * from post_category;


select date_format(post_regiDate,'%h:%m')
from post;

#조회
select * from member;
select * from board_manager;
select * from image_profile;
select * from board;
select * from post;
select * from board_favor;
select count(*) from post where board_uri = 'test';
select * from comment order by comment_parentNo asc,comment_sequence asc,comment_level asc;

select *
from post
where board_uri ='test' and
      DATE_FORMAT(post_regiDate,'%Y-%m-%d') =now();

select member_id
from stop_member;


select * from stop_member;


select b.id,b.board_uri,b.board_title,b.board_description,b.member_id,b.board_create_date,
       f.upload_file_name,f.store_file_name,f.regi_date
from board b inner join file_board_image f on b.board_uri = f.board_uri
where b.board_uri = 'test1095828'
order by id;


select * from board where id = 17 and board_uri='voidboard';
update board
    set board_title = '이름바꿈',
        board_description ='설명바꾼다'
where board_uri='voidboard' and id = 17;

select * from board;
select * from file_board_image;

select * from file_board_image;

select * from post_category;
delete from post_category
where id=220;

insert into post_category(id,board_uri,category_menu,category_order,category_regiDate)
values(215,'mymymymyboard','zzzzz321',4,now())
on duplicate key update category_menu = values(category_menu),category_order = values(category_order);

select * from post_category;
select * from post where board_uri = 'mymymymyboard';

update post
set
    post_category = 'asdfdsaf'
where board_uri = 'mymymymyboard' and post_category = 'zz';

select * from post;