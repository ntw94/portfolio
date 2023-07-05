drop table member;
drop table image_profile;
drop table board;
drop table comment;



#회원
create table member (
    id int auto_increment primary key ,
    member_id varchar(50) unique not null,
    member_pwd varchar(100) not null,
    member_name varchar(50) not null,
    member_phone varchar(20) not null,
    member_regiDate datetime
);

#회원이미지
create table image_profile(
    id int auto_increment primary key ,
    member_id varchar(50) not null,
    upload_file_name varchar(100),
    store_file_name varchar(100),
    regi_date datetime
);

#게시판
#
# boardGroup,boardSequence,level,available\\\\
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


#게시판 매니저
create table board_manager(
    id int auto_increment primary key,
    member_id varchar(50) not null,
    board_uri varchar(50) not null,
    board_role varchar(50) not null,
    regiDate datetime
);

#게시판 즐겨찾기
create table board_bookmark(
    id int auto_increment primary key,
    member_id varchar(50) not null,
    board_uri varchar(50) not null
);

#게시글
create table post(
    id int auto_increment primary key,
    board_uri varchar(50) not null,
    post_title varchar(100) not null,
    post_writer varchar(100) not null,
    post_hit int default 0,
    post_content text,
    post_regiDate datetime
);


#댓글
create table comment(
    id int auto_increment primary key,
    post_id int not null,
    comment_writer varchar(50) not null,
    comment_content text,
    comment_parentNo int,
    comment_sequence int default 0,
    comment_level int default 0,
    comment_available int default 1,
    comment_regiDate datetime
);


insert into post (board_uri, post_title, post_writer, post_content, post_regiDate)
VALUES ('test', '제목1', 'hong', 'gdgd', now());

insert into post (board_uri, post_title, post_writer, post_content, post_regiDate)
VALUES ('test','제목1','hong','gdgd',now());
insert into post (board_uri, post_title, post_writer, post_content, post_regiDate)
VALUES ('test','제목1','hong','gdgd',now());
insert into post (board_uri, post_title, post_writer, post_content, post_regiDate)
VALUES ('test','제목1','hong','gdgd',now());


;
#조회
select * from member;
select * from board_manager;
select * from image_profile;
select * from board;
select * from post;
select count(*) from post where board_uri = 'test';
select * from comment order by comment_parentNo asc,comment_sequence asc,comment_level asc;



