/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jad_ica;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 * This is the driver class of the Car Park program.
 * 
 * <p>
 * It creates a frame and adds the Interface Manager panel on it.
 * </p>
 * 
 * @version 1.8
 * @author v8002382
 */
public class JAD_ICA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        InterfaceManager interManager = new InterfaceManager();
        frame.add(interManager);
        frame.pack();
        frame.setDefaultCloseOperation(3);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
}
