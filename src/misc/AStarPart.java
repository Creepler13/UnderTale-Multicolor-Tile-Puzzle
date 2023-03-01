package misc;

public class AStarPart extends PathPart {

	public boolean smellsLikeOrange;
	public double score;

	public AStarPart(int x, int y, boolean smellsLikeOrange, double score, AStarPart parent) {
		super(x, y, parent);
		this.smellsLikeOrange = smellsLikeOrange;
		this.score = score;
	}

	@Override
	public String toString() {
		return x + " " + y + "->" + score;
	}

}
