package ch.unstable.migrosm.tools;

import ch.unstable.migrosm.model.Market;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;


public class TextMarketListWriter implements MarketListWriter{

    @Override
    public void writeMarketList(List<Market> marketList, OutputStream outputStream) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        for(Market market: marketList) {
            outputStreamWriter.append(market.toString());
            outputStreamWriter.append("\n");
        }
        outputStreamWriter.flush();
    }
}
