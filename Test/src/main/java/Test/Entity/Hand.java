package Test.Entity;

import Test.Utility.IO_Interface;

import java.util.ArrayList;

class Hand
{
	private ArrayList<Card> receivedCards;

	private int playerIndex;

	Hand(int inPlayerIndex)
	{
		this.playerIndex=inPlayerIndex;
		this.receivedCards=new ArrayList<>();
	}

	void Clear()
	{
		receivedCards.clear();
	}

	ArrayList<Card> getReceivedCards()
	{
		return receivedCards;
	}

	int getCardNumber()
	{
		return this.receivedCards.size();
	}

	boolean receiveCard(Card input)
	{
		if(this.getCardNumber()<Parameters.getMaxCardNumber())
		{
			receivedCards.add(input);
			return true;
		}
		else
		{
			IO_Interface.ConsoleWriteLine("Can not get more card. Please show the card you have.");
			return false;
		}
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

	//Player摸牌后判定是否超过界限
	boolean settle()
	{
		int currentResult=getCardValueSum();
		return currentResult<=Parameters.getTargetPoint();
	}

}
