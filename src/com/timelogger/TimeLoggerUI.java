package com.timelogger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 *
 * @author rkissvincze
 */
public class TimeLoggerUI {    
    
    private Scanner scannerInt = new Scanner(System.in);    
    private Scanner scannerTxt = new Scanner(System.in);    
    private TimeLogger timeLogger;
    private WorkDay workDay;
    private WorkMonth workMonth;
    private Task task;
    private boolean isEmpty = true;
    private final List<String> MENU = Arrays.asList("Exit",
                                                    "List the months",
                                                    "List the days",
                                                    "List the tasks",
                                                    "Add a new month",
                                                    "Add a new day",
                                                    "Start a task",
                                                    "Finish a task",
                                                    "Delete a task",
                                                    "Modify a task",
                                                    "Statistics");
    
    public TimeLoggerUI(TimeLogger timeLogger){
        this.timeLogger = timeLogger;
        printMenu();
    }
    
    public void printMenu(){
        
        while(true){
            System.out.println("\n::: MENU :::");
            System.out.println  ("============\n");
            IntStream.range(0, MENU.size()).forEach(index -> 
                    System.out.println(index + ". " + MENU.get(index)));
            System.out.println("\nChoose a menu, pleas!");
            selectMenu();
        }
    }
    public void selectMenu(){           
        switch(scannerInt.nextInt())
        {
            case 0: exit(); break;
            case 1: listMonths(); break;
            case 2: listDays(); break;
            case 3: listTasks(); break;
            case 4: addNewMonth(); break;
            case 5: addNewDay(); break;
            case 6: addNewTask(); break;
            case 7: finishATask(); break;
            case 8: deleteATask(); break;
            case 9: modifyATask(); break;
            case 10: statistics(); break;
            default: System.out.println("Wrong menu! "
                    + "Please choose a correct menu!"); break;
        }        
    }

    private void exit() {        
        System.exit(0);
    }

    private boolean listMonths() {    
        if( !timeLogger.getMonths().isEmpty() ){            
            IntStream.range(0, timeLogger.getMonths().size())
                    .forEach(index -> System.out.println(index+1 + ". "
                            + timeLogger.getMonths().get(index).toString()));
            return !isEmpty;
        }else{System.out.println("Workmonth is EMPTY!"); return isEmpty;}
    }

    private boolean listDays() {    
        if( listMonths() ) { System.out.println("WorkDAY is EMPTY!"); return isEmpty; }       
        selectMonth();
        if( workMonth.getDays().size() > 0){        
            IntStream.range(0, workMonth.getDays().size())
                    .forEach(index -> System.out.println( index+1 + ". " 
                            + workMonth.getDays().get(index) ));
            return !isEmpty;
        }
        return isEmpty;
    }

    private void selectMonth() {        
        System.out.println("Month selecter");
        System.out.println("=============="); 
        System.out.println("Chosse a month!");
        workMonth = timeLogger.getMonths().get(scannerInt.nextInt() -1);
    }
    
    private void selectDay() {        
        System.out.println("Day selecter");
        System.out.println("============");
        System.out.println("Chosse a day!");
        workDay = workMonth.getDays().get(scannerInt.nextInt() - 1);
    }
    
    private void selectTask() { 
        System.out.println("Task selecter");
        System.out.println("=============");
        System.out.println("Chosse a task!");
        task = workDay.getTasks().get(scannerInt.nextInt() -1);
    }
      
    private void listTasks() {
        listDays();
        selectDay();
        if( workDay.getTasks().size() > 0 ){
            IntStream.range(0, workDay.getTasks().size())
                    .forEach(index -> System.out.println( index+1 + ". " 
                            + workDay.getTasks().get(index) )); 
            return;       
        }
    }

    private void addNewMonth() {        
        try{
            System.out.println("Type the date or "
                    + "press enter to use this month (YYYYMM).");
            String input = scannerTxt.nextLine();
            if( input.equals("") ) timeLogger.addMonth(new WorkMonth());
            if( input.matches( "[1-2][0|9][0-9][0-9][01][0-2]" ) ) {
                if( timeLogger != null ) {
                    timeLogger.addMonth( WorkMonth.fromString(input));
                    return;
                }
            }
        }catch(Exception ex) { System.out.println(ex.getMessage()); }
    }

    private void addNewDay(boolean weekendEnabled) {
        boolean isWeekendEnabled = weekendEnabled;
        LocalDate actualDay = null;
        long requiredMin;
        listMonths();
        selectMonth();
        
        System.out.println("Type the required minute for the day or "
                + "press enter for default 7.5 Hour .");
        String intInput = scannerTxt.nextLine();
        
        requiredMin = intInput.equals("")? 450 : 
                Math.abs(Long.parseLong(intInput) * 60);               
        
        System.out.println("Type the day or press enter (DD).");
        String input = scannerTxt.nextLine();
        int dayInput = input.equals("") ? 0 : Integer.parseInt(input);
        if( dayInput >= 0 && dayInput <= 31 ) {
            
            actualDay = dayInput == 0 ? 
                    LocalDate.now() : LocalDate.of(workMonth.getDate().getYear(), 
                            workMonth.getDate().getMonthValue(), dayInput);
        }
        if( workMonth != null ) {
            try{
                workMonth.addWorkDay( WorkDay.fromNumberAndLocalDate(
                                    requiredMin, actualDay), isWeekendEnabled);
            }catch(Exception ex) { System.out.println(ex.getMessage()); }
        }
    }
    
