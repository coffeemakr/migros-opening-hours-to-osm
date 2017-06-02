package ch.unstable.migrosm.model;

/**
 * Created on 31.05.17.
 */
public class Coordinates {
    public double lon;
    public double lat;

    public Coordinates() {

    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "lot=" + lon +
                ", lat=" + lat +
                '}';
    }

    public double getLongitude() {
        return lon;
    }

    public double getLatitude() {
        return lat;
    }
}
