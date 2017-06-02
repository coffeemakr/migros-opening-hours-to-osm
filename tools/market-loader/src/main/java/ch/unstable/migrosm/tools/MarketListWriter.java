package ch.unstable.migrosm.tools;

import ch.unstable.migrosm.model.Market;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created on 31.05.17.
 */
public interface MarketListWriter {
    void writeMarketList(List<Market> marketList, OutputStream outputStream) throws IOException;
}
