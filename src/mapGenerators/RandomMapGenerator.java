package mapGenerators;

import misc.Colors;
import misc.MapGenerator;
import src.Utils;

public class RandomMapGenerator extends MapGenerator {

	@Override
	public Colors[][] generateMap(int w, int h) {
		Colors[][] map = new Colors[w][h];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = Utils.getRandomColor();
			}
		}

		return map;
	}

}
