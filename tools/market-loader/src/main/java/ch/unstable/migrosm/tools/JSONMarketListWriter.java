package ch.unstable.migrosm.tools;

import ch.unstable.migrosm.model.Market;
import ch.unstable.migrosm.response.GsonResponseHandler;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * Created on 31.05.17.
 */
public class JSONMarketListWriter implements MarketListWriter {
    private Gson gson;

    public JSONMarketListWriter() {
        gson = GsonResponseHandler.createGson();
    }

    @Override
    public void writeMarketList(List<Market> marketList, OutputStream outputStream) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        gson.toJson(marketList, writer);
        writer.close();
    }
}
