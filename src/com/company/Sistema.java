package com.company;

import java.util.Scanner;

public class Sistema {
    Horista[] horistas;
    Assalariado[] assalariados;
    Comissionado[] comissionados;
    int lastDay;
    int firstDay
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
        int day = input.nextInt();
        this.firstDay = day;
        this.lastDay = dayCalc(day);
        System.out.print("select an operation:\n add an employee -- 1 / remove an employee -- 2 / "+
                "post a point card -- 3 / post a sale results -- 4 / launch a service fee -- 5 /" +
                "change an employee's details -- 6 / turn payroll to today -- 7/ Undo/Redo -- 8 / payment schedule -- 9\n");
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
            case 6:
                changeEmployeeDetails();
                break;
            case 7:
                payments();
                break;
            case 11:
                exit = true;
                break;
        }
        return exit;
    }
    private int dayCalc(int day){
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
    private void addEmployee(){
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
    private void addHourly(){
        for (int i = 0; i<1000; i++){
            if (this.horistas[i] == null){
                this.horistas[i] =  new Horista(i);
                System.out.printf("the employee id is %d", i);
                break;
            }
        }
    }
    private void addSalaried(){
        for (int i = 0; i < 1000; i++){
            if (this.assalariados[i] == null){
                this.assalariados[i] = new Assalariado(i);
                System.out.printf("the employee id is %d", i);
                break;
            }
        }
    }
    private void addCommissioned(){
        for (int i = 0; i < 1000; i++){
            if (this.comissionados[i] == null){
                this.comissionados[i] = new Comissionado(i);
                System.out.printf("the employee id is %d", i);
                break;
            }
        }
    }
    private void removeEmployee(){
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
    private void postPointCard(){
        System.out.print("insert the hourly id\n");
        Scanner input = new Scanner(System.in);
        int id = input.nextInt();
        this.horistas[id].salary += this.horistas[id].pointCard.postPC();
    }
    private void postSaleResults(){
        System.out.print("insert the commissioned id\n");
        Scanner input = new Scanner(System.in);
        int id = input.nextInt();
        System.out.print("enter the value of the sale\n");
        double value = input.nextDouble();
        this.comissionados[id].newSale(value);
    }
    private void launchServiceFee(){
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
    private void changeEmployeeDetails(){
        System.out.print("choose a type:\n hourly --1 / salaried --2 / commissioned --3\n");
        Scanner input = new Scanner(System.in);
        int type = input.nextInt();
        System.out.print("insert the employee's id\n");
        int id = input.nextInt();
        System.out.print("Do you want to change the type?\n yes -- 1 / no -- 2\n");
        int changeType = input.nextInt();
        int newType;
        if (changeType == 1){
            System.out.print("choose a type for change:\n hourly --1 / salaried --2 / commissioned --3\n");
            newType = input.nextInt();
            changeType(type, newType, id);
        }else {
            switch (type) {
                case 1:
                    this.horistas[id] = new Horista(id);
                    break;
                case 2:
                    this.assalariados[id] = new Assalariado(id);
                    break;
                case 3:
                    this.comissionados[id] = new Comissionado(id);
                    break;
            }
        }
    }
    private void changeType(int in, int out, int id){
        switch (in){
            case 1:
                switch (out){
                    case 2:
                        for (int i = 0; i<1000; i++){
                            if (this.assalariados[i] == null){
                                this.assalariados[i] = new Assalariado(i, this.horistas[id].name,
                                        this.horistas[id].syndicate, this.horistas[id].serviceRate,
                                        this.horistas[id].syndicateRate, this.horistas[id].payment);
                                this.horistas[id] = null;
                                break;
                            }
                        }
                        break;
                    case 3:
                        for (int i = 0; i<1000; i++){
                            if (this.comissionados[i] == null){
                                this.comissionados[i] = new Comissionado(i, this.horistas[id].name,
                                        this.horistas[id].syndicate, this.horistas[id].serviceRate,
                                        this.horistas[id].syndicateRate, this.horistas[id].payment);
                                this.horistas[id] = null;
                                break;
                            }
                        }
                        break;
                }
                break;
            case 2:
                switch (out){
                    case 1:
                        for (int i = 0; i<1000; i++){
                            if (this.horistas[i] == null){
                                this.horistas[i] = new Horista(i, this.assalariados[id].name,
                                        this.assalariados[id].syndicate, this.assalariados[id].serviceRate,
                                        this.assalariados[id].syndicateRate, this.assalariados[id].payment);
                                this.assalariados[id] = null;
                                break;
                            }
                        }
                        break;
                    case 3:
                        for (int i = 0; i<1000; i++){
                            if (this.comissionados[i] == null){
                                this.comissionados[i] = new Comissionado(i, this.assalariados[id].name,
                                        this.assalariados[id].syndicate, this.assalariados[id].serviceRate,
                                        this.assalariados[id].syndicateRate, this.assalariados[id].payment);
                                this.assalariados[id] = null;
                                break;
                            }
                        }
                        break;
                }
                break;
            case 3:
                switch (out){
                    case 1:
                        for (int i = 0; i<1000; i++){
                            if (this.horistas[i] == null){
                                this.horistas[i] = new Horista(i, this.comissionados[id].name,
                                        this.comissionados[id].syndicate, this.comissionados[id].serviceRate,
                                        this.comissionados[id].syndicateRate, this.comissionados[id].payment);
                                this.comissionados[id] = null;
                                break;
                            }
                        }
                        break;
                    case 2:
                        for (int i = 0; i<1000; i++){
                            if (this.assalariados[i] == null){
                                this.assalariados[i] = new Assalariado(i, this.comissionados[id].name,
                                        this.comissionados[id].syndicate, this.comissionados[id].serviceRate,
                                        this.comissionados[id].syndicateRate, this.comissionados[id].payment);
                                this.comissionados[id] = null;
                                break;
                            }
                        }
                        break;
                }
                break;
        }
    }
    private void payments(){
        Scanner input = new Scanner(System.in);
        System.out.print("insert today\n");
        int atualDay = input.nextInt();
        int weekday = weekDay(atualDay);
        int atualWeek = week(atualDay);
        if (weekday == 6){
            payHourly();
            if (atualWeek == 2 || atualWeek == 4){
                payCommissioned();
            }
        }
        if (atualDay == this.lastDay){
            payAssalaried();
        }

    }
    private int weekDay(int atualDay){
        return (atualDay%7 + (this.firstDay-1))%7;
    }
    private int week(int atualDay){
        if(atualDay <= 7){
            return 1;
        }else if (atualDay > 7 && atualDay <=14){
            return 2;
        }else if (atualDay > 14 && atualDay <=21){
            return 3;
        }else {
            return 4;
        }
    }
    private  String paymentType(int type){
        if (type == 1){
            return "bank deposit";
        }else if (type == 2){
            return "check in hand";
        }else{
            return "check by mail";
        }
    }
    private void payHourly(){
        System.out.print("id - name - salary - syndicate rate - payment type\n");
        for (int i = 0; i< 1000; i++){
            if (this.horistas[i] != null){
                System.out.printf("%d - %s - %2f - %2f - %s\n", i, this.horistas[i].name,
                        this.horistas[i].salary, this.horistas[i].syndicateRate, paymentType(this.horistas[i].payment));
                this.horistas[i].salary = 0;
            }
        }
    }
    private void payCommissioned(){
        System.out.print("id - name - salary - syndicate rate - payment type\n");
        for (int i = 0; i< 1000; i++){
            if (this.comissionados[i] != null){
                System.out.printf("%d - %s - %2f - %2f - %s\n", i, this.horistas[i].name,
                        this.comissionados[i].salary, this.comissionados[i].syndicateRate, paymentType(this.comissionados[i].payment));
                this.comissionados[i].salary = this.comissionados[i].fixedSalary;
            }
        }
    }
    private void payAssalaried(){
        System.out.print("id - name - salary - syndicate rate - payment type\n");
        for (int i = 0; i< 1000; i++){
            if (this.assalariados[i] != null){
                System.out.printf("%d - %s - %2f - %2f - %s\n", i, this.horistas[i].name,
                        this.assalariados[i].salary, this.assalariados[i].syndicateRate, paymentType(this.assalariados[i].payment));
            }
        }
    }
}