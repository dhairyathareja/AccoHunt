package model;

public class Accommodation {
    public String id;
    public String name;
    public String type; // hostel, pg, flat
    public String address;

    public Accommodation(String name, String type, String address) {
        this.name = name;
        this.type = type;
        this.address = address;
    }
}
