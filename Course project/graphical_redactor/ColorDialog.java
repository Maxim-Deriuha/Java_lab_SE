package ua.khpi.striukov_andrii.graphical_redactor;

import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * Создаёт диалоговое окно выбора цвета.
 * 
 * @author Andrey
 * @version 1.0.0
 */
public class ColorDialog extends JDialog {

	/** Автоматически сгенерированная константа. */
	private static final long serialVersionUID = 1L;

	/**
	 * Добавляет новое окно в массив. Устанавливает его размер и положение.
	 * 
	 * @param jFrame
	 *            - дескриптор окна.
	 * @param title
	 *            - заголовок окна.
	 */
	public ColorDialog(final JFrame jFrame, final String title) {
		super(jFrame, title, true);
		add(ImageEdit.getJColorChooser());
		setSize(600, 400);
		setLocation(300, 150);
	}
}