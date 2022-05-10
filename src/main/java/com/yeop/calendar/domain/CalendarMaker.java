package com.yeop.calendar.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarMaker {
    private int yoil;

    private LocalDate date;

    private int lastDayYoil;

    private LocalDate startDate;

    private LocalDate lastDate;

    private List<LocalDate> dateList;

    private List<Holiday> holidayList;

    private int year;
    private int month;

    public CalendarMaker(){}

    public CalendarMaker(Integer year, Integer month){
        this(new CalendarVO(year, month));
    }
    public CalendarMaker(CalendarVO vo){
        year = vo.getYear();

        month = vo.getMonth();
        // Date 값 세팅
        date = LocalDate.of(year, month, 1);
        // n년 n월 1일의 요일 값 세팅
        yoil = date.getDayOfWeek().getValue();
        // n년 n월의 전달의 마지막 일의 요일 세팅
        lastDayYoil = date.plusDays(date.lengthOfMonth() - 1).getDayOfWeek().getValue();
        // 시작일
        startDate = (yoil == 7) ? date : date.minusDays(yoil);
        // 종료일
        lastDate = lastDayYoil == 6 ? date.plusDays(date.lengthOfMonth() - 1)
                : date.plusDays(date.lengthOfMonth() - 1 + (lastDayYoil == 7 ? 6 : 6 - lastDayYoil));

    }

    public List<LocalDate> createDateList(){
        dateList = new ArrayList<LocalDate>();
        int cnt = 0;
        while(true){
            LocalDate currentDate = startDate.plusDays(cnt);
            dateList.add(currentDate);
            if(currentDate.equals(lastDate)) return dateList;
            cnt++;
        }
    }

    public List<Holiday> createHolidayList() throws Exception{
        return HolidayAPI.getHoliday(year, month);
    }

    @Override
    public String toString() {
        return "CalendarMaker{" +
                "yoil=" + yoil +
                ", date=" + date +
                ", lastDayYoil=" + lastDayYoil +
                ", startDate=" + startDate +
                ", lastDate=" + lastDate +
                ", dateMap=" + dateList +
                '}';
    }
}
