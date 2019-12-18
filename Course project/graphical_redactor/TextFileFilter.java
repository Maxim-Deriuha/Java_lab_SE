package ua.khpi.striukov_andrii.graphical_redactor;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * Отбор файлов по расширению.
 * 
 * @author Andrey
 * @version 1.0.0
 */
public class TextFileFilter extends FileFilter {

	/** Расширение файла. */
	private String ext;

	/**
	 * Инициализация поля.
	 * 
	 * @param ext
	 *            - значение для инициализации.
	 */
	public TextFileFilter(final String ext) {
		this.ext = ext;
	}

	@Override
	public boolean accept(final File file) {
		if (file.isDirectory())
			return true;
		return (file.getName().endsWith(ext));
	}

	@Override
	public String getDescription() {
		return "*" + ext;
	}
}