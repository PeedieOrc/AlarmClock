/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clock;

import java.awt.event.*;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * This class created the action listener for the alarm at the head of the priority queue
 * @author Ray Banks
 * @version 1 06/05/2022
 *  
 */
public class AlarmListner {

    ActionListener listener;
    Timer timer;
    Model model;
    private final PriorityQueue<Person> queue;

    /**
     *
     * @param m
     * @param q
     */
    public AlarmListner(Model m, final PriorityQueue<Person> q) {
        queue = q;
        model = m;

        listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Calendar date = Calendar.getInstance();
                

                System.out.println("ALARM Listner");
                try {
                    long alarmMillis = queue.head().storedtime.getTimeInMillis()/10000;
                    long currentMillis = date.getTimeInMillis()/10000;
                    System.out.println(queue.head());
                    System.out.println("Current Time in millis: "+ date.getTimeInMillis() + "  / by 100000  " + currentMillis);
                    System.out.println("Alarm Hourmillis: "+queue.head().storedtime.getTimeInMillis()+ "  / by 100000  " + alarmMillis);
                    //System.out.println(queue.head().storedtime.toInstant()); used as a test for the exit saving file
                    if (alarmMillis == currentMillis) {
                        System.out.println("ALARM TRIGGER WEE WOO");
                        q.remove();
                        JFrame jFrame = new JFrame();
                        JOptionPane.showMessageDialog(jFrame, "CLOCK ALARM - ALARM REMOVED FROM QUEUE");
                        //need to update the lists :/
                        //alarmlist.setText(q.toString());
                        //lablefirstscreen.setText(q.toString());
                    }
                } catch (QueueUnderflowException ex) {
                    Logger.getLogger("Can't get head of queue");
                }
            }
        };
        timer = new Timer(10000, listener);
        timer.start();
    }
}
