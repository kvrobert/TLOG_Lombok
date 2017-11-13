/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timelogger;

import com.exceptions.NotExpectedTimeOrderException;
import com.exceptions.InvalidTaskIdException;
import com.exceptions.FutureWorkException;
import com.exceptions.NegativeMinutesOfWorkException;
import com.exceptions.NotSeparatedTimesException;
import com.exceptions.NotTheSameMonthException;
import com.exceptions.NotNewDateException;
import com.exceptions.NoTaskIdException;
import com.exceptions.WeekendNotEnabledException;
import com.exceptions.EmptyTimeFieldException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Robesz
 */
public class Tmp {

    public static void main(String[] args) throws NotExpectedTimeOrderException, EmptyTimeFieldException, InvalidTaskIdException, NoTaskIdException, NotSeparatedTimesException, NegativeMinutesOfWorkException, FutureWorkException, WeekendNotEnabledException, NotNewDateException, NotTheSameMonthException {
        
        String str = "-300";
        System.out.println(Long.parseLong(str));
        LocalDate dat = LocalDate.parse("20151015", DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(dat);
        
        WorkDay wd = WorkDay.fromNumbers(400, 2017, 11, 8);
        Task t1 =  Task.fromString("4545", "Valami task" , "07:30", "08:45");
        Task t2 =  Task.fromString("4545", "Valami task" , "08:45", "09:45");
        System.out.println(Util.isSeparatedTime(t1, wd));
        System.out.println(Util.isSeparatedTime(t2, wd));
        wd.addTask(t1);
        wd.addTask(t2);
        
        WorkMonth wm = new WorkMonth();
        wm.addWorkDay(wd);
                
        
     /*   Task tsk1 = new Task("4556", "Helloka", "07:30", "08:45");
        WorkDay wd1 = new  WorkDay(420, 2017, 11, 5);
        wd1.addTask(tsk1);        
        WorkDay wd2 = new WorkDay(420, 2017, 11, 2);
        Task tsk2 = new Task("5656", "valami", "08:45", "09:45");
        wd2.addTask(tsk2);
        WorkMonth wm = new WorkMonth();
        wm.addWorkDay(wd1);
        wm.addWorkDay(wd2);
        System.out.println(wm.getDays().size());*/
    
    TimeLogger tlogger = new TimeLogger();
    tlogger.addMonth(wm);
    TimeLoggerUI ui = new TimeLoggerUI(tlogger);
        
        
        
    }
    
}
