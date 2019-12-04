package ex04;

import ex02.View;
import ex03.ViewableTable;
import ex07.ViewableWindow;

public class Application {
	private static Application instance = new Application();
	private Application() {}
	public static Application getInstance() {
		return instance;
	}
	private View view = new ViewableTable().getView();
	private Menu menu = new Menu();
	public void run() {
		menu.add(new ViewConsoleCommand(view));
		menu.add(new GenerateConsoleCommand(view));
		menu.add(new ChangeConsoleCommand(view));
		menu.add(new SaveConsoleCommand(view));
		menu.add(new RestoreConsoleCommand(view));
		menu.execute();
	}
	public void run(ViewableWindow viewableWindow) {
		menu.add(new ViewConsoleCommand(viewableWindow));
		menu.add(new GenerateConsoleCommand(viewableWindow));
		menu.add(new ChangeConsoleCommand(viewableWindow));
		menu.add(new SaveConsoleCommand(viewableWindow));
		menu.add(new RestoreConsoleCommand(viewableWindow));
		menu.execute();
	}
}
