package ua.khpi.striukov_andrii.graphical_redactor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * Создаёт панели.
 * 
 * @author Andrey
 * @version 1.0.0
 */
public class NewPanel extends JPanel {

	/** Автоматически сгенерированная константа. */
	private static final long serialVersionUID = 1L;

	/**
	 * Empty constructor.
	 */
	public NewPanel() {
		// Nothing to do.
	}

	@Override
	public void paintComponent(final Graphics g) {
		if (ImageEdit.getBufferedImage() == null) {
			ImageEdit
					.setBufferedImage(new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB));
			Graphics2D d2 = (Graphics2D) ImageEdit.getBufferedImage().createGraphics();
			d2.setColor(Color.white);
			d2.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
		super.paintComponent(g);
		g.drawImage(ImageEdit.getBufferedImage(), 0, 0, this);
	}
}