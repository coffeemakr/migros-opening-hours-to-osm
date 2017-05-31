package ch.unstable.migrosm.response.gson;

import ch.unstable.migrosm.model.MarketTypes;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created on 31.05.17.
 */
public class MarketTypeSerializer implements JsonSerializer<MarketTypes> {
    @Override
    public JsonElement serialize(MarketTypes src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getIdentifier());
    }
}
