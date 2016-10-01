package lotus.types;

import lotus.LotusClient;
import lotus.Stocks;
import lotus.algo.NonBondAlgo;

import lotus.util.DirType;

import static lotus.algo.NonBondAlgo.*;

public class Valbz extends Symbol {

    private static Valbz instance;

    public Valbz() {
        super("VALBZ");
    }

    @Override
    public void calc(LotusClient client) {
        if (BuyOrderCheck(getCurrentBuyPrices()) || SellOrderCheck(getCurrentSellPrices())) {
            getAverageFairValue(getCurrentBuyPrices(), getCurrentSellPrices());
            findHighestBid(getCurrentBuyPrices());
            if (checkingForBuyPrice()) {
                client.add(this, DirType.BUY, getBuyVal(), 1);
            }

            findLowestBid(getCurrentSellPrices());
            if (checkingForSellPrice()) {
                client.add(this, DirType.SELL, getSellVal(), 1);
            }
        }
    }

    @Override
    public void updateAverages0() {
        Stocks.setVALBZ(NonBondAlgo.getMarketVal());
    }

    public static Valbz getInstance() {
        return instance == null ? instance = new Valbz() : instance;
    }
}
