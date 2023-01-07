package com.isa;

import java.util.*;

public class CoinsList {

    private Map<Integer, List<String>> pagesMap;
    private List<String> coins;
    private int totalPages;
    private int recordsPerPage;

    public CoinsList(int recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
        this.coins = Endpoints.getEndpoints();
        setTotalPages();
        pagesCreator();
        openPageFromKeyboard();
    }

    public void pagesCreator() {
        this.pagesMap = new LinkedHashMap<>();

        for (int i = 0; i < totalPages; i++) {
            List<String> temporaryCoinList;
            if (coins.size() < recordsPerPage) temporaryCoinList = coins.subList(0, coins.size());
            else if (i == totalPages - 1) {
                temporaryCoinList = coins.subList(i * recordsPerPage, coins.size());
            } else temporaryCoinList = coins.subList(i * recordsPerPage, (i + 1) * recordsPerPage);

            pagesMap.put(i, temporaryCoinList);
        }
    }

    public List<String> openPage(Integer pageNumber){
        System.out.println(pagesMap.get(pageNumber - 1));
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
    }

    public Map<Integer, List<String>> getPagesMap() {
        return pagesMap;
    }
}
