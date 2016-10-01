//DATA TYPE TO STORE THE CURRENT AMOUNT OF STOCK WE HAVE
public class Stocks
{
  public final int BONDPRICE = 1000;
  public final int XLFfee = 100;
  public final int VALBZfee = 10;
  public int VALBZprice;
  public int VALEprice;
  public int GSprice;
  public int MSprice;
  public int WFCprice;
  public int XLFprice;
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
  public void setVALBZ(int price)
  {
	  this.VALBZprice = price;
  }
  public void setVALE(int price)
  {
	  this.VALEprice = price;
  }
  public void setGS(int price)
  {
	  this.GSprice = price;
  }
  public void setMS(int price)
  {
	  this.MSprice = price;
  }
  public void setWFC(int price)
  {
	  this.WFCprice = price;
  }
  public void setXLF(int price)
  {
	  this.XLFprice = price;
  }
  public int getBONDamt()
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
  public boolean worthItToConvertXLF()
  {
    int totVal = 0;
    if (this.getBONDamt()>=30&&this.getGSamt()>=20&&this.getMSamt()>=30 && this.getWFCamt()>=20)
    {
      totVal = 30*this.BONDPRICE + 20*this.GSprice + 30*this.MSprice + 20*this.WFCprice + XLFfee;
      if (totVal<this.XLFprice)
      {
        return true;
      }
    }
    return false;
  }
  public boolean worthItToConvertVALE()
  {
    int totVal = 0;
    if(this.getVALBZamt()>=0||this.getVALEamt()>=0)
    {
      totVal =this.VALBZprice+VALBZfee;
      if(totVal<VALEprice)
      {
        return true;
      }
    }
    return false;
  }
}
