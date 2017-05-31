package ch.unstable.migrosm.model;

/**
 * Created on 31.05.17.
 */
public class Coordinates {
    public float lot;
    public float lat;

    public Coordinates() {

    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "lot=" + lot +
                ", lat=" + lat +
                '}';
    }
}
