package com.company;

import java.util.Scanner;

public class Sistema {
    Horista[] horistas;
    Assalariado[] assalariados;
    Comissionado[] comissionados;
    int lastDay;
    public Sistema (){
        this.horistas = new Horista[1000];
        this.assalariados = new Assalariado[1000];
        this.comissionados = new Comissionado[1000];
    }
    public boolean firstDisplay (){
        boolean exit = false;
        Scanner input = new Scanner(System.in);
        System.out.print("enter the day of the week that the month starts\n"+
                "sun -- 1 / mon -- 2 / tue -- 3 / wed -- 4 / thu -- 5 / fri -- 6 / sat -- 7\n");
        this.lastDay = dayCalc(input.nextInt());
        System.out.print("select an operation:\n add an employee -- 1 / remove an employee -- 2 / "+
                "post a point card -- 3 / post a sale results -- 4 / launch a service fee -- 5 /" +
                "change am employee's details -- 6 / turn payroll to today -- 7/ Undo/Redo -- 8 / payment schedule -- 9\n");
        int operation = input.nextInt();
        switch (operation){
            case 1:
                addEmployee();
                break;
            case 2:
                removeEmployee();
                break;
            case 3:
                postPointCard();
                break;
            case 4:
                postSaleResults();
                break;
            case 5:
                launchServiceFee();
                break;
            case 11:
                exit = true;
                break;
        }
        return exit;
    }
    public int dayCalc(int day){
        int lastDay;
        if (day == 7){
            lastDay = 1;
        }else{
            lastDay = day +1;
        }
        if (lastDay == 1){
            lastDay = 28;
        }else if (lastDay == 7){
            lastDay = 29;
        }else{
            lastDay = 30;
        }
        return lastDay;
    }
    public void addEmployee(){
        System.out.print("choose a type:\n hourly --1 / salaried --2 / commissioned --3\n");
        Scanner input = new Scanner(System.in);
        int type = input.nextInt();
        switch (type){
            case 1:
                addHourly();
                break;
            case 2:
                addSalaried();
                break;
            case 3:
                addCommissioned();
                break;
        }
    }
    public void addHourly(){
        for (int i = 0; i<1000; i++){
            if (this.horistas[i] == null){
                this.horistas[i] =  new Horista(i);
                System.out.printf("the employee id is %d", i);
                break;
            }
        }
    }
    public void addSalaried(){
        for (int i = 0; i < 1000; i++){
            if (this.assalariados[i] == null){
                this.assalariados[i] = new Assalariado(i);
                System.out.printf("the employee id is %d", i);
                break;
            }
        }
    }
    public void addCommissioned(){
        for (int i = 0; i < 1000; i++){
            if (this.comissionados[i] == null){
                this.comissionados[i] = new Comissionado(i);
                System.out.printf("the employee id is %d", i);
                break;
            }
        }
    }
    public void removeEmployee(){
        System.out.print("choose a type:\n hourly --1 / salaried --2 / commissioned --3\n");
        Scanner input = new Scanner(System.in);
        int type = input.nextInt();
        System.out.print("insert the employee id\n");
        int id = input.nextInt();
        switch (type){
            case 1:
                this.horistas[id] = null;
                break;
            case 2:
                this.assalariados[id] = null;
                break;
            case 3:
                this.comissionados[id] = null;
                break;
        }
    }
    public void postPointCard(){
        System.out.print("insert the hourly id\n");
        Scanner input = new Scanner(System.in);
        int id = input.nextInt();
        this.horistas[id].salary += this.horistas[id].pointCard.postPC();
    }
    public void postSaleResults(){
        System.out.print("insert the commissioned id\n");
        Scanner input = new Scanner(System.in);
        int id = input.nextInt();
        System.out.print("enter the value of the sale\n");
        double value = input.nextDouble();
        this.comissionados[id].newSale(value);
    }
    public void launchServiceFee(){
        System.out.print("choose a type:\n hourly --1 / salaried --2 / commissioned --3\n");
        Scanner input = new Scanner(System.in);
        int type = input.nextInt();
        System.out.print("insert the employee's id\n");
        int id = input.nextInt();
        System.out.print("insert the service fee\n");
        double fee = input.nextDouble();
        switch (type){
            case 1:
                if(this.horistas[id].syndicate){
                    this.horistas[id].serviceRate += fee;
                }else{
                    System.out.print("this employee is not a trade unionist\n");
                }
                break;
            case 2:
                if(this.assalariados[id].syndicate){
                    this.assalariados[id].serviceRate += fee;
                }else{
                    System.out.print("this employee is not a trade unionist\n");
                }
                break;
            case 3:
                if (this.comissionados[id].syndicate){
                    this.comissionados[id].serviceRate += fee;
                }else{
                    System.out.print("this employee is not a trade unionist\n");
                }
                break;
        }
    }
}
