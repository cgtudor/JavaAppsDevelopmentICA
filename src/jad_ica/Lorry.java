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
public class Lorry extends Vehicle implements java.io.Serializable{
    private int weight;
    private boolean allowed;

    public Lorry(int weight, String regNo, ImageIcon picture) 
    {
        super(regNo, picture);
        this.weight = weight;
        allowed = true;
        calculateAmount();
    }
    

    @Override
    protected void calculateAmount() {
        if(weight < 20)
            super.amountCharged = 5;
        else if(weight >= 20 && weight <= 35)
            super.amountCharged = 8;
        else
            allowed = false;
    }
    
    @Override
    public void modify(int weight, String regNo)
    {
        this.weight = weight;
        super.regNo = regNo;
        calculateAmount();
    }
    
    //2 is my choice for a number representing a lorry.
    @Override
    public int type() {
        return 2;
    }
    
    @Override
    public String toString()
    {
        return "The lorry has a weight of " + weight + "\nThe registration number is " + super.regNo + "\nThe amount charged is £" + super.amountCharged;
    }

    @Override
    public void modify(int i, boolean k, String j)
    {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
