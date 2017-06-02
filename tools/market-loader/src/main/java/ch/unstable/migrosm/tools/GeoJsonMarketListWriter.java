package ch.unstable.migrosm.tools;

import ch.unstable.migrosm.model.Coordinates;
import ch.unstable.migrosm.model.Location;
import ch.unstable.migrosm.model.Market;
import ch.unstable.migrosm.model.osm.OsmNode;
import ch.unstable.migrosm.osm.MarketOsmCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.Point;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;

/**
 * Created on 31.05.17.
 */
public class GeoJsonMarketListWriter implements MarketListWriter {
    private final ObjectMapper objectMapper;
    private final MarketOsmCreator osmMapper;

    public GeoJsonMarketListWriter() {
        objectMapper = new ObjectMapper();
        osmMapper = new MarketOsmCreator();
    }

    @Override
    public void writeMarketList(List<Market> marketList, OutputStream outputStream) throws IOException {
        FeatureCollection features = new FeatureCollection();
        for(Market market: marketList) {
            features.add(convertToFeature(market));
        }
        objectMapper.writeValue(outputStream, features);
    }

    private Feature convertToFeature(Market market) {
        Feature feature = new Feature();
        feature.setId(Integer.toString(market.getId()));
        Coordinates coordinates = market.getLocation().getCoordinates();
        Point point = new Point(coordinates.getLongitude(), coordinates.getLatitude());
        feature.setGeometry(point);

        OsmNode osmNode = osmMapper.convertToOSM(market);
        for (Map.Entry<String, String> tag: osmNode.getTags().entrySet()) {
            feature.setProperty(tag.getKey(), tag.getValue());
        }
        return feature;
    }
}
