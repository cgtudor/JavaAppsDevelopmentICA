/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jad_ica;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This panel holds all the buttons and performs all actions related to them.
 * 
 * <p>
 * It performs all actions related to buttons by communicating with the {@link VehiclePanel} and {@link InterfaceManager} to store information.
 * </p>
 * 
 * @see InterfaceManager
 * @author v8002382
 */
public class ButtonsPanel extends JPanel implements ActionListener{
    private String vehicleSelected;
    private JButton clearAll, save, load, currentTotal, totalForDay, addVehicle;
    private JComboBox selectionBox;
    private JButton[] buttons;
    private VehiclePanel vehPanel;
    private InterfaceManager parentPanel;
    /**
     * Initialises all components of the panel.
     * @param vehPanel is a reference to the {@link VehiclePanel}.
     * @param interManager is a reference to the {@link InterfaceManager}.
     */
    public ButtonsPanel(VehiclePanel vehPanel, InterfaceManager interManager) 
    {
        playSound("creating2.wav");
        this.setPreferredSize(new Dimension(170, 560));
        this.vehPanel = vehPanel;
        this.parentPanel = interManager;
        buttons = new JButton[7];
        Dimension buttonSize = new Dimension(170,70);
        this.setLayout(new GridLayout(7,1));
        String[] vehicleChoices = { "Car", "Lorry", "Coach" };
        selectionBox = new JComboBox(vehicleChoices);
        selectionBox.setPreferredSize(buttonSize);
        ((JLabel) selectionBox.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        vehicleSelected = "Car";
        add(selectionBox);
        addVehicle = new JButton("Add Vehicle");
        addVehicle.setPreferredSize(buttonSize);
        buttons[0] = addVehicle;
        selectionBox.addActionListener(this);
        clearAll = new JButton("Clear All");
        clearAll.setPreferredSize(buttonSize);
        buttons[1] = clearAll;
        save = new JButton("Save");
        save.setPreferredSize(buttonSize);
        buttons[2] = save;
        load = new JButton("Load");
        load.setPreferredSize(buttonSize);
        buttons[3] = load;
        currentTotal = new JButton("Current Total");
        currentTotal.setPreferredSize(buttonSize);
        buttons[4] = currentTotal;
        totalForDay = new JButton("Total for the day");
        totalForDay.setPreferredSize(buttonSize);
        buttons[5] = totalForDay;
        for (int i = 0; i < 6; i++) 
        {
            add(buttons[i]);
            buttons[i].addActionListener(this);
        }
    }

    /**
     * Plays sound clips.
     * @param soundName is the name of the sound file to be played. Must be located in the root folder of the project.
     */
    public void playSound(String soundName)
    {
        try
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex)
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
    
    /**
     * Adds a car to the information-storing array and to the label array.
     * The registration number must be between 1 and 15 characters long.
     * The allowed length is between 1 and 20. 
     * If there is no more room the addCar method from {@link CarParkPanel} returns false.
     */
    public void addCar()
    {
        playSound("car toot.wav");
        int carL = 0;
        int ok;
        do
        {
            String carLength = (String) JOptionPane.showInputDialog("Please input the car length in meters:\n", null);
            if (carLength == null)
            {
                playSound("cancel.wav");
                return;
            }
            ok = 0;
            try
            {
                carL = Integer.parseInt(carLength);
                if (carL < 1 || carL > 20)
                {
                    JOptionPane.showMessageDialog(null, "Car length must be between 1 and 20");
                    ok = 1;
                }   
            } catch (NumberFormatException en)
            {
                ok = 1;
            }
            
        } while (ok == 1);
        playSound("ok.wav");
        int badgeHolderTemp = JOptionPane.showConfirmDialog(null, "Is the driver a disabled badge holder?", null, JOptionPane.YES_NO_OPTION);
        playSound("ok.wav");
        boolean badgeHolder;
        if (badgeHolderTemp == 0)
        {
            badgeHolder = true;
        } else
        {
            badgeHolder = false;
        }
        String regNo;
        do {
            ok = 0;
            regNo = (String) JOptionPane.showInputDialog("Please input the car registration number:\n", null);
            if (regNo == null) {
                playSound("cancel.wav");
                return;
            }
            else if(regNo.length() < 1 || regNo.length() > 15)
            {
                JOptionPane.showMessageDialog(null, "Please input a valid registration number.");
                ok = 1;
            }
        } while (ok == 1);
        playSound("ok.wav");
        ImageIcon pic;
        if(carL < 6)
            pic = new ImageIcon(new ImageIcon("car.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        else
            pic = new ImageIcon(new ImageIcon("campervan.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        Car newCar = new Car(carL, badgeHolder, regNo, pic);
        boolean added = vehPanel.getCars().addCar(newCar);
        if (!added)
        {
            JOptionPane.showMessageDialog(null, "There is no more room for cars");
        }
    }
    /**
     * Adds a lorry to the information-storing array and to the label array.
     * The registration number must be between 1 and 15 characters long.
     * The allowed weight is between 1 and 35. 
     * If there is no more room the addVehicle method from {@link LoCoPanel} returns false.
     */
    public void addLorry() 
    {
        playSound("lorry toot.wav");
        int lorryWeight = 0;
        int ok;
        do {
            String lorryWeightString = (String) JOptionPane.showInputDialog("Please input the lorry weight in tonnes:\n", null);
            if (lorryWeightString == null) {
                playSound("cancel.wav");
                return;
            }
            ok = 0;
            try {
                lorryWeight = Integer.parseInt(lorryWeightString);
                if (lorryWeight < 1 || lorryWeight > 35) {
                    JOptionPane.showMessageDialog(null, "Invalid weight. Enter an amount between 1 and 35");
                    ok = 1;
                }

            } catch (NumberFormatException en) {
                ok = 1;
            }

        } while (ok == 1);
        playSound("ok.wav");
        String regNo;
        do 
        {
            ok = 0;
            regNo = (String) JOptionPane.showInputDialog("Please input the car registration number:\n", null);
            if (regNo == null) {
                playSound("cancel.wav");
                return;
            } else if (regNo.length() < 1 || regNo.length() > 15) {
                JOptionPane.showMessageDialog(null, "Please input a valid registration number.");
                ok = 1;
            }
        } while (ok == 1);
        playSound("ok.wav");
        ImageIcon pic = new ImageIcon(new ImageIcon("lorry.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        Lorry newLorry = new Lorry(lorryWeight, regNo, pic);
        boolean added = vehPanel.getLoCo().addVehicle(newLorry);
        if (!added) {
            JOptionPane.showMessageDialog(null, "There is no more room for lorries or coaches");
        }
    }
    /**
     * Adds a coach to the information-storing array and to the label array.
     * The registration number must be between 1 and 15 characters long.
     * The allowed number of passengers is between 0 and 57. 
     * If there is no more room the addVehicle method from {@link LoCoPanel} returns false.
     */
    public void addCoach() {
        playSound("coach2.wav");
        int numberOfPassengers = 0;
        int ok;
        do {
            String numberOfPassengersString = (String) JOptionPane.showInputDialog("Please input the number of passengers:\n", null);
            if (numberOfPassengersString == null) {
                playSound("cancel.wav");
                return;
            }

            ok = 0;
            try {
                numberOfPassengers = Integer.parseInt(numberOfPassengersString);
                if (numberOfPassengers < 0 || numberOfPassengers > 57) {
                    JOptionPane.showMessageDialog(null, "Invalid number. Enter an amount between 0 and 57");
                    ok = 1;
                }

            } catch (NumberFormatException en) {
                ok = 1;
            }

        } while (ok == 1);
        playSound("ok.wav");
        int touristTemp = JOptionPane.showConfirmDialog(null, "Does the coach possess a tourist pass?", null, JOptionPane.YES_NO_OPTION);
        boolean tourist;
        if (touristTemp == 0) {
            tourist = true;
        } else {
            tourist = false;
        }
        playSound("ok.wav");
        String regNo;
        do {
            ok = 0;
            regNo = (String) JOptionPane.showInputDialog("Please input the car registration number:\n", null);
            if (regNo == null) {
                playSound("cancel.wav");
                return;
            } else if (regNo.length() < 1 || regNo.length() > 15) {
                JOptionPane.showMessageDialog(null, "Please input a valid registration number.");
                ok = 1;
            }
        } while (ok == 1);
        playSound("ok.wav");
        ImageIcon pic = new ImageIcon(new ImageIcon("coach.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        Coach newCoach = new Coach(numberOfPassengers, tourist, regNo, pic);
        boolean added = vehPanel.getLoCo().addVehicle(newCoach);
        if (!added) {
            JOptionPane.showMessageDialog(null, "There is no more room for lorries or coaches");
        }
    }
    /**
     * Saves the {@link VehiclePanel} by serialising it to a file with all the information contained in it.
     * @see VehiclePanel
     */
    public void save()
    {
        String saveLocation = (String) JOptionPane.showInputDialog("Please input the name and extension of the file you want to save as (absolute path if not in the project directory):\n", null);
        if (saveLocation == null) {
            playSound("cancel.wav");
            return;
        }
        try {
            File saveFile = new File(saveLocation);
            FileOutputStream fileOut = new FileOutputStream(saveFile, false);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(vehPanel);
            out.close();
            fileOut.close();
            playSound("saved.wav");
            JOptionPane.showMessageDialog(null, "Serialized data is saved in " + saveLocation);
        } catch (IOException i) {
            System.out.println("Save location not found");
        }
    }
    /**
     * Loads the {@link VehiclePanel} by de-serialising it from a file and sending it to the {@link InterfaceManager}.
     * It creates a new VehiclePanel which is then sent to the InterfaceManager that facilitates the replace of the old one.
     */
    public void load()
    {
        vehPanel.clearAll();
        vehPanel = null;
        String loadLocation = (String) JOptionPane.showInputDialog("Please input the name and extension of the file you want to load (absolute path if not in the project directory):\n", null);
        if (loadLocation == null) {
            playSound("cancel.wav");
            return;
        }
        File loadFile = new File(loadLocation);
        while (!loadFile.exists()) {
            loadLocation = (String) JOptionPane.showInputDialog("Please input the name and extension of the file you want to load (absolute path if not in the project directory):\n", null);
            loadFile = new File(loadLocation);
        }
        try {
            FileInputStream fileIn = new FileInputStream(loadLocation);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            vehPanel = (VehiclePanel) in.readObject();
            in.close();
            fileIn.close();
            playSound("load.wav");
            JOptionPane.showMessageDialog(null, "Deserialized data from " + loadLocation);
        } catch (FileNotFoundException f) {
            return;
        } catch (IOException i) {
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Vehicle Panel class not found");
            return;
        }
        parentPanel.loadPicturePanels(vehPanel);
    }
    /**
     * Pulls an appropriate method for each button.
     * @see VehiclePanel#clearAll() 
     * @param e represents the button clicked.
     */
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == selectionBox)
        {
            JComboBox cb = (JComboBox) e.getSource();
            String choice = (String) cb.getSelectedItem();
            switch (choice)
            {
                case "Car":
                    vehicleSelected = "Car";
                    break;
                case "Lorry":
                    vehicleSelected = "Lorry";
                    break;
                case "Coach":
                    vehicleSelected = "Coach";
                    break;
            }
        }
        if(e.getSource() == addVehicle)
        {
            switch(vehicleSelected)
            {
                case "Car":
                    addCar();
                    break;
                case "Lorry":
                    addLorry();
                    break;
                case "Coach":
                    addCoach();
                    break;
            }
        }
        if(e.getSource() == clearAll)
        {
            playSound("clear.wav");
            vehPanel.clearAll();
        }
        if(e.getSource() == save)
        {
            save();
        }
        if(e.getSource() == load)
        {
            load();
        }
        if(e.getSource() == currentTotal)
        {
            playSound("cash.wav");
            JOptionPane.showMessageDialog(null, "The total amount charged for the vehicles currently in the park is £" + (vehPanel.getCars().currentTotalAmount() + vehPanel.getLoCo().getCurrentTotal()));
            playSound("ok.wav");
        }
        if(e.getSource() == totalForDay)
        {
            playSound("cash.wav");
            JOptionPane.showMessageDialog(null, "The total amount charged for the whole day is £" + (vehPanel.getCars().getTotal() + vehPanel.getLoCo().getTotal()));
            playSound("ok.wav");
        }
    }
    
}
