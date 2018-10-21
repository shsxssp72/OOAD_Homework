package Test.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

public class CardPile
{
	private ArrayList<Card> pile;
	private static int inferiorLimit=Parameters.getCardDeckNumberPerPile()*Parameters.getCardNumPerDeck()/2;

	public CardPile()
	{
		pile=new ArrayList<>();
		for(int i=0;i<Parameters.getCardDeckNumberPerPile();i++)
		{
			pile.addAll(CardDeck.getACardDeck());
		}

		Collections.shuffle(pile,new Random(new Date().getTime()));
	}

	//! @Description 初始化牌堆
	//! @param null
	//! @return
	//! throws
	private void Shuffle()
	{
		pile.clear();
		for(int i=0;i<Parameters.getCardDeckNumberPerPile();i++)
		{
			pile.addAll(CardDeck.getACardDeck());
		}
		Collections.shuffle(pile,new Random(new Date().getTime()));
	}

	//! @Description 从当前牌堆顶部发出一张牌
	//! @param null
	//! @return 移交的牌
	//! throws
	Card DealCard()
	{
		//TODO 关于发牌逻辑，若一局游戏中少于一半的动作
		if(!pile.isEmpty())
			return pile.remove(0);
		else
		{
			pile.addAll(CardDeck.getACardDeck());
			return pile.remove(0);
		}
	}

	//! @Description 新一轮游戏开始时判断是否需要洗牌，即牌堆初始化
	//! @param null
	//! @return
	//! throws
	void onStartNewGame()
	{
		if(pile.size()<inferiorLimit)
			Shuffle();
	}

}
