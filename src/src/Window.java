package src;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import misc.InfoScreenManager;
import misc.JBackgroundPanel;

public class Window {

	public JFrame frame;
	public JBackgroundPanel panel;
	public InfoScreenManager infoScreenManager;
	private Game game;

	public void openWindow(Game game) {

		this.game = game;
		infoScreenManager = new InfoScreenManager(game, this);

		frame = new JFrame("Game");
		frame.setBounds(0, 0, 500, 500);
		this.panel = new JBackgroundPanel();
		frame.setContentPane(panel);

		this.frame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {

				switch (e.getKeyChar() + "") {
				case "w":
					game.move(0, -1);
					break;
				case "s":
					game.move(0, 1);
					break;
				case "a":
					game.move(-1, 0);
					break;
				case "d":
					game.move(1, 0);
					break;
				default:
					break;
				}

				render();
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});

		frame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				render();
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				infoScreenManager.onFrameCloseAll();
				System.exit(0);
			}

			@Override
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}
		});

		frame.setVisible(true);

	}

	public int tileWidth, tileHeight;

	public void render() {
		BufferedImage i = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D gtx = i.createGraphics();

		tileWidth = panel.getWidth() / game.map.length;
		tileHeight = panel.getHeight() / game.map[0].length;

		for (int x = 0; x < game.map.length; x++) {
			for (int y = 0; y < game.map[x].length; y++) {
				gtx.setColor(game.map[x][y].color);
				gtx.fillRect(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
			}

		}

		gtx.setColor(game.orangeSmell ? Color.CYAN : Color.black);
		gtx.fillRect(game.x * tileWidth + tileWidth / 4, game.y * tileHeight + tileHeight / 4, tileWidth / 2,
				tileHeight / 2);

		infoScreenManager.onDrawAll(gtx);

		this.panel.setBackground(i);
	}

}
