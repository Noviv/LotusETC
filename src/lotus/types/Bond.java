package lotus.types;

import lotus.LotusClient;
import lotus.util.DirType;

import static lotus.algo.BondAlgo.*;

public class Bond extends Symbol {

    private static Bond instance;

    private long lastMillis;

    public Bond() {
        super("BOND");

        lastMillis = System.currentTimeMillis();
    }

    @Override
    public void calc(LotusClient client) {
        if (System.currentTimeMillis() - lastMillis > 5 * 1000 && traded) {
            traded = false;
            lastMillis = System.currentTimeMillis();

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

    public static Bond getInstance() {
        return instance == null ? instance = new Bond() : instance;
    }
}
