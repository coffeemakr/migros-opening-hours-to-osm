package ch.unstable.migrosm.tools;

import ch.unstable.migrosm.ApacheMigrosDTO;
import ch.unstable.migrosm.MigrosDTO;
import ch.unstable.migrosm.model.Market;
import ch.unstable.migrosm.model.MarketTypes;
import ch.unstable.migrosm.response.GsonResponseHandler;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.action.CountArgumentAction;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 31.05.17.
 */
public class MarketLoader {

    public static final String ARG_OUTPUT_FORMAT = "format";
    private static final String NAME = "market-loader";

    private static final Level[] LEVELS = {Level.INFO, Level.DEBUG, Level.TRACE};

    private static final Logger LOGGER = LogManager.getLogger(MarketLoader.class);
    public static final String ARG_VERBOSITY = "verbose";
    public static final String ARG_API_KEY = "api_key";
    public static final String ARG_BASE_URL = "base_url";
    public static final String ARG_TYPE = "type";

    private static void setLogLevel(int verbosity) {
        verbosity = Math.min(LEVELS.length - 1, verbosity);
        Level level = LEVELS[verbosity];
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        Configuration config = ctx.getConfiguration();
        LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
        loggerConfig.setLevel(level);
        ctx.updateLoggers();  // This causes all Loggers to refetch information from their LoggerConfig.
        LOGGER.debug("Using log level " + level.name());
    }

    private static MarketListWriter getMarketListWriter(OutputFormat outputFormat, OutputStream outputStream) {
        MarketListWriter marketListWriter;
        switch (outputFormat) {
            case TEXT:
                marketListWriter = new TextMarketListWriter();
                break;
            case JSON:
                marketListWriter = new JSONMarketListWriter();
                break;
            case GEOJSON:
                marketListWriter = new GeoJsonMarketListWriter();
                break;
            default:
                throw new IllegalArgumentException("Unknown Format: " + outputFormat);
        }
        return marketListWriter;
    }

    private static Namespace getArguments(String args[]) {
        ArgumentParser parser = ArgumentParsers
                .newArgumentParser(NAME);
        parser.addArgument("-k", "--api-key")
                .metavar("KEY")
                .dest(ARG_API_KEY)
                .setDefault("8ApUDaqeNER3Mest")
                .help("The API key to use");

        parser.addArgument("--base-url")
                .metavar("URL")
                .dest(ARG_BASE_URL)
                .setDefault("https://web-api.migros.ch")
                .help("The URL to use as the api");

        parser.addArgument("market-type")
                .metavar("market-type")
                .dest(ARG_TYPE)
                .help("The type of markets to load")
                .choices(MarketTypes.values())
                .type((parser1, arg, value) -> MarketTypes.valueOf(value.toUpperCase()))
                .nargs("+");

        parser.addArgument("-v", "--verbose")
                .action(new CountArgumentAction())
                .dest(ARG_VERBOSITY)
                .help("Set verbosity level. Can be used multiple time to increase");

        parser.addArgument("-f", "--format")
                .choices(OutputFormat.values())
                .dest(ARG_OUTPUT_FORMAT)
                .type((parser1, arg, value) -> OutputFormat.valueOf(value.toUpperCase()))
                .setDefault(OutputFormat.TEXT);

        Namespace arguments = null;
        try {
            arguments = parser.parseArgs(args);
        } catch (ArgumentParserException e) {
            parser.handleError(e);
            System.exit(1);
        }
        if(arguments == null) {
            throw new IllegalStateException("Should never come to this point");
        }
        return arguments;
    }

    public static void main(String[] args) throws IOException {
        Namespace arguments = getArguments(args);

        int verbosity = arguments.getInt(ARG_VERBOSITY);
        setLogLevel(verbosity);

        LOGGER.trace("Got arguments: ", arguments.getAttrs());
        MarketListWriter marketListWriter = getMarketListWriter(arguments.get(ARG_OUTPUT_FORMAT), System.out);

        MigrosDTO migrosDTO = new ApacheMigrosDTO.Builder()
                .setKey(arguments.getString(ARG_API_KEY))
                .setBaseURL(arguments.getString(ARG_BASE_URL))
                .setResponseHandler(new GsonResponseHandler())
                .build();

        List<Market> markets = migrosDTO.getMarkets(arguments.getList(ARG_TYPE));
        LOGGER.debug("Loaded " + markets.size() + " markets.");
        marketListWriter.writeMarketList(markets, System.out);
    }

    private enum OutputFormat {
        TEXT, JSON, GEOJSON
    }
}
