package misc;

public class PathPart {

	public PathPart parent;

	public int x;
	public int y;

	public PathPart(int x, int y, PathPart parent) {
		this.parent = parent;
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return x + " " + y;
	}

}
