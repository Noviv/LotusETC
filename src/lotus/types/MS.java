package lotus.types;

import lotus.Stocks;
import static lotus.algo.NonBondAlgo.*;

public class MS extends Symbol {

    private static MS instance;

    public MS() {
        super("MS");
    }

    @Override
    protected void updateAverages0() {
        Stocks.setMS(getMarketVal());
    }

    public static MS getInstance() {
        return instance == null ? instance = new MS() : instance;
    }
}
