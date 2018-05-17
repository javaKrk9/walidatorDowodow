package com.company;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dowod {

    final char letters[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    final int wagi[]= {7, 3, 1, 9, 7, 3, 1, 7, 3};

    public Dowod()
    {

    }

    public boolean sprawdz(String numerDowodu)
    {
        if(!sprawdzIloscZnakow(numerDowodu))return false;

        numerDowodu = numerDowodu.toUpperCase();
        if(!sprawdzWzor(numerDowodu))return false;

        if(!sprawdzSumeKontrolna(numerDowodu))return false;
        else return true;

    }

    private boolean sprawdzIloscZnakow(String numerDowodu)
    {
        if(numerDowodu.length()!=9)
        {
            System.out.println("Nieprawidłowa ilość znaków");
            return false;
        }
        else
        {
            System.out.println("Prawidłowa ilość znaków");
            return true;
        }
    }

    private boolean sprawdzWzor(String numerDowodu)
    {
        String pattern = "^[A-Z]{3}[0-9]{6}$";
        Pattern patternCompiled = Pattern.compile(pattern);
        Matcher m = patternCompiled.matcher(numerDowodu);
        if(!m.matches())
        {
            System.out.println("Nie zgadza się proporcja cyfr i liter");
            return false;
        }
        else
        {
            System.out.println("Zgadza się proporcja cyfr i liter");
            return true;
        }
    }

    private boolean sprawdzSumeKontrolna(String numerDowodu)
    {
        int sumaKontrolna = 0;

        for(int i=0; i<3; i++)
        {
            sumaKontrolna+=charToInt(numerDowodu.charAt(i))*wagi[i];
        }
        for(int i=4; i<numerDowodu.length(); i++)
        {
            if(Character.isDigit(numerDowodu.charAt(i))) {
                sumaKontrolna +=Character.getNumericValue(numerDowodu.charAt(i))*wagi[i];
            }
            else
            {
                return false;
            }
        }

        if(sumaKontrolna%10==Character.getNumericValue(numerDowodu.charAt(3)))
        {
            System.out.println("Cyfra kontrolna się zgadza!");
            sumaKontrolna +=Character.getNumericValue(numerDowodu.charAt(3))*wagi[3];
        }
        else
        {
            System.out.println("Cyfra kontrolna się nie zgadza!");
            return false;
        }

        if(sumaKontrolna%10==0)
        {
            System.out.println("Suma kontrolna się zgadza!");
            return true;
        }
        else
        {
            System.out.println("Suma kontrolna się nie zgadza!");
            return false;
        }
    }

    private int charToInt(char letter)
    {
        for(int i=0; i<letters.length; i++)
        {
            if(letters[i]==letter)
            {
                return i;
            }
        }
        return -1;
    }


    public String generujNumerDowodu()
    {
        //String nowyNumerDowodu = "A";
        char [] nowyNumerDowoduChars = new char[9];
        nowyNumerDowoduChars[0] = 'A';
        //nowyNumerDowodu+=generujZnak('X','Z');
        nowyNumerDowoduChars[1] = generujZnak('X','Z');

        if(nowyNumerDowoduChars[1]=='X')
            nowyNumerDowoduChars[2] = generujZnak('I','Z');
        else
            nowyNumerDowoduChars[2] = generujZnak('A','Z');

        for(int i=4; i<nowyNumerDowoduChars.length-1; i++)
        nowyNumerDowoduChars[i] = generujZnak('0','9');

        nowyNumerDowoduChars[8] = generujZnak('1','9');

        nowyNumerDowoduChars[3] = generujCyfręKontrolną(nowyNumerDowoduChars);


        return tabCharToString(nowyNumerDowoduChars);
    }

    private String tabCharToString(char[] tabChar)
    {
        String returnString="";

        for(int i=0; i<tabChar.length;i++)
        {
            returnString+=tabChar[i];
        }
        return returnString;
    }

    private char generujCyfręKontrolną(char[] tabChar)
    {
        int sumaKontrolna=0;
        int cyfraKontrolna=0;
        for(int i=0; i<tabChar.length; i++)
        {
            if(i!=3) 
            {
                sumaKontrolna += charToInt(tabChar[i]) * wagi[i];
            }
        }
        for(int i=0; i<10; i++)
        {
            if((sumaKontrolna+i*9)%10==0)
            {
                cyfraKontrolna=i;
                break;
            }
        }

        System.out.println("Cyfra kontrolna = "+cyfraKontrolna+"; Suma kontrolna = "+sumaKontrolna);
        return letters[cyfraKontrolna];
    }

    private char generujZnak(char min, char max)
    {
        int numerNowegoZnaku= generujInt(charToInt(min), charToInt(max));
        return letters[numerNowegoZnaku];
    }

    private int generujInt(int min, int max)
    {
        Random rand = new Random();
        int  n = rand.nextInt((max+1-min))+min;
        return n;
    }

}
