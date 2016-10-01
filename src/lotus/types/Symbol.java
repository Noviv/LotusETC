package lotus.types;

import java.util.ArrayList;
import java.util.TreeMap;
import lotus.LotusClient;
import lotus.algo.NonBondAlgo;
import lotus.util.LotusUtil;

public abstract class Symbol {

    private String symbol;

    private TreeMap<Integer, Integer> bookSellPairs;
    private TreeMap<Integer, Integer> bookBuyPairs;
    private TreeMap<Integer, Integer> tradeSellPairs;
    private TreeMap<Integer, Integer> tradeBuyPairs;

    protected boolean traded;

    protected Symbol(String symb) {
        symbol = symb;
        bookSellPairs = new TreeMap<>();
        bookBuyPairs = new TreeMap<>();
        tradeSellPairs = new TreeMap<>();
        tradeBuyPairs = new TreeMap<>();

        traded = false;
    }

    public void appendBook(String[] comps) {
        boolean buyOrder = true;
        String[] pricePair;
        for (int i = 2; i < comps.length; i++) {
            switch (comps[i]) {
                case "BUY":
                    buyOrder = true;
                    break;
                case "SELL":
                    buyOrder = false;
                    break;
                default:
                    pricePair = comps[i].split(":");
                    if (buyOrder) {
                        bookBuyPairs.put(Integer.parseInt(pricePair[0]), Integer.parseInt(pricePair[1]));
                    } else {
                        bookSellPairs.put(Integer.parseInt(pricePair[0]), Integer.parseInt(pricePair[1]));
                    }
            }
        }
    }

    public void appendTrade(String _price, String _qty) {
        traded = true;

        int price = Integer.parseInt(_price);
        int qty = Integer.parseInt(_qty);
        for (int cprice : bookSellPairs.keySet()) {
            if (cprice == price) {
                tradeSellPairs.put(price, qty);
                return;
            }
        }

        for (int i : bookBuyPairs.keySet()) {
            if (i == price) {
                tradeBuyPairs.put(price, qty);
                return;
            }
        }
    }

    public ArrayList<Integer> getCurrentSellPrices() {
        return LotusUtil.cvtMapToKeyArray(tradeSellPairs);
    }

    public ArrayList<Integer> getCurrentBuyPrices() {
        return LotusUtil.cvtMapToKeyArray(tradeBuyPairs);
    }

    public void print() {
        for (int i : tradeSellPairs.keySet()) {
            System.out.println("sell " + i + " @ " + tradeSellPairs.get(i));
        }

        for (int i : tradeBuyPairs.keySet()) {
            System.out.println("buy " + i + " @ " + tradeBuyPairs.get(i));
        }
        tradeBuyPairs.clear();
        tradeSellPairs.clear();
    }

    public void updateAverages() {
        NonBondAlgo.getAverageFairValue(getCurrentBuyPrices(), getCurrentSellPrices());
        updateAverages0();
    }

    protected abstract void updateAverages0();

    public abstract void calc(LotusClient client);

    public final String getSymbol() {
        return symbol;
    }
}
