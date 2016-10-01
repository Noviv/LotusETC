package lotus.types;

import lotus.LotusClient;
import lotus.algo.BondAlgo;
import lotus.util.DirType;

public class Bond extends Symbol {

    private static Bond instance;

    private BondAlgo algo;

    private long lastMillis;

    public Bond() {
        super("BOND");

        algo = new BondAlgo();
        lastMillis = System.currentTimeMillis();
    }

    @Override
    public void calc(LotusClient client) {
        if (System.currentTimeMillis() - lastMillis > 5 * 1000 && traded) {
            traded = false;
            lastMillis = System.currentTimeMillis();

            System.out.println("RUNNING ALGORITHM");
            algo.findHighestBid(getCurrentBuyPrices());
            if (algo.checkingForBuyPrice()) {
                System.out.println("BUY @ " + algo.getBuyVal());
                client.add(this, DirType.BUY, algo.getBuyVal(), 1);
            }

            algo.findLowestBid(getCurrentSellPrices());
            if (algo.checkingForSellPrice()) {
                System.out.println("SELL @ " + algo.getSellVal());
                client.add(this, DirType.SELL, algo.getSellVal(), 1);
            }
        }
    }

    public static Bond getInstance() {
        return instance == null ? instance = new Bond() : instance;
    }
}
