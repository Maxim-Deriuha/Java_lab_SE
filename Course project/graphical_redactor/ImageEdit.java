package ua.khpi.striukov_andrii.graphical_redactor;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.*;

/**
 * Создаёт все окна и их компоненты. Содержит реализацию статического метода
 * main().
 */

/**
 * Создаёт все окна и их компоненты. Содержит реализацию статического метода
 * main().
 * 
 * @author Andrey
 * @version 1.0.0
 */
public class ImageEdit {

	/** Режим рисования. */
	private int mode = 0;

	/** Координаты начала и конца отрезков. */
	private int xOne, xTwo, yTwo, yOne;

	/** Флаг события нажатия клавиши. */
	private boolean pressed = false;

	/** Флаг события загрузки. */
	private boolean loading = false;

	/** Экземпляр класса Color. */
	private Color color;

	/** Экземпляр класса NewFrame. */
	private NewFrame newFrame;

	/** Экземпляр класса NewPanel. */
	private NewPanel newPanel;

	/** Экземпляр класса JButton. */
	private JButton jButton;

	/** Экземпляр класса JColorChooser. */
	private static JColorChooser jColorChooser;

	/** Экземпляр класса BufferedImage. */
	private static BufferedImage bufferedImage;

	/** Хранит имя файла. */
	private String fileName;

	/** Инициализация новой вкладки "File". */
	private JMenu fileMenu = new JMenu("File");

	/** Инициализация новой вкладки "About". */
	private JMenu aboutMenu = new JMenu("About");

	/** Инициализация новой панели инструментов. */
	private JToolBar toolBar = new JToolBar("Toolbar", JToolBar.VERTICAL);

	/** Инициализация новой панели выбора цвета. */
	private JToolBar colorBar = new JToolBar("Colorbar", JToolBar.HORIZONTAL);

	/** Информация о разработчике программы. */
	private static final String AUTHOR_INFO = "Developed by student of group CIT-37, Deriuha Maxim";

	/**
	 * Создание, установка размера, размещения и цвета для главного окна, панели
	 * инструментов, панели цвета и вкладок.
	 */
	public ImageEdit() {
		color = Color.BLACK;
		newFrame = new NewFrame("Graphical Redactor");
		newFrame.setSize(800, 500);
		newFrame.setLocation(200, 100);
		newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar jMenuBar = new JMenuBar();
		newFrame.setJMenuBar(jMenuBar);
		jMenuBar.setBounds(200, 0, 100, 500);
		jMenuBar.add(fileMenu);
		jMenuBar.add(aboutMenu);
		newPanel = new NewPanel();
		newPanel.setBounds(30, 30, 260, 260);
		newPanel.setBackground(Color.white);
		newPanel.setOpaque(true);
		newFrame.add(newPanel);
		toolBar.setBounds(0, 0, 30, 300);
		colorBar.setBounds(30, 0, 260, 40);
		jButton = new JButton();
		jButton.setBackground(color);
		jButton.setBounds(15, 5, 20, 20);
	}

	/**
	 * Возвращает значение поля.
	 * 
	 * @return текущее значение.
	 */
	public static JColorChooser getJColorChooser() {
		return jColorChooser;
	}

	/**
	 * Установка значения для поля.
	 * 
	 * @param jColorChooser
	 *            - значение для инициализации.
	 */
	public static void setJColorChooser(final JColorChooser jColorChooser) {
		ImageEdit.jColorChooser = jColorChooser;
	}

	/**
	 * Возвращает значение поля.
	 * 
	 * @return текущее значение.
	 */
	public static BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	/**
	 * Установка значения для поля.
	 * 
	 * @param bufferedImage
	 *            - значение для инициализации.
	 */
	public static void setBufferedImage(final BufferedImage bufferedImage) {
		ImageEdit.bufferedImage = bufferedImage;
	}

	/**
	 * Возвращает значение поля.
	 * 
	 * @return текущее значение.
	 */
	public int getMode() {
		return mode;
	}

	/**
	 * Установка значения для поля.
	 * 
	 * @param mode
	 *            - режим рисованию.
	 */
	public final void setMode(final int mode) {
		this.mode = mode;
	}

