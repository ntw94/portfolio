show variables like 'event%'; /* 이벤트 스케줄러 상태 확인 */
set global  event_scheduler = on;
# set global  event_scheduler = off;

select * from information_schema.EVENTS;

show events;
drop event event_cal_board_score; /* 이벤트 삭제 */
/* ON COMPLETION [NOT] PRESERVE 이벤트 완료 후 삭제하지 않겠다는 의미. 이벤트 완료후 삭제하고 싶으면 not을 빼면된다.*/

create EVENT IF NOT EXISTS event_cal_board_score
    ON SCHEDULE
        EVERY 1 minute
    ON COMPLETION not PRESERVE
    ENABLE
    COMMENT '게시판 활동별 점수 계산'
    DO
    update board b, (
        select b.board_uri,
               ifnull(sum(`게시글`) + sum(`댓글 수`) + sum(`조회수`),0) score
        from (
                 select p.board_uri,ifnull(count(*),0) `게시글`,
                        ifnull( (select count(c.id)
                                 from comment c
                                 where c.comment_available = true
                                   and c.board_uri = p.board_uri),0) `댓글 수`,ifnull(sum(p.post_hit),0) `조회수`
                 from board b left join post p on (b.board_uri = p.board_uri )
                 where p.available = true
                 group by p.board_uri) result right join board b on (result.board_uri = b.board_uri)
        group by b.board_uri
    ) a1
    set b.score = a1.score
    where b.board_uri = a1.board_uri;

