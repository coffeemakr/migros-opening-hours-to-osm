package ch.unstable.migrosm.tools;

import ch.unstable.migrosm.model.Market;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;


public class TextMarketListWriter extends MarketListWriter{

    public TextMarketListWriter(OutputStream outputStream) {
        super(outputStream);
    }

    @Override
    public void writeMarketList(List<Market> marketList) throws IOException {
        for(Market market: marketList) {
            append(market.toString());
            append("\n");
        }
        flush();
    }
}
