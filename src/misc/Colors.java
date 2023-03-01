package misc;
import java.awt.Color;

public enum Colors {
	RED(Color.red), BLUE(Color.blue), PURPLE(Color.MAGENTA), YELLOW(Color.YELLOW), PINK(Color.pink),
	ORANGE(Color.ORANGE);

	public final Color color;

	private Colors(Color color) {
		this.color = color;
	}

}
