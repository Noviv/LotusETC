package lotus.types;

import lotus.Stocks;

import static lotus.algo.NonBondAlgo.*;

public class XLF extends Symbol {

    private static XLF instance;

    public XLF() {
        super("XLF");
    }

    @Override
    protected void updateAverages0() {
        Stocks.setXLF(getMarketVal());
    }

    public static XLF getInstance() {
        return instance == null ? instance = new XLF() : instance;
    }
}
