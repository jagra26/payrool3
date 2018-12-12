package com.company;

import java.util.Scanner;

public class Assalariado {
    String name;
    int id;
    double salary;
    boolean syndicate;
    double syndicateRate;
    double serviceRate;
    int payment;
    public Assalariado(int id){
        System.out.print("insert the employee name\n");
        Scanner input = new Scanner(System.in);
        this.name = input.nextLine();
        this.id = id;
        System.out.print("insert the monthly salary\n");
        this.salary = input.nextDouble();
        System.out.print("unionist? yes -- 1 / no -- 2 \n ");
        int unionist = input.nextInt();
        if (unionist == 1){
            this.syndicate = true;
            System.out.print("insert the syndicate rate\n");
            this.syndicateRate = input.nextDouble();
        }else{
            this.syndicate = false;
            this.syndicateRate = 0;
        }
        this.salary = 0;
        this.serviceRate = 0;
        System.out.print("choose a method of payment:\n" +
                "bank deposit -- 1 / check in hand -- 2 / check by mail -- 3\n");
        this.payment = input.nextInt();
    }
    public Assalariado(int id, String name, boolean syndicate, double serviceRate, double syndicateRate, int payment){
        Scanner input = new Scanner(System.in);
        System.out.print("insert the monthly salary");
        this.salary = input.nextDouble();
        this.id = id;
        this.name = name;
        this.syndicate = syndicate;
        this.serviceRate = serviceRate;
        this.syndicateRate = syndicateRate;
        this.payment = payment;
    }
}
