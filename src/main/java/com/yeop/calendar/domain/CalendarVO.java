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


    public void print(){
        System.out.println(toString());
        System.out.println("\t\t\t"+month+"월\t\t\t");
        System.out.println("일\t월\t화\t수\t목\t금\t토\t");
        int index = 0;
        for(int i : getCm().getDayList()){
            if(index >= 7){
                System.out.println();
                index = 0;
            }
            System.out.print(i);
            System.out.print("\t");
            index++;
        }
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
