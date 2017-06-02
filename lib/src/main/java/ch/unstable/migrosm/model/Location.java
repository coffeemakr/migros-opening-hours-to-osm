package ch.unstable.migrosm.model;


public class Location {
    private String address;
    private String city;
    private String country;
    private Coordinates geo;
    private double coordinates;
    private int zip;

    private Location() {
        // Empty constructor for gson
    }

    @Override
    public String toString() {
        return "Location{" +
                "address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", geo=" + geo +
                '}';
    }

    public Coordinates getCoordinates() {
        return geo;
    }

    public String getStreet() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public int getZIP() {
        return zip;
    }
}
