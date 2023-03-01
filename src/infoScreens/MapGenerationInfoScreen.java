package infoScreens;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

import misc.ClassesFromPackage;
import misc.ClassesFromPackage.InstantiateEvent;
import misc.InfoScreenPanel;
import misc.MapGenerator;
import misc.PathFinder;

public class MapGenerationInfoScreen extends InfoScreenPanel {

	ClassesFromPackage<MapGenerator> generators = new ClassesFromPackage<>("mapGenerators");
	int generatorIndex;

	public MapGenerationInfoScreen() {
		super("MapGenerator", 600, 0, 200, 100);

	}

	@Override
	public void onOpen() {
		generators.instantiate(new InstantiateEvent<MapGenerator>() {

			@Override
			public void onInstance(MapGenerator instance) {
				instance.game = game;
				instance.window = window;
			}
		});

		JComboBox<MapGenerator> combo = new JComboBox<MapGenerator>(
				generators.instances.toArray(new MapGenerator[generators.instances.size()]));

		JButton but = new JButton("Generate");

		combo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				generatorIndex = combo.getSelectedIndex();
			}
		});

		but.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				generators.instances.get(generatorIndex).newMap(game.w, game.h);
			}
		});

		add(combo);
		add(but);

		ownFrame.pack();
	}

	@Override
	public void onMapUpdate() {
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
