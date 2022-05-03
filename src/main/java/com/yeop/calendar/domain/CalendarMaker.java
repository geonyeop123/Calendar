package com.yeop.calendar.domain;

import java.time.LocalDate;

public class CalendarMaker {
    private int lastDay;
    private int prevDateLastDay;
    private int yoil;
    private LocalDate date;
    private int lastDayYoil;

    CalendarMaker(DateVO vo){
        date = LocalDate.of(vo.getYear(), vo.getMonth(), 1);
        yoil = date.getDayOfWeek().getValue();
        lastDay = date.lengthOfMonth();
        prevDateLastDay = date.minusMonths(1).lengthOfMonth();
        lastDayYoil = date.plusDays(date.lengthOfMonth() - 1).getDayOfWeek().getValue();
    }


    public int getLastDay() {
        return lastDay;
    }

    public void setLastDay(int lastDay) {
        this.lastDay = lastDay;
    }

    public int getPrevDateLastDay() {
        return prevDateLastDay;
    }

    public void setPrevDateLastDay(int prevDateLastDay) {
        this.prevDateLastDay = prevDateLastDay;
    }

    public int getYoil() {
        return yoil;
    }

    public void setYoil(int yoil) {
        this.yoil = yoil;
    }

    public int getLastDayYoil() {
        return lastDayYoil;
    }

    public void setLastDayYoil(int lastDayYoil) {
        this.lastDayYoil = lastDayYoil;
    }

    @Override
    public String toString() {
        return "CalendarMaker{" +
                "lastDay=" + lastDay +
                ", prevDateLastDay=" + prevDateLastDay +
                ", yoil=" + yoil +
                '}';
    }
}
