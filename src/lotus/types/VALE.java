package lotus.types;

import lotus.LotusClient;
import lotus.Stocks;

import static lotus.algo.NonBondAlgo.*;
import lotus.util.DirType;

public class VALE extends Symbol {

    private static VALE instance;

    public VALE() {
        super("VALE");
    }

    @Override
    protected void updateAverages0() {
        Stocks.setVALE(getMarketVal());
    }

    public static VALE getInstance() {
        return instance == null ? instance = new VALE() : instance;
    }

}
