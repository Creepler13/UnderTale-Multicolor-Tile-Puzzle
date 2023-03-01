package misc;

import src.Game;
import src.Window;

public abstract class PathFinder {

	public Game game;
	public Window window;

	public void init(Game game, Window window) {
		this.game = game;
		this.window = window;
	}

	public PathfindingInfoScreenEvent listener;

	public void addEventListener(PathfindingInfoScreenEvent ev) {
		listener = ev;
	}

	public void callEvent(String data) {
		if (listener != null)
			listener.onUpdateData(data);
	}

	public abstract PathPart solve(int startX, int startY, int endX, int endY);

	public abstract void abort();

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

}
