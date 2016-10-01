package lotus.types;

import lotus.Stocks;

import static lotus.algo.NonBondAlgo.*;

public class VALBZ extends Symbol {

    private static VALBZ instance;

    public VALBZ() {
        super("VALBZ");
    }

    @Override
    public void updateAverages0() {
        Stocks.setVALBZ(getMarketVal());
    }

    public static VALBZ getInstance() {
        return instance == null ? instance = new VALBZ() : instance;
    }
}
