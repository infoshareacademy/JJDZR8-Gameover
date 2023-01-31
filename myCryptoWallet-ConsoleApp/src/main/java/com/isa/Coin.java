package com.isa;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Coin {
    @SerializedName("symbol")
    @Expose
    private String symbol;
    private String shortSymbol;
    private String name;
    @SerializedName("priceChange")
    @Expose
    private String priceChange;
    @SerializedName("priceChangePercent")
    @Expose
    private String priceChangePercent;
    @SerializedName("weightedAvgPrice")
    @Expose
    private String weightedAvgPrice;
    @SerializedName("prevClosePrice")
    @Expose
    private String prevClosePrice;
    @SerializedName("lastPrice")
    @Expose
    private String lastPrice;
    @SerializedName("lastQty")
    @Expose
    private String lastQty;
    @SerializedName("bidPrice")
    @Expose
    private String bidPrice;
    @SerializedName("bidQty")
    @Expose
    private String bidQty;
    @SerializedName("askPrice")
    @Expose
    private String askPrice;
    @SerializedName("askQty")
    @Expose
    private String askQty;
    @SerializedName("openPrice")
    @Expose
    private String openPrice;
    @SerializedName("highPrice")
    @Expose
    private String highPrice;
    @SerializedName("lowPrice")
    @Expose
    private String lowPrice;
    @SerializedName("volume")
    @Expose
    private String volume;
    @SerializedName("quoteVolume")
    @Expose
    private String quoteVolume;
    @SerializedName("openTime")
    @Expose
    private Long openTime;
    @SerializedName("closeTime")
    @Expose
    private Long closeTime;
    @SerializedName("firstId")
    @Expose
    private long firstId;
    @SerializedName("lastId")
    @Expose
    private long lastId;
    @SerializedName("count")
    @Expose
    private Integer count;


    public Coin() {
        System.out.println("konstruktor1");
        setShortSymbol(getSymbol().replace("BUSD", ""));
        setName(Endpoints.getCoinsNames().get(getShortSymbol()));
    }



    public Coin(String symbol, String priceChange, String priceChangePercent, String weightedAvgPrice, String prevClosePrice, String lastPrice, String lastQty, String bidPrice, String bidQty, String askPrice, String askQty, String openPrice, String highPrice, String lowPrice, String volume, String quoteVolume, Long openTime, Long closeTime, long firstId, long lastId, Integer count) {
        System.out.println("konstruktor2");
        this.symbol = symbol;
        setShortSymbol(symbol.replace("BUSD", ""));
        setName(Endpoints.getCoinsNames().get(shortSymbol));
        this.priceChange = priceChange;
        this.priceChangePercent = priceChangePercent;
        this.weightedAvgPrice = weightedAvgPrice;
        this.prevClosePrice = prevClosePrice;
        this.lastPrice = lastPrice;
        this.lastQty = lastQty;
        this.bidPrice = bidPrice;
        this.bidQty = bidQty;
        this.askPrice = askPrice;
        this.askQty = askQty;
        this.openPrice = openPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.volume = volume;
        this.quoteVolume = quoteVolume;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.firstId = firstId;
        this.lastId = lastId;
        this.count = count;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getShortSymbol() {
        return shortSymbol;
    }

    public void setShortSymbol(String shortSymbol) {
        this.shortSymbol = shortSymbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(String priceChange) {
        this.priceChange = priceChange;
    }

    public String getPriceChangePercent() {
        return priceChangePercent;
    }

    public void setPriceChangePercent(String priceChangePercent) {
        this.priceChangePercent = priceChangePercent;
    }

    public String getWeightedAvgPrice() {
        return weightedAvgPrice;
    }

    public void setWeightedAvgPrice(String weightedAvgPrice) {
        this.weightedAvgPrice = weightedAvgPrice;
    }

    public String getPrevClosePrice() {
        return prevClosePrice;
    }

    public void setPrevClosePrice(String prevClosePrice) {
        this.prevClosePrice = prevClosePrice;
    }

    public String getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(String lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getLastQty() {
        return lastQty;
    }

    public void setLastQty(String lastQty) {
        this.lastQty = lastQty;
    }

    public String getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(String bidPrice) {
        this.bidPrice = bidPrice;
    }

    public String getBidQty() {
        return bidQty;
    }

    public void setBidQty(String bidQty) {
        this.bidQty = bidQty;
    }

    public String getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(String askPrice) {
        this.askPrice = askPrice;
    }

    public String getAskQty() {
        return askQty;
    }

    public void setAskQty(String askQty) {
        this.askQty = askQty;
    }

    public String getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(String openPrice) {
        this.openPrice = openPrice;
    }

    public String getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(String highPrice) {
        this.highPrice = highPrice;
    }

    public String getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(String lowPrice) {
        this.lowPrice = lowPrice;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getQuoteVolume() {
        return quoteVolume;
    }

    public void setQuoteVolume(String quoteVolume) {
        this.quoteVolume = quoteVolume;
    }

    public Long getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Long openTime) {
        this.openTime = openTime;
    }

    public Long getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Long closeTime) {
        this.closeTime = closeTime;
    }

    public long getFirstId() {
        return firstId;
    }

    public void setFirstId(Integer firstId) {
        this.firstId = firstId;
    }

    public long getLastId() {
        return lastId;
    }

    public void setLastId(Integer lastId) {
        this.lastId = lastId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
