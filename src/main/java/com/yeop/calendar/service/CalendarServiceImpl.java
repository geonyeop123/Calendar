package com.yeop.calendar.service;

import com.yeop.calendar.domain.HolidayDTO;
import com.yeop.calendar.mapper.CalendarMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class CalendarServiceImpl implements CalendarService{

    @Autowired
    CalendarMapper mapper;

    @Override
    public HolidayDTO read() throws Exception {
        return mapper.read();
    }
}
