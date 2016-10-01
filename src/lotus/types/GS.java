package lotus.types;

import lotus.LotusClient;
import lotus.Stocks;

import static lotus.algo.NonBondAlgo.*;
import lotus.util.DirType;

public class GS extends Symbol {

    private static GS instance;

    public GS() {
        super("GS");
    }

    @Override
    protected void updateAverages0() {
        Stocks.setGS(getMarketVal());
    }

    public static GS getInstance() {
        return instance == null ? instance = new GS() : instance;
    }

}
