//DATA TYPE TO STORE THE CURRENT AMOUNT OF STOCK WE HAVE
public class Stocks
{
  public final int BONDPRICE = 1000;
  public int BONDamt;
  public int VALBZamt;
  public int VALEamt;
  public int GSamt;
  public int MSamt;
  public int WFCamt;
  public int XLFamt;

  public Stocks(int BOND, int VALBZ, int VALE, int GS, int MS, int WFC, int XLF)
  {
    this.BONDamt = BOND;
    this.VALBZamt = VALBZ;
    this.VALEamt = VALE;
    this.GSamt = GS;
    this.MSamt = MS;
    this.WFCamt = WFC;
    this.XLFamt = XLF;
  }

  public int getBONDAmt()
  {
    return this.BONDamt;
  }

  public int getVALBZamt()
  {
    return this.VALBZamt;
  }

  public int getVALEamt()
  {
    return this.VALEamt;
  }
  public int getGSamt()
  {
    return this.GSamt;
  }
  public int getMSamt()
  {
    return this.MSamt;
  }
  public int getWFCamt()
  {
    return this.WFCamt;
  }
  public int getXLFamt()
  {
    return this.XLFamt;
  }
}
