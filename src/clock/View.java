package clock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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

        JButton aboutButton = new JButton("About");
        pane.add(aboutButton, BorderLayout.PAGE_START);
        aboutButton.addActionListener(new AboutClock(this));

        panel.setPreferredSize(new Dimension(200, 200));
        pane.add(panel, BorderLayout.CENTER);

        //JButton button = new JButton("Button 3 (LINE_START)");
       //pane.add(button, BorderLayout.LINE_START);
        JPanel alarmClockSet = new JPanel();
        alarmClockSet.setPreferredSize(new Dimension(150, 200)); 
        pane.add(alarmClockSet,BorderLayout.LINE_START);
        //#TODO must be a better way to store the numbers for the drop down - either using loop or internal timer? 
        String hours[]={"1","2","3","4","5","6","7","8","9","11","12","13","14","15","16","17","18","19","20","21","22","23",};
        String mins[] = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"}; 
        String message = "Hello World";
        final JComboBox bcHours = new JComboBox(hours);
        alarmClockSet.add(bcHours, BorderLayout.CENTER);
        final JComboBox bcMins = new JComboBox(mins);
        alarmClockSet.add(bcMins, BorderLayout.LINE_START);
        JButton alarmbutton = new JButton("Set Alarm");
        alarmClockSet.add(alarmbutton, BorderLayout.PAGE_END);
        alarmbutton.setToolTipText("Click this button to add an alarm for the set time");
        //Will only pass when initilised - will not send latest selection. Action listener on an action listner?
        //alarmbutton.addActionListener(new SetAlarmHandler(bcHours, this));

        //define the sorted array here
        final PriorityQueue<Person> q;
        q = new SortedArrayPriorityQueue<>(8);
        System.out.println("Using a sorted array.");


        alarmbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String hours = bcHours.getSelectedItem().toString();
                String mins = bcMins.getSelectedItem().toString();
                System.out.println("Current Alarm: " + hours + ":" + mins);
            //pass the below into sorted array 
                
                
                Person person = new Person(hours);
                int priority = Integer.parseInt(hours);
                System.out.println("Adding " + person.getName() + " with priority " + priority);
                try {
                    q.add(person, priority);
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
            }
        });

        JButton button = new JButton("Long-Named Button 4 (PAGE_END)");
        pane.add(button, BorderLayout.PAGE_END);

        button = new JButton("5 (LINE_END)");
        pane.add(button, BorderLayout.LINE_END);

        //menu - Could end up putting this in its own class... 
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;
        //add file
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "Add description here");
        menuBar.add(menu);
        //add exit 
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic('x');
        class ExitItemListener implements ActionListener {

            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        }
        exitItem.addActionListener(new ExitItemListener());
        menuBar.add(exitItem);
        //add about below file
        menu.addSeparator();
        menuItem = new JMenuItem("About",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Information for the about");
        menuItem.addActionListener(new AboutClock(this));
        menu.add(menuItem);
        frame.setJMenuBar(menuBar);




        // End of borderlayout code

        frame.pack();
        frame.setVisible(true);
    }

    public void update(Observable o, Object arg) {
        panel.repaint();
    }

}
