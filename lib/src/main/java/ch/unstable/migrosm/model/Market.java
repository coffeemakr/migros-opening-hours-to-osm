package ch.unstable.migrosm.model;

/**
 * Created on 31.05.17.
 */
public class Market {
    private String name;
    private int id;
    private Location location;
    private String phone;
    private String slug;
    private MarketTypes type;

    private  Market() {
        // Empty constructor for gson
    }

    @Override
    public String toString() {
        return "Market{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", location=" + location +
                ", phone='" + phone + '\'' +
                ", slug='" + slug + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
