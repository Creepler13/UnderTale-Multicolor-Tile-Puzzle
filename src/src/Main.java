package src;

import infoScreens.InfoScreenManagerInfoScreen;
import misc.InfoScreenManager;

public class Main {
	public static void main(String[] args) {

		InfoScreenManager.registerInfoScreenPanels();

		Game game = new Game();
		Window window = new Window();

		window.openWindow(game);
		game.init(window);
		window.infoScreenManager.openInfoScreen(new InfoScreenManagerInfoScreen());

	}

}
