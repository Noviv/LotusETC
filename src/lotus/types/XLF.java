package lotus.types;

import lotus.LotusClient;
import lotus.Stocks;

import static lotus.algo.NonBondAlgo.*;
import lotus.util.DirType;

public class XLF extends Symbol {

    private static XLF instance;

    public XLF() {
        super("XLF");
    }

    @Override
    protected void updateAverages0() {
        Stocks.setXLF(getMarketVal());
    }

    @Override
    public void calc(LotusClient client) {
        getAverageFairValue(XLF.getInstance().getCurrentBuyPrices(), XLF.getInstance().getCurrentSellPrices());
        int xlfValue = getMarketVal();

        getAverageFairValue(BOND.getInstance().getCurrentBuyPrices(), BOND.getInstance().getCurrentSellPrices());
        int bondValue = getMarketVal();

        getAverageFairValue(GS.getInstance().getCurrentBuyPrices(), GS.getInstance().getCurrentSellPrices());
        int gsValue = getMarketVal();

        getAverageFairValue(MS.getInstance().getCurrentBuyPrices(), MS.getInstance().getCurrentSellPrices());
        int msValue = getMarketVal();

        getAverageFairValue(WFC.getInstance().getCurrentBuyPrices(), WFC.getInstance().getCurrentSellPrices());
        int wfcValue = getMarketVal();

        if (xlfValue * 10 > bondValue * 3 + gsValue * 2 + msValue * 3 + wfcValue * 2 + 100) {
            //buy compent shares @ lowest
            client.add(BOND.getInstance(), DirType.BUY, bondValue, 3);
            client.add(GS.getInstance(), DirType.BUY, bondValue, 2);
            client.add(MS.getInstance(), DirType.BUY, bondValue, 3);
            client.add(WFC.getInstance(), DirType.BUY, bondValue, 2);
            
            //convert to XLF
            client.convert(XLF.getInstance(), DirType.BUY, 10);
            //sell XLF @ highest
            
            client.add(XLF.getInstance(), DirType.SELL, 0, 10);
            
        } else {
            //buy XLF @ lowest
            //conver to component shares
            client.convert(XLF.getInstance(), DirType.SELL, 10);
            //sell shares @ highest
        }
    }

    public static XLF getInstance() {
        return instance == null ? instance = new XLF() : instance;
    }
}
