package ch.unstable.migrosm.tools;

import ch.unstable.migrosm.model.Market;
import ch.unstable.migrosm.response.GsonResponseHandler;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created on 31.05.17.
 */
public class JSONMarketListWriter extends MarketListWriter {
    private Gson gson;

    public JSONMarketListWriter(OutputStream outputStream) {
        super(outputStream);
        gson = GsonResponseHandler.createGson();
    }

    @Override
    public void writeMarketList(List<Market> marketList) throws IOException {
        gson.toJson(marketList, this);
        flush();
    }
}
