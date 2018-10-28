package Test.Entity;

import Test.Utility.IO_Interface;

public class Cash
{
	private double bet;
	private double currentMoney;

	Cash()
	{
		this.bet=0;
		this.currentMoney=100;
	}

	public void Clear()
	{
		bet=0;
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

	public void AddBonusToMoney()
	{
		currentMoney+=bet*Parameters.getBonusRatio();
	}

	public void DoubleBet()
	{
		if(bet>currentMoney)
		{
			IO_Interface.ConsoleWriteLine("No enough money.");
			throw new IllegalArgumentException();
		}
		currentMoney-=bet;
		bet*=2;
	}

	public void Bet(double inBet)
	{
		if(inBet>currentMoney)
		{
			IO_Interface.ConsoleWriteLine("No enough money.");
			throw new IllegalArgumentException();
		}
		if(inBet<0)
		{
			IO_Interface.ConsoleWriteLine("Please enter a valid number.");
			throw new IllegalArgumentException();
		}
		currentMoney-=inBet;
		bet=inBet;
	}
}
