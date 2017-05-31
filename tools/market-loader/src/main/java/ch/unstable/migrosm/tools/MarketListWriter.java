package ch.unstable.migrosm.tools;

import ch.unstable.migrosm.model.Market;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.List;

/**
 * Created on 31.05.17.
 */
public abstract class MarketListWriter extends OutputStreamWriter {
    public MarketListWriter(OutputStream outputStream, String s) throws UnsupportedEncodingException {
        super(outputStream, s);
    }

    public MarketListWriter(OutputStream outputStream) {
        super(outputStream);
    }

    public MarketListWriter(OutputStream outputStream, Charset charset) {
        super(outputStream, charset);
    }

    public MarketListWriter(OutputStream outputStream, CharsetEncoder charsetEncoder) {
        super(outputStream, charsetEncoder);
    }

    abstract void writeMarketList(List<Market> marketList) throws IOException;
}
