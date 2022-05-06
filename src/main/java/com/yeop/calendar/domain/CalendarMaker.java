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

    private List<Map<String, Integer>> dateList;
    public CalendarMaker(){}

    public CalendarMaker(Integer year, Integer month){
        this(new CalendarVO(year, month));
    }
    public CalendarMaker(CalendarVO vo){
        // Date 값 세팅
        date = LocalDate.of(vo.getYear(), vo.getMonth(), 1);
        // n년 n월 1일의 요일 값 세팅
        yoil = date.getDayOfWeek().getValue();
        // n년 n월의 전달의 마지막 일의 요일 세팅
        lastDayYoil = date.plusDays(date.lengthOfMonth() - 1).getDayOfWeek().getValue();
        // 시작일
        startDate = (yoil == 7) ? date : date.minusDays(yoil - 2);
        // 종료일
        lastDate = lastDayYoil == 6 ? date.plusDays(date.lengthOfMonth() - 1)
                : date.plusDays(date.lengthOfMonth() - 1 + (lastDayYoil == 7 ? 6 : 6 - lastDayYoil));

    }

    public List<Map<String, Integer>> createDateList(){
        dateList = new ArrayList<Map<String, Integer>>();
        Map map = new HashMap<String, Integer>();
        int cnt = 0;
        while(true){
            LocalDate currentDate = startDate.plusDays(cnt);

            int month = currentDate.getMonthValue();
            int day = currentDate.getDayOfMonth();

            map.put("month", month);
            map.put("day", day);
            System.out.println("map = " + map);
            dateList.add(map);
            System.out.println("dateList = " + dateList);
            System.out.println("currentDate = " + currentDate);
            System.out.println("lastDate = " + lastDate);
            if(currentDate.equals(lastDate)) return dateList;
            cnt++;
        }
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
