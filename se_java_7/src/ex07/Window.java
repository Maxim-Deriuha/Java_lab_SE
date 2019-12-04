package ex07;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import ex01.Calc;
import ex02.ViewResult;

@SuppressWarnings("serial")
public class Window extends Frame{

	private static final int BORDER = 20;
	
	private ViewResult view;
	private Window window;
	
	public Window(ViewResult view) {
		this.view = view;
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				setVisible(false);
			}
		});
	}

	public Window(Window window) {
		this.window = window;
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				setVisible(false);
			}
		});
	}

	
	@Override
	public void paint(Graphics g) {
		Rectangle r = getBounds(), c = new Rectangle();
		r.x += BORDER; r.y += 25 + BORDER;
		r.width -=r.x + BORDER; r.height -= r.y + BORDER;
		c.x = r.x; c.y = r.y + r.height / 2;
		c.width = r.width; c.height = r.height / 2;
		g.setColor(Color.LIGHT_GRAY);
		g.setColor(Color.RED);
		g.drawLine(c.x, c.y, c.x + c.width, c.y);
		g.drawLine(c.x, r.y, c.x, r.y + r.height);
		double maxX = 0, maxY = view.getItems().get(0).getResult(), scaleX, scaleY;
		for (Calc item: view.getItems()) {
			if(item.getResult() > maxX) maxX = item.getResult();
			if(Math.abs(item.getResult()) > maxY) maxY = Math.abs(item.getResult());
		}
		g.drawString("+" + maxY, r.x, r.y);
		g.drawString("-" + maxY, r.x, r.y + r.height);
		g.drawString("+" + maxX, c.x + c.width - g.getFontMetrics().stringWidth("+" + maxX), c.y);
		scaleX = c.width/maxX; scaleY = c.height/maxY;
		g.setColor(Color.BLUE);
		for(Calc item: view.getItems()) {
			g.drawOval(
					c.x + (int)(item.getResult() * scaleX) - 5,
					c.y - (int)(item.getResult() * scaleY) - 5,
					10,10
					);
		}
	}
}
