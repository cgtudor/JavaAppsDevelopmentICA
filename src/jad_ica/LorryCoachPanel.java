/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jad_ica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 * A panel that holds all the lorry and coach labels and intercepts all the mouse input related to them.
 * This panel holds all the lorry and coach labels and all information related to the labels. It intercepts all mouse input and modifies internal data accordingly.
 * @author v8002382
 */
public class LorryCoachPanel extends JPanel implements MouseListener, java.io.Serializable{
    private Vehicle[] vehicles;
    private int loCoNumber;
    private JLabel vehPanels[];
    private double totalAmount, currentTotalAmount;
    LorryCoachPanel()
    {
        currentTotalAmount = 0;
        totalAmount = 0;
        this.setLayout(new GridLayout(1,4));
        this.setPreferredSize(new Dimension(500,200));
        vehicles = new Vehicle[4];
        vehPanels = new JLabel[4];
        for(int i = 0; i < vehPanels.length; i++)
        {
            JLabel newVehicle = new JLabel();
            //newVehicle.setVisible(false);
            vehPanels[i] = newVehicle;
            vehPanels[i].setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK));
            vehPanels[i].addMouseListener(this);
            this.add(vehPanels[i]);
        }
        loCoNumber = 0;
    }

    /**
     * Adds a vehicle to the vehicle array and fills in the first empty space found in the label array.
     * It also adds the charge to the totals.
     * @param vehicle is the vehicle sent from the ButtonPanel. This vehicle is to be added to the vehicle array.
     * @return true if the lorry+coach park is full and false if it is not.
     */
    public boolean addVehicle(Vehicle vehicle)
    {
        if(isFull())
            return false;
        int i;
        for (i = 0; i < vehicles.length; i++) 
        {
            if (vehicles[i] == null) 
            {
                vehicles[i] = vehicle;
                break;
            }
        }
        vehPanels[i].setIcon(vehicle.getPicture());
        //vehPanels[i].setVisible(true);
        totalAmount += vehicle.getAmountCharged();
        currentTotalAmount += vehicle.getAmountCharged();
        loCoNumber++;
        this.revalidate();
        return true;
    }
    
    /**
     * Clears all labels and the vehicle array.
     * Sets all the icons in the label array to null and gets rid of all vehicles in the vehicle array.
     */
    public void clearAll()
    {   
        for(int i = 0; i < vehPanels.length; i++)
            vehPanels[i].setIcon(null);
            //vehPanels[i].setVisible(false);
        for (int i = 0; i < vehicles.length; i++) 
        {
            vehicles[i] = null;
        }
        loCoNumber = 0;
        for(JLabel label : vehPanels)
            label = null;
        totalAmount = 0;
        currentTotalAmount = 0;
        this.revalidate();
        this.repaint();
    }
    
    /**
     * Checks the lorry+coach number to see if the park is full or not.
     * @return true if the lorry+coach park is full and false if it is not.
     */
    public boolean isFull()
    {
        if(loCoNumber == 4)
            return true;
        return false;
    }
    
    public double getTotal()
    {
        return totalAmount;
    }
    
    public double getCurrentTotal()
    {
        return currentTotalAmount;
    }
    
    /**
     * Finds the label clicked and displays information related to it.
     * @param source represents the label clicked
     */
    public void displayInformation(JLabel source)
    {
        for (int i = 0; i < vehPanels.length; i++)
        {
            if (source.equals(vehPanels[i]))
            {
                if(vehPanels[i].getIcon() == null)
                {
                    JOptionPane.showMessageDialog(null, "There is no lorry or coach parked here");
                    break;
                }
                JOptionPane.showMessageDialog(null, vehicles[i].toString());
                break;
            }
        }
    }
    
    /**
     * Finds the label clicked, removes its icon and removes the related vehicle from the vehicle array
     * @param source represents the label clicked
     */
    public void removeVehicle(JLabel source)
    {
        int choice = JOptionPane.showConfirmDialog(null,"Are you sure you want to remove this vehicle?",null, JOptionPane.YES_NO_OPTION);
        if(choice == 0)
        {
            int i;
            for (i = 0; i < vehPanels.length; i++)
            {
                if (source.equals(vehPanels[i]))
                {
                    break;
                }
            }
            currentTotalAmount -= vehicles[i].getAmountCharged();
            vehPanels[i].setIcon(null);
            vehicles[i] = null;
            loCoNumber--;
            revalidate();
            repaint();
        }
    }
    
    /**
     * Finds the label clicked and modifies the details related to it.
     * After it finds the label it asks the user for new information and modifies the details related to that vehicle in the vehicle array.
     * @param source represents the label clicked
     */
    public void modifyVehicle(JLabel source)
    {
        int choice = JOptionPane.showConfirmDialog(null,"Are you sure you want to modify this vehicle?",null, JOptionPane.YES_NO_OPTION);
        if(choice == 0)
        {
            int i;
            for (i = 0; i < vehPanels.length; i++)
            {
                if (source.equals(vehPanels[i]))
                {
                    break;
                }
            }
            currentTotalAmount -= vehicles[i].getAmountCharged();
            totalAmount -= vehicles[i].amountCharged;
            if (vehicles[i].type() == 2)
            {
                int lorryWeight = 0;
                int ok;
                do
                {
                    String lorryWeightString = (String) JOptionPane.showInputDialog("Please input the lorry weight in tonnes:\n", null);
                    if (lorryWeightString == null)
                    {
                        return;
                    }
                    ok = 0;
                    try
                    {
                        lorryWeight = Integer.parseInt(lorryWeightString);
                        if (lorryWeight < 1 || lorryWeight > 35)
                        {
                            JOptionPane.showMessageDialog(null, "Invalid weight. Enter an amount between 1 and 35");
                            ok = 1;
                        }

                    } catch (NumberFormatException en)
                    {
                        ok = 1;
                    }

                } while (ok == 1);
                String regNo = (String) JOptionPane.showInputDialog("Please input the lorry registration number:\n", null);
                if (regNo == null || regNo.equals(""))
                {
                    return;
                }
                ImageIcon pic = new ImageIcon(new ImageIcon("lorry.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
                vehicles[i].modify(lorryWeight, regNo);
            }
            else if (vehicles[i].type() == 3)
            {
                int numberOfPassengers = 0;
                int ok;
                do
                {
                    String numberOfPassengersString = (String) JOptionPane.showInputDialog("Please input the number of passengers:\n", null);
                    if (numberOfPassengersString == null)
                    {
                        return;
                    }
                    ok = 0;
                    try
                    {
                        numberOfPassengers = Integer.parseInt(numberOfPassengersString);
                        if (numberOfPassengers < 0 || numberOfPassengers > 57)
                        {
                            JOptionPane.showMessageDialog(null, "Invalid number. Enter an amount between 0 and 57");
                            ok = 1;
                        }

                    } catch (NumberFormatException en)
                    {
                        ok = 1;
                    }

                } while (ok == 1);
                int touristTemp = JOptionPane.showConfirmDialog(null, "Does the coach possess a tourist pass?", null, JOptionPane.YES_NO_OPTION);
                boolean tourist;
                if (touristTemp == 0)
                {
                    tourist = true;
                } else
                {
                    tourist = false;
                }
                String regNo = (String) JOptionPane.showInputDialog("Please input the coach registration number:\n", null);
                if (regNo == null || regNo.equals(""))
                {
                    return;
                }
                ImageIcon pic = new ImageIcon(new ImageIcon("coach.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
                vehicles[i].modify(numberOfPassengers, tourist, regNo);
            }
            currentTotalAmount += vehicles[i].getAmountCharged();
            totalAmount += vehicles[i].amountCharged;
            revalidate();
            repaint();
        }
    }
    
    /**
     * Finds what mouse button was clicked and pulls an according method.
     * @param e represents the mouse button clicked.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        switch (e.getButton()) {
            case 1:
                displayInformation((JLabel)e.getSource());
                break;
            case 3:
                removeVehicle((JLabel)e.getSource());    
                break;
            case 2:
                modifyVehicle((JLabel)e.getSource());
                break;
            default:
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}
