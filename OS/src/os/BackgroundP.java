package os;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

class BackgroundP extends JPanel {
	Image image;

	BackgroundP(Image img) {
		image = img;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
}
