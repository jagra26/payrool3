package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Cartao {
    int hours;
    double tax;
    public Cartao(){
        try {
            System.out.print("insert a tax salary/hours\n");
            Scanner input = new Scanner(System.in);
            this.tax = input.nextDouble();
            this.hours = 0;
        }catch (InputMismatchException e){
            new Cartao();
        }
    }
    public double postPC(){
        try {
            System.out.print("enter the number of hours worked\n");
            Scanner input = new Scanner(System.in);
            this.hours = input.nextInt();
            double salary;
            if (this.hours > 8) {
                salary = 1.5 * this.hours * this.tax;
            } else {
                salary = this.hours * this.tax;
            }
            return salary;
        }catch (InputMismatchException e){
            return postPC();
        }
    }
}
