package ex04;

import java.io.IOException;
import ex02.View;
import ex07.ViewableWindow;

public class SaveConsoleCommand implements ConsoleCommand {
	private View view;
	private ViewableWindow viewableWindow;	
	public SaveConsoleCommand(View view) {
		this.view = view;
	}
	public SaveConsoleCommand(ViewableWindow viewableWindow) {
		this.viewableWindow = viewableWindow;
	}
	@Override
	public char getKey() {
		return 's';
	}
	@Override
	public String toString() {
		return "'s'ave";
	}
	@Override
	public void execute() {
		System.out.println("Save current.");
		try {
			view.viewSave();
		}catch(IOException e) {
//			System.err.println("Serialization error: " + e);
		}
		view.viewShow();
	}

}
