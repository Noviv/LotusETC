package lotus.types;

import lotus.Stocks;

import static lotus.algo.NonBondAlgo.*;

public class Valbz extends Symbol {

    private static Valbz instance;

    public Valbz() {
        super("VALBZ");
    }

    @Override
    public void updateAverages0() {
        Stocks.setVALBZ(getMarketVal());
    }

    public static Valbz getInstance() {
        return instance == null ? instance = new Valbz() : instance;
    }
}
