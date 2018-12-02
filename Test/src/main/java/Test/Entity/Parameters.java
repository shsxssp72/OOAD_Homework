package Test.Entity;

public class Parameters
{
	private static final int CARD_NUM_PER_PATTERN=13;
	private static final int PATTERN_NUMBER=4;
	private static final int CARD_NUM_PER_DECK=CARD_NUM_PER_PATTERN*PATTERN_NUMBER;
	private static int CARD_DECK_NUMBER_PER_PILE=1;

	/**
	 * default settings will be overwritten
	 **/
	private static int PLAYER_NUMBER=1;
	private static int MAX_CARD_NUMBER=5;
	private static int TARGET_POINT=21;
	private static int DEALER_POINT_INFERIOR_LIMIT=17;
	private static double BONUS_RATIO=1.5;
	private static double PLAYER_INITIAL_MONEY=100;

	static int getCardNumPerPattern()
	{
		return CARD_NUM_PER_PATTERN;
	}

	static int getPatternNumber()
	{
		return PATTERN_NUMBER;
	}

	static int getCardNumPerDeck()
	{
		return CARD_NUM_PER_DECK;
	}

	static int getCardDeckNumberPerPile()
	{
		return CARD_DECK_NUMBER_PER_PILE;
	}

	static void setCardDeckNumberPerPile(int cardDeckNumberPerPile)
	{
		CARD_DECK_NUMBER_PER_PILE=cardDeckNumberPerPile;
	}

	static int getPlayerNumber()
	{
		return PLAYER_NUMBER;
	}

	static void setPlayerNumber(int playerNumber)
	{
		PLAYER_NUMBER=playerNumber;
	}

	static int getMaxCardNumber()
	{
		return MAX_CARD_NUMBER;
	}

	static void setMaxCardNumber(int maxCardNumber)
	{
		MAX_CARD_NUMBER=maxCardNumber;
	}

	static int getTargetPoint()
	{
		return TARGET_POINT;
	}

	static void setTargetPoint(int targetPoint)
	{
		TARGET_POINT=targetPoint;
	}

	static int getDealerPointInferiorLimit()
	{
		return DEALER_POINT_INFERIOR_LIMIT;
	}

	static void setDealerPointInferiorLimit(int dealerPointInferiorLimit)
	{
		DEALER_POINT_INFERIOR_LIMIT=dealerPointInferiorLimit;
	}

	static double getBonusRatio()
	{
		return BONUS_RATIO;
	}

	static void setBonusRatio(double bonusRatio)
	{
		BONUS_RATIO=bonusRatio;
	}

	static double getPlayerInitialMoney()
	{
		return PLAYER_INITIAL_MONEY;
	}

	static void setPlayerInitialMoney(double playerInitialMoney)
	{
		PLAYER_INITIAL_MONEY=playerInitialMoney;
	}
}
