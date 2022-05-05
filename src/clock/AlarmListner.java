/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clock;

import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

public class AlarmListner {

    ActionListener listener;
    Timer timer;
    Model model;
    private final PriorityQueue<Person> queue;

    public AlarmListner(Model m, PriorityQueue<Person> q) {
        queue = q;
        model = m;

        listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Action Listner");
                try {
                    System.out.println(queue.head());
                } catch (QueueUnderflowException ex) {
                    Logger.getLogger("Can't get head of queue");
                }
            }
        };
        timer = new Timer(10000, listener);
        timer.start();
    }
}
