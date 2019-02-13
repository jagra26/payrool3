package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Comissionado extends Empregado {
    double fixedSalary;
    double rate;
    public Comissionado(int id){
        try {
            System.out.print("insert the employee name\n");
            Scanner input = new Scanner(System.in);
            this.name = input.nextLine();
            this.id = id;
            System.out.print("insert the fortnightly wage\n");
            this.fixedSalary = input.nextDouble();
            System.out.print("enter commission rate %\n");
            this.rate = input.nextDouble() / 100;
            System.out.print("unionist? yes -- 1 / no -- 2 \n ");
            int unionist = input.nextInt();
            if (unionist == 1) {
                this.syndicate = true;
                System.out.print("insert the syndicate rate\n");
                this.syndicateRate = input.nextDouble();
            } else {
                this.syndicate = false;
                this.syndicateRate = 0;
            }
            this.serviceRate = 0;
            this.salary = this.fixedSalary;
            System.out.print("choose a method of payment:\n" +
                    "bank deposit -- 1 / check in hand -- 2 / check by mail -- 3\n");
            this.payment = input.nextInt();
        }catch (InputMismatchException e){
            new Comissionado(id);
        }
    }
    public Comissionado(int id, String name, boolean syndicate, double serviceRate, double syndicateRate, int payment){
        Scanner input = new Scanner(System.in);
        System.out.print("insert the fortnightly wage\n");
        this.fixedSalary = input.nextDouble();
        System.out.print("enter commission rate %\n");
        this.rate = input.nextDouble()/100;
        this.id = id;
        this.name = name;
        this.syndicate = syndicate;
        this.syndicateRate = syndicateRate;
        this.serviceRate = serviceRate;
        this.salary = this.fixedSalary;
        this.payment = payment;
    }
    public void newSale(double value){
        this.salary += value*this.rate;
    }
}
