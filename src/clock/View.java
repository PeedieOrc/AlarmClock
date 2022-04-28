package clock;

import java.awt.*;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;

public class View implements Observer {
    
    ClockPanel panel;
    
    public View(Model model) {
        JFrame frame = new JFrame();
        panel = new ClockPanel(model);
        //frame.setContentPane(panel);
        frame.setTitle("Java Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Start of border layout code
        
        // I've just put a single button in each of the border positions:
        // PAGE_START (i.e. top), PAGE_END (bottom), LINE_START (left) and
        // LINE_END (right). You can omit any of these, or replace the button
        // with something else like a label or a menu bar. Or maybe you can
        // figure out how to pack more than one thing into one of those
        // positions. This is the very simplest border layout possible, just
        // to help you get started.
        
        Container pane = frame.getContentPane();
//Set up project first - get using github through jetbeans
//start here - amend so that left hand side creates the alarms - right side displays them
//bottom button to set the alarm - Look at creating drop down - type menctioned in lecture slides 
//once complete read data in - will need to decide on the data type
//could just compare the date and time - can set this so that its set by default todays date, if hours < current hours then next day
// then need to send to array list for storage (use default for ease since my lists not completed. - will need to amend it so that it does it in reverse order and stores as date
// can then call list when new alarm is set or when button is pressed - can output list with already existing call

        
        JButton button = new JButton("Need something here or it crashes?");
        pane.add(button, BorderLayout.PAGE_START);
         
        panel.setPreferredSize(new Dimension(200, 200));
        pane.add(panel, BorderLayout.CENTER);
         
        //button = new JButton("Button 3 (LINE_START)");
        //pane.add(button, BorderLayout.LINE_START);

        JPanel alarmClockSet = new JPanel();
        alarmClockSet.setPreferredSize(new Dimension(150, 200)); 
        pane.add(alarmClockSet,BorderLayout.LINE_START);
        //#TODO must be a better way to store the numbers for the drop down - either using loop or internal timer? 
        String hours[]={"1","2","3","4","5","6","7","8","9","10","11","12"};
        String mins[] = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"}; 
        JComboBox bcHours =new JComboBox(hours);
        JComboBox bcMins =new JComboBox(mins);
        alarmClockSet.add(bcHours, BorderLayout.CENTER);
        alarmClockSet.add(bcMins, BorderLayout.LINE_START);
        button = new JButton("Set Alarm");
        alarmClockSet.add(button, BorderLayout.PAGE_END);


        //button = new JButton("LETS FUCKING GO OW2!");
        //pane.add(button, BorderLayout.PAGE_END);
        //TODO variable name
        
        //button = new JButton("5 (LINE_END)");
        //pane.add(button, BorderLayout.LINE_END);
        DefaultListModel<String> test = new DefaultListModel<>();
          test.addElement("Alarms Set");          
          test.addElement("01:00 pm");  
          test.addElement("05:00 am");  
          test.addElement("06:00 pm");  
          test.addElement("09:00 am");
        JList<String> list = new JList<>(test); 
        list.setPreferredSize(new Dimension(100, 200));
        pane.add(list, BorderLayout.LINE_END);

        
        // End of borderlayout code
        
        frame.pack();
        frame.setVisible(true);
    }
    
    public void update(Observable o, Object arg) {
        panel.repaint();
    }
}
