package ex07;

import java.awt.Dimension;
import java.awt.Window;

import ex02.ViewResult;

public class ViewWindow extends ViewResult {
	private static final int POINTS_NUM = 100;
	private Window window = null;
	
	public ViewWindow() {
		super(POINTS_NUM);
//		window = new Window(this);
		window.setSize(new Dimension(640,480));
//		window.setTitle("Result");
		window.setVisible(true);
	}
	
	@Override
	public void viewInit() {
		init((360.0 + Math.random() * 360.0 * 2) / (POINTS_NUM - 1));
	}
	
	@Override
	public void viewShow() {
		super.viewShow();
		window.setVisible(true);
		window.repaint();
	}
}
