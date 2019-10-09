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
public class CarTest {
    public static void main(String[] args)
    {
        ImageIcon carPNG = new ImageIcon("car.png");
        Car newCar = new Car(6, false, "B40ABG", carPNG);
        System.out.println(newCar.toString());
        System.out.println("\nThe amount charged is: £"+ newCar.getAmountCharged());
    }
}
