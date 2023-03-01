package misc;

import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import src.Game;
import src.Window;

public abstract class InfoScreenPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2505891327757266350L;
	public Window window;
	public JFrame ownFrame;
	public Game game;
	public String titel;
	public int x, y, width, height;

	public InfoScreenPanel(String titel, int x, int y, int width, int height) {
		this.x = x;
		this.titel = titel;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public abstract void onOpen();

	public abstract void onMapUpdate();

	public abstract void onPlayerMove(int oldX, int oldY, int newX, int newY);

	public abstract void onDraw(Graphics2D gtx);

	public abstract void onFrameClose();

	public abstract void onClose();

}
