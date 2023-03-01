package infoScreens;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import misc.InfoScreenManager;
import misc.InfoScreenPanel;

public class InfoScreenManagerInfoScreen extends InfoScreenPanel {

	public InfoScreenManagerInfoScreen() {
		super("InfoScreenManager", 500, 0, 200, (InfoScreenManager.registeredInfoPanels.size() - 1) * 70);

		for (int i = 0; i < InfoScreenManager.registeredInfoPanels.size(); i++) {
			Class<InfoScreenPanel> class1 = InfoScreenManager.registeredInfoPanels.get(i);

			if (class1.getName() == getClass().getName())
				continue;

			JButton but = new JButton();

			try {
				but.setText(class1.newInstance().titel);

			} catch (InstantiationException | IllegalAccessException e2) {
				e2.printStackTrace();
			}

			but.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						window.infoScreenManager.openInfoScreen(class1.newInstance());

					} catch (InstantiationException | IllegalAccessException e2) {
						e2.printStackTrace();
					}

				}
			});

			but.setBounds(0, i * 100, 200, 100);
			add(but);
		}

	}

	@Override
	public void onOpen() {
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
