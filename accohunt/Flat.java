package accohunt;

import java.util.Scanner;

class Flat extends Property{
    
    int price, flatType, available;

    void registerProperty() throws AccoException{
        
        getPropertyDetails();
        
        Scanner inp = new Scanner(System.in);

        System.out.print("Enter Price of Your Flat: ");
        price=inp.nextInt();
        inp.nextLine();

        System.out.print("Enter Flat Type: ");
        flatType=inp.nextInt();
        inp.nextLine();

        System.out.print("Enter Number of Avaialble Flats: ");
        available=inp.nextInt();
        inp.nextLine();

        if(available==0 || flatType==0 || price==0){
            throw new AccoException("Inavlid Details...");
        }
        else{
            System.err.println("Your Flat Has Been Registered successfully with following details:- ");
        }
    }

    void displayProperty(){
        System.out.println("Name:- "+propName);
        System.out.println("Flat Type:- "+flatType);
        System.out.println("Available:- "+available);
    }

    void unregisterProperty(){
        location="";
        propName="";
        vendorName="";
        price=0;
        flatType=0;
        available=0;
    }
}