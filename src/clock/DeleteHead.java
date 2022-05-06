/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

/**
 * will will delete the head of the queue -
 * @author Ray Banks
 * @version 1 06/05/2022
 *  
 */
public class DeleteHead implements ActionListener {
        private final PriorityQueue<Person> pDelete;
        JLabel alarmlist;
       
        JLabel clockAlarms;

    /**
     *
     * @param p
     * @param j
     * @param k
     */
    public DeleteHead(PriorityQueue<Person> p, JLabel j, JLabel k ) {
        pDelete = p;
        alarmlist=j;
        
        clockAlarms =k;
    }

    /**
     *
     * @param ae
     */
    public void actionPerformed(ActionEvent ae) { 
        //need to amend the prioty item queue to look for and delete the specific item menction in the text box

        try {
                    System.out.println("Removing item from the head of the queue");
                    pDelete.remove();
                    alarmlist.setText(pDelete.toString());
                    //lablefirstscreen.setText(pDelete.toString());
                    clockAlarms.setText(pDelete.toString());
                } catch (QueueUnderflowException e) {
                    System.out.println("Can't remove head of queue: " + e);
                }
}
}
