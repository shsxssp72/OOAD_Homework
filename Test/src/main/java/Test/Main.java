package Test;

import Test.Entity.Config;
import Test.Entity.Game;


public class Main {
	public static void main(String[] args) {
		Config.Init();
		Config.readConfig();
		Config.VerifyConfig();
		Game game = new Game();
		for (; game.getCurrentPlayerNumber() != 0; ) {
			game.Init();
			game.MainProcess();
		}
	}
}
