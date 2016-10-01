public class ETCalgo()
{

public int lowestSellVal;
public int highestBuyVal;
public final int marketVal;


public int getBuyVal()
{
  reutrn highestBuyVal;
}
public int getSellVal()
{
  return lowestSellVal;
}
//getting the market Value for a given stock
//pre: value is not null
//post: stores the value into the instance variable marketVal
public void getMarketVal(int value)
{
  marketVal = value;
}
public int returnMarketVal()
{
  returnMarketVal();
}

//algorithm for checking for the lowest sell bid currently
//pre: acquire the list of all bid orders
//post return the price at which to sell at
  public void findLowestBid(ArrayList<Integer> currentListofSellOrders)
{
  Iterator(Integer) it = currentListofSellOrders.iterator();
  int minVal = Integer.MAX_VALUE;
  while(it.hasNext())
  {
    int curVal = it.next();
    if(curVal<minVal)
    {
      minVal = curVal;
    }
  }
  lowestSellVal = minVal+1;
}



//algorithm for checking for the highest buy bid currently
//pre: acquire the list of all bid orders
//post return the price at which to buy at
  public void findHighestBid(ArrayList<Integer> currentListofBuyOrders)
  {
  Iterator(Integer) it = currentListofBuyOrders.iterator();
  int maxVal = Integer.MIN_VALUE;
  while(it.hasNext())
  {
    int curVal = it.next();
    if(curVal>maxVal)
    {
      maxVal = curVal;
    }
  }
  highestBuyVal = maxVal-1;
}


//finds if the highest Buy price is less than the marketVal
//pre: acqure the market Val for a given stock, and the highest buy value
//post: return true if buy value is less than the market value
//return false if it is not
  public boolean checkingForBuyPrice()
  {
  if(highestBuyVal<marketVal)
  {
    return true;
  }
  return false;
}

//finds if the lowest sell price is greater than the current market value
//pre: acquire the market value for a given stock, and the lowest sell value
//for a given stock
//post: return true if the sell value is greater than the market value
//false otherwise
  public boolean checkingForSellPrice()
  {
  if (lowestSellVal>marketVal)
  {
    return true;
  }
  return false;
}
}
