package ch.unstable.migrosm.response;

import ch.unstable.migrosm.model.Market;
import ch.unstable.migrosm.model.MarketTypes;
import ch.unstable.migrosm.response.gson.MarketTypeDeserializer;
import ch.unstable.migrosm.response.gson.MarketTypeSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created on 31.05.17.
 */
public class GsonResponseHandler implements ResponseHandler {

    private static final Type MARKET_LIST_TYPE = new TypeToken<List<Market>>(){}.getType();
    public static final String FIELD_NAME_STORES = "stores";

    private final Gson gson;

    public GsonResponseHandler() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MarketTypes.class, new MarketTypeDeserializer());
        gsonBuilder.registerTypeAdapter(MarketTypes.class, new MarketTypeSerializer());
        gson = gsonBuilder.create();
    }

    @Override
    public List<Market> parseStoresResponse(InputStream inputStream) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonStores = jsonParser.parse(inputStreamReader)
                .getAsJsonObject()
                .getAsJsonArray(FIELD_NAME_STORES);
        gson.newJsonReader(inputStreamReader);
        return gson.fromJson(jsonStores, MARKET_LIST_TYPE);
    }
}
