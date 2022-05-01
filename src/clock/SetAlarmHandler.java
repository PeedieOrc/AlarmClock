/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

/**
 *
 * @author Terra
 */
public class SetAlarmHandler implements ActionListener{
private String message;
private String hours;
int count;

public SetAlarmHandler(JComboBox o, View a){
    message = "Button has been pressed"; 
    this.hours = o.getSelectedItem().toString();
    count++;
}


    @Override
    public void actionPerformed(ActionEvent e) { 
        //hours = e.getSource().getClass().toString();
        //String savedhours = (String)bcHours.getSelectedItem();
        System.out.println(message + count++);
        System.out.println("Current Hour selected: " + this.hours);
}
    
}
