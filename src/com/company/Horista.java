package com.company;

import java.util.Scanner;

public class Horista {
    String name;
    int id;
    Cartao pointCard;
    boolean syndicate;
    double salary;
    public Horista(int id){
        System.out.print("insert the employee name\n");
        Scanner input = new Scanner(System.in);
        this.name = input.nextLine();
        this.id = id;
        this.pointCard = new Cartao();
        System.out.print("unionist? yes -- 1 / no -- 2 \n ");
        int unionist = input.nextInt();
        if (unionist == 1){
            this.syndicate = true;
        }else{
            this.syndicate = false;
        }
        this.salary = 0;
    }
}
