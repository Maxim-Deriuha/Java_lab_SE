package ex04;

import ex02.View;
import ex07.ViewableWindow;

public class RestoreConsoleCommand implements ConsoleCommand{
	private View view;
	private ViewableWindow viewableWindow;
	public RestoreConsoleCommand(View view) {
		this.view = view;
	}
	
	public RestoreConsoleCommand(ViewableWindow viewableWindow) {
		this.viewableWindow = viewableWindow;
	}

	@Override
	public char getKey() {
		return 'r';
	}
	@Override
	public String toString() {
		return "'r'estore";
	}
	@Override
	public void execute() {
		System.out.println("Restore last saved.");
		try {
			view.viewRestore();
		}catch (Exception e) {
//			System.err.println("Serialization error: " + e);
		}
		view.viewShow();
	}

}
