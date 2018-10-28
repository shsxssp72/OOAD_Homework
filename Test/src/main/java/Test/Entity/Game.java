package Test.Entity;

import Test.Utility.IO_Interface;

import java.util.*;

public class Game
{
	private CardPile pile;
	private Player dealer;
	private LinkedList<Player> playerList;
	ArrayList<Player> naturalBlackJackList;

	public Game()
	{
		pile=new CardPile();
		playerList=new LinkedList<>();
		for(int i=0;i<Parameters.getPlayerNumber();i++)
		{
			playerList.addLast(new Player());
		}

		dealer=new Player();
		naturalBlackJackList=new ArrayList<>();
	}

	public void Init()
	{
		IO_Interface.ConsoleWriteLine("New Game!");
		dealer.Clear();
		for(Player player: playerList)
		{
			player.Clear();
		}

		pile.onStartNewGame();
		for(Player player: playerList)
		{
			player.getHand().receiveCard(pile.DealCard());
		}
		dealer.getHand().receiveCard(pile.DealCard());
		for(Player player: playerList)
		{
			player.getHand().receiveCard(pile.DealCard());
		}
		dealer.getHand().receiveCard(pile.DealCard());
	}

	private void displayInstruction(Player player)
	{
		IO_Interface.ConsoleWriteLine("");
		IO_Interface.ConsoleWriteLine("Your bet:"+player.getCash().getBet());
		IO_Interface.ConsoleWriteLine("Your current money:"+player.getCash().getCurrentMoney());
		displayPlayerCard(player,"You");
		displayDealerCard();
		IO_Interface
				.ConsoleWriteLine("1. Double\n2. PASS\n3. More Card\n4. Back\n5. All Players' Information\n6. Current Config");
	}

	private void displayConfig()
	{
		IO_Interface.ConsoleWriteLine("Current Config:");
		IO_Interface.ConsoleWriteLine("CardDeckNumberPerPile: "+Parameters.getCardDeckNumberPerPile());
		IO_Interface.ConsoleWriteLine("PlayerNumber: "+Parameters.getPlayerNumber());
		IO_Interface.ConsoleWriteLine("MaxCardNumber: "+Parameters.getMaxCardNumber());
		IO_Interface.ConsoleWriteLine("TargetPoint: "+Parameters.getTargetPoint());
		IO_Interface.ConsoleWriteLine("DealerPointInferiorLimit: "+Parameters.getDealerPointInferiorLimit());
		IO_Interface.ConsoleWriteLine("BonusRatio: "+Parameters.getBonusRatio());
		IO_Interface.ConsoleWriteLine("PlayerInitialMoney: "+Parameters.getPlayerInitialMoney());
		IO_Interface.ConsoleWriteLine("");
	}


	private void displayPlayerCard(Player player,String name)
	{
		IO_Interface.ConsoleWrite(name+" have(has) these cards: [");
		for(Card card: player.getHand().getReceivedCards())
		{
			IO_Interface.ConsoleWrite(card.toString()+",");
		}
		IO_Interface.ConsoleWriteLine("]("+player.getHand().getCardValueSum()+")");
	}

	private void displayDealerCard()
	{
		IO_Interface.ConsoleWrite("Dealer has these cards :[");
		IO_Interface.ConsoleWrite(dealer.getHand().getReceivedCards().get(0).toString());
		if(dealer.getHand().getReceivedCards().size()>1)
			IO_Interface.ConsoleWrite(",*:*");
		for(int i=2;i<dealer.getHand().getReceivedCards().size();i++)
		{
			IO_Interface.ConsoleWrite(","+dealer.getHand().getReceivedCards().get(i).toString());
		}
		IO_Interface.ConsoleWrite("]\n");
	}

	private void displayAllPlayerCard(boolean showDealerSecretCard)
	{
		for(Player r: playerList)
		{
			displayPlayerCard(r,"Player "+r.getIndex());
		}
		if(showDealerSecretCard)
		{
			displayPlayerCard(dealer,"Dealer");
		}
		else
		{
			displayDealerCard();
		}
	}

	public int getCurrentPlayerNumber()
	{
		return playerList.size();
	}

