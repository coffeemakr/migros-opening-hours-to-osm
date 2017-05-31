package ch.unstable.migrosm.response.gson;

import ch.unstable.migrosm.model.MarketTypes;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;


public class MarketTypeDeserializer implements JsonDeserializer<MarketTypes> {
    @Override
    public MarketTypes deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return MarketTypes.fromIdentifier(json.getAsString());
    }
}
