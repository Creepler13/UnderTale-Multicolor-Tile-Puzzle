package misc;

import java.awt.Graphics2D;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import src.Game;
import src.Utils;
import src.Window;

public class InfoScreenManager {

	public InfoScreenManager(Game game, Window window) {
		this.window = window;
		this.game = game;
	}

	public Window window;
	public Game game;

	public ArrayList<InfoScreenPanel> InfoScreens = new ArrayList<>();

	public void openInfoScreen(InfoScreenPanel screenPanel) {
		JFrame frame = new JFrame(screenPanel.titel);
		screenPanel.ownFrame = frame;
		screenPanel.window = window;
		screenPanel.game = game;

		frame.setBounds(screenPanel.x, screenPanel.y, screenPanel.width, screenPanel.height);
		screenPanel.setBounds(0, 0, screenPanel.width, screenPanel.height);
		frame.setContentPane(screenPanel);

		frame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				screenPanel.onOpen();
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
				screenPanel.onClose();
				InfoScreens.remove(screenPanel);
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}
		});

		InfoScreens.add(screenPanel);

		frame.setVisible(true);
	}

	public void onDrawAll(Graphics2D gtx) {
		InfoScreens.forEach(i -> i.onDraw(gtx));
	}

	public void onCloseAll() {
		InfoScreens.forEach(i -> i.onClose());
	}

	public void onOpenAll() {
		InfoScreens.forEach(i -> i.onOpen());
	}

	public void onFrameCloseAll() {
		InfoScreens.forEach(i -> i.onFrameClose());
	}

	public void onMapUpdateAll() {
		InfoScreens.forEach(i -> i.onMapUpdate());
	}

	public void onPlayerMoveAll(int oldX, int oldY, int newX, int newY) {
		InfoScreens.forEach(i -> i.onPlayerMove(oldX, oldY, newX, newY));
	}

	public static ArrayList<Class<InfoScreenPanel>> registeredInfoPanels;

	public static void registerInfoScreenPanels() {
		registeredInfoPanels = Utils.getClassesPackage("infoScreens", new ArrayList<Class<InfoScreenPanel>>());
	}

}
