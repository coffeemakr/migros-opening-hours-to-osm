package ch.unstable.migrosm.tools;

import ch.unstable.migrosm.ApacheMigrosDTO;
import ch.unstable.migrosm.Market;
import ch.unstable.migrosm.MarketTypes;
import ch.unstable.migrosm.MigrosDTO;
import ch.unstable.migrosm.response.GsonResponseHandler;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 31.05.17.
 */
public class MarketLoader {

    public static void printMarkets(List<Market> markets) {
        for(Market market: markets) {
            System.out.println("Market: " + market);
        }
    }

    public static void main(String[] args) throws IOException {
        MigrosDTO migrosDTO = new ApacheMigrosDTO.Builder()
                .setKey("8ApUDaqeNER3Mest")
                .setBaseURL("https://web-api.migros.ch")
                .setResponseHandler(new GsonResponseHandler())
                .build();

        List<Market> markets = migrosDTO.getMarkets(Arrays.asList(MarketTypes.ALNATURA));
        printMarkets(markets);
    }
}
