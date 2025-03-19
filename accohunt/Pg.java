package accohunt;

import java.util.Scanner;

class Pg extends Property{
    
    int price, available;

    void registerProperty() throws AccoException{
        
        getPropertyDetails();

        Scanner inp = new Scanner(System.in);
        
        System.out.print("Enter Price of Your Property: ");
        price=inp.nextInt();
        inp.nextLine();

        System.out.print("Enter Number of Avaialble PG: ");
        available=inp.nextInt();
        inp.nextLine();

        if(available==0 || price==0){
            throw new AccoException("Inavlid Details...");
        }
        else{
            System.err.println("Your PG Has Been Registered successfully with following details:- ");
        }
    }

    void displayProperty(){
        System.out.println("Name:- "+propName);
        System.out.println("Available:- "+available);
    }

    void unregisterProperty(){
        location="";
        propName="";
        vendorName="";
        price=0;
        available=0;
    }
}