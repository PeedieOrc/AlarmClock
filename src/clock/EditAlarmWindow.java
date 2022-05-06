/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clock;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Loads the edit alarms screen- edit alarms does not work just yet - needed to create another function within priority queue to do this rather than just delete the head
 * @author Ray Banks
 * @version 1 06/05/2022
 *  
 */
public class EditAlarmWindow implements ActionListener {
    private final String about;
    private final PriorityQueue<Person> alarm;
 
    private final JLabel clockAlarmslist;

    /**
     *
     * @param p
     * @param j
     */
    public EditAlarmWindow (PriorityQueue<Person> p, JLabel j) {
        about = "Current set alarms";
        alarm = p;
        clockAlarmslist = j;
    System.out.println(alarm.toString());
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) { 
        
        System.out.println(about);
        JFrame frame  = new JFrame("Edit Alarms");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        final JPanel clockAlarms = new JPanel();
        clockAlarms.setPreferredSize(new Dimension(200, 200)); 
        final JLabel alarmlisttitle = new JLabel(about);
        clockAlarms.add(alarmlisttitle);
        final JLabel alarmlist = new JLabel(alarm.toString());
        clockAlarms.add(alarmlist);
        final JPanel editclockAlarms = new JPanel();
        editclockAlarms.setPreferredSize(new Dimension(200, 200)); 
        final JLabel editalarmtitle = new JLabel("Edit or Delete alarms");
        editclockAlarms.add(editalarmtitle, BorderLayout.LINE_START);
        JTextField idtextbox = new JTextField(15);
        editclockAlarms.add(idtextbox,BorderLayout.CENTER);
        JButton delete = new JButton("Delete");
        editclockAlarms.add(delete,BorderLayout.LINE_END);
        JButton edit = new JButton("Edit");
        editclockAlarms.add(edit,BorderLayout.LINE_END);
        //TODO add new listeners for Delete and EDIT - pop up window for edit with just text field and OK
        frame.add(clockAlarms,BorderLayout.LINE_END);
        frame.add(editclockAlarms,BorderLayout.CENTER);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);

        delete.addActionListener(new DeleteHead(alarm, alarmlist, clockAlarmslist));
        edit.addActionListener(new EditAlarmQueue(alarm, alarmlist));

                
}
}
