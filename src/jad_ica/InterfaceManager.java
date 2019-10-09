/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jad_ica;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 * A panel that holds and creates connections between the {@link ButtonsPanel} and {@link VehiclePanel} classes.
 * 
 * <p>
 * This class extends JPanel and creates the {@link ButtonsPanel}, {@link VehiclePanel}, {@link CarParkPanel} and {@link LorryCoachPanel} classes.
 * After creating them, it places each one in the correct position. It sends the vehicle panel and this class as a reference to the button panel
 * in order to keep connections between the GUIs.
 * </p>
 * 
 * @author v8002382
 */
public class InterfaceManager extends JPanel{
    private final CarParkPanel carPanel;
    private final LorryCoachPanel loCoPanel;
    private VehiclePanel vehicles;
    private final ButtonsPanel panel;
    InterfaceManager()
    {
        carPanel = new CarParkPanel();
        loCoPanel = new LorryCoachPanel();
        vehicles = new VehiclePanel(carPanel, loCoPanel);
        panel = new ButtonsPanel(vehicles, this);
        setLayout(new BorderLayout());
        add(panel, BorderLayout.WEST);
        add(vehicles, BorderLayout.CENTER);
    }
    /**
     * Replaces the old vehicle panel with the de-serialised one. 
     * @param vehiclePanel is the new VehiclePanel that got de-serialised
     * @see ButtonsPanel#load() 
     */
    public void loadPicturePanels(VehiclePanel vehiclePanel)
    {
        this.remove(vehicles);
        this.revalidate();
        vehicles = vehiclePanel;
        this.add(vehicles, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
    
}
