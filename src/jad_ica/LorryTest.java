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
public class LorryTest {
    public static void main(String[] args)
    {
        ImageIcon lorry = new ImageIcon("lorry.png");
        Lorry newLorry = new Lorry(32, "B764GAS", lorry);
        System.out.println(newLorry.toString());
        System.out.println("\nThe amount charged is: £" + newLorry.getAmountCharged());
    }
}
