package com.yeop.calendar.mapper;

import com.yeop.calendar.domain.HolidayDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CalendarMapper {
    HolidayDTO read() throws Exception;
}