    public void addNewDay(){    
        addNewDay(false);
    }
    
    private void addNewTask() {              
        String taskId = "";
        String taskComment;
        LocalTime startTime;
        LocalTime endTime;
        String input;
        
        listDays();
        selectDay();
        
        taskId = giveTaskID(); 
        
        System.out.println("Type the task comment, what you do, or"
                + " press enter for a black field");
        taskComment = scannerTxt.nextLine();
        
        System.out.println("Type the start time of task or "
                + "press enter for the actual time (HH:MM)");
        startTime = giveTime(false);
        System.out.println("Type the end time of task or "
                + "( press enter to leave blank (HH:MM) )");
        endTime = giveTime(true);
        
        System.out.println("Workday........." + workDay);
        
        if( workDay != null ){
            try{
                if( endTime  != null) workDay.addTask(new Task(
                                        taskId, taskComment, startTime, endTime));
                workDay.addTask( Task.fromStringAndLocalDate(
                                    taskId, taskComment, startTime));
                System.out.println("Task's adding....Done.");
                return;
            }catch(Exception ex) { System.err.println(ex.getMessage()); }
        }
    }

    private String giveTaskID() {
        String input = "";
        do{
            System.out.println("Type a valid task ID in format 1234 or LT-1234");
            input = scannerTxt.nextLine();
            if( input.matches("\\d{4}||LT-\\d{4}") )  return input;
            else {                
               System.out.println("Wrong task ID. Give a valid ID.");                
            }
        }while(true);
    }

    private LocalTime giveTime(boolean withNull) {
        LocalTime time;
        String startT = scannerTxt.nextLine();
        if( startT.matches("(\\s)||[0-2][0-9]:[0-5][0-9]") ){
            if(withNull){
                time = startT.equals("") ? null : LocalTime.parse(startT);
                return time;
            }else{
                time = startT.equals("") ? LocalTime.now() : LocalTime.parse(startT);
                return time;
            }
        }else{ System.out.println("Wrong time format! Give it in HH:MM format!"); }        
        return null;
    }

    private void finishATask() {        
        List<Task> unfinished;
        int index = 0;
        int input;
        listDays();
        selectDay();
        
        System.out.println("The unfinished task...");
        System.out.println("======================");
        try{
            if (workDay.getTasks().stream().filter(
                    i -> i.getEndTime() == null).count() > 0) {            
                workDay.getTasks().stream().filter(i -> i.getEndTime() == null)
                    .forEach( i -> System.out.println( 
                        workDay.getTasks().indexOf(i) + 1 + ". " + i.toString()) );

                selectTask();

                System.out.println("Enter the new finish time for the task.");
                task.setEndTime(giveTime(false));
                System.out.println("Task was modified\n");
                System.out.println("The new details... " + task.toString());            
            }else{        
                System.out.println("You don't have unfinished task.");
            }
        }catch(Exception ex) { System.err.println(ex.getMessage()); }
    }

    private void deleteATask() {        
        System.out.println("Task deletig....");
        System.out.println("================");
        listTasks();
        selectTask();
        workDay.getTasks().remove(task);
    }

    private void modifyATask() {        
        String input;
        String taskComm;
        LocalTime taskStartTime;
        LocalTime taskEndTime;
        
        System.out.println("Task data editing....");
        System.out.println("=====================");
        listTasks();
        selectTask();
        try{
            System.out.println("Type a new ID or press enter to keep it.");
            input = scannerTxt.nextLine();
            if( !input.equals("")) { 
                //taskID = giveTaskID(input); 
                task.setTaskID(giveTaskID());
            }        

            System.out.println("Type a new comment or press enter to keep it.");
            input = scannerTxt.nextLine();
            if( !input.equals("")) { 
                //taskComm = giveTaskID(taskID);
                task.setComment(input);
            }

            System.out.println("Type a new start time for the task  or "
                    + "press enter to keep it.");
            taskStartTime = giveTime(true);
            if( taskStartTime != null ) { 
                task.setStartTime(taskStartTime);
            }

            System.out.println("Type a new end time for the task  or "
                    + "press enter to keep it.");
            taskEndTime = giveTime(true);
            if( taskEndTime != null ) { 
                task.setEndTime(taskEndTime);
            }
        }catch(Exception ex) { System.err.println(ex.getMessage()); }
    }

    private void statistics() {        
        System.out.println("Statistics");
        System.out.println("==========");        
        listMonths();
        selectMonth();
        try{ 
            System.out.println("Statistic of the chosen month:\n");
            System.out.println(workMonth.getDate().toString() + ':');
            System.out.println("Extra minutes in the month: " 
                + workMonth.getExtraMinPerMonth() +
                    "\nThe full time in the month: " + workMonth.getSumPerMonth());
            
                                                                        /*   workMonth.getDays().stream().forEach( i -> 
                                                                                    System.out.println( i.getActualDay() + 
                                                                                        ", required day minute: " + i.getRequiredMinPerDay() +
                                                                                        ", full work time: "  + i.getSumPerDay() +
                                                                                        ", overtime: " + i.getExtraMinPerDay()));*/            
        
           for( WorkDay wd : workMonth.getDays() ){
               System.out.println(
                       ", required day minute: " + wd.getRequiredMinPerDay() +
                        ", full work time: "  + wd.getSumPerDay() +
                        ", overtime: " + wd.getExtraMinPerDay());
           }  
        }catch(Exception ex) { 
                System.err.println(ex.getMessage()); 
            }
    }
}
