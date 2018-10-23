package Test.Entity;

public class Parameters {
	private static final int CARD_NUM_PER_PATTERN = 13;
	private static final int PATTERN_NUMBER = 4;
	private static final int CARD_NUM_PER_DECK = CARD_NUM_PER_PATTERN * PATTERN_NUMBER;
	private static int CARD_DECK_NUMBER_PER_PILE = 1;

	/**
	 * default settings will be overwritten
	 **/
	private static int PLAYER_NUMBER = 1;
	private static int MAX_CARD_NUMBER = 5;
	private static int TARGET_POINT = 21;
	private static int DEALER_POINT_INFERIOR_LIMIT = 17;
	private static double BONUS_RATIO = 1.5;
	private static double PLAYER_INITIAL_MONEY = 100;

	public static int getCardNumPerPattern() {
		return CARD_NUM_PER_PATTERN;
	}

	public static int getPatternNumber() {
		return PATTERN_NUMBER;
	}

	public static int getCardNumPerDeck() {
		return CARD_NUM_PER_DECK;
	}

	public static int getCardDeckNumberPerPile() {
		return CARD_DECK_NUMBER_PER_PILE;
	}

	public static void setCardDeckNumberPerPile(int cardDeckNumberPerPile) {
		CARD_DECK_NUMBER_PER_PILE = cardDeckNumberPerPile;
	}

	public static int getPlayerNumber() {
		return PLAYER_NUMBER;
	}

	public static void setPlayerNumber(int playerNumber) {
		PLAYER_NUMBER = playerNumber;
	}

	public static int getMaxCardNumber() {
		return MAX_CARD_NUMBER;
	}

	public static void setMaxCardNumber(int maxCardNumber) {
		MAX_CARD_NUMBER = maxCardNumber;
	}

	public static int getTargetPoint() {
		return TARGET_POINT;
	}

	public static void setTargetPoint(int targetPoint) {
		TARGET_POINT = targetPoint;
	}

	public static int getDealerPointInferiorLimit() {
		return DEALER_POINT_INFERIOR_LIMIT;
	}

	public static void setDealerPointInferiorLimit(int dealerPointInferiorLimit) {
		DEALER_POINT_INFERIOR_LIMIT = dealerPointInferiorLimit;
	}

	public static double getBonusRatio() {
		return BONUS_RATIO;
	}

	public static void setBonusRatio(double bonusRatio) {
		BONUS_RATIO = bonusRatio;
	}

	public static double getPlayerInitialMoney() {
		return PLAYER_INITIAL_MONEY;
	}

	public static void setPlayerInitialMoney(double playerInitialMoney) {
		PLAYER_INITIAL_MONEY = playerInitialMoney;
	}
}
