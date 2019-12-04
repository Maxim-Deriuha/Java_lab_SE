package ex04;

import ex02.View;
import ex07.ViewableWindow;

public class GenerateConsoleCommand implements ConsoleCommand{
	private View view;
	private ViewableWindow viewableWindow;
	public GenerateConsoleCommand(View view) {
		this.view = view;
	}
	public GenerateConsoleCommand(ViewableWindow viewableWindow) {
		this.viewableWindow = viewableWindow;
	}
	@Override
	public char getKey() {
		return 'g';
	}
	@Override
	public String toString() {
		return "'g'enerate";
	}
	@Override
	public void execute() {
		System.out.println("Random generation.");
		view.viewInit();
		view.viewShow();
	}
}
