package ch.unstable.migrosm.osm;

public enum TagName {
    ADDRESS_ZIP("addr:postalcode"),
    ADDRESS_STREET("addr:street"),
    ADDRESS_HOUSENUMBER("addr:housenumber"),
    NAME("name"),
    ADDRESS_CITY("addr:city"),
    SHOP("shop");

    private final String id;

    TagName(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }

    public String getId() {
        return id;
    }
}
