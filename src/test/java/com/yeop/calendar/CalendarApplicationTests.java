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
        LocalDate date = LocalDate.of(2022, 1, 1);
        System.out.println(date.plusMonths(1));
    }
}
