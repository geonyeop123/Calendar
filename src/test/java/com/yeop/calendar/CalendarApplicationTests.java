package com.yeop.calendar;

import com.yeop.calendar.domain.CalendarMaker;
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
        DateVO vo = new DateVO(2023, 1);
        CalendarMaker cm = new CalendarMaker(vo);

        System.out.println(toString());
        System.out.println("\t\t\t"+vo.getMonth()+"월\t\t\t");
        System.out.println("일\t월\t화\t수\t목\t금\t토\t");
        int index = 0;
        for(int i : cm.getDayList()){
            if(index >= 7){
                System.out.println();
                index = 0;
            }
            System.out.print(i);
            System.out.print("\t");
            index++;
        }
    }


}
