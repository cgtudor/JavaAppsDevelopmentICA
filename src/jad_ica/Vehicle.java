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
public abstract class Vehicle implements java.io.Serializable{
    protected String regNo;
    protected double amountCharged;
    protected ImageIcon picture;

    public Vehicle(String regNo, ImageIcon picture) 
    {
        this.regNo = regNo;
        this.amountCharged = 0;
        this.picture = picture;
    }

    public Vehicle() 
    {
        this.regNo = "";
        this.amountCharged = 0;
        this.picture = null;
    }

    public String getRegNo() {
        return regNo;
    }

    public double getAmountCharged() {
        return amountCharged;
    }

    public Vehicle(ImageIcon picture) {
        this.picture = picture;
    }
    
    protected abstract void calculateAmount();
    public abstract int type();
    public abstract void modify(int i, String j);
    public abstract void modify(int i, boolean k, String j);

    public ImageIcon getPicture() {
        return picture;
    }
    
}
