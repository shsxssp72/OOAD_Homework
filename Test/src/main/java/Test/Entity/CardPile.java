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


	private void Shuffle()
	{
		pile.clear();
		for(int i=0;i<Parameters.getCardDeckNumberPerPile();i++)
		{
			pile.addAll(CardDeck.getACardDeck());
		}
		Collections.shuffle(pile,new Random(new Date().getTime()));
	}


	Card DealCard()
	{
		if(!pile.isEmpty())
			return pile.remove(0);
		else
		{
			System.exit(-1);
			return new Card(Card.CardPatternType.Diamond,-1,-1,-1);
		}
	}


	void onStartNewGame()
	{
		if(pile.size()<inferiorLimit)
			Shuffle();
	}

}
