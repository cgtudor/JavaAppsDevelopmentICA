/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jad_ica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 * Creates a {@link CarParkPanel} and a {@link LorryCoachPanel} and adds them to the correct position.
 * It is used as a reference to access the two panels from the ButtonPanel.
 * @author v8002382
 */
public class VehiclePanel extends JPanel implements java.io.Serializable{
    LorryCoachPanel loCo;
    CarParkPanel cars;
    VehiclePanel(CarParkPanel cars, LorryCoachPanel loCo)
    {
        this.loCo = loCo;
        this.cars = cars;
        this.setPreferredSize(new Dimension(400,400));
        this.setLayout(new BorderLayout());
        add(this.loCo, BorderLayout.NORTH);
        add(this.cars, BorderLayout.CENTER);
    }
    /**
     * Clears both panels.
     */
    public void clearAll()
    {
        cars.clearAll();
        loCo.clearAll();
    }

    public LorryCoachPanel getLoCo() {
        return loCo;
    }

    public CarParkPanel getCars() {
        return cars;
    }
    
}
