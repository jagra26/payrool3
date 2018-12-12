package com.company;

public class Main {

    public static void main(String[] args) {
	Sistema payrool = new Sistema();
	payrool.firstNLastday();
	boolean exit = false;
	while (!exit){
	    exit = payrool.firstDisplay();
    }
    }
}
