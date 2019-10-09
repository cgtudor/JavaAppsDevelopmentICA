/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jad_ica;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author v8002382
 */
public class Car extends Vehicle implements java.io.Serializable{
    private int length;
    private boolean disabledBadge;

    public Car(int length, boolean disabledBadge, String regNo, ImageIcon picture) 
    {
        super(regNo, picture);
        this.length = length;
        this.disabledBadge = disabledBadge;
        calculateAmount();
    }
    @Override
    protected void calculateAmount()
    {
        if(disabledBadge)
            super.amountCharged = 0;
        else if(length > 6)
            super.amountCharged = 1.50;
        else
            super.amountCharged = 1.00;
    }
    
    @Override
    public void modify(int length, boolean disabledBadge, String regNo)
    {
        this.length = length;
        this.disabledBadge = disabledBadge;
        super.regNo = regNo;
        if(length > 6)
            super.picture = new ImageIcon(new ImageIcon("campervan.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        else
            super.picture = new ImageIcon(new ImageIcon("car.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        calculateAmount();
    }
    
    //1 is my choice for a number representing a car
    @Override
    public int type()
    {
        return 1;
    }
    
    @Override
    public String toString()
    {
        if(disabledBadge)
            return "The car has a length of " + length + "\nThe registration number is " + super.regNo + "\nThe owner is a disabled badge holder\nThe amount charged is £" + super.amountCharged;
        else
            return "The car has a length of " + length + "\nThe registration number is " + super.regNo + "\nThe owner is NOT a disabled badge holder\nThe amount charged is £" + super.amountCharged;
    }

    @Override
    public void modify(int i, String j)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
