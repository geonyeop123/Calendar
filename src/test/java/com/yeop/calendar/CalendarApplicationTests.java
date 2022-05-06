package com.yeop.calendar;

import com.yeop.calendar.domain.CalendarMaker;
import com.yeop.calendar.domain.CalendarVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@SpringBootTest
class CalendarApplicationTests {

    @Test
    void contextLoads() {
        CalendarVO vo = new CalendarVO(2022, 5);
        CalendarMaker cm = new CalendarMaker(vo);
        List<Map<String, Integer>> list = cm.createDateList();
        System.out.println(list);
    }


}
