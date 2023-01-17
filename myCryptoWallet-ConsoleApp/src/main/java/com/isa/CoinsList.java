package com.isa;

import com.isa.control.Data;

import java.util.*;
import java.util.stream.Collectors;

public class CoinsList {

    private Map<Integer, List<Coin>> pagesMap;
    private final List<Coin> coins;
    private List<Coin> favouriteCoinsList;
    private int totalPages;
    private final int recordsPerPage;

    public CoinsList(int recordsPerPage) {
        List<Coin> coinsList;
        this.recordsPerPage = recordsPerPage;
        try {
            coinsList = new ArrayList<>(Arrays.asList(Data.deserializeCoin()));
        }catch (Exception e){
            e.printStackTrace();
            coinsList = null;
        }
        this.coins = coinsList;
        setTotalPages();
        pagesCreator();
        openPageFromKeyboard();
    }

    public void pagesCreator() {
        this.pagesMap = new LinkedHashMap<>();

        for (int i = 0; i < totalPages; i++) {
            List<Coin> temporaryCoinList;
            if (coins.size() < recordsPerPage) temporaryCoinList = coins.subList(0, coins.size());
            else if (i == totalPages - 1) {
                temporaryCoinList = coins.subList(i * recordsPerPage, coins.size());
            } else temporaryCoinList = coins.subList(i * recordsPerPage, (i + 1) * recordsPerPage);

            pagesMap.put(i, temporaryCoinList);
        }
    }

    public List<Coin> openPage(Integer pageNumber){
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

    public Map<Integer, List<Coin>> getPagesMap() {
        return pagesMap;
    }

    public void AddYourFavouriteToken(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wpisz szukaną frazę");
        String string = scanner.nextLine().toUpperCase();
        List<Coin> temporaryList = coins.stream().takeWhile(n -> n.getSymbol().contains(string))
                .collect(Collectors.toList());

        if (!temporaryList.isEmpty()){
            temporaryList.forEach(n-> System.out.println(n.getSymbol() + " - " + n.getLastPrice() + " USD"));
            System.out.println("czy chcesz dodać wybrane coiny do ulubionych? [Y/N]");
            char[] choice = scanner.nextLine().toUpperCase().toCharArray();
            if (choice[0] == 'Y') {
                favouriteCoinsList.addAll(temporaryList);
                Collections.sort(favouriteCoinsList);
                // # TODO - dodać zapis do pliku "ulubione"
            }
        }

    }
}
