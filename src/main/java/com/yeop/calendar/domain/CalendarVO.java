package com.yeop.calendar.domain;

public class CalendarVO {
    private Integer year;
    private Integer month;
    private CalendarMaker cm;

    public CalendarVO(){}

    public CalendarVO(Integer year, Integer month){
        this.year = year;
        this.month = month;
    }

    public void setCm(CalendarMaker cm) { this.cm = cm; }

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

    public CalendarMaker getCm() {
        return cm;
    }


    @Override
    public String toString() {
        return "DateVO{" +
                "year=" + year +
                ", month=" + month +
                ", cm=" + cm +
                '}';
    }
}
