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
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This Class will take open the add alarm screen - and pass the information across to the priority queue
 * @author Ray Banks
 * @version 1 06/05/2022
 *  
 */
public class AddAlarm implements ActionListener {

    private final String about;
    private final JLabel lablefirstscreen;
    private final PriorityQueue<Person> q;

    /**
     * 
     * @param aThis
     * @param a
     * @param l
     */
    public AddAlarm(PriorityQueue<Person> aThis, View a, JLabel l ) {
        about = "Add Alarms";
        q = aThis;
        lablefirstscreen = l;
    }

    /**
     *
     * @param e
     */
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
        String hours[]={"00","01","02","03","04","05","06","07","08","09","11","12","13","14","15","16","17","18","19","20","21","22","23",};
        String mins[] = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"}; 
        //String message = "Hello World";
        final JComboBox bcHours = new JComboBox(hours);
        alarmClockSet.add(bcHours, BorderLayout.PAGE_START);
        final JComboBox bcMins = new JComboBox(mins);
        alarmClockSet.add(bcMins, BorderLayout.PAGE_START);
        JButton alarmbutton = new JButton("Set Alarm");
        alarmClockSet.add(alarmbutton, BorderLayout.CENTER);
        alarmbutton.setToolTipText("Click this button to add an alarm for the set time");
        //add button here to import alarms
        
        final JPanel clockAlarms = new JPanel();
        clockAlarms.setPreferredSize(new Dimension(150, 200));
        pane.add(clockAlarms, BorderLayout.LINE_END);
        final JLabel alarmlist = new JLabel(q.toString());
        JButton alarmimport = new JButton("Import Alarms");
        alarmClockSet.add(alarmimport, BorderLayout.PAGE_END);
        alarmimport.setToolTipText("Click here to import alarms");
        alarmimport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent aae) {  
                System.out.println("Import Pressed");
                try {
                      File myObj = new File("alarms.ics");
                      Scanner myReader = new Scanner(myObj);
                      while (myReader.hasNextLine()) {
                        
                        String name = myReader.nextLine();
                        long seconds2 = Long.parseLong(myReader.nextLine());                        
                        int seconds = Integer. parseInt(myReader.nextLine());
                        
                        Calendar calanderread = Calendar.getInstance();
                        calanderread.setTimeInMillis(seconds2);
                        System.out.println(name + " " + seconds + " " + seconds2);
                        Person person = new Person(name, seconds, calanderread);
                        q.add(person, seconds);
                        //reading file does work however doesnt re-fresh alarm displays. 
                      }
                      myReader.close();
                    } catch (FileNotFoundException e) {
                      System.out.println("An error occurred.");
                      e.printStackTrace();
                    } catch (QueueOverflowException ex) {
                    Logger.getLogger("Unable to add alarm from file: " + ex);
                }



                }
            });



          
        clockAlarms.add(alarmlist);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        alarmbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                //for some reason the hour - is reading one ahead of what it should - so i need to take away one from the input
                //FUCKING BRITISH SUMMER TIME

                String hoursString = bcHours.getSelectedItem().toString();
                String mins = bcMins.getSelectedItem().toString();
                //System.out.println("Current Alarm: " + hours + ":" + mins);
                //pass the below into sorted array 
                //LocalTime time = LocalTime.parse(hoursString + ":" + mins + ":00");
                
                //put time conversation in here
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat formatter = new SimpleDateFormat("EE MMM dd ");

                //hard coded year - yuk Sorry if you find this! 
                System.out.println(formatter.format(calendar.getTime()) + hoursString +":"+mins+":01 BST 2022");
                String timetoadd = formatter.format(calendar.getTime()) + hoursString +":"+mins+":01 BST 2022";
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy");
                try {
                cal.setTime(sdf.parse(timetoadd));// all done
                }
                catch(ParseException e) {  
                    System.out.println(e);
                }
                System.out.println("alarm set to: " + (cal.getTimeInMillis()/100000) + " - " + cal);
                System.out.println("Current Time as Int: " + (calendar.getTimeInMillis()/100000) + " - " + calendar);
                System.out.println(cal);

                //working need to compare the hours and mins of these dates and if the selected alarm is before the current time then add a day to the calander date then store as an int


            //Log here to check if the current time > alarm if so then add a date
            if (calendar.getTimeInMillis() > cal.getTimeInMillis() ) {
                    cal.add(Calendar.DATE, 1);
                    System.out.println("Inside if loop - added a day");
                }
                



                int secondOfDay = (int)(cal.getTimeInMillis()/100000);
                //^^^^^^^^^^^^^^^^^^^^
                Person person = new Person(hoursString + ":" + mins, secondOfDay, cal);
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
        //JButton editAlarm = new JButton("Edit set alarms");
        //alarmClockSet.add(editAlarm, BorderLayout.PAGE_END);
        //Will only pass when initilised - will not send latest selection. Action listener on an action listner?
        //editAlarm.addActionListener(new EditAlarmWindow(q, lablefirstscreen, alarmlist));
    }
}
