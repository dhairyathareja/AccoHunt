package accohunt;
import java.util.Scanner;

abstract class Property{

    String location, propName, vendorName;
    void getPropertyDetails(){
        
        Scanner inp = new Scanner(System.in);
        System.out.print("Enter Location of Your Property: ");
        location=inp.nextLine();

        System.out.print("Enter Name of Your Property: ");
        propName=inp.nextLine();

        System.out.print("Enter Vendor of Your Property: ");
        vendorName=inp.nextLine();
    }

    abstract void registerProperty() throws AccoException;
    abstract void unregisterProperty();
    abstract void displayProperty();
}