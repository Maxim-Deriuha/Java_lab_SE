package ex03;

import java.util.Formatter;
import ex01.Calc;
import ex02.ViewResult;

public class ViewTable extends ViewResult{
	private static final int DEFAULT_WIDTH= 10;
	
	private int width;
	
	public ViewTable() {
		width = DEFAULT_WIDTH;
	}
	
	public ViewTable(int width) {
		this.width = width;
	}
	
	public ViewTable(int width,int n) {
		super(n);
		this.width = width;
	}

	public int setWidth(int width) {
		return this.width = width;
	}
	
	public int getWidth() {
		return width;
	}
	
	private void outLine() {
		for(int i = 7; i > 0; i--) {
			System.out.print("-");
		}
	}
	
	private void outLineLn() {
		outLine();
		System.out.println();
	}
	
	private void outHeader() {
		Formatter fmt = new Formatter();
		fmt.format("|%s%d%s","%",(width-3)/2,"s|\n");
		System.out.printf(fmt.toString(),"result");
		fmt.close();
	}
	private void outBody() {
		Formatter fmt = new Formatter();
		fmt.format(" |%s%d%s", "%", (width-3)/2, ".0f |\n");
		for(Calc item: getItems()) {
			System.out.printf(fmt.toString(), item.getResult());
		}
		fmt.close();
	}

	public final void init(int width) {
		this.width = width;
		viewInit();
	}
	public final void init (int width, double stepX) {
		this.width = width;
		init(stepX);
	}
	@Override
	public void init(double stepX) {
		System.out.print("Initialization...");
		super.init(stepX);
		System.out.println("Done.");
		System.out.println();
	}
	
	@Override
	public void viewHeader() {
		outHeader();
		outLineLn();
	}
	@Override
	public void viewBody() {
		outBody();
	}
	@Override
	public void viewFooter() {
		outLineLn();
	}

}
