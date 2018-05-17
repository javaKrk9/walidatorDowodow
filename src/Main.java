package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here

        System.out.println("Wpisz numer dowodu:");
        Scanner scanner = new Scanner(System.in);

        //String numerDowodu = scanner.next();

        Dowod dowod = new Dowod();
        
        String numerDowodu = dowod.generujNumerDowodu();

        System.out.println(numerDowodu);

        Boolean autentyczny = dowod.sprawdz(numerDowodu);

        if(autentyczny)
            System.out.println("Dowód jest autentyczny");
        else
            System.out.println("Dowód jest fałszywy");

    }
}
