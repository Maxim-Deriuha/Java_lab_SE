package ua.khpi.striukov_andrii.graphical_redactor;

import java.awt.Graphics;

import javax.swing.JFrame;

/**
 * Рисует окна.
 * 
 * @author Andrey
 * @version 1.0.0
 */
public class NewFrame extends JFrame {

	/** Автоматически сгенерированная константа. */
	private static final long serialVersionUID = 1L;

	/**
	 * Наследует конструктор суперкласса.
	 * 
	 * @param title
	 *            - заголовок окна.
	 */
	public NewFrame(final String title) {
		super(title);
	}

	@Override
	public void paint(final Graphics graphics) {
		super.paint(graphics);
	}
}