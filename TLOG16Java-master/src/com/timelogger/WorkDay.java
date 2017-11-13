package com.timelogger;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import sun.util.calendar.BaseCalendar;

/**
 *
 * @author rkissvincze
 */
public class WorkDay {
    private List<Task> tasks = new ArrayList<>();
    private long requiredMinPerDay = (long) (7.5 * 60);
    private LocalDate actualDay = LocalDate.now();
    private long sumPerDay;
    
    public WorkDay(){}
    
    public WorkDay(long requiredMinperDay, LocalDate actualDay){
    
        this.requiredMinPerDay = requiredMinperDay;
        this.actualDay = actualDay;
    }
    
    public WorkDay(long requiredMinperDay, int year, int month, int day){
    
        this.requiredMinPerDay = requiredMinperDay;
        this.actualDay = LocalDate.of(year, month, day);
    }
    
    public WorkDay(String requiredMinperDay, String actualDay){
        
        int year = Integer.parseInt(actualDay.substring(0, 4));
        int month = Integer.parseInt(actualDay.substring(4, 6));
        int day = Integer.parseInt(actualDay.substring(6, 8));
        
        this.requiredMinPerDay = Long.parseLong(requiredMinperDay);
        this.actualDay = LocalDate.of(year, month, day);
    }

    public long getRequiredMinPerDay() {
        return requiredMinPerDay;
    }

    public long getSumPerDay() {
        
        if( sumPerDay != 0 ) return sumPerDay;
        return tasks.stream().mapToLong(Task::getMinPerTask).sum();
    }

    public LocalDate getActualDay() {
        return actualDay;
    }    

    public List<Task> getTasks() {
        return tasks;
    }
       
    public long getExtraMinPerDay(){
    
        return getSumPerDay() - getRequiredMinPerDay();
    }
    
    public LocalTime getLastTaskEndTime(){
        
        /* Task tsk = tasks.stream().max((p1, p2) -> p1.getStartTime().compareTo(p2.getStartTime())).get();
         return tsk; 
        
        tasks.stream().reduce( (p1, p2) -> p1.getStartTime().isAfter(p2.getStartTime())  ? p2:p1  ).ifPresent(System.out::println);
        Collections.max(tasks, comp) */
        Task lastTask;
        if( tasks.size() > 0 ) {
            lastTask = tasks.get(0);        
            for(Task tsk : tasks){
            if( tsk.getStartTime().isAfter(lastTask.getStartTime()) ) lastTask = tsk;
            }
            return lastTask.getEndTime();
        }    
        return null;    // not elegant..yet..
    }    

    public void setRequiredMinPerDay(long requiredMinPerDay) {
        this.requiredMinPerDay = requiredMinPerDay;
    }

    public void setActualDay(int year, int month, int day) {
        this.actualDay = LocalDate.of(year, month, day);
    }          
    
    public void addTask(Task task){
    
        if( Util.isMultipleQuarterHour(task) && !Util.isSeparatedTime(task, this) || tasks.isEmpty()){
            System.out.println("TASK ADDED");
            tasks.add(task);
            sumPerDay = 0;
            return;
        }
        System.out.println("TASK NOOOOOT ADDED");
        return;            
    }

    @Override
    public String toString() {
        return "WorkDay: " + actualDay + ", requ: " + requiredMinPerDay + ", sum: " + sumPerDay;
    }
    
    
}
