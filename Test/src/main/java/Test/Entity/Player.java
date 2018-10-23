package Test.Entity;

import java.util.ArrayList;

public class Player
{
	private int index;
	private double bet;
	private double currentMoney;
	private boolean lose;
	private ArrayList<Card> receivedCards;
	private static int localIndex=0;

	public Player()
	{
		this.index=localIndex;
		localIndex++;
		this.bet=0;
		this.currentMoney=100;
		this.lose=false;
		this.receivedCards=new ArrayList<>();
	}

	//	only clears some info since Clear() is used at the start of every game
	public void Clear()
	{
		bet=0;
		lose=false;
		receivedCards.clear();
	}

	public int getIndex()
	{
		return index;
	}

	public void setIndex(int index)
	{
		this.index=index;
	}

	public double getBet()
	{
		return bet;
	}

	public void setBet(double bet)
	{
		this.bet=bet;
	}

	public double getCurrentMoney()
	{
		return currentMoney;
	}

	public void setCurrentMoney(double currentMoney)
	{
		this.currentMoney=currentMoney;
	}

	public boolean isLose()
	{
		return lose;
	}

	public void setLose(boolean lose)
	{
		this.lose=lose;
	}

	public ArrayList<Card> getReceivedCards()
	{
		return receivedCards;
	}

	public void setReceivedCards(ArrayList<Card> receivedCards)
	{
		this.receivedCards=receivedCards;
	}

	public int getCardNumber()
	{
		return this.receivedCards.size();
	}

	public void receiveCard(Card input)
	{
		receivedCards.add(input);
	}

	int getCardValueSum()
	{
		int result=0;
		for(Card r: receivedCards)
		{
			if(r.getFigure()==1)//判断Ace
			{
				if(result<=Parameters.getTargetPoint()-11)
					result+=11;
				else
					result+=1;
			}
			else
			{
				result+=r.getFaceValue();
			}
		}
		return result;
	}
}
