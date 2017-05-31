package ch.unstable.migrosm;

import ch.unstable.migrosm.model.Market;
import ch.unstable.migrosm.model.MarketTypes;
import ch.unstable.migrosm.response.ResponseHandler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

/**
 * Created on 31.05.17.
 */
public abstract class AbstractMigrosDTO implements MigrosDTO {
    private static final String MARKET_URL = "widgets/stores";
    protected final URL baseURL;
    protected final URI marketURI;
    protected final ResponseHandler responseHandler;
    private final List<Header> marketHeaders;
    private final String key;

    protected AbstractMigrosDTO(Builder builder) {
        this.baseURL = Objects.requireNonNull(builder.baseURL, "baseURL");
        this.responseHandler = Objects.requireNonNull(builder.responseHandler, "responseHandler");
        this.key = Objects.requireNonNull(builder.key, "key");
        try {
            this.marketURI = new URL(baseURL, MARKET_URL).toURI();
        } catch (URISyntaxException | MalformedURLException e) {
            throw new IllegalArgumentException("BaseURL can't be combined", e);
        }
        ArrayList<Header> marketHeaders = new ArrayList<>();
        marketHeaders.add(new Header("Origin", "https://filialen.migros.ch"));
        this.marketHeaders = Collections.unmodifiableList(marketHeaders);
    }

    public String getKey() {
        return key;
    }

    @Override
    public List<Market> getMarkets(Collection<MarketTypes> types) throws IOException {
        List<Header> headers = getMarketHeaders();
        return getMarkets(types, headers);
    }

    abstract List<Market> getMarkets(Collection<MarketTypes> types, Collection<Header> headers) throws IOException;

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
            this.key = Objects.requireNonNull(key, "key");
            return this;
        }
    }
}
