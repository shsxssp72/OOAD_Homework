package Test.Entity;

public class Player
{
	private int index;

	private boolean lose;
	private Hand hand;
	private Cash cash;
	private static int localIndex=0;

	Player()
	{
		this.index=localIndex;
		localIndex++;
		this.lose=false;
		hand=new Hand(this.index);
		cash=new Cash();
	}

	//	only clears some info since Clear() is used at the start of every game
	void Clear()
	{
		lose=false;
		hand.Clear();
		cash.Clear();
	}

	int getIndex()
	{
		return index;
	}

	void setIndex(int index)
	{
		this.index=index;
	}

	boolean isLose()
	{
		return lose;
	}

	void setLose(boolean lose)
	{
		this.lose=lose;
	}

	Hand getHand()
	{
		return hand;
	}

	void setHand(Hand hand)
	{
		this.hand=hand;
	}

	Cash getCash()
	{
		return cash;
	}

	void setCash(Cash cash)
	{
		this.cash=cash;
	}
}
