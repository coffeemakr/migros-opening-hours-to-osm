package ch.unstable.migrosm;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * Created on 30.05.17.
 */
public interface MigrosDTO {
    List<Market> getMarkets(Collection<MarketTypes> types) throws IOException;
}
