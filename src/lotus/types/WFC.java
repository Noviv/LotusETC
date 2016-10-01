package lotus.types;

import lotus.Stocks;

import static lotus.algo.NonBondAlgo.*;

public class WFC extends Symbol {

    private static WFC instance;

    public WFC() {
        super("WFC");
    }

    @Override
    protected void updateAverages0() {
        Stocks.setWFC(getMarketVal());
    }

    public static WFC getInstance() {
        return instance == null ? instance = new WFC() : instance;
    }

}
