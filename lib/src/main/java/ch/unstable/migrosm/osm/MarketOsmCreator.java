package ch.unstable.migrosm.osm;

import ch.unstable.migrosm.model.Location;
import ch.unstable.migrosm.model.Market;
import ch.unstable.migrosm.model.osm.OsmNode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Convert a market to a OSM-Node.
 */
public class MarketOsmCreator implements OsmCreator<Market> {

    private void setStreetAndNumber(OsmNode node, String streetAndNumber) {
        Pattern streetAndNumberPattern = Pattern.compile("^(\\S+)\\s+(\\d+)$");
        Matcher matcher = streetAndNumberPattern.matcher(streetAndNumber);
        if(matcher.matches()) {
            node.setTag(TagName.ADDRESS_STREET.getId(), matcher.group(1));
            node.setTag(TagName.ADDRESS_HOUSENUMBER.getId(), matcher.group(2));
        } else {
            node.setTag(TagName.ADDRESS_STREET.getId(), streetAndNumber);
        }
    }

    @Override
    public OsmNode convertToOSM(Market market) {
        Location location = market.getLocation();
        OsmNode node = new OsmNode(location.getCoordinates());
        node.setTag(TagName.NAME.getId(), market.getName());
        node.setTag(TagName.ADDRESS_ZIP.getId(), Integer.toString(location.getZIP()));
        node.setTag(TagName.ADDRESS_CITY.getId(), location.getCity());
        node.setTag(TagName.SHOP.getId(), "supermarket");
        setStreetAndNumber(node, location.getStreet());
        return node;
    }
}
