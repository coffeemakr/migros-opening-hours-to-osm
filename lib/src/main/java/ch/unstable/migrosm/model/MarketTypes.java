package ch.unstable.migrosm.model;

import java.util.Objects;

public enum MarketTypes {
    MIGROS_SUPERMARKET("super"),
    MIGROLINO("mno"),
    VOI("voi"),
    MP("mp"),
    M_OUTLET("out"),
    OBI("obi"),
    SPORTX("spx"),
    DO_IT_AND_GARDEN("doi"),
    M_ELECTRONICS("mec"),
    MICASA("mica"),
    MIGROS_RESTAURANT("res"),
    FLORISSIMO("flori"),
    TAKE_AWAY("gour"),
    CHICKERIA("chick"),
    ALNATURA("alna"),
    COF("cof");


    private final String identifier;

    MarketTypes(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public static MarketTypes fromIdentifier(String identifier) {
        Objects.requireNonNull(identifier, "identifier");
        String lowercaseId = identifier.toLowerCase();
        for(MarketTypes marketType:values()) {
            if(marketType.getIdentifier().equals(lowercaseId)) {
                return marketType;
            }
        }
        return null;
    }
}
