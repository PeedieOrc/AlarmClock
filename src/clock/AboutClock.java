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
 * this lass will open the about window and display the below message to the user
 * @author Ray Banks
 * @version 1.2    
 * @date 06/05/2022
 */
public class AboutClock implements ActionListener {
    private String about;

    /**
     *
     * @param aThis
     */
    public AboutClock(View aThis) {
        about = "This clock will only go from the head item in the array queue. Created by Ray Banks. 055756";
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) { 
        
        System.out.println(about);
        JFrame jFrame = new JFrame();
        JOptionPane.showMessageDialog(jFrame, about);
}
    
}
