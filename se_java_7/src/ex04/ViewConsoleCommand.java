package ex04;

import ex02.View;
import ex07.ViewableWindow;

public class ViewConsoleCommand implements ConsoleCommand{
	private View view;
	private ViewableWindow viewableWindow;
	public ViewConsoleCommand(View view) {
		this.view = view;
	}
	
	public ViewConsoleCommand(ViewableWindow viewableWindow) {
		this.viewableWindow = viewableWindow;
	}

	@Override
	public char getKey() {
		return 'v';
	}
	@Override
	public String toString() {
		return "'v'iew";
	}
	@Override
	public void execute() {
		System.out.println("View current.");
		view.viewShow();
	}
}
