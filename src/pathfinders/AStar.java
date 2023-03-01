package pathfinders;

import java.util.ArrayList;

import misc.AStarPart;
import misc.PathFinder;

public class AStar extends PathFinder {

	public ArrayList<AStarPart> PartsTODO = new ArrayList<>();
	public AStarPart bestPart;

	public AStarPart solve(int startX, int startY, int x, int y) {
		PartsTODO.clear();
		PartsTODO.add(new AStarPart(startX, startY, game.orangeSmell, 0, null));

		while (PartsTODO.size() > 0) {
			if (abort) {
				abort = false;
				return null;
			}

			bestPart = PartsTODO.remove(0);
			if (bestPart.x == x && bestPart.y == y)
				break;

			if (game.exists(bestPart.x + 1, bestPart.y))
				addPart(makePart(bestPart.x + 1, bestPart.y, bestPart, x, y));
			if (game.exists(bestPart.x - 1, bestPart.y))
				addPart(makePart(bestPart.x - 1, bestPart.y, bestPart, x, y));
			if (game.exists(bestPart.x, bestPart.y + 1))
				addPart(makePart(bestPart.x, bestPart.y + 1, bestPart, x, y));
			if (game.exists(bestPart.x, bestPart.y - 1))
				addPart(makePart(bestPart.x, bestPart.y - 1, bestPart, x, y));

			callEvent("Paths: " + PartsTODO.size());

		}

		return bestPart;
	}

	public AStarPart makePart(int x, int y, AStarPart parentPart, int endX, int endY) {
		if (hasVisited(x, y, parentPart))
			return null;
		double dist = Math.sqrt((Math.pow(x - endX, 2) * Math.pow(y - endY, 2))) / 2;
		switch (game.map[x][y]) {
		case BLUE:
			if (!game.checkBlue(x, y) || parentPart.smellsLikeOrange)
				return null;
			else
				return new AStarPart(x, y, parentPart.smellsLikeOrange, parentPart.score + 1 + dist, parentPart);
		case PINK:
			return new AStarPart(x, y, parentPart.smellsLikeOrange, parentPart.score + 1 + dist, parentPart);
		case ORANGE:
			return new AStarPart(x, y, true, parentPart.score + 1 + dist, parentPart);
		case PURPLE:
			AStarPart p = new AStarPart(x, y, false, parentPart.score + 1 + dist, parentPart);
			if (!game.exists(x + (x - parentPart.x), y + (y - parentPart.y)))
				return p;
			AStarPart p2 = makePart(x + (x - parentPart.x), y + (y - parentPart.y), p, endX, endY);
			return p2 == null ? p : p2;
		default:
			return null;
		}
	}

	private boolean hasVisited(int x, int y, AStarPart part) {
		AStarPart temp = part;
		while (temp.parent != null) {
			if (temp.x == x && temp.y == y)
				return true;
			temp = (AStarPart) temp.parent;
		}
		return false;
	}

	public void addPart(AStarPart part) {
		if (part == null)
			return;
		for (int i = 0; i < PartsTODO.size(); i++) {
			if (PartsTODO.get(i).score > part.score) {
				PartsTODO.add(i, part);
				return;
			}
		}
		PartsTODO.add(part);
	}

	boolean abort = false;

	@Override
	public void abort() {
		abort = true;
	}

}
