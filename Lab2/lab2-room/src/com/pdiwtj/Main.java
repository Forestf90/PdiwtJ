package com.pdiwtj;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Room bedroom = new Room("sypialnia", 4,5,2,3.5f,2.2f);
        Room kitchen = new Room("kuchnia", 5, 7, 3, 12.5f, 4.7f);
        Room salon = new Room("salon", 10, 12, 3, 8.4f, 7.1f);


        bedroom.display();
        kitchen.display();
        salon.display();


    try{
        Scanner input = new Scanner(System.in);

        input.useLocale(Locale.US);
        System.out.println("Enter name of room:");
        String name = input.nextLine();
        System.out.println("Enter x value:");
        int x = input.nextInt();
        if(x<=0) throw new Exception("wartosc musi byc wieksza od zera");
        System.out.println("Enter y value:");
        int y = input.nextInt();
        if(y<=0) throw new Exception("wartosc musi byc wieksza od zera");
        System.out.println("Enter z value:");
        int z = input.nextInt();
        if(z<=0) throw new Exception("wartosc musi byc wieksza od zera");
        System.out.println("Enter floor cost:");
        float floor = input.nextFloat();
        if(floor<=0) throw new Exception("wartosc musi byc wieksza od zera");
        System.out.println("Enter wall cost:");
        float wall = input.nextFloat();
        if(wall<=0) throw new Exception("wartosc musi byc wieksza od zera");

        Room temp = new Room(name,x,y,z,floor,wall);
        temp.display();

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("file.txt"));
            writer.write(temp.getName());
            writer.newLine();
            writer.write("Wymiary: "+temp.getX()+"x"+temp.getY()+"x"+temp.getZ());
            writer.newLine();
            writer.write("Objetosc: "+temp.obj());
            writer.newLine();
            writer.write("Powierzchnia scian: "+temp.wallObj()+" m2");
            writer.newLine();
            writer.write("Powierzchnia podlogi: "+temp.floorObj()+" m2");
            writer.newLine();
            writer.write("Cena wykonania podlogi: "+temp.floorPrice()+" zl");
            writer.newLine();
            writer.write("Cena wykonania sciany: "+ temp.wallPrice()+" zl");

            writer.close();
        }catch(IOException io){
            System.out.println(io.getStackTrace());
        }


    }catch(Exception ex){
        System.out.println(ex.getMessage());
        System.out.println("Invalid data - abording .....");
    }



    }
}
