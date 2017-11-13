/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timelogger;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rkissvincze
 */
public class WorkMonth {
    private List<WorkDay> days = new ArrayList<>();
    private YearMonth date = YearMonth.now();
    private long sumPerMonth;
    private long requiredMinPerMonth;
    
    public WorkMonth(){System.out.println("WM added..");}
    
    public WorkMonth(int year, int month){
        
        this.date = YearMonth.of(year, month);
    }
    
    public WorkMonth(String year, String month){
        
        this.date = YearMonth.of( Integer.parseInt(year), Integer.parseInt(month) );
       
    }
    
    public WorkMonth(String yearMonth){
        
        String year = yearMonth.substring(0, 4);
        String month = yearMonth.substring(4, 6);
        
        this.date = YearMonth.of( Integer.parseInt(year), Integer.parseInt(month) );
    }

    public List<WorkDay> getDays() {
        return days;
    }

    public YearMonth getDate() {
        return date;
    }

    public long getSumPerMonth() {
        
        if ( sumPerMonth != 0 ) return sumPerMonth;
        sumPerMonth = days.stream().mapToLong(WorkDay::getSumPerDay).sum();
        return sumPerMonth;
    }

    public long getRequiredMinPerMonth() {
        
        if( requiredMinPerMonth != 0 ) return requiredMinPerMonth;
        requiredMinPerMonth = days.stream().mapToLong(WorkDay::getRequiredMinPerDay).sum();
        return requiredMinPerMonth;
    }
    
    public long getExtraMinPerMonth(){
        
        return getSumPerMonth() - getRequiredMinPerMonth();
    }
    
    public boolean isNewDate(WorkDay workDay){
    
        return days.stream().filter(i -> i.getActualDay() == workDay.getActualDay()).count() == 0;
    }
    
    public boolean isSameMonth(WorkDay workDay){
    
        return date.getMonth() == workDay.getActualDay().getMonth();
    }
    
    public void addWorkDay(WorkDay workDay, boolean isWeekendEnabled){
        
        if( isSameMonth(workDay) && isNewDate(workDay) ){
        
            if( isWeekendEnabled || Util.isWeekDay(workDay) ){

                days.add(workDay);
                sumPerMonth = 0;
                requiredMinPerMonth = 0;
                return;
            }
        }
    }
    
    public void addWorkDay(WorkDay workDay){
        addWorkDay(workDay, false);
    }

    @Override
    public String toString() {
        return "WorkMonth: " + date;
    }
    
    
}
