package com.company;

import java.util.Scanner;

public class Assalariado {
    String name;
    int id;
    double salary;
    boolean syndicate;
    public Assalariado(int id){
        System.out.print("insert the employee name\n");
        Scanner input = new Scanner(System.in);
        this.name = input.nextLine();
        this.id = id;
        System.out.print("insert the monthly salary");
        this.salary = input.nextDouble();
        System.out.print("unionist? yes -- 1 / no -- 2 \n ");
        int unionist = input.nextInt();
        if (unionist == 1){
            this.syndicate = true;
        }else{
            this.syndicate = false;
        }
    }
}
