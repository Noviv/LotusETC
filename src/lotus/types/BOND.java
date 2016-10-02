package lotus.types;

import lotus.LotusClient;
import lotus.util.DirType;

import static lotus.algo.BondAlgo.*;

public class BOND extends Symbol {

    private static BOND instance;

    public BOND() {
        super("BOND");
    }

    @Override
    public void calc(LotusClient client) {
        if (System.currentTimeMillis() - lastMillis > 1 * 1000 && traded) {
            traded = false;
            lastMillis = System.currentTimeMillis();

            findHighestBid(getCurrentBuyPrices());
            if (checkingForBuyPrice()) {
                client.add(this, DirType.BUY, getBuyVal(), 10);
            }

            findLowestBid(getCurrentSellPrices());
            if (checkingForSellPrice()) {
                client.add(this, DirType.SELL, getSellVal(), 10);
            }
        }
    }

    @Override
    public void updateAverages0() {
        //don't update bond average
    }

    public static BOND getInstance() {
        return instance == null ? instance = new BOND() : instance;
    }
}
