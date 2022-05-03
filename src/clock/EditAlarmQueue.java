/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

/**
 *
 * @author Terra
 */
public class EditAlarmQueue implements ActionListener {

    private final PriorityQueue<Person> pEdit;
    JLabel alarmList;

    public EditAlarmQueue(PriorityQueue<Person> alarm, JLabel alarmlist) {
        pEdit = alarm;
        alarmList = alarmlist;

    }

    public void actionPerformed(ActionEvent ae) {
        //Need to complete the below - Solution would be to use the delete previous verion - then use call add item to add item to the arary
        System.out.println("EDIT ITEM");
    }
}
