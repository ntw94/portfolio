create table member (
    id int auto_increment primary key ,
    member_id varchar(50) unique not null,
    member_pwd varchar(100) not null,
    member_name varchar(50) not null,
    member_phone varchar(20) not null,
    member_regiDate datetime
);

drop table member;
drop table image_profile;

create table image_profile(
    id int auto_increment primary key ,
    member_id varchar(50) not null,
    upload_file_name varchar(100),
    store_file_name varchar(100),
    regi_date datetime
);
select * from member;
#
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

insert into board (board_title, board_uri,
                    board_description, upload_file_name,
                    store_file_name, member_id, board_create_date,board_update_date)
VALUES ('테스트','test','테스트로 만들어보았읍니다.',null,null,'kim',now(),now());

truncate table board;
select * from board;
