package com.isa;

import java.util.*;

public class CoinsList {

    private Map<Integer, List<String>> pagesMap;
    private ArrayList<String> coins;
    private int totalPages;
    private int recordsPerPage;


    public void pagesCreator() {
        this.pagesMap = new LinkedHashMap<>();

        if (coins.size() < recordsPerPage) this.totalPages = 1;
        else if (coins.size() % recordsPerPage != 0) this.totalPages = coins.size() / recordsPerPage + 1;
        else this.totalPages = coins.size() / recordsPerPage;

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
        return pagesMap.get(pageNumber - 1);
    }

    public List<String> openPageFromKeyboard(){
        Scanner scanner = new Scanner(System.in);
        Integer pageNumber = 0;
        do {
            pageNumber = Integer.parseInt(scanner.nextLine());
            return openPage(pageNumber);
        }while(pageNumber > 0 && pageNumber <= totalPages);



    }


    public Map<Integer, List<String>> getPagesMap() {
        return pagesMap;
    }
}
