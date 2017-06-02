package ch.unstable.migrosm.osm;

import ch.unstable.migrosm.model.osm.OsmNode;

/**
 * Created on 31.05.17.
 */
public interface OsmCreator<T> {
    OsmNode convertToOSM(T value);
}
