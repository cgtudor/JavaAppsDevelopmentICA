/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jad_ica;

import javax.swing.ImageIcon;

/**
 *
 * @author v8002382
 */
public class Coach extends Vehicle implements java.io.Serializable{
    private int passengers;
    private boolean tourist;

    public Coach(int passengers, boolean tourist, String regNo, ImageIcon picture) 
    {
        super(regNo, picture);
        this.passengers = passengers;
        this.tourist = tourist;
        calculateAmount();
    }

    
    @Override
    protected void calculateAmount() 
    {
        double discount;
        if(passengers <= 20)
        {
            super.amountCharged = 4.50;
            discount = 0.45;
        }
        else
        {
            super.amountCharged = 6;
            discount = 0.6;
        }
        if(tourist)
        {
            super.amountCharged -= discount;
        }
    }
    
    @Override
    public void modify(int passengers, boolean tourist, String regNo)
    {
        this.passengers = passengers;
        this.tourist = tourist;
        super.regNo = regNo;
        calculateAmount();
    }
    
    //3 is my choice for a number representing a coach
    @Override
    public int type()
    {
        return 3;
    }
    
    @Override
    public String toString()
    {
        if(tourist)
            return "The coach has " + passengers + " passengers" + "\nThe registration number is " + super.regNo + "\nThe coach has a tourist pass" + "\nThe amount charged is £" + super.amountCharged;
        else
            return "The coach has " + passengers + " passengers" + "\nThe registration number is " + super.regNo + "\nThe coach DOESN'T have a tourist pass" + "\nThe amount charged is £" + super.amountCharged;
    }

    @Override
    public void modify(int i, String j)
    {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
