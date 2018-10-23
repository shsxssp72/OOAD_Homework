package Test;

import Test.Entity.Game;
import Test.Entity.Parameters;
import org.ini4j.Wini;


import java.io.*;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		readConfig();
		Game game = new Game();
		for (; ; ) {
			game.Init();
			game.MainProcess();
		}
//		ini4jTest();
	}

	private static void readConfig() {
		try {
			String fileName = "config.ini";
			Wini ini = new Wini(new File(fileName));
			int CardDeckNumberPerPile = ini.get("BlackjackConfig", "CardDeckNumberPerPile", int.class);
			int PlayerNumber = ini.get("BlackjackConfig", "PlayerNumber", int.class);
			int MaxCardNumber = ini.get("BlackjackConfig", "MaxCardNumber", int.class);
			int TargetPoint = ini.get("BlackjackConfig", "TargetPoint", int.class);
			int DealerPointInferiorLimit = ini.get("BlackjackConfig", "DealerPointInferiorLimit", int.class);
			double BonusRatio = ini.get("BlackjackConfig", "BonusRatio", double.class);
			double PlayerInitialMoney = ini.get("BlackjackConfig", "PlayerInitialMoney", double.class);
			Parameters.setCardDeckNumberPerPile(CardDeckNumberPerPile);
			Parameters.setMaxCardNumber(MaxCardNumber);
			Parameters.setPlayerNumber(PlayerNumber);
			Parameters.setTargetPoint(TargetPoint);
			Parameters.setDealerPointInferiorLimit(DealerPointInferiorLimit);
			Parameters.setBonusRatio(BonusRatio);
			Parameters.setPlayerInitialMoney(PlayerInitialMoney);
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.print("config.ini not found. Using default configuration.\n");
		}

	}

}
