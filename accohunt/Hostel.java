package accohunt;

import java.util.Scanner;

class Hostel extends Property{
    
    String location, propName, vendorName;
    int price, available;

    void getPropertyDetails(){
        
    }

    void registerProperty() throws AccoException{
        
        getPropertyDetails();

        Scanner inp = new Scanner(System.in);
        
        System.out.print("Enter Price of Your Property: ");
        price=inp.nextInt();
        inp.nextLine();

        System.out.print("Enter Number of Avaialble Rooms: ");
        available=inp.nextInt();
        inp.nextLine();

        if(available==0 || price==0){
            throw new AccoException("Inavlid Details...");
        }
        else{
            System.err.println("Your Hostel Has Been Registered successfully with following details:- ");
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