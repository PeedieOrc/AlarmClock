package clock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.logging.Logger;

/**
 * this will load the initial interface - some of the action listeners are also build into here
 * @author Ray Banks
 * @version 1 06/05/2022
 *  
 */
public class View implements Observer {

    ClockPanel panel;

    /**
     *
     * @param model
     */
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

        panel.setPreferredSize(new Dimension(200, 200));
        pane.add(panel, BorderLayout.CENTER);

        //define the sorted array here
        final PriorityQueue<Person> q;
        q = new SortedArrayPriorityQueue<>(8);
        //System.out.println("Using a sorted array.");

        //display array here!
        final JPanel clockAlarms = new JPanel();
        clockAlarms.setPreferredSize(new Dimension(150, 200));
        pane.add(clockAlarms, BorderLayout.LINE_END);
        final JLabel alarmlist = new JLabel(q.toString());
        clockAlarms.add(alarmlist);

        //menu - Could end up putting this in its own class... 
        JMenuBar menuBar;
        JMenu menu;
        //add file menu
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "Acces file menu");
        menuBar.add(menu);
        //add exit 
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic('x');
        class ExitItemListener implements ActionListener {

            public void actionPerformed(ActionEvent event) {
                String filename = "Alarms.ics";
                try {
                    String time = q.head().storedtime.toInstant().toString().replace( ":" , "" ).replace( "-" , "" );

                    System.out.println(time);
                    StringBuilder builder = new StringBuilder();
                    builder.append(filename);
                    
                    String personname = q.head().name+"\r\n";
                    String personCalander = q.head().storedtime.getTimeInMillis()+"\r\n";
                    String peronLongtime = ""+ q.head().longtime+"\r\n";



                    try {

                        File file = new File(builder.toString());

                        // if file doesnt exists, then create it
                        if (!file.exists()) {
                            file.createNewFile();
                        }

                        FileWriter fw = new FileWriter(file.getAbsoluteFile());
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(personname);
                        bw.write(personCalander);
                        bw.write(peronLongtime);


                        bw.close();

                        System.out.println("Done");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } catch (QueueUnderflowException ex) {
                    Logger.getLogger("something went wrong");
                }

                System.exit(0);
            }
        }
        exitItem.addActionListener(new ExitItemListener());
        menuBar.add(exitItem);
        //add about below file
        menu.addSeparator();
        JMenuItem menuAbout = new JMenuItem("About",
                KeyEvent.VK_T);
        menuAbout.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuAbout.getAccessibleContext().setAccessibleDescription(
                "Information for the about");
        menuAbout.addActionListener(new AboutClock(this));

        JMenuItem menuAdd = new JMenuItem("Add Alarm",
                KeyEvent.VK_T);
        menuAdd.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuAdd.getAccessibleContext().setAccessibleDescription(
                "Add new alarms");
        menuAdd.addActionListener(new AddAlarm(q, this, alarmlist));

        JMenuItem menuEdit = new JMenuItem("Add Edit",
                KeyEvent.VK_T);
        menuEdit.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuEdit.getAccessibleContext().setAccessibleDescription(
                "Add new alarms");
        menuEdit.addActionListener(new EditAlarmWindow(q, alarmlist));

        menu.add(menuAbout);
        menu.add(menuAdd);
        menu.add(menuEdit);

        frame.setJMenuBar(menuBar);
        frame.setLocationRelativeTo(null);

        // End of borderlayout code
        frame.pack();
        frame.setVisible(true);

        //look at creating the action listener against the frame - if update check hours of model against head priority item. 
        new AlarmListner(model, q);
    }

    /**
     *
     * @param o
     * @param arg
     */
    public void update(Observable o, Object arg) {
        panel.repaint();
    }

}
