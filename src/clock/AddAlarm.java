/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clock;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Terra
 */
public class AddAlarm implements ActionListener {

    private final String about;
    private final JLabel lablefirstscreen;
    private final PriorityQueue<Person> q;

    public AddAlarm(PriorityQueue<Person> aThis, View a, JLabel l ) {
        about = "Add Alarms";
        q = aThis;
        lablefirstscreen = l;
    }

    public void actionPerformed(ActionEvent e) {

//        System.out.println(about);
//        JFrame jFrame = new JFrame();
//        JOptionPane.showMessageDialog(jFrame, about);

        //JButton button = new JButton("Button 3 (LINE_START)");
       //pane.add(button, BorderLayout.LINE_START);
        JFrame frame = new JFrame();
        frame.setTitle("Java Clock - Add Alarms");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container pane = frame.getContentPane();
        JPanel alarmClockSet = new JPanel();
        alarmClockSet.setPreferredSize(new Dimension(150, 200)); 
        pane.add(alarmClockSet,BorderLayout.LINE_START);
        //#TODO must be a better way to store the numbers for the drop down - either using loop or internal timer? 
        String hours[]={"01","02","03","04","05","06","07","08","09","11","12","13","14","15","16","17","18","19","20","21","22","23",};
        String mins[] = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"}; 
        //String message = "Hello World";
        final JComboBox bcHours = new JComboBox(hours);
        alarmClockSet.add(bcHours, BorderLayout.PAGE_START);
        final JComboBox bcMins = new JComboBox(mins);
        alarmClockSet.add(bcMins, BorderLayout.PAGE_START);
        JButton alarmbutton = new JButton("Set Alarm");
        alarmClockSet.add(alarmbutton, BorderLayout.CENTER);
        alarmbutton.setToolTipText("Click this button to add an alarm for the set time");
        final JPanel clockAlarms = new JPanel();
        clockAlarms.setPreferredSize(new Dimension(150, 200));
        pane.add(clockAlarms, BorderLayout.LINE_END);
        final JLabel alarmlist = new JLabel(q.toString());
        
        clockAlarms.add(alarmlist);
        frame.pack();
        frame.setVisible(true);

        alarmbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String hours = bcHours.getSelectedItem().toString();
                String mins = bcMins.getSelectedItem().toString();
                //System.out.println("Current Alarm: " + hours + ":" + mins);
                //pass the below into sorted array 
                LocalTime time = LocalTime.parse(hours + ":" + mins + ":00");
                int secondOfDay = time.toSecondOfDay();
                Person person = new Person(hours + ":" + mins, secondOfDay);
                System.out.println("Adding " + person.getName() + " with priority " + secondOfDay);
                try {
                    q.add(person, secondOfDay);
                } catch (QueueOverflowException e) {
                    System.out.println("Add operation failed: " + e);
                }
                try {
                    String name = q.head().getName();
                    System.out.println("The person at the head of the queue is " + name);
                } catch (QueueUnderflowException e) {
                    System.out.println("Can't get head of queue: " + e);
                }
                System.out.println(q);
                alarmlist.setText(q.toString());
                lablefirstscreen.setText(q.toString());
            }
        });

        //JButton button = new JButton("Long-Named Button 4 (PAGE_END)");
        //pane.add(button, BorderLayout.PAGE_END);
        JButton editAlarm = new JButton("Edit set alarms");
        alarmClockSet.add(editAlarm, BorderLayout.PAGE_END);
        //Will only pass when initilised - will not send latest selection. Action listener on an action listner?
        editAlarm.addActionListener(new EditAlarmWindow(q, lablefirstscreen, alarmlist));
    }
}
