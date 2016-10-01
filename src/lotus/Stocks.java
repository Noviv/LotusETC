package lotus;

//DATA TYPE TO STORE THE CURRENT AMOUNT OF STOCK WE HAVE

public class Stocks {

    public static final int BONDPRICE = 1000;
    public static final int XLFfee = 100;
    public static final int VALBZfee = 10;
    public static int VALBZprice;
    public static int VALEprice;
    public static int GSprice;
    public static int MSprice;
    public static int WFCprice;
    public static int XLFprice;
    public static int BONDamt;
    public static int VALBZamt;
    public static int VALEamt;
    public static int GSamt;
    public static int MSamt;
    public static int WFCamt;
    public static int XLFamt;

    public static void setVALBZ(int price) {
        VALBZprice = price;
    }

    public static void setVALE(int price) {
        VALEprice = price;
    }

    public static void setGS(int price) {
        GSprice = price;
    }

    public static void setMS(int price) {
        MSprice = price;
    }

    public static void setWFC(int price) {
        WFCprice = price;
    }

    public static void setXLF(int price) {
        XLFprice = price;
    }

    public static int getBONDamt() {
        return BONDamt;
    }

    public static int getVALBZamt() {
        return VALBZamt;
    }

    public static int getVALEamt() {
        return VALEamt;
    }

    public static int getGSamt() {
        return GSamt;
    }

    public static int getMSamt() {
        return MSamt;
    }

    public static int getWFCamt() {
        return WFCamt;
    }

    public static int getXLFamt() {
        return XLFamt;
    }

    public static boolean worthItToConvertXLF() {
        int totVal = 0;
        totVal = 30 * BONDPRICE + 20 * GSprice + 30 * MSprice + 20 * WFCprice + XLFfee;
        if (totVal < XLFprice) {
            return true;
        }
        return false;
    }

    public static boolean worthItToConvertVALE() {
        int totVal = 0;
        totVal = VALBZprice + VALBZfee;
        if (totVal < VALEprice) {
            return true;
        }
        return false;
    }
}
