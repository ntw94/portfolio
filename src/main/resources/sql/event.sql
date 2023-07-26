show variables like 'event%';
show events;
drop event event_test;

# 매 1분마다 insert구문을 실행시키기
create EVENT IF NOT EXISTS event_test
 ON SCHEDULE
     EVERY 1 minute
 ON COMPLETION not PRESERVE
 ENABLE
 COMMENT '설명 적는ㄱ칸'
 DO
  delete from testTable
    where DATEDIFF(stopDate,now()) < 0;


create table testTable(
    id int auto_increment primary key ,
    stopDate datetime,
    regiDate datetime
);
insert into testTable(stopDate, regiDate) values
    ('2026-07-26 15:50:00',now());
insert into testTable(stopDate, regiDate) values
    (DATE_SUB(now(),INTERVAL 5 DAY),now());

insert into testTable(stopDate, regiDate) values
    ('2020-01-01',now());

select * from testTable;

select * from test;