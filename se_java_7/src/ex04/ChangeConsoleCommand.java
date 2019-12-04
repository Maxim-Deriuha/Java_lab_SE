package ex04;

import ex01.Calc;
import ex02.View;
import ex02.ViewResult;
import ex07.ViewableWindow;

public class ChangeConsoleCommand extends ChangeItemCommand implements ConsoleCommand{
	private View view;
	private ViewableWindow viewableWindow;
	public View getView() {
		return view;
	}
	public View setView(View view) {
		return this.view = view;
	}
	public ChangeConsoleCommand(View view) {
		this.view = view;
	}
	public ChangeConsoleCommand(ViewableWindow viewableWindow) {
		this.viewableWindow = viewableWindow;
	}
	@Override
	public char getKey() {
		return 'c';
	}
	@Override
	public String toString() {
		return "'c'hange";
	}
	@Override
	public void execute() {
		System.out.println("Change item: scale factor " + setOffset(Math.random() * 100.0));
		for(Calc item: ((ViewResult)view).getItems()) {
			super.setItem(item);
			super.execute();
		}
		view.viewShow();
	}
}
