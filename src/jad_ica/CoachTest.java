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
public class CoachTest {
    public static void main(String[] args)
    {
        ImageIcon coach = new ImageIcon("coach.png");
        Coach newCoach = new Coach(32, true, "B654NSK", coach);
        System.out.println(newCoach.toString());
        System.out.println("\nThe amount charged is: £" + newCoach.getAmountCharged());
    }
}
