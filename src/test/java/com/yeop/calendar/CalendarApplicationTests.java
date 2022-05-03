package com.yeop.calendar;

import com.yeop.calendar.domain.DateVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Calendar;

@SpringBootTest
class CalendarApplicationTests {

    @Test
    void contextLoads() {
        String yoil = "일월화수목금토";
        DateVO vo = new DateVO(2022, 4);
        System.out.println(vo);
        System.out.println("\t\t\t"+vo.getMonth()+"월\t\t\t");
        int j = (vo.getCm().getYoil() - 2);
        int day = vo.getCm().getPrevDateLastDay() - j;
        int nextDayCount =  7 - vo.getCm().getLastDayYoil();
        boolean flag = false;
        Loop : while(true){
            for(int i = 0; i < 7; i++){
                if(i != 0) System.out.print("\t");
                System.out.print(day);
                if(day == vo.getCm().getPrevDateLastDay()){
                    day = 1;
                    flag = true;
                    continue;
                }
                if(day == vo.getCm().getLastDay() && flag) break Loop;
                day++;
            }
            System.out.println();

        }
        day = 1;
        for(int x = 0; x < nextDayCount; x++){
            System.out.print("\t");
            System.out.print(day);
            day++;
        }
    }

}
