package clock;

import java.awt.event.*;
import javax.swing.Timer;

/**
 * Timer for the update the clock face - will call an another class to do the actual update
 * @author Ray Banks
 * @version 1 06/05/2022
 *  
 */
public class Controller {
    
    ActionListener listener;
    Timer timer;
    
    Model model;
    View view;
    
    /**
     *
     * @param m
     * @param v
     */
    public Controller(Model m, View v) {
        model = m;
        view = v;
        
        listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.update();
                //System.out.println(model.minute);
            }
        };
        
        timer = new Timer(100, listener);
        timer.start();
    }
}