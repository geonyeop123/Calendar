package com.yeop.calendar;

import com.yeop.calendar.service.CalendarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DbTest {

    @Autowired
    CalendarService service;

    @Test
    public void test()throws Exception{
        System.out.println(service.read());
    }
}
