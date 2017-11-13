/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timelogger;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


/**
 *
 * @author rkissvincze
 */
public class Task {
    
    private String taskID;
    private LocalTime startTime;
    private LocalTime endTime;
    private String comment;
    
   
    public Task(String taskId, String comment, LocalTime startTime, LocalTime endtime) throws NotExpectedTimeOrderException, EmptyTimeFieldException, InvalidTaskIdException, NoTaskIdException{
                    
        this.taskID = taskId;
        this.comment = comment;
        this.startTime = startTime;
        this.endTime = endtime; 
        if( Util.isTimeNull(this) ) throw  new EmptyTimeFieldException(" time is null " + this.toString() );
        if( !Util.isCorrectTimeOrder(this) ) throw new NotExpectedTimeOrderException(" The start time must be before the endtime ");
        if( !isValidTaskId() ) throw new InvalidTaskIdException("Invalid task ID is of " + this.toString() + "task. It must be in 1234 or LT-1234 form");
        if( this.getTaskID() == null || this.getTaskID().equals("") ) throw new NoTaskIdException();
        if( !Util.isMultipleQuarterHour(this) ) {
        
            this.endTime =  Util.roundToMultipleQuarterHour(this.getStartTime(), this.getEndTime());
        }
    }
    
    public Task(String taskId, String comment, int startHour, int startMin, int endHour, int endMin) throws NotExpectedTimeOrderException, EmptyTimeFieldException, InvalidTaskIdException, NoTaskIdException{        
    
        this( taskId, comment, LocalTime.of(startHour, startMin), LocalTime.of(endHour, endMin));
    }
    
    public Task(String taskId, String commnet, String startTime, String endTime) throws NotExpectedTimeOrderException, EmptyTimeFieldException, InvalidTaskIdException, NoTaskIdException{
        
        this( taskId, commnet, LocalTime.parse(startTime, DateTimeFormatter.ISO_TIME), LocalTime.parse(endTime, DateTimeFormatter.ISO_TIME)  );
        
    }
    
    public Task(String taskId, String commnet, String startTime) throws NotExpectedTimeOrderException, EmptyTimeFieldException, InvalidTaskIdException, NoTaskIdException{
        
        this( taskId, commnet, LocalTime.parse(startTime, DateTimeFormatter.ISO_TIME), null  );
        
    }
    
    public Task(String taskId, String commnet, LocalTime startTime) throws NotExpectedTimeOrderException, EmptyTimeFieldException, InvalidTaskIdException, NoTaskIdException{
        
        this( taskId, commnet, startTime, null  );
        
    }
    
    public Task(String taskId){
    
        this.taskID = taskId;
    }

    public String getTaskID() {
        return taskID;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getComment() {
        return comment;
    }
    
    public long getMinPerTask(){
        
        if( startTime != null && endTime != null ) return ChronoUnit.MINUTES.between(startTime, endTime);
        return -1;
    }

    public void setTaskID(String taskID) throws InvalidTaskIdException {
        this.taskID = taskID;
        if( !this.isValidTaskId() ) throw new InvalidTaskIdException("Invalid task ID is of " + this.toString() + "task. It must be in 1234 or LT-1234 form");
    }

    public void setStartTime(LocalTime startTime) throws NotExpectedTimeOrderException {
        
        this.startTime = startTime;
        if( !Util.isCorrectTimeOrder(this) ) throw new NotExpectedTimeOrderException("The start time must be before the endtime ");
    }
    
    public void setStartTime(int hour, int minnute) throws NotExpectedTimeOrderException {
                
        this.setStartTime(LocalTime.of(hour, minnute));
    }
    
    public void setStartTime(String startTime) throws NotExpectedTimeOrderException {
        
        this.setStartTime(LocalTime.parse(startTime, DateTimeFormatter.ISO_TIME));
    }
            
    public void setEndTime(LocalTime endTime) throws NotExpectedTimeOrderException {
        this.endTime = endTime;
        if( !Util.isCorrectTimeOrder(this) ) throw new NotExpectedTimeOrderException("The start time must be before the endtime ");
    }
    
    public void setEndTime(int hour, int minnute) throws NotExpectedTimeOrderException {
        
        this.setEndTime(LocalTime.of(hour, minnute));
    }
    
    public void setEndTime(String endTime) throws NotExpectedTimeOrderException {
        
        this.setEndTime(LocalTime.parse(endTime, DateTimeFormatter.ISO_TIME));
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public boolean isValidTaskId(){
    
        return taskID.matches("\\d{4}") || taskID.matches("LT-\\d{4}");
    }

    @Override
    public String toString() {
        return "Task: " + taskID + ", " + comment + ", " + startTime + ", " + endTime ;
    }
    
    
}
