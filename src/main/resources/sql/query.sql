
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
    comment_regiDate datetime
);

#조회
select * from member;
select * from image_profile;
select * from board;
select * from post;
select * from comment;

insert into board (board_title, board_uri,
                    board_description, upload_file_name,
                    store_file_name, member_id, board_create_date,board_update_date)
VALUES ('테스트','test','테스트로 만들어보았읍니다.',null,null,'kim',now(),now());

insert into comment(post_id, comment_writer, comment_content, comment_regiDate)
values (1,'hong','잘봣습니다.',now());

#수정시 밑의 코드 돌려야됨
drop table member;
drop table image_profile;
drop table board;
