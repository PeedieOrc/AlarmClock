/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Terra
 */
public class AboutClock implements ActionListener {
    private String about;

    public AboutClock(View aThis) {
        about = "Created by Ray Banks. 055756";
    }
@Override
    public void actionPerformed(ActionEvent e) { 
        
        System.out.println(about);
        JFrame jFrame = new JFrame();
        JOptionPane.showMessageDialog(jFrame, about);
}
    
}
