package ex07;

import ex04.Application;

public class Main {

	public static void main(String[] args) {
		Application app = Application.getInstance();
		app.run(new ViewableWindow());
		System.exit(0);
	}

}
