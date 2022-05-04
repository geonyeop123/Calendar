package com.yeop.calendar.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CalendarMaker {
    private int lastDay;
    private int prevDateLastDay;
    private int yoil;
    private LocalDate date;
    private int lastDayYoil;

    private int startDay;

    private List<Integer> dayList;

    CalendarMaker(){}

    public CalendarMaker(Integer year, Integer month){
        this(new DateVO(year, month));
    }
    public CalendarMaker(DateVO vo){
        // Date 값 세팅
        date = LocalDate.of(vo.getYear(), vo.getMonth(), 1);
        // n년 n월 1일의 요일 값 세팅
        yoil = date.getDayOfWeek().getValue();
        // n년 n월의 마지막 일 세팅
        lastDay = date.lengthOfMonth();
        // n년 n월의 전달의 마지막 일 세팅
        prevDateLastDay = date.minusMonths(1).lengthOfMonth();
        // n년 n월의 전달의 마지막 일의 요일 세팅
        lastDayYoil = date.plusDays(date.lengthOfMonth() - 1).getDayOfWeek().getValue();
        // 시작일 세팅
        startDay = setStartDay();
        // JSP로 넘길 List 세팅
        dayList = createList();
    }

    private int setStartDay(){
        // 요일이 일요일인 경우 전 달이 없으므로 1부터 시작, 아닌 경우 시작 일 세팅
        return (yoil == 7) ? 1 : prevDateLastDay - (yoil - 1);
    }

    private List<Integer> createList(){

        List<Integer> list = new ArrayList<>();

        // 만일, 전 달을 표시해주어야 할 경우 수행될 로직
        if(startDay > 1) list.addAll(makeList(startDay, prevDateLastDay));
        // 현재의 달을 표시
        list.addAll(makeList(1, lastDay));
        // 다음 달을 표시해주어야 할 경우 수행될 로직
        list.addAll(makeList(1, lastDayYoil == 7 ? 6 : 6 - lastDayYoil));

        return list;
    }

    // 리스트에 값을 넣을 때 사용될 메서드
    private List<Integer> makeList(int start, int end){
        List<Integer> list = new ArrayList<>();
        for(int i = start; i <= end; i++){
            list.add(i);
        }
        return list;
    }

    public List<Integer> getDayList() {
        return dayList;
    }

    public int getLastDay() {
        return lastDay;
    }


    public int getPrevDateLastDay() {
        return prevDateLastDay;
    }


    public int getYoil() {
        return yoil;
    }



    public int getLastDayYoil() {
        return lastDayYoil;
    }


    @Override
    public String toString() {
        return "CalendarMaker{" +
                "lastDay=" + lastDay +
                ", prevDateLastDay=" + prevDateLastDay +
                ", yoil=" + yoil +
                ", date=" + date +
                ", lastDayYoil=" + lastDayYoil +
                ", startDay=" + startDay +
                ", dayList=" + dayList +
                '}';
    }
}
