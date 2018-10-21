package Test.Entity;

import java.util.LinkedList;
import java.util.List;

import Test.Entity.Parameters;

import static Test.Entity.Card.CardPatternType.*;

public class CardDeck
{
	public CardDeck()
	{
	}

	private static int localIndex=0;

	//! @Description 返回一整副牌
	//! @param null
	//! @return
	//! throws
	static LinkedList<Card> getACardDeck()
	{

		LinkedList<Card> cardList=new LinkedList<>();
		for(int i=0;i<Parameters.getCardNumPerPattern();i++)
		{
			cardList.addLast(new Card(Spade,i+1,i+1<10?i+1:10,localIndex));
			cardList.addLast(new Card(Heart,i+1,i+1<10?i+1:10,localIndex));
			cardList.addLast(new Card(Diamond,i+1,i+1<10?i+1:10,localIndex));
			cardList.addLast(new Card(Club,i+1,i+1<10?i+1:10,localIndex));
		}
		localIndex++;
		return cardList;
	}

}
