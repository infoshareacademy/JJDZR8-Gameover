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
                        Wallet wallet = Data.deserializeWallet();
                        Wallet changedWallet = walletService(wallet);
                       // saveWalletsToFile(wallets,changedWallet);
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

    public static Wallet choiceWallet(Map<String, Wallet> wallets){

        System.out.println("czy chcesz utworzyć nowy portfel? [Y = yes/ N = no");
        Scanner scanner = new Scanner(System.in);
        char yourChoice = scanner.nextLine().toUpperCase().charAt(0);
        if(yourChoice == 'Y'){
           return Wallet.createNewWalletFromKeyboard(scanner);
        }else {
            System.out.println("***********");
            wallets.keySet().forEach(System.out::println);
            System.out.println("***********");
            System.out.println("wpisz nazwę portfela z listy powyżej:");
            Scanner sc = new Scanner(System.in);
            String walletId = sc.nextLine().trim();
            if (wallets.containsKey(walletId)) return wallets.get(walletId);
            else return choiceWallet(wallets);
           }
    }
    public static Wallet walletService(Wallet wallet){
        wallet.updateWallet();
        boolean flag = true;
        while (flag) {
            printWalletServiceInstruction();
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("wybierz opcję dla portfela:");
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1:
                        //kup nowy token
                        Coin coin = Wallet.searchCoinForBuying();
                        System.out.println("czy jesteś pewien, że chcesz zakupić tego coina? [Y = yes/ N = no]");
                        char guard = scanner.nextLine().toUpperCase().charAt(0);
                        if(guard == 'Y'){
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
                            Scanner scanner1 = new Scanner(System.in);
                            ActiveTransaction activeTransaction = wallet.searchActiveTransaction(scanner1);
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
                        //wyświetl otwarte pozycje
                        wallet.getActiveTransactions().forEach(ActiveTransaction::printDetails);
                        break;
                    case 5:
                        //wyświetl transakcje historyczne
                        wallet.getTransactionsHistory().forEach(ClosedTransaction::printDetails);
                        break;
                    case 6:
                        //ustaw SL dla wybranej transakcji
                        System.out.println("wyszukaj transakcję dla której chcesz ustawić zlecenie stop loss");
                        ActiveTransaction activeTransaction = wallet.searchActiveTransaction(scanner);
                        if (!(activeTransaction == null)){
                            System.out.println("podaj wartość stop loss dla poniższej transakcji");
                            activeTransaction.printDetails();
                            long price = scanner.nextLong();
                            activeTransaction.setSLAlarm(price, true);
                        }
                        wallet.updateWallet();
                        break;
                    case 7:
                        // ustaw TP dla wybranej transakcji
                        System.out.println("wyszukaj transakcję dla której chcesz ustawić zlecenie take profit");
                        ActiveTransaction activeTrans = wallet.searchActiveTransaction(scanner);
                        if (!(activeTrans == null)){
                            System.out.println("podaj wartość take profit dla poniższej transakcji:");
                            activeTrans.printDetails();
                            long price = scanner.nextLong();
                            activeTrans.setTPAlarm(price, true);
                        }
                        wallet.updateWallet();
                        break;
                    case 8:
                        wallet.updateWallet();
                        break;
                    case 9:
                        flag = false;
                        break;
                    default:
                        System.out.println("Nie ma takiej opcji, spróbuj ponownie");
                        break;

                }
            }catch (InputMismatchException e) {
                System.out.println("Podaj liczbę całkowitą");
            }
        }
        return wallet;
    }

    public static void printWalletServiceInstruction(){
        System.out.println("""
                Aby skorzystać z opcji portfela wybierz:
                1 - kup nowy token
                2 - sprzedaj token
                3 - wyświetl wartość portfela
                4 - wyświetl otwarte pozycje
                5 - wyświetl historię portfela
                6 - ustaw zlecenie stop loss
                7 - ustaw zlecenie take profit
                8 - odśwież portfel
                9 - wyjście
                """);
    }

    public static void saveWalletsToFile(Map<String, Wallet> wallets, Wallet wallet){
        if(wallets == null){
            wallets = new HashMap<>();
            wallets.put(wallet.getWalletId(), wallet);
            Data.serializer(wallets, "wallet.json");
        } else if (!wallets.containsKey(wallet.getWalletId())){
            wallets.put(wallet.getWalletId(), wallet);
            Data.serializer(wallets, "wallet.json");
        } else{
            wallets.replace(wallet.getWalletId(), wallet);
            Data.serializer(wallets, "wallet.json");
        }
    }

    @Override
    public String toString() {
        return position +  " - " + description;
    }
}