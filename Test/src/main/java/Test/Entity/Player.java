package Test.Entity;

public class Player
{
	private int index;

	private boolean lose;
	private Hand hand;
	private Cash cash;
	private static int localIndex=0;

	public Player()
	{
		this.index=localIndex;
		localIndex++;
		this.lose=false;
		hand=new Hand(this.index);
		cash=new Cash();
	}

	//	only clears some info since Clear() is used at the start of every game
	public void Clear()
	{
		lose=false;
		hand.Clear();
		cash.Clear();
	}

	public int getIndex()
	{
		return index;
	}

	public void setIndex(int index)
	{
		this.index=index;
	}

	public boolean isLose()
	{
		return lose;
	}

	public void setLose(boolean lose)
	{
		this.lose=lose;
	}

	public Hand getHand()
	{
		return hand;
	}

	public void setHand(Hand hand)
	{
		this.hand=hand;
	}

	public Cash getCash()
	{
		return cash;
	}

	public void setCash(Cash cash)
	{
		this.cash=cash;
	}
}
