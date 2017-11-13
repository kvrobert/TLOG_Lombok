/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timelogger;

import com.exceptions.NotTheSameMonthException;
import com.exceptions.NotNewDateException;
import com.exceptions.WeekendNotEnabledException;
import com.exceptions.EmptyTimeFieldException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author rkissvincze
 */
@NoArgsConstructor
public class WorkMonth {
    @Getter @Setter private List<WorkDay> days = new ArrayList<>();
    @Getter @Setter private YearMonth date = YearMonth.now();
    private long sumPerMonth;
    private long requiredMinPerMonth;
    
    public long getSumPerMonth() throws EmptyTimeFieldException {
        
        long summ = 0;
        if ( sumPerMonth != 0 ) return sumPerMonth;
    
//    sumPerMonth = days.stream().mapToLong(WorkDay::getSumPerDay).sum();       Exceptiont nem tom feloldani....
        
        for( WorkDay workDay : days ){
            summ += workDay.getSumPerDay();
        }
        sumPerMonth = summ;
        return sumPerMonth;
    }

    public long getRequiredMinPerMonth() {
        
        if( requiredMinPerMonth != 0 ) return requiredMinPerMonth;
        requiredMinPerMonth = days.stream().mapToLong(WorkDay::getRequiredMinPerDay).sum();
        return requiredMinPerMonth;
    }
    
    public long getExtraMinPerMonth() throws EmptyTimeFieldException{
        
        return getSumPerMonth() - getRequiredMinPerMonth();
    }
    
    public boolean isNewDate(WorkDay workDay){
            
        return days.stream().filter(i -> i.getActualDay().equals( workDay.getActualDay() )).count() == 0;
    }
    
    public boolean isSameMonth(WorkDay workDay){
    
        return date.getMonth() == workDay.getActualDay().getMonth();
    }
    
    public void addWorkDay(WorkDay workDay, boolean isWeekendEnabled) throws WeekendNotEnabledException, NotNewDateException, NotTheSameMonthException{
        
        if( !isNewDate(workDay) ) throw new NotNewDateException(" This day (" + workDay.getActualDay() +") already exist. Give an another. ");
        if( isSameMonth(workDay) ){
        
            if( isWeekendEnabled || Util.isWeekDay(workDay) ){
                
                days.add(workDay);
                sumPerMonth = 0;
                requiredMinPerMonth = 0;
                return;                
            }else { throw new WeekendNotEnabledException(" You should enable weekend work to add this day: " + workDay.toString()); }
            
        }else{ throw new NotTheSameMonthException(); }
    }
    
    public void addWorkDay(WorkDay workDay) throws WeekendNotEnabledException, NotNewDateException, NotTheSameMonthException{
        addWorkDay(workDay, false);
    }

    @Override
    public String toString() {
        return "WorkMonth: " + date;
    }
    
    
}
