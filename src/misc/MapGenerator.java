package misc;

import src.Game;

import src.Window;

public abstract class MapGenerator {

	public Game game;
	public Window window;

	public abstract Colors[][] generateMap(int w, int h);

	public void newMap(int w, int h) {
		game.map = generateMap(w, h);
		window.render();
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}
