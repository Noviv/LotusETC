import java.util.ArrayList;
import java.util.Iterator;

public class ETCalgoNotBond 
{
	public static int lowestSellVal;
	public static int highestBuyVal;
	public static int marketVal;

	public boolean BuyOrderCheck(ArrayList<Integer>allBuyOrders)
	{
		Iterator<Integer> it = allBuyOrders.iterator();
		Iterator<Integer> it2 = allBuyOrders.iterator();
		double total = 0;
		double counter = 0;
		while(it.hasNext())
		{
			counter++;
			int curVal = it.next();
			total = total + curVal;
			
		}
		double average = total/counter;
		while(it2.hasNext())
		{
			int curVal = it.next();
			if (Math.abs(average-curVal)>=20)
			{
				return false;
			}
		}
		return true;
	}
	
	public boolean SellOrderCheck(ArrayList<Integer>allSellOrders)
	{
		Iterator<Integer> it = allSellOrders.iterator();
		Iterator<Integer> it2 = allSellOrders.iterator();
		double total = 0;
		double counter = 0;
		while(it.hasNext())
		{
			counter++;
			int curVal = it.next();
			total = total + curVal;
			
		}
		double average = total/counter;
		while(it2.hasNext())
		{
			int curVal = it.next();
			if (Math.abs(average-curVal)>=20)
			{
				return false;
			}
		}
		return true;
	}
	public void getAverageFairValue(ArrayList<Integer>allBuyOrders, ArrayList<Integer> allSellOrders)
	{
		Iterator<Integer> it = allBuyOrders.iterator();
		double total = 0;
		double counter = 0;
		while(it.hasNext())
		{
			counter++;
			int curVal = it.next();
			total = total + curVal;
			
		}
		double average = total/counter;	
		
		Iterator<Integer> it2 = allSellOrders.iterator();
		double total1 = 0;
		double counter1 = 0;
		while(it2.hasNext())
		{
			counter1++;
			int curVal = it.next();
			total1 = total1 + curVal;
			
		}
		double average1 = total1/counter1;
		int AverageFairValue = (int)(average+average1)/2;
		marketVal = AverageFairValue;
	}
	
	
	//algorithm for checking for the highest buy bid currently
	//pre: acquire the list of all bid orders
	//post return the price at which to buy at
	  public void findHighestBid(ArrayList<Integer> currentListofBuyOrders)
	  {
	  Iterator<Integer> it = currentListofBuyOrders.iterator();
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
	
	  
	//algorithm for checking for the lowest sell bid currently
	//pre: acquire the list of all bid orders
	//post return the price at which to sell at
	  public void findLowestBid(ArrayList<Integer> currentListofSellOrders)
	{
	  Iterator<Integer> it = currentListofSellOrders.iterator();
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
	  
	public int getBuyVal()
	{
	  return highestBuyVal;
	}
	public int getSellVal()
	{
	  return lowestSellVal;
	}

	public int returnMarketVal()
	{
	  return marketVal;
	}
}
