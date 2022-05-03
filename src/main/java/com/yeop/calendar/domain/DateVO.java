package com.yeop.calendar.domain;

import java.util.Calendar;

public class DateVO {
    private Integer year;
    private Integer month;
    private CalendarMaker cm;

    public DateVO(){}

    public DateVO(Integer year, Integer month){
        this.year = year;
        this.month = month;
        cm = new CalendarMaker(this);
    }

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


    public void print(){
        System.out.println(toString());
        System.out.println();
        System.out.println("=============="+month+"월==============");
        System.out.println("월   화   수   목   금   토   일");
        System.out.println("01  02   03  04  05   06  07");
        System.out.println("08  09   10  11  12   13  14");
        System.out.println("15  16   17  18  19   20  21");
        System.out.println("22  23   24  25  26   27  28");
        System.out.println("29  30  31");
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
