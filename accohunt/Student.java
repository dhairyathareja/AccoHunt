package accohunt;

import java.rmi.AccessException;
import java.util.Scanner;


class Student extends User implements Bookable{
    
    Boolean bookingStatus=false;
    int age;
    char gender;
    String university;
    int contact;
    String roomType;

    Scanner inp= new Scanner(System.in);

    public Student(String email, String name, String username, int age, char gender, String university, int contact, String roomType) {
        this.age = age;
        this.gender = gender;
        this.university = university;
        this.contact = contact;
        this.roomType = roomType;
    }


    public void bookAcc() throws AccoException{ 
        
        String accoType,selectedName;
        System.out.println("Accomodation Available:- Flat Hostel PG");
        System.out.print("Enter Accomodation You Want to Book:- ");
        accoType=inp.nextLine();

        System.out.print("Enter PropertyName You Want to Book:- ");
        selectedName=inp.nextLine();

        switch(accoType){
            case "Flat":
                for (int i = 0; i < flatIndex; i++) {
                    if(selectedName.equals(flatArr[i].propName)){
                        flatArr[i].available-=1;
                    }
                }
                break;
            case "Hostel":
                for (int i = 0; i < hostelIndex; i++) {
                    if(selectedName.equals(hostelArr[i].propName)){
                        hostelArr[i].available-=1;
                    }
                }
                break;
            case "PG":
                for (int i = 0; i < pgIndex; i++) {
                    if(selectedName.equals(pgArr[i].propName)){
                        pgArr[i].available-=1;
                    }
                }
                break;
            default:
                throw new AccoException("Invalid Input");
        }
        
    }
    

    public void searchAcc() throws AccoException{
        String accoType;
        System.out.println("Accomodation Available:- Flat Hostel PG");
        System.out.print("Enter Accomodation You Want to Seach:- ");
        accoType=inp.nextLine();

        switch(accoType){
            case "Flat":
                for (int i = 0; i < flatIndex; i++) {
                    flatArr[i].displayProperty();
                }
                break;
            case "Hostel":
                for (int i = 0; i < hostelIndex; i++) {
                    hostelArr[i].displayProperty();
                }
                break;
            case "PG":
                for (int i = 0; i < pgIndex; i++) {
                    pgArr[i].displayProperty();
                }
                break;
            default:
                throw new AccoException("Invalid Input");
        }
        
    }

    public void scheduleVisit(){} //Implemented through DB;

}