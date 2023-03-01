package infoScreens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

import misc.ClassesFromPackage;
import misc.ClassesFromPackage.InstantiateEvent;
import misc.InfoScreenPanel;
import misc.PathFinder;
import misc.PathPart;
import misc.PathfindingInfoScreenEvent;
import src.Utils;

public class PathFindingInfoScreen extends InfoScreenPanel {

	public PathFindingInfoScreen() {
		super("PathFinding", 700, 200, 200, 100);
	}

	ClassesFromPackage<PathFinder> pathfinders = new ClassesFromPackage<PathFinder>("pathfinders");
	int pathfinderIndex = 0;

	PathPart goalPart;

	Thread t;

	JTextArea dataText;

	@Override
	public void onOpen() {
		System.out.println("test");

		pathfinders.instantiate(new InstantiateEvent<PathFinder>() {

			@Override
			public void onInstance(PathFinder instance) {
				instance.game = game;
				instance.window = window;
			}
		});

		JButton StopBut = new JButton("Stop");
		JButton StartBut = new JButton("Start");
		JComboBox<PathFinder> combo = new JComboBox<PathFinder>(
				pathfinders.instances.toArray(new PathFinder[pathfinders.instances.size()]));

		StartBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (t != null)
					return;
				t = new Thread(new Runnable() {
					@Override
					public void run() {
						goalPart = pathfinders.instances.get(pathfinderIndex).solve(game.x, game.y, game.w - 1,
								game.h - 1);
						window.render();
					}
				});
				t.start();
			}
		});

		StopBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pathfinders.instances.get(pathfinderIndex).abort();
				t = null;
			}
		});

		combo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pathfinderIndex = combo.getSelectedIndex();
			}
		});

		add(combo);
		add(StartBut);
		add(StopBut);

		dataText = new JTextArea();
		dataText.setEditable(false);

		pathfinders.instances.forEach(pathfind -> pathfind.addEventListener(new PathfindingInfoScreenEvent() {

			@Override
			public void onUpdateData(String data) {
				dataText.setText(data);
				ownFrame.pack();
			}
		}));

		add(dataText);

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
		PathPart part = goalPart;
		if (part != null) {
			if (part.parent != null) {
				gtx.setColor(Color.GREEN);
				while (part.parent != null) {
					gtx.drawLine(part.parent.x * window.tileWidth + window.tileWidth / 2,
							part.parent.y * window.tileHeight + window.tileHeight / 2,
							part.x * window.tileWidth + window.tileWidth / 2,
							part.y * window.tileHeight + window.tileHeight / 2);
					part = part.parent;
				}
			}
		}
	}

	@Override
	public void onFrameClose() {
	}

	@Override
	public void onClose() {
	}

}
