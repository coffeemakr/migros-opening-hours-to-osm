package ch.unstable.migrosm;

import ch.unstable.migrosm.model.Market;
import ch.unstable.migrosm.model.MarketTypes;
import org.apache.hc.client5.http.impl.sync.AbstractResponseHandler;
import org.apache.hc.client5.http.impl.sync.CloseableHttpClient;
import org.apache.hc.client5.http.impl.sync.HttpClientBuilder;
import org.apache.hc.client5.http.sync.methods.HttpGet;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.net.URIBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;

/**
 * Created on 30.05.17.
 */
public class ApacheMigrosDTO extends AbstractMigrosDTO {

    private static final String PARAMETER_KEY = "key";
    private static final String PARAMETER_MARKET_TYPES = "market_types[]";

    public class StoresResponseHandler extends AbstractResponseHandler<List<Market>> {
        @Override
        public List<Market> handleEntity(HttpEntity entity) throws IOException {
            return responseHandler.parseStoresResponse(entity.getContent());
        }
    }

    private final CloseableHttpClient httpClient;

    public ApacheMigrosDTO(Builder builder) {
        super(builder);
        httpClient = HttpClientBuilder.create()
                .setUserAgent(getUserAgent())
                .build();
    }

    @Override
    public List<Market> getMarkets(Collection<MarketTypes> types, Collection<AbstractMigrosDTO.Header> headers) throws IOException {
        URIBuilder uriBuilder = new URIBuilder(marketURI)
                .addParameter(PARAMETER_KEY, getKey());

        for (MarketTypes marketType: types) {
            uriBuilder.addParameter(PARAMETER_MARKET_TYPES, marketType.getIdentifier());
        }


        HttpGet get;
        try {
            get = new HttpGet(uriBuilder.build());
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Couldn't build URI");
        }
        for(AbstractMigrosDTO.Header header: headers) {
            get.addHeader(header.getName(), header.getValue());
        }

        return httpClient.execute(get, new StoresResponseHandler());
    }

    public static class Builder extends AbstractMigrosDTO.Builder<ApacheMigrosDTO> {
        @Override
        public ApacheMigrosDTO build() {
            return new ApacheMigrosDTO(this);
        }
    }
}
