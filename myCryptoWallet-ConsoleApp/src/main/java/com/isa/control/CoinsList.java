package com.isa.control;

import com.isa.control.Coin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class CoinsList {

    private static Logger LOGGER = LoggerFactory.getLogger(CoinsList.class.getName());

    private Map<Integer, List<Coin>> pagesMap;
    private final List<Coin> coins;
    private int totalPages;
    private final int recordsPerPage;

   // public CoinsList(int recordsPerPage) {

   // }

    public CoinsList( List<Coin> coins,int recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
        this.coins = coins;
    }

    public void pagesCreator() {
        setTotalPages();
        this.pagesMap = new LinkedHashMap<>();

        for (int i = 0; i < totalPages; i++) {
            List<Coin> temporaryCoinList;
            if (coins.size() < recordsPerPage) temporaryCoinList = coins.subList(0, coins.size());
            else if (i == totalPages - 1) {
                temporaryCoinList = coins.subList(i * recordsPerPage, coins.size());
            } else temporaryCoinList = coins.subList(i * recordsPerPage, (i + 1) * recordsPerPage);
            pagesMap.put(i, temporaryCoinList);
        }
        LOGGER.debug("The map with key = page number and value = CoinsList has been created successfully.");
    }

    public List<Coin> openPage(Integer pageNumber){
        System.out.println(pagesMap.get(pageNumber - 1));
        LOGGER.trace("Page {} is open", pageNumber);
        return pagesMap.get(pageNumber - 1);
    }

    public void openPageFromKeyboard(){

        int pageNumber = 0;
        do {
            System.out.println("podaj numer strony (total pages: " + totalPages + ")");
            System.out.println("Aby wyjść podaj \"0\"");
            Scanner scanner = new Scanner(System.in);
            pageNumber = Integer.parseInt(scanner.nextLine());
            openPage(pageNumber);
        }while(pageNumber != 0);
    }

    public void setTotalPages() {
        if (coins.size() < recordsPerPage) this.totalPages = 1;
        else if (coins.size() % recordsPerPage != 0) this.totalPages = coins.size() / recordsPerPage + 1;
        else this.totalPages = coins.size() / recordsPerPage;
        LOGGER.trace("Total pages = {}", this.totalPages);
    }

    public Map<Integer, List<Coin>> getPagesMap() {
        return pagesMap;
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public int getRecordsPerPage() {
        return recordsPerPage;
    }

    @Override
    public String toString() {
        return "CoinsList{" +
                "pagesMap=" + pagesMap +
                ", coins=" + coins +
                ", totalPages=" + totalPages +
                ", recordsPerPage=" + recordsPerPage +
                '}';
    }
}
