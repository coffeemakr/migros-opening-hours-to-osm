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

    public Market(String name, int id, Location location, String phone, MarketTypes type) {
        this.name = name;
        this.id = id;
        this.location = location;
        this.phone = phone;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getSlug() {
        return slug;
    }

    public MarketTypes getType() {
        return type;
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

    public int getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
