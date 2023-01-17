package com.isa;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Comparator;
import java.util.Objects;

public class Coin implements Comparable<Coin> {
    @SerializedName("symbol")
    @Expose
    private String symbol;
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coin coin = (Coin) o;
        return firstId == coin.firstId && lastId == coin.lastId && Objects.equals(symbol, coin.symbol) && Objects.equals(priceChange, coin.priceChange) && Objects.equals(priceChangePercent, coin.priceChangePercent) && Objects.equals(weightedAvgPrice, coin.weightedAvgPrice) && Objects.equals(prevClosePrice, coin.prevClosePrice) && Objects.equals(lastPrice, coin.lastPrice) && Objects.equals(lastQty, coin.lastQty) && Objects.equals(bidPrice, coin.bidPrice) && Objects.equals(bidQty, coin.bidQty) && Objects.equals(askPrice, coin.askPrice) && Objects.equals(askQty, coin.askQty) && Objects.equals(openPrice, coin.openPrice) && Objects.equals(highPrice, coin.highPrice) && Objects.equals(lowPrice, coin.lowPrice) && Objects.equals(volume, coin.volume) && Objects.equals(quoteVolume, coin.quoteVolume) && Objects.equals(openTime, coin.openTime) && Objects.equals(closeTime, coin.closeTime) && Objects.equals(count, coin.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, priceChange, priceChangePercent, weightedAvgPrice, prevClosePrice, lastPrice, lastQty, bidPrice, bidQty, askPrice, askQty, openPrice, highPrice, lowPrice, volume, quoteVolume, openTime, closeTime, firstId, lastId, count);
    }

    @Override
    public int compareTo(Coin coin) {
        return this.symbol.compareTo(coin.getSymbol());
    }
}
