package ch.unstable.migrosm.response;

import ch.unstable.migrosm.Market;

import java.io.InputStream;
import java.util.List;

/**
 * Created on 31.05.17.
 */
public interface ResponseHandler {
    List<Market> parseStoresResponse(InputStream inputStream);
}
