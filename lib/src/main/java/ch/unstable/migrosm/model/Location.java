package ch.unstable.migrosm.model;


public class Location {
    private String address;
    private String city;
    private String country;
    private Coordinates geo;

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
}
