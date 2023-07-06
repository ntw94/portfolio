package project.forums.date;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class LocalDateTimeTest {
    @Test
    void localDateTimeTest1(){

        LocalDateTime first = LocalDateTime.now();
        LocalDateTime second = LocalDateTime.of(2023,7,7,1,25,30);

        if(ChronoUnit.DAYS.between(second,first) == 0 ){
            DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("HH:mm");
            System.out.println(second.format(newPattern));
        }else {
            DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("yyyy.MM.dd");
            System.out.println(second.format(newPattern));
        }
        System.out.println("값:"+ChronoUnit.HOURS.between(first,second));
        System.out.println("값:"+ChronoUnit.SECONDS.between(first,second));
    }
}
