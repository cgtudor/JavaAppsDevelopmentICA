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
import jdk.nashorn.internal.ir.annotations.Ignore;

/**
 * A panel that holds all the car labels and intercepts all the mouse input related to them.
 * This panel holds all the car labels and all information related to the labels. It intercepts all mouse input and modifies internal data accordingly.
 * @author v8002382
 */
public class CarParkPanel extends JPanel implements MouseListener, java.io.Serializable{
    
    private Car[] cars;
    private int carsNo;
    private JLabel[] carLabels;
    private double totalAmount, currentTotalAmount;
    
    public CarParkPanel() 
    {
        totalAmount = 0;
        currentTotalAmount = 0;
        this.setLayout(new GridLayout(3,4));
        carLabels = new JLabel[12];
        for (int i = 0; i < carLabels.length; i++) 
        {
            JLabel newCar = new JLabel();
            //newCar.setVisible(false);
            carLabels[i] = newCar;
            carLabels[i].addMouseListener(this);
            carLabels[i].setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.BLACK));
            this.add(carLabels[i]);
        }
        cars = new Car[12];
    }
    /**
     * Adds a car to the car array and fills in the first empty space found in the label array.
     * It also adds the charge to the totals.
     * @param car is the car sent from the ButtonPanel. This car is to be added to the car array.
     * @return true if the car park is full and false if it is not.
     */
    public boolean addCar(Car car)
    {
        this.setPreferredSize(new Dimension(400,300));
        if(isFull())
            return false;
        totalAmount += car.getAmountCharged(); 
        currentTotalAmount += car.getAmountCharged();
        int i;
        for (i = 0; i < cars.length; i++) 
        {
            if (cars[i]== null) 
            {
                cars[i] = car;
                break;
            }
        }
        carLabels[i].setIcon(car.getPicture());
        carsNo++;
        this.revalidate();
        return true;
    }
    /**
     * Clears all labels and the car array.
     * Sets all the icons in the label array to null and gets rid of all cars in the car array.
     */
    public void clearAll()
    {   
        for (int i = 0; i < cars.length; i++) 
        {
            cars[i] = null;
            carsNo = 0;
        }
        for (int i = 0; i < carLabels.length; i++)
            carLabels[i].setIcon(null);
        totalAmount = 0;
        currentTotalAmount = 0;
         this.revalidate();
         this.repaint();
    }
    /**
     * Checks the cars number to see if the park is full or not.
     * @return true if the car park is full and false if it is not.
     */
    public boolean isFull()
    {
        if(carsNo == 12)
            return true;
        return false;
    }
    
    public double getTotal()
    {
        return totalAmount;
    }
    
    public double currentTotalAmount()
    {
        return currentTotalAmount;
    }
    
    /**
     * Finds the label clicked and displays information related to it.
     * @param source represents the label clicked
     */
    public void displayInformation(JLabel source)
    {
        for (int i = 0; i < carLabels.length; i++) 
        {
            if (source.equals(carLabels[i])) 
            {
                if (carLabels[i].getIcon() == null) 
                {
                    JOptionPane.showMessageDialog(null, "There is no car parked here");
                    break;
                }
                JOptionPane.showMessageDialog(null, cars[i].toString());
                break;
            }
        }
    }
    
    /**
     * Finds the label clicked, removes its icon and removes the related car from the car array
     * @param source represents the label clicked
     */
    public void removeCar(JLabel source)
    {
        int choice = JOptionPane.showConfirmDialog(null,"Are you sure you want to remove this vehicle?",null, JOptionPane.YES_NO_OPTION);
        if(choice == 0)
        {
            int i;
            for (i = 0; i < carLabels.length; i++)
            {
                if (source.equals(carLabels[i]))
                {
                    break;
                }
            }
            currentTotalAmount -= cars[i].getAmountCharged();
            cars[i] = null;
            carLabels[i].setIcon(null);
            carsNo--;
            revalidate();
            repaint();
        }
    }
    
    /**
     * Finds the label clicked and modifies the details related to it.
     * After it finds the label it asks the user for new information and modifies the details related to that car in the car array.
     * @param source represents the label clicked
     */
    public void modifyCar(JLabel source)
    {
        int choice = JOptionPane.showConfirmDialog(null,"Are you sure you want to modify this vehicle?",null, JOptionPane.YES_NO_OPTION);
        if(choice == 0)
        {
            int i;
            for (i = 0; i < carLabels.length; i++)
            {
                if (source.equals(carLabels[i]))
                {
                    break;
                }
            }
            totalAmount -= cars[i].getAmountCharged();
            currentTotalAmount -= cars[i].getAmountCharged();
            int carL = 0;
            int ok;
            do
            {
                String carLength = (String) JOptionPane.showInputDialog("Please input the car length in meters:\n" ,null);
                if(carLength == null)
                    return;
                ok = 0;
                try
                {
                    carL = Integer.parseInt(carLength);
                    if(carL < 1 || carL > 20)
                    {
                        JOptionPane.showMessageDialog(null, "Car length must be between 1 and 20");
                        ok = 1;
                    }

                } catch(NumberFormatException en)
                {
                    ok = 1;
                }

            } while (ok == 1);
            int badgeHolderTemp = JOptionPane.showConfirmDialog(null, "Is the driver a disabled badge holder?", null, JOptionPane.YES_NO_OPTION);
            boolean badgeHolder;
            if (badgeHolderTemp == 0)
            {
                badgeHolder = true;
            } else
            {
                badgeHolder = false;
            }
            String regNo = (String) JOptionPane.showInputDialog("Please input the car registration number:\n", null);
            if (regNo == null || regNo.equals(""))
            {
                return;
            }
            ImageIcon pic = new ImageIcon(new ImageIcon("car.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
            cars[i].modify(carL, badgeHolder, regNo);
            carLabels[i].setIcon(cars[i].getPicture());
            totalAmount += cars[i].getAmountCharged();
            currentTotalAmount += cars[i].getAmountCharged();
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
                removeCar((JLabel)e.getSource());
                break;
            case 2:
                modifyCar((JLabel)e.getSource());
                break;
            default:
                break;
        }
    }

    @Ignore
    @Override
    public void mousePressed(MouseEvent e) {
    
    }

    @Ignore
    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Ignore
    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Ignore
    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}