	/**
	 * Обрабатывает событие загрузки изображений.
	 */
	public void loadAction() {
		Action loadAction = new AbstractAction("Load...") {

			/** Автоматически сгенерированная константа. */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(final ActionEvent event) {
				JFileChooser jFileChooser = new JFileChooser();
				int result = jFileChooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION)
					try {
						fileName = jFileChooser.getSelectedFile().getAbsolutePath();
						File file = new File(fileName);
						jFileChooser.addChoosableFileFilter(new TextFileFilter(".png"));
						jFileChooser.addChoosableFileFilter(new TextFileFilter(".jpg"));
						setBufferedImage(ImageIO.read(file));
						loading = true;
						newFrame.setSize(getBufferedImage().getWidth() + 40, getBufferedImage().getWidth() + 80);
						newPanel.setSize(getBufferedImage().getWidth(), getBufferedImage().getWidth());
						newPanel.repaint();
					} catch (FileNotFoundException ex) {
						JOptionPane.showMessageDialog(newFrame, "Error! File not found");
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(newFrame, "Error! I/O Exception");
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(newFrame, "Error! " + ex.getMessage());
					}
			}
		};

		JMenuItem loadMenu = new JMenuItem(loadAction);
		fileMenu.add(loadMenu);
	}

	/**
	 * Обрабатывает событие сохранения изображений.
	 */
	public void saveAction() {
		Action saveAction = new AbstractAction("Save") {

			/** Автоматически сгенерированная константа. */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(final ActionEvent event) {
				try {
					JFileChooser jFileChooser = new JFileChooser();
					TextFileFilter pngFilter = new TextFileFilter(".png");
					TextFileFilter jpgFilter = new TextFileFilter(".jpg");
					if (fileName == null) {
						jFileChooser.addChoosableFileFilter(pngFilter);
						jFileChooser.addChoosableFileFilter(jpgFilter);
						int result = jFileChooser.showSaveDialog(null);
						if (result == JFileChooser.APPROVE_OPTION)
							fileName = jFileChooser.getSelectedFile().getAbsolutePath();
					}
					if (jFileChooser.getFileFilter() == pngFilter)
						ImageIO.write(getBufferedImage(), "png", new File(fileName + ".png"));
					else
						ImageIO.write(getBufferedImage(), "jpeg", new File(fileName + ".jpg"));
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(newFrame, "Error! I/O Exception");
				}
			}
		};

		JMenuItem saveMenu = new JMenuItem(saveAction);
		fileMenu.add(saveMenu);
	}

	/**
	 * Обрабатывает событие сохранения изображений с параметрами.
	 */
	public void saveAsAction() {
		Action saveAsAction = new AbstractAction("Save as...") {

			/** Автоматически сгенерированная константа. */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(final ActionEvent event) {
				try {
					JFileChooser jFileChooser = new JFileChooser();
					TextFileFilter pngFilter = new TextFileFilter(".png");
					TextFileFilter jpgFilter = new TextFileFilter(".jpg");
					jFileChooser.addChoosableFileFilter(pngFilter);
					jFileChooser.addChoosableFileFilter(jpgFilter);
					int result = jFileChooser.showSaveDialog(null);
					if (result == JFileChooser.APPROVE_OPTION)
						fileName = jFileChooser.getSelectedFile().getAbsolutePath();
					if (jFileChooser.getFileFilter() == pngFilter)
						ImageIO.write(getBufferedImage(), "png", new File(fileName + ".png"));
					else
						ImageIO.write(getBufferedImage(), "jpeg", new File(fileName + ".jpg"));
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(newFrame, "Error! I/O Exception");
				}
			}
		};

		JMenuItem saveasMenu = new JMenuItem(saveAsAction);
		fileMenu.add(saveasMenu);
	}

	/**
	 * Обрабатывает событие вывода информации о авторе.
	 */
	public void aboutAuthorAction() {
		Action aboutAuthorAction = new AbstractAction("About author") {

			/** Автоматически сгенерированная константа. */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(null, AUTHOR_INFO);
			}
		};
		JMenuItem aboutAuthorMenu = new JMenuItem(aboutAuthorAction);
		aboutMenu.add(aboutAuthorMenu);
	}

	/**
	 * Добавляет єлементы на панель инструментов.
	 */
	public void toolBar() {

		// Добавление ручки.
		JButton penButton = new JButton(new ImageIcon("pen.png"));
		penButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				setMode(0);
			}
		});
		toolBar.add(penButton);

		// Добавление кисти.
		JButton brushButton = new JButton(new ImageIcon("brush.png"));
		brushButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				setMode(1);
			}
		});
		toolBar.add(brushButton);

		// Добавление ластика.
		JButton eraserButton = new JButton(new ImageIcon("eraser.png"));
		eraserButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				setMode(2);
			}
		});
		toolBar.add(eraserButton);

		// Добавление текста.
		JButton textButton = new JButton(new ImageIcon("text.png"));
		textButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				setMode(3);
			}
		});
		toolBar.add(textButton);

		// Добавление линии.
		JButton lineButton = new JButton(new ImageIcon("line.png"));
		lineButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				setMode(4);
			}
		});
		toolBar.add(lineButton);

		// Добавление элипса.
		JButton ellipseButton = new JButton(new ImageIcon("ellipse.png"));
		ellipseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				setMode(5);
			}
		});
		toolBar.add(ellipseButton);

		// Добавление прямоугольника.
		JButton rectangleButton = new JButton(new ImageIcon("rectangle.png"));
		rectangleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				setMode(6);
			}
		});
		toolBar.add(rectangleButton);
		newFrame.add(toolBar);
	}

	/**
	 * Добавляет элементы на панель цвета.
	 */
	public void colorBar() {

		// Добавление диалога выбора цвета.
		jButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				ColorDialog colorDialog = new ColorDialog(newFrame, "Color selection");
				colorDialog.setVisible(true);
			}
		});
		colorBar.add(jButton);

		// Добавление красного цвета.
		JButton redButton = new JButton();
		redButton.setBackground(Color.RED);
		redButton.setBounds(40, 5, 15, 15);
		redButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				color = Color.RED;
				jButton.setBackground(color);
			}
		});
		colorBar.add(redButton);

		// Добавление оранжевого цвета.
		JButton orangeButton = new JButton();
		orangeButton.setBackground(Color.ORANGE);
		orangeButton.setBounds(60, 5, 15, 15);
		orangeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				color = Color.ORANGE;
				jButton.setBackground(color);
			}
		});
		colorBar.add(orangeButton);

		// Добавление жёлтого цвета.
		JButton yellowButton = new JButton();
		yellowButton.setBackground(Color.YELLOW);
		yellowButton.setBounds(80, 5, 15, 15);
		yellowButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				color = Color.YELLOW;
				jButton.setBackground(color);
			}
		});
		colorBar.add(yellowButton);

		// Добавление зелёного цвета.
		JButton greenButton = new JButton();
		greenButton.setBackground(Color.GREEN);
		greenButton.setBounds(100, 5, 15, 15);
		greenButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				color = Color.GREEN;
				jButton.setBackground(color);
			}
		});
		colorBar.add(greenButton);

		// Добавление синего цвета.
		JButton blueButton = new JButton();
		blueButton.setBackground(Color.BLUE);
		blueButton.setBounds(120, 5, 15, 15);
		blueButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				color = Color.BLUE;
				jButton.setBackground(color);
			}
		});
		colorBar.add(blueButton);

		// Добавление цвета сиан.
		JButton cyanButton = new JButton();
		cyanButton.setBackground(Color.CYAN);
		cyanButton.setBounds(140, 5, 15, 15);
		cyanButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				color = Color.CYAN;
				jButton.setBackground(color);
			}
		});
		colorBar.add(cyanButton);

		// Добавление цвета маджента.
		JButton magentaButton = new JButton();
		magentaButton.setBackground(Color.MAGENTA);
		magentaButton.setBounds(160, 5, 15, 15);
		magentaButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				color = Color.MAGENTA;
				jButton.setBackground(color);
			}
		});
		colorBar.add(magentaButton);

		// Добавление белого цвета.
		JButton whiteButton = new JButton();
		whiteButton.setBackground(Color.WHITE);
		whiteButton.setBounds(180, 5, 15, 15);
		whiteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				color = Color.WHITE;
				jButton.setBackground(color);
			}
		});
		colorBar.add(whiteButton);

		// Добавление чёрного цвета.
		JButton blackButton = new JButton();
		blackButton.setBackground(Color.BLACK);
		blackButton.setBounds(200, 5, 15, 15);
		blackButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				color = Color.BLACK;
				jButton.setBackground(color);
			}
		});
		colorBar.add(blackButton);
		colorBar.setLayout(null);
		newFrame.add(colorBar);
	}

	/**
	 * Обрабатывает события мыши.
	 */
	public void drawing() {
		setJColorChooser(new JColorChooser(color));
		getJColorChooser().getSelectionModel().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(final ChangeEvent e) {
				color = getJColorChooser().getColor();
				jButton.setBackground(color);
			}
		});
		newPanel.addMouseMotionListener(new MouseMotionAdapter() {

			// Обрабатывает событие перетаскивания мыши.
			@Override
			public void mouseDragged(final MouseEvent e) {
				if (pressed) {
					Graphics graphics = getBufferedImage().getGraphics();
					Graphics2D graphicsTwoD = (Graphics2D) graphics;
					graphicsTwoD.setColor(color);
					switch (getMode()) {
					case 0:
						graphicsTwoD.drawLine(xOne, yOne, e.getX(), e.getY());
						break;
					case 1:
						graphicsTwoD.setStroke(new BasicStroke(3.0f));
						graphicsTwoD.drawLine(xOne, yOne, e.getX(), e.getY());
						break;
					case 2:
						graphicsTwoD.setStroke(new BasicStroke(3.0f));
						graphicsTwoD.setColor(Color.WHITE);
						graphicsTwoD.drawLine(xOne, yOne, e.getX(), e.getY());
						break;
					default:
						break;
					}
					xOne = e.getX();
					yOne = e.getY();
				}
				newPanel.repaint();
			}
		});

		// Обрабатывает события нажатия и отжатия мыши.
		newPanel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(final MouseEvent e) {

				Graphics graphics = getBufferedImage().getGraphics();
				Graphics2D graphicsTwoD = (Graphics2D) graphics;
				graphicsTwoD.setColor(color);
				switch (getMode()) {
				case 0:
					graphicsTwoD.drawLine(xOne, yOne, xOne + 1, yOne + 1);
					break;
				case 1:
					graphicsTwoD.setStroke(new BasicStroke(3.0f));
					graphicsTwoD.drawLine(xOne, yOne, xOne + 1, yOne + 1);
					break;
				case 2:
					graphicsTwoD.setStroke(new BasicStroke(3.0f));
					graphicsTwoD.setColor(Color.WHITE);
					graphicsTwoD.drawLine(xOne, yOne, xOne + 1, yOne + 1);
					break;
				case 3:
					newPanel.requestFocus();
					break;
				default:
					break;
				}
				xOne = e.getX();
				yOne = e.getY();

				pressed = true;
				newPanel.repaint();
			}

			@Override
			public void mousePressed(final MouseEvent e) {
				xOne = e.getX();
				yOne = e.getY();
				xTwo = e.getX();
				yTwo = e.getY();
				pressed = true;
			}

			@Override
			public void mouseReleased(final MouseEvent e) {

				Graphics graphics = getBufferedImage().getGraphics();
				Graphics2D graphicsTwoD = (Graphics2D) graphics;
				graphicsTwoD.setColor(color);
				int x1 = xTwo, x2 = xOne, y1 = yTwo, y2 = yOne;
				if (xTwo > xOne) {
					x2 = xTwo;
					x1 = xOne;
				}
				if (yTwo > yOne) {
					y2 = yTwo;
					y1 = yOne;
				}
				switch (getMode()) {
				case 4:
					graphics.drawLine(xTwo, yTwo, e.getX(), e.getY());
					break;
				case 5:
					graphics.drawOval(x1, y1, (x2 - x1), (y2 - y1));
					break;
				case 6:
					graphics.drawRect(x1, y1, (x2 - x1), (y2 - y1));
					break;
				default:
					break;
				}
				xTwo = 0;
				yTwo = 0;
				pressed = false;
				newPanel.repaint();
			}
		});

		// Обрабатывает события нажатия и отжатия клавиш при вводе символов.
		newPanel.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(final KeyEvent e) {
				newPanel.requestFocus();
			}

			@Override
			public void keyTyped(final KeyEvent e) {
				if (getMode() == 3) {
					Graphics graphics = getBufferedImage().getGraphics();
					Graphics2D graphicsTwoD = (Graphics2D) graphics;
					graphicsTwoD.setColor(color);
					graphicsTwoD.setStroke(new BasicStroke(2.0f));
					String str = new String("");
					str += e.getKeyChar();
					graphicsTwoD.setFont(new Font("Verdana", 0, 16));
					graphicsTwoD.drawString(str, xOne, yOne);
					xOne += 10;
					newPanel.requestFocus();
					newPanel.repaint();
				}
			}
		});

		// Обрабатывает событие изменения размеров компонентов окна.
		newFrame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(final ComponentEvent evt) {
				if (!loading) {
					newPanel.setSize(newFrame.getWidth() - 40, newFrame.getHeight() - 80);
					BufferedImage tempImage = new BufferedImage(newPanel.getWidth(), newPanel.getHeight(),
							BufferedImage.TYPE_INT_RGB);
					Graphics2D graphicsTwoD = (Graphics2D) tempImage.createGraphics();
					graphicsTwoD.setColor(Color.WHITE);
					graphicsTwoD.fillRect(0, 0, newPanel.getWidth(), newPanel.getHeight());
					tempImage.setData(getBufferedImage().getRaster());
					setBufferedImage(tempImage);
					newPanel.repaint();
				}
				loading = false;
			}
		});
		newFrame.setLayout(null);
		newFrame.setVisible(true);
	}

	/**
	 * Точка входа в программу. Вызывает функцию run().
	 * 
	 * @param args
	 *            - параметры запуска программы.
	 */
	public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				ImageEdit imageEdit = new ImageEdit();
				imageEdit.loadAction();
				imageEdit.saveAction();
				imageEdit.saveAsAction();
				imageEdit.aboutAuthorAction();
				imageEdit.toolBar();
				imageEdit.colorBar();
				imageEdit.drawing();
			}
		});
	}
}