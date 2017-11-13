package com.timelogger;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rkissvincze
 */
public class TimeLogger {
    
    private List<WorkMonth> months = new ArrayList<>();

    public List<WorkMonth> getMonths() {
        return months;
    }
    
    public boolean isNewMonth(WorkMonth workMonth){
    
        return months.stream().filter(i -> i.getDate() == workMonth.getDate()).count() == 0;
    }
    
    public void addMonth(WorkMonth workMonth){
    
        if( isNewMonth(workMonth) ){
        
            months.add(workMonth);
            return;
        }
    }
}
