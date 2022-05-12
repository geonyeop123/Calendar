package com.yeop.calendar.service;

import com.yeop.calendar.domain.HolidayDTO;
import org.springframework.stereotype.Service;

@Service
public interface CalendarService {
    HolidayDTO read() throws Exception;
}
