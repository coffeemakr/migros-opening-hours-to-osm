package ch.unstable.migrosm.model.osm;

import ch.unstable.migrosm.model.Coordinates;
import ch.unstable.migrosm.osm.TagName;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public abstract class OsmObject {
    private String id;
    private Coordinates coordinates;
    private final Map<String, String> tags = new HashMap<>();

    public OsmObject(Coordinates coordinates) {
        this.coordinates = coordinates;
        this.id = null;
    }

    public Map<String, String> getTags() {
        return Collections.unmodifiableMap(tags);
    }


    public void setTag(String name, String value) {
        tags.put(name, value);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
