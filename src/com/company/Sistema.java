package com.company;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Sistema {
    private ArrayList<Empregado> empregados;
    int lastDay;
    int firstDay;
    public Sistema (){
        this.empregados = new ArrayList<>();
    }
    public void firstNLastday(){
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("enter the day of the week that the month starts\n" +
                    "sun -- 1 / mon -- 2 / tue -- 3 / wed -- 4 / thu -- 5 / fri -- 6 / sat -- 7\n");
            int day = input.nextInt();
            this.firstDay = day;
            this.lastDay = dayCalc(day);
        }catch (InputMismatchException e){
            firstNLastday();
        }

    }
    public boolean firstDisplay (){
        try {
            boolean exit = false;
            Scanner input = new Scanner(System.in);
            System.out.print("select an operation:\n add an employee -- 1 / remove an employee -- 2 / " +
                    "post a point card -- 3 / post a sale results -- 4 / launch a service fee -- 5 /" +
                    "change an employee's details -- 6 / turn payroll to today -- 7/ Undo/Redo -- 8 / payment schedule -- 9" +
                    "new payment schedule -- 10 / exit -- 11\n");
            int operation = input.nextInt();
            switch (operation) {
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
        }catch (InputMismatchException e){
            return firstDisplay();
        }
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
        try {
            System.out.print("choose a type:\n hourly --1 / salaried --2 / commissioned --3\n");
            Scanner input = new Scanner(System.in);
            int type = input.nextInt();
            switch (type) {
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
        }catch (InputMismatchException e){
            addEmployee();
        }
    }
    private void addHourly(){
        int id = this.empregados.size();
        Horista novoHorista = new Horista(id);
        this.empregados.add(novoHorista);
    }
    private void addSalaried(){
        int id = this.empregados.size();
        Assalariado novoAssalariado = new Assalariado(id);
        this.empregados.add(novoAssalariado);
    }
    private void addCommissioned(){
        int id = this.empregados.size();
        Comissionado novoComissionado = new Comissionado(id);
        this.empregados.add(novoComissionado);
    }
    private void removeEmployee(){
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("insert the employee id\n");
            int id = input.nextInt();
            this.empregados.remove(id);
        }catch (InputMismatchException e){
            removeEmployee();
        }
    }
    private void postPointCard(){
        try {
            System.out.print("insert the hourly id\n");
            Scanner input = new Scanner(System.in);
            int id = input.nextInt();
            Horista aux = (Horista) this.empregados.get(id);
            aux.salary += aux.pointCard.postPC();
            this.empregados.add(id, aux);
        }catch (InputMismatchException e){
            postPointCard();
        }
    }
    private void postSaleResults(){
        try {
            System.out.print("insert the commissioned id\n");
            Scanner input = new Scanner(System.in);
            int id = input.nextInt();
            System.out.print("enter the value of the sale\n");
            double value = input.nextDouble();
            Comissionado aux = (Comissionado) this.empregados.get(id);
            aux.newSale(value);
            this.empregados.add(id, aux);
        }catch (InputMismatchException e){
            postSaleResults();
        }
    }
    private void launchServiceFee(){
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("insert the employee's id\n");
            int id = input.nextInt();
            System.out.print("insert the service fee\n");
            double fee = input.nextDouble();
            if (this.empregados.get(id).syndicate) {
                this.empregados.get(id).serviceRate += fee;
            } else {
                System.out.print("this employee is not a trade unionist\n");
            }
        }catch (InputMismatchException e){
            launchServiceFee();
        }
    }
    private void changeEmployeeDetails(){
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("insert the employee's id\n");
            int id = input.nextInt();
            System.out.print("Do you want to change the type?\n yes -- 1 / no -- 2\n");
            int changeType = input.nextInt();
            int newType;
            if (changeType == 1) {
                System.out.print("choose a type for change:\n hourly --1 / salaried --2 / commissioned --3\n");
                newType = input.nextInt();
                changeType(newType, id);
            }
        }catch (InputMismatchException e){
            changeEmployeeDetails();
        }
    }
    private void changeType(int out, int id){
        switch (out){
            case 1:
                this.empregados.add(id, new Horista(
                        id, this.empregados.get(id).name, this.empregados.get(id).syndicate,
                        this.empregados.get(id).serviceRate, this.empregados.get(id).syndicateRate,
                        this.empregados.get(id).payment));
                break;
            case 2:
                this.empregados.add(id, new Assalariado(
                        id, this.empregados.get(id).name, this.empregados.get(id).syndicate,
                        this.empregados.get(id).serviceRate, this.empregados.get(id).syndicateRate,
                        this.empregados.get(id).payment));
                break;
            case 3:
                this.empregados.add(id, new Comissionado(
                        id, this.empregados.get(id).name, this.empregados.get(id).syndicate,
                        this.empregados.get(id).serviceRate, this.empregados.get(id).syndicateRate,
                        this.empregados.get(id).payment));
                break;
        }
    }
    private void payments(){
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("insert today\n");
            int atualDay = input.nextInt();
            int weekday = weekDay(atualDay);
            int atualWeek = week(atualDay);
            System.out.print("id - name - salary - syndicate rate - payment type\n");
            if (weekday == 6) {
                payHourly();
                if (atualWeek == 2 || atualWeek == 4) {
                    payCommissioned();
                }
            }
            if (atualDay == this.lastDay) {
                payAssalaried();
            }
        }catch (InputMismatchException e){
            payments();
        }
    }
    private int weekDay(int atualDay){
        int dayWeek = (atualDay%7 + (this.firstDay-1))%7;
        if (dayWeek == 0){
            return 7;
        }
        return dayWeek;
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
    private void payHourly() {
        Horista aux = new Horista(-1);
        System.out.print("hourly\n");
        for (Empregado empregado: this.empregados
             ) {
            if(empregado.getClass() == aux.getClass()){
                System.out.printf("%d - %s - %2f - %2f - %s\n", empregado.id, empregado.name,
                        empregado.salary, empregado.syndicateRate, paymentType(empregado.payment));
                empregado.salary = 0;
            }
        }

    }
    private void payCommissioned() {
            System.out.print("commissioned\n");
            Comissionado aux = new Comissionado(-1);
            for (Empregado empregado: this.empregados
            ) {
                if(empregado.getClass() == aux.getClass()){
                    System.out.printf("%d - %s - %2f - %2f - %s\n", empregado.id, empregado.name,
                            empregado.salary, empregado.syndicateRate, paymentType(empregado.payment));
                    empregado.salary = 0;
                }
            }
    }
    private void payAssalaried() {
        Assalariado aux = new Assalariado(-1);
        System.out.print("Assalaried\n");
        for (Empregado empregado: this.empregados
        ) {
            if(empregado.getClass() == aux.getClass()){
                System.out.printf("%d - %s - %2f - %2f - %s\n", empregado.id, empregado.name,
                        empregado.salary, empregado.syndicateRate, paymentType(empregado.payment));
                empregado.salary = 0;
            }
        }
    }
}