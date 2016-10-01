package lotus.types;

import lotus.LotusClient;
import lotus.Stocks;

import static lotus.algo.NonBondAlgo.*;
import lotus.util.DirType;

public class Vale extends Symbol {

    private static Vale instance;

    public Vale() {
        super("VALE");
    }

    @Override
    protected void updateAverages0() {
        Stocks.setVALE(getMarketVal());
    }

    public static Vale getInstance() {
        return instance == null ? instance = new Vale() : instance;
    }

}
