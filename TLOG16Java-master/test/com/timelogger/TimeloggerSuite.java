/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timelogger;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author rkissvincze
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({com.timelogger.Test_TMPTest.class, com.timelogger.TimeLoggerUITest.class, com.timelogger.TaskTest.class, com.timelogger.TimeLoggerTest.class, com.timelogger.UtilTest.class, com.timelogger.WorkDayTest.class, com.timelogger.WorkMonthTest.class})
public class TimeloggerSuite {
    
}
