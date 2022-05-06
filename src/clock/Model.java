package clock;

import java.util.Calendar;
import java.util.Observable;
//import java.util.GregorianCalendar;

/**
 * will update the hours a min - linked to controller
 * @author Ray Banks
 * @version 1 06/05/2022
 *  
 */
public class Model extends Observable {
    
    int hour = 0;
    int minute = 0;
    int second = 0;
    
    int oldSecond = 0;
    
    /**
     *
     */
    public Model() {
        update();
    }
    
    /**
     *
     */
    public void update() {
        Calendar date = Calendar.getInstance();
        hour = date.get(Calendar.HOUR);
        minute = date.get(Calendar.MINUTE);
        oldSecond = second;
        second = date.get(Calendar.SECOND);
        if (oldSecond != second) {
            setChanged();
            notifyObservers();
        }
    }
}