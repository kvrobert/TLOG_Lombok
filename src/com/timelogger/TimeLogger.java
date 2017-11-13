package com.timelogger;

import com.exceptions.NotNewDateException;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author rkissvincze
 */
@Getter
@Setter
@NoArgsConstructor
public class TimeLogger {
    
    private List<WorkMonth> months = new ArrayList<>();
      
    public boolean isNewMonth(WorkMonth workMonth){
    
        return months.stream().filter(i -> i.getDate().equals( workMonth.getDate() ) ).count() == 0;
    }
    
    public void addMonth(WorkMonth workMonth) throws NotNewDateException{    
        if( isNewMonth(workMonth) ){        
            months.add(workMonth);
            return;
        }else{ throw new NotNewDateException(" The month (" + workMonth.toString() + ") already exists. Give an another."); }
    }
}
