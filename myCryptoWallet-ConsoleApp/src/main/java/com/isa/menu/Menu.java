package com.isa.menu;

import com.isa.control.*;
import com.isa.control.transactions.ActiveTransaction;
import com.isa.control.transactions.ClosedTransaction;

import java.util.*;

public enum Menu {
    ADD_COIN(1, "Dodaj nowy coin"),
    SEARCH_COIN(2, "Wyszukaj coin z listy"),
    LIST_COINS(3, "Wyświetl listę coin-ów"),
    UPDATE_COIN_LIST (4, "Zaktualizuj listę coin–ów"),
    ADD_FAVOURITE_COIN (5, "Dodaj coin-a do listy ulubionych"),
    EXPORT_FILE (6, "Exportuj bibliotekę do pliku"),
    IMPORT_FILE (7, "Importuj bibliotekę z pliku"),
    USER_WALLET(8, "Portfel użytkownika"),
    EXIT (9, "Zakończ działanie programu \n  Wybierz opcję");

    static User user1 = new User("Bogus");
    static Coin coin1 = new Coin();

    private final int position;

    private final String description;

    Menu(final int position, final String description) {
        this.description = description;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    String getDescription() {
        return description;
    }
    public static void printMenu() {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                "myCryptoWallet \n" +
                "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n");
        for (Menu menu : Menu.values()) {
            System.out.println(menu);
        }

    }
    public static Menu getMenuItem(int number) {
        Menu menuToReturn = null;
        for (Menu menu : Menu.values()) {
            if (menu.getPosition() == number) {
                menuToReturn = menu;
            }
        }
        return menuToReturn;
    }

    public static void getMenu() {
        boolean flag = true;
        while (flag)
            try {
                Scanner sc = new Scanner(System.in);
                Menu.printMenu();
                int chooseOption = sc.nextInt();
                switch (chooseOption) {
                    case 1:
                        //System.out.println(Menu.ADD_COIN);
                        Endpoints.addCoin();
                        break;
                    case 2:
                        System.out.println(Menu.SEARCH_COIN);
                        CoinSearch coinSearch = new CoinSearch();
                        coinSearch.findYourToken();
                        break;
                    case 3:
                        // System.out.println(Menu.LIST_COINS);
                        List<Coin> coinsList = Coins.getInstance().getCoinList();
                        CoinsList cL  = new CoinsList(coinsList, 10);
                        cL.pagesCreator();
                        cL.openPageFromKeyboard();
                        break;
                    case 4:
                        //System.out.println(Menu.UPDATE_COIN_LIST);
                        // aktualizuje plik coin.json
                        System.out.println("Aktualizauje listę kryptowalut...");
                        Data.updateCoinList();
                        break;
                    case 5:
                        System.out.println(Menu.ADD_FAVOURITE_COIN);
                        List<Coin> favCoinsList = Favourite.invocationFavouriteList();
                        Favourite favourite = new Favourite(favCoinsList, 2);
                        favourite.AddYourFavouriteToken();
                        favourite.pagesCreator();
                        favourite.openPageFromKeyboard();
                        break;
                    case 6:
                        System.out.println(Menu.EXPORT_FILE);
                        //zapisuje endpointsy do pliku
                        Data.serializer(Endpoints.getEndpoints(), "endpoints.json");
                        break;
                    case 7:
                        System.out.println(Menu.IMPORT_FILE);
                        //importuje listę endpoints z pliku endpoints.json
                        Endpoints.setEndpoints();
                        break;
                    case 8:
                        System.out.println(Menu.USER_WALLET);
                        //wyswietla portfel uzytkownika
                        Wallet wallet = choiceWallet();
                        walletService(wallet);

                        break;
                    case 9:
                        flag = false;
                        break;
                    default:
                        System.out.println("Nie ma takiej opcji, spróbuj ponownie");
                }
            } catch (InputMismatchException e) {
                System.out.println("Podaj liczbę całkowitą");
            }

    }
    //wybierz portfel lub skonfiguruj nowy portfel
    public static Wallet choiceWallet(){
        HashSet<Wallet> wallets = Data.deserializeWallet();
        Set<String> walletIdSet = new HashSet<>();
        wallets.forEach(n->walletIdSet.add(n.getWalletId()));

        System.out.println("Lista twoich portfeli:");
        System.out.println("*********************");
        walletIdSet.forEach(System.out::println);
        System.out.println("*********************");

        System.out.println("czy chcesz utworzyć nowy portfel? [Y = yes/ N = no");
        Scanner scanner = new Scanner(System.in);
        String yourChoice = scanner.nextLine().toUpperCase();
        if(yourChoice == "Y"){
           return Wallet.createNewWalletFromKeyboard(scanner);
        }else {
            System.out.println("wpisz nazwę portfela z listy powyżej:");
            String walletId = scanner.nextLine();
            if (walletIdSet.contains(walletId)) {
                return wallets.stream().filter(n -> n.getWalletId() == walletId).findFirst().get();
            }else {
                System.out.println("podałeś złą nazwę portfela");
                return choiceWallet();
            }
        }
    }
    public static void walletService(Wallet wallet){
        wallet.updateWallet();
        boolean flag = true;
        while (flag) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("wybierz opcję dla portfela:");
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        //kup nowy token
                        Coin coin = Wallet.searchCoinForBuying();
                        System.out.println("czy jesteś pewien, że chcesz zakupić tego coina? [Y = yes/ N = no]");
                        String guard = scanner.nextLine().toUpperCase();
                        if(guard == "Y"){
                            System.out.println("podaj ilość którą chcesz zakupić(volumen):");
                            double volume = 0;
                            while (volume <= 0) {
                                volume = scanner.nextDouble();
                            }
                            wallet.buyNewToken(coin, volume);
                            wallet.updateWallet();
                        }
                        break;
                    case 2:
                        //sprzedaj token
                        if (wallet.getActiveTransactions().isEmpty()) {
                            System.out.println("nie masz otwartych transakcji");
                        } else {
                            ActiveTransaction activeTransaction = wallet.searchActiveTransactionForSelling(scanner);
                            System.out.println("podaj volumen sprzedaży:");
                            double trVolume = 0;
                            while (trVolume <= 0) {
                                trVolume = scanner.nextDouble();
                            }
                            wallet.closeActiveTransaction(activeTransaction, trVolume);
                            wallet.updateWallet();
                        }
                        break;
                    case 3:
                        //wyświetl wartość portfela
                        System.out.println("zysk na otwartych pozycjach: " + wallet.getProfitLoss());
                        System.out.println("dostępne środki: " + wallet.getWalletBalance());
                        System.out.println("całkowita wartość portfela: " + wallet.getWalletSum());
                        System.out.println("zysk na pozycjach zamkniętych: " + wallet.getHistoricalProfitLoss());
                        break;
                    case 4:
                        //wyświetl transakcje historyczne
                        wallet.getTransactionsHistory().forEach(ClosedTransaction::printDetails);
                        break;
                    case 5:
                        //ustaw SL dla wybranej transakcji
                    case 6:
                        // ustaw TP dla wybranej transakcji
                    case 7:
                        flag = false;
                    default:
                        System.out.println("Nie ma takiej opcji, spróbuj ponownie");

                }
            }catch (InputMismatchException e) {
                System.out.println("Podaj liczbę całkowitą");
            }
        }
    }

    @Override
    public String toString() {
        return position +  " - " + description;
    }
}