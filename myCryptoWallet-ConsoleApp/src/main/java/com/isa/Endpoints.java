package com.isa;

public enum Endpoints {
    BTC("BTC","Bitcoin"),
    ETH("ETH","Etherum"),
    LTC("LTC","Litecoin"),
    XRP("XRP","Ripple"),
    XLM("XLM","Stellar");

    private final String name;
    private final String symbol;

    Endpoints(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }
    public static String buildRequest() {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("https://api.binance.com/api/v3/ticker/24hr?symbols=[");
        for (Endpoints endpoint : Endpoints.values()) {
            sBuilder.append("%22" + endpoint.symbol + "BUSD%22,");
        }
        sBuilder.replace(sBuilder.length() - 1, sBuilder.length(), "]");
        return sBuilder.toString();
    }
}
