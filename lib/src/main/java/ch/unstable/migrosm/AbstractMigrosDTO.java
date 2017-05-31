package ch.unstable.migrosm;

import ch.unstable.migrosm.response.ResponseHandler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Created on 31.05.17.
 */
public abstract class AbstractMigrosDTO implements MigrosDTO {
    private static final String MARKET_URL = "widgets/stores";
    private final URL baseURL;
    private final URL marketURL;
    private final ResponseHandler deserializer;
    private List<Header> marketHeaders;

    public AbstractMigrosDTO(URL baseURL, ResponseHandler deserializer) {
        this.baseURL = baseURL;
        this.deserializer = deserializer;
        try {
            this.marketURL = new URL(baseURL, MARKET_URL);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("BaseURL can't be combined", e);
        }
        ArrayList<Header> marketHeaders = new ArrayList<>();
        marketHeaders.add(new Header("Origin", "https://filialen.migros.ch"));
        this.marketHeaders = Collections.unmodifiableList(marketHeaders);
    }

    @Override
    public List<Market> getMarkets(Collection<MarketTypes> types) throws IOException {
        List<Header> headers = getMarketHeaders();
        return getMarkets(types, headers);
    }

    abstract List<Market> getMarkets(Collection<MarketTypes> types, Collection<Header> headers) throws IOException;

    public URL getMarketURL() {
        return this.marketURL;
    }

    public URL getBaseURL() {
        return baseURL;
    }

    public ResponseHandler getDeserializer() {
        return deserializer;
    }

    public List<Header> getMarketHeaders() {
        return marketHeaders;
    }

    protected String getUserAgent() {
        return "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0";
    }

    protected static class Header {
        private final String name;
        private final String value;

        public Header(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }
    }

    public static abstract class Builder<T extends AbstractMigrosDTO> {
        protected URL baseURL;
        protected ResponseHandler responseHandler;
        private String key;

        public Builder<T> setResponseHandler(ResponseHandler responseHandler){
            this.responseHandler = responseHandler;
            return this;
        }

        public Builder<T> setBaseURL(URL baseURL) {
            this.baseURL = Objects.requireNonNull(baseURL);
            return this;
        }

        public Builder<T> setBaseURL(String baseURL) {
            Objects.requireNonNull(baseURL);
            try {
                this.baseURL = new URL(baseURL);
            } catch (MalformedURLException e) {
                throw new IllegalArgumentException("Invalid url: " + baseURL, e);
            }
            return this;
        }

        public abstract T build();

        public Builder<T> setKey(String key) {
            this.key = Objects.requireNonNull(key);
            return this;
        }
    }
}
