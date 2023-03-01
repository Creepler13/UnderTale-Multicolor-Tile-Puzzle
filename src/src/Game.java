package src;

import java.util.Random;

import infoScreens.MapDataInfoScreen;
import misc.Colors;
import misc.InfoScreenPanel;

public class Game {

	public int w = 10, h = 10;
	public int x = 0, y = 0;
	public Colors[][] map = new Colors[w][h];

	public Colors[][] makeMap(int width, int height) {
		Colors[][] map = new Colors[width][height];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = Utils.getRandomColor();
			}
		}
		return map;
	}

	Window window;

	public boolean orangeSmell = false;

	public void move(int x, int y) {
		if (this.x + x < 0 || this.x + x >= w || this.y + y < 0 || this.y + y >= h)
			return;

		this.x = this.x + x;
		this.y = this.y + y;

		// this.x = this.x + x < 0 ? 0 : this.x + x >= w - 1 ? w - 1 : this.x + x;
		// this.y = this.y + y < 0 ? 0 : this.y + y >= h - 1 ? h - 1 : this.y + y;

		switch (map[this.x][this.y]) {
		case RED:
			this.x = this.x - x;
			this.y = this.y - y;
			break;
		case BLUE:
			if (!checkBlue(this.x, this.y) || orangeSmell) {
				this.x = this.x - x;
				this.y = this.y - y;
			}
			break;
		case YELLOW:
			this.x = this.x - x;
			this.y = this.y - y;
			break;
		case ORANGE:
			orangeSmell = true;
			break;
		case PINK:
			break;
		case PURPLE:
			orangeSmell = false;
			move(x, y);
			break;
		default:
			break;
		}

	}

	public boolean checkBlue(int x, int y) {

		if (exists(x + 1, y))
			if (map[x + 1][y] == Colors.YELLOW)
				return false;
		if (exists(x - 1, y))
			if (map[x - 1][y] == Colors.YELLOW)
				return false;
		if (exists(x, y + 1))
			if (map[x][y + 1] == Colors.YELLOW)
				return false;
		if (exists(x, y - 1))
			if (map[x][y - 1] == Colors.YELLOW)
				return false;

		return true;
	}

	public boolean exists(int x, int y) {
		if (x < 0 || y < 0 || x >= w || y >= h)
			return false;
		if (map[x] != null)
			if (map[x][y] != null)
				return true;
		return false;
	}

	public void init(Window window) {
		this.window = window;

		map = makeMap(w, h);
		window.infoScreenManager.onMapUpdateAll();

	}

}
