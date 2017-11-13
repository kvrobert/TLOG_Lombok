/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timelogger;

/**
 *
 * @author rkissvincze
 */
public class Test_TMP {
    

    public static void main(String[] args) {
        
    /*    Task task1 = new Task("Helloka task1", "testelek1", 15, 30, 16, 15);
        Task task2 = new Task("Helloka task2", "testelek2", 16, 45, 17, 15);
        Task task3 = new Task("Helloka task3", "testelek3", "18:15", "18:30");
        Task task4 = new Task("Helloka task4", "testelek3", "20:15", "20:30");
        Task task5 = new Task("Helloka task5", "testelek3", "21:15", "21:30");
        Task task6 = new Task("Helloka task6", "testelek3", "21:45");
        
        WorkMonth wm = new WorkMonth();
        WorkDay wd1 = new WorkDay();
        WorkDay wd2 = new WorkDay(450, 1985, 11, 20);
    //    System.out.println(task1.getMinPerTask());
    //    System.out.println(task1.toString());
    //   System.out.println(wd1.getActualDay());
    //   System.out.println(wd2.getActualDay());
        
        
        
    /*    wd1.addTask(task1);
        wd1.addTask(task2);
        wd1.addTask(task3);
        
        WorkMonth wm1 = new WorkMonth();
        WorkMonth wm2 = new WorkMonth("201706");
        WorkMonth wm3 = new WorkMonth(1981, 6);
        
        wm1.addWorkDay(wd1);
        wm1.addWorkDay(wd2);        */
        
        TimeLogger tlogger = new TimeLogger();
    //    tlogger.addMonth(wm1);
    //    tlogger.addMonth(wm2);
    //   tlogger.addMonth(wm3);
                
        
    /*    System.out.println(wm1.getDate());
        System.out.println(wm2.getDate());
        System.out.println(wm3.getDate());
        
        System.out.println(wd1.getTasks().size());
        System.out.println(wd1.getLastTaskEndTime()); */
    
     /*   TimeLoggerUI ui = new TimeLoggerUI(tlogger);
        tlogger.addMonth(wm);
        wm.addWorkDay(wd1);
        wm.addWorkDay(wd2);
        wd1.addTask(task1);
        wd1.addTask(task2);
        wd1.addTask(task3);
        wd1.addTask(task4);
        wd1.addTask(task5);
        wd1.addTask(task6);
        
        ui.printMenu();
        ui.selectMenu();*/
     
     TimeLoggerUI tui = new TimeLoggerUI(tlogger);
        
        
    }

}
