package ch.unstable.migrosm;

import ch.unstable.migrosm.response.ResponseHandler;
import org.apache.hc.client5.http.impl.sync.AbstractResponseHandler;
import org.apache.hc.client5.http.impl.sync.CloseableHttpClient;
import org.apache.hc.client5.http.impl.sync.HttpClientBuilder;
import org.apache.hc.client5.http.sync.methods.HttpGet;
import org.apache.hc.core5.http.HttpEntity;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.List;

/**
 * Created on 30.05.17.
 */
public class ApacheMigrosDTO extends AbstractMigrosDTO {

    public class StoresResponseHandler extends AbstractResponseHandler<List<Market>> {
        @Override
        public List<Market> handleEntity(HttpEntity entity) throws IOException {
            return getDeserializer().parseStoresResponse(entity.getContent());
        }
    }

    private final CloseableHttpClient httpClient;

    public ApacheMigrosDTO(URL baseURL, ResponseHandler deserializer) {
        super(baseURL, deserializer);
        httpClient = HttpClientBuilder.create()
                .setUserAgent(getUserAgent())
                .build();
    }

    private static URI getURIFromURLUnchecked(URL url) {
        try {
            return url.toURI();
        } catch (URISyntaxException e) {
            throw new IllegalStateException("market URL can't be converted to URI");
        }
    }

    @Override
    public List<Market> getMarkets(Collection<MarketTypes> types, Collection<AbstractMigrosDTO.Header> headers) throws IOException {
        HttpGet get = new HttpGet(getURIFromURLUnchecked(getMarketURL()));
        for(AbstractMigrosDTO.Header header: headers) {
            get.addHeader(header.getName(), header.getValue());
        }
        return httpClient.execute(get, new StoresResponseHandler());
    }

    public static class Builder extends AbstractMigrosDTO.Builder<ApacheMigrosDTO> {
        @Override
        public ApacheMigrosDTO build() {
            return new ApacheMigrosDTO(baseURL, responseHandler);
        }
    }
}
