package Test.Entity;

import java.util.*;

public class Game
{
	private CardPile pile;
	private Player dealer;
	private LinkedList<Player> playerList;

	public Game()
	{
		pile=new CardPile();
		playerList=new LinkedList<>();
		for(int i=0;i<Parameters.getPlayerNumber();i++)
		{
			playerList.addLast(new Player());
		}

		dealer=new Player();
	}

	//! @Description 新一局游戏开始时的初始化工作，包括玩家信息，牌堆和牌
	//! @param null
	//! @return
	//! throws
	public void Init()
	{
		System.out.println("New Game!");
		dealer.Clear();
		for(Player player: playerList)
		{
			player.Clear();
		}

		pile.onStartNewGame();
		for(Player player: playerList)
		{
			player.receiveCard(pile.DealCard());
		}
		dealer.receiveCard(pile.DealCard());
		for(Player player: playerList)
		{
			player.receiveCard(pile.DealCard());
		}
		dealer.receiveCard(pile.DealCard());
	}

	//! @Description 输出指示信息，包括玩家当前信息，庄家的牌，可用选项
	//! @param null
	//! @return
	//! throws
	private void displayInstruction(Player player)
	{
		System.out.print("Your bet:"+player.getBet()+"\n");
		System.out.print("Your current money:"+player.getCurrentMoney()+"\n");
		System.out.print("You have these cards:");
		for(Card r: player.getReceivedCards())
		{
			System.out.print(r.getFaceValue()+",");
		}
		System.out.print("\nDealer has:");

		System.out.print(dealer.getReceivedCards().get(0).getFaceValue());
		if(dealer.getReceivedCards().size()>1)
			System.out.print(",Secret");
		for(int i=2;i<dealer.getReceivedCards().size();i++)
		{
			System.out.print(","+dealer.getReceivedCards().get(i).getFaceValue());
		}
		System.out.print("\n1. Double\n2. PASS\n3. More Card\n4. Back\n5. All Players' Information\n");
	}

	//! @Description 展示所有玩家的牌
	//! @param null
	//! @return
	//! throws
	private void showOtherPlayerInf()
	{
		for(Player r: playerList)
		{
			System.out.print("Player "+r.getIndex()+" has these cards:");
			for(Card t: r.getReceivedCards())
			{
				System.out.print(t.getFaceValue()+",");
			}
			System.out.print("\n");
		}
	}

	//! @Description 判断当前玩家的输赢情况
	//! @param null
	//! @return
	//! throws
	private boolean Settle(Player player)
	{
//		int dealResult=dealer.getCardValueSum();
		int currentResult=player.getCardValueSum();
		if(currentResult>Parameters.getTargetPoint())
		{
			System.out.print(player.getIndex()+" Lose.\n");
			player.setLose(true);
			return false;
		}
		return true;
//		if(dealResult>Parameters.getTargetPoint())
//		{
//			System.out.print(player.getIndex()+" Win.\n");
//			dealer.setLose(true);
//			return true;
//		}
//		if(Parameters.getTargetPoint()-currentResult<Parameters.getTargetPoint()-dealResult)
//		{
//			System.out.print(player.getIndex()+" Win.\n");
//			dealer.setLose(true);
//			return true;
//		}
//		else
//		{
//			System.out.print(player.getIndex()+" Lose.\n");
//			player.setLose(true);
//			return false;
//		}
	}

	//! @Description 发牌
	//! @param null
	//! @return
	//! throws
	private boolean Deal(Player player)
	{
		if(player.getCardNumber()<Parameters.getMaxCardNumber())
		{
			player.receiveCard(pile.DealCard());
			return true;
		}
		else
		{
			System.out.print("Can not get more card. Please show the card you have.\n");
			return false;
		}
	}

	private int readOption()
	{
		int option=0;
		Scanner sc=new Scanner(System.in);
		String input=sc.nextLine();
		option=Integer.parseInt(input);
		return option;
	}

	//! @Description 玩家操作的界面
	//! @param null
	//! @return
	//! throws
	private void PlayerProcess(Player player)
	{
		for(;;)
		{
			displayInstruction(player);
			int option=readOption();
			switch(option)
			{
				case 1:
				{
					player.setCurrentMoney(player.getCurrentMoney()-player.getBet());
					player.setBet(player.getBet()*2);
					break;
				}
				case 2:
				{
					return;
				}
				case 3:
				{
					if(!Deal(player))
						if(!Settle(player))
						{
							player.setLose(true);
							return;
						}
					break;
				}
				case 4:
				{
					player.setLose(true);
					return;
				}
				case 5:
				{
					showOtherPlayerInf();
					break;
				}
				default:
				{
					System.out.print("Please enter valid option.\n");
					break;
				}
			}
		}
	}

	//! @Description 总体流程
	//! @param null
	//! @return
	//! throws
	public void MainProcess()
	{

		for(Player player: playerList)
		{
			if(player.isLose())
				continue;
			System.out.print("Player "+player.getIndex()+"'s turn.\n");
			System.out.print("Enter money:\n");
			int money=0;
			Scanner sc=new Scanner(System.in);
			String input=sc.nextLine();
			money=Integer.parseInt(input);

			player.setCurrentMoney(player.getCurrentMoney()-money);
			player.setBet(money);
			PlayerProcess(player);
//			if(PlayerProcess(player))
//				continue;
//			else
//			{
//				if(dealer.isLose())
//					return;
//			}
//			if(playerList.isEmpty())
//				break;
		}
		for(;dealer.getCardValueSum()<Parameters.getDealerPointInferiorLimit();)
			dealer.receiveCard(pile.DealCard());
		System.out.print("Dealer has these cards:");
		for(Card card:dealer.getReceivedCards())
			System.out.print(card.getFaceValue()+",");
		System.out.print("\n");

		int dealerResult=dealer.getCardValueSum();
		int maxValue=0, maxIndex=-1;
		for(Player player: playerList)
		{
			if(player.getCardValueSum()>Parameters.getTargetPoint())
			{
				player.setLose(true);
				continue;
			}
			if(player.getCardValueSum()>maxValue)
			{
				maxIndex=player.getIndex();
				maxValue=player.getCardValueSum();
			}
		}

		if(dealerResult>Parameters.getTargetPoint())
		{
			for(Player player: playerList)
			{
				if(!player.isLose())
				{
					player.setCurrentMoney(player.getCurrentMoney()+player.getBet()*Parameters.getBonusRatio());
					System.out.print("Player "+player.getIndex()+" Wins.\n");
				}
			}
		}
		else
		{
			if(maxValue>dealerResult)
			{
				for(Player player: playerList)
					if(player.getIndex()==maxIndex)
					{
						player.setCurrentMoney(player.getCurrentMoney()+player.getBet()*Parameters.getBonusRatio());
						System.out.print("Player "+player.getIndex()+" Wins.\n");
					}
			}
			else
			{
				System.out.print("Dealer Wins.\n");
			}
		}
	}

}