	private boolean gainMoreCard(Player player)
	{
		if(player.getHand().receiveCard(pile.DealCard())&&(!player.getHand().settle()))
		{
			IO_Interface.ConsoleWriteLine("Player "+player.getIndex()+" Lose.");
			player.setLose(true);
			return false;
		}
		else
		{
			return true;
		}
	}

	//加倍赌注并摸牌
	private void doubleBet(Player player)
	{
		try
		{
			player.getCash().DoubleBet();
			gainMoreCard(player);
		}
		catch(IllegalArgumentException e)
		{
		}
	}


	private boolean playerProcess(Player player)
	{
		//Natural Black Jack判断
		if(player.getHand().getCardValueSum()==Parameters.getTargetPoint())
		{
			naturalBlackJackList.add(player);
			IO_Interface.ConsoleWriteLine("Bingo! You have natural Black Jack!");
			return true;
		}
		for(;;)
		{
			displayInstruction(player);
			int option=IO_Interface.ReadInteger();
			switch(option)
			{
				case 1:
				{
					doubleBet(player);
					return true;
				}
				case 2:
				{
					return true;
				}
				case 3:
				{
					if(gainMoreCard(player))
					{
						break;
					}
					else
					{
						return true;
					}
				}
				case 4:
				{
					player.setLose(true);
					return false;
				}
				case 5:
				{
					displayAllPlayerCard(false);
					break;
				}
				case 6:
				{
					displayConfig();
					break;
				}
				default:
				{
					IO_Interface.ConsoleWriteLine("Please enter valid option.");
					break;
				}
			}
		}
	}

	//此处为Dealer获得牌直到上限17
	private void dealerProcess()
	{
		for(;dealer.getHand().getCardValueSum()<Parameters.getDealerPointInferiorLimit();)
		{
			dealer.getHand().receiveCard(pile.DealCard());
		}
	}

	private void setWin(Player player)
	{
		player.getCash().AddBonusToMoney();
		IO_Interface.ConsoleWriteLine("Player "+player.getIndex()+" Wins.\n");
	}


	private void judge()
	{
		int dealerResult=dealer.getHand().getCardValueSum();
		int maxValue=0, notLoseNumber=0;
		for(Player player: playerList)
		{
			if(player.getHand().getCardValueSum()>Parameters.getTargetPoint())
			{
				player.setLose(true);
				continue;
			}
			else
			{
				notLoseNumber++;
			}
			if(player.getHand().getCardValueSum()>maxValue)
			{
				maxValue=player.getHand().getCardValueSum();
			}
		}
		if(notLoseNumber>0)
		{
			if(dealerResult>Parameters.getTargetPoint())//Dealer超过界限
			{
				for(Player player: playerList)
				{
					if(!player.isLose())
					{
						setWin(player);
					}
				}
			}
			else if(maxValue>dealerResult)
			{
				for(Player player: playerList)
				{
					if(player.getHand().getCardValueSum()==maxValue)
					{
						setWin(player);
					}
				}
			}
			else if(dealerResult==Parameters.getTargetPoint()&&dealer.getHand()
					.getCardNumber()>2)//Dealer不是Natural Black Jack
			{
				for(Player player: naturalBlackJackList)
				{
					setWin(player);
				}
			}
			else
			{
				IO_Interface.ConsoleWriteLine("Dealer Wins.\n");
			}
		}
		else
		{
			IO_Interface.ConsoleWriteLine("Dealer Wins.\n");
		}
	}

	public void MainProcess()
	{
		for(Player player: playerList)
		{
			if(player.isLose())
			{
				continue;
			}
			IO_Interface.ConsoleWriteLine("Player "+player.getIndex()+"'s turn.");
			IO_Interface.ConsoleWriteLine("You have :"+player.getCash().getCurrentMoney());
			IO_Interface.ConsoleWriteLine("Enter bet:");
			int bet=IO_Interface.ReadInteger();
			try
			{
				player.getCash().Bet(bet);
			}
			catch(IllegalArgumentException e)
			{
				IO_Interface.ConsoleWriteLine("Enter bet:");
				bet=IO_Interface.ReadInteger();
				player.getCash().Bet(bet);
			}

			boolean isInNewGame=playerProcess(player);
			if(!isInNewGame)
			{
				playerList.remove(player);
			}
		}
		dealerProcess();
		displayAllPlayerCard(true);
		judge();
	}


}

