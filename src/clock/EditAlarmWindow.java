/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clock;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class EditAlarmWindow implements ActionListener {
    private String about;
    private PriorityQueue<Person> alarm;
public EditAlarmWindow (PriorityQueue<Person> p) {
        about = "Current set alarms";
        alarm = p;
    System.out.println(alarm.toString());
    }
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
        editclockAlarms.add(editalarmtitle);
        

        frame.add(clockAlarms,BorderLayout.LINE_END);
        frame.add(editclockAlarms,BorderLayout.CENTER);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
                
}
}
