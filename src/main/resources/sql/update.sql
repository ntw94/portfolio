ALTER TABLE portfolio.post ADD available boolean default true; # available 컬럼 추가
ALTER TABLE portfolio.post ADD no_tag_content text; #태그 제거한 순수 내용
ALTER TABLE portfolio.board ADD score bigint default 0;
ALTER TABLE portfolio.board ADD main_category_id int;
ALTER TABLE portfolio.board ADD sub_category_id int;

create table board_main_category(
    id int auto_increment primary key,
    category_name varchar(50) not null unique
);


create table board_sub_category(
    id int auto_increment primary key,
    board_main_category_id int not null,
    category_name varchar(50) not null unique
);



insert into board_main_category values
                                (null,'게임'),
                                (null,'만화/애니메이션'),
                                (null,'방송/연예'),
                                (null,'문화/예술'),
                                (null,'영화');

insert into board_sub_category values
                                (null,1,'롤플레잉게임'),
                                (null,1,'FPS/슈팅게임'),
                                (null,1,'액션/어드벤쳐게임'),
                                (null,1,'레이싱게임'),
                                (null,1,'모바일게임'),
                                (null,1,'게임일반');

insert into board_sub_category values
                               (null,2,'순정/드라마'),
                               (null,2,'코믹'),
                               (null,2,'액션/무협'),
                               (null,2,'공포/추리'),
                               (null,2,'판타지/SF'),
                               (null,2,'스포츠');

insert into board_sub_category values
                               (null,3,'TV방송'),
                               (null,3,'라디오방송'),
                               (null,3,'인터넷방송'),
                               (null,3,'신문/잡지'),
                               (null,3,'연예정보'),
                               (null,3,'오디션'),
                               (null,3,'방송/연예일반');

insert into board_sub_category values
                               (null,4,'연극'),
                               (null,4,'뮤지컬/오페라'),
                               (null,4,'무용/댄스'),
                               (null,4,'회화'),
                               (null,4,'공예/장식미술'),
                               (null,4,'조소/도예');

insert into board_sub_category values
                               (null,5,'드라마/로맨스영화'),
                               (null,5,'코미디영화'),
                               (null,5,'액션/무협영화'),
                               (null,5,'공포/스릴러영화'),
                               (null,5,'SF/판타지'),
                               (null,5,'다큐멘터리영화');




