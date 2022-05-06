package com.yeop.calendar.domain;

import java.util.Map;

public class CalendarVO {
    private Integer year;
    private Integer month;
    private Map<Integer, Integer> dateMap;

    public CalendarVO(){}

    public CalendarVO(Integer year, Integer month){
        this.year = year;
        this.month = month;
    }

    public void setDateMap(Map dateMap) { this.dateMap = dateMap; }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Map<Integer, Integer> getDateMap() {
        return dateMap;
    }


    @Override
    public String toString() {
        return "DateVO{" +
                "year=" + year +
                ", month=" + month +
                ", dateMap=" + dateMap +
                '}';
    }
}
