package infoScreens;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map.Entry;

import misc.Colors;
import misc.InfoScreenPanel;
import misc.JBackgroundPanel;

public class MapDataInfoScreen extends InfoScreenPanel {

	JBackgroundPanel panel = new JBackgroundPanel();

	public MapDataInfoScreen() {
		super("Map Data", 500, 0, 320, 350);

		panel.setBounds(0, 0, width - 20, height - 50);
	}

	@Override
	public void onOpen() {
		// game.map=game.makeMap(game.w, game.h);
		ownFrame.setContentPane(panel);
		onMapUpdate();
		System.out.println("panel height " + panel.getHeight());
	}

	@Override
	public void onMapUpdate() {

		BufferedImage i = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D gtx = i.createGraphics();
		gtx.fillRect(0, 0, panel.getWidth(), panel.getHeight());
		HashMap<Colors, Integer> count = new HashMap<>();
		int max = 0;

		for (Colors[] rwo : game.map) {
			for (Colors c : rwo) {

				if (!count.containsKey(c))
					count.put(c, 0);

				count.put(c, count.get(c) + 1);

				max++;
			}
		}

		System.out.println(panel.getWidth());

		double lastAngle = 0;
		for (Entry<Colors, Integer> entry : count.entrySet()) {

			double newAngle = ((double) entry.getValue() / max) * 360;
			gtx.setColor(entry.getKey().color);
			gtx.fillArc(0, 0, panel.getWidth(), panel.getHeight(), (int) lastAngle, (int) newAngle);

			lastAngle = lastAngle + newAngle;
		}

		panel.setBackground(i);
	}

	@Override
	public void onPlayerMove(int oldX, int oldY, int newX, int newY) {
	}

	@Override
	public void onDraw(Graphics2D gtx) {
	}

	@Override
	public void onFrameClose() {
	}

	@Override
	public void onClose() {
	}

}
