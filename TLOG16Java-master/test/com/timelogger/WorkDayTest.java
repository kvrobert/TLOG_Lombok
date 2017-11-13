/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timelogger;

import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author rkissvincze
 */
public class WorkDayTest {
    
 

    /**
     * Test of getSumPerDay method, of class WorkDay.
     */
    @Test
    public void testGetExtraMinutesPerDay() throws NotExpectedTimeOrderException, EmptyTimeFieldException, InvalidTaskIdException, InvalidTaskIdException, NoTaskIdException {
        
        Task t1 = new Task("4545", "Valami task" , "07:30", "08:45");
        WorkDay wd1 = new WorkDay();
        wd1.addTask(t1);
        long exRes = -375;
        long result = wd1.getExtraMinPerDay();
        assertEquals(exRes, result);
    }
    
    @Test
    public void testGetExtraMinutesPerDay2() throws NotExpectedTimeOrderException, EmptyTimeFieldException, InvalidTaskIdException, InvalidTaskIdException, NoTaskIdException {
        
        Task t1 = new Task("4545", "Valami task" , "07:30", "08:45");
        WorkDay wd1 = new WorkDay();        
        long result = wd1.getExtraMinPerDay();
        assertTrue(result < 0);
        assertTrue(result == -450);
    }
    
}
