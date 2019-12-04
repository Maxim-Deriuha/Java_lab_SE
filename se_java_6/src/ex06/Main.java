package ex06;

import ex04.ConsoleCommand;
import ex04.Menu;

public class Main {
	abstract class ConsoleCmd implements ConsoleCommand{
		protected Items items;
		private String name;
		private char key;
		
		ConsoleCmd(Items items, String name, char key){
			this.items = items;
			this.name = name;
			this.key = key;
		}
		@Override
		public char getKey() {
			return key;
		}
		@Override
		public String toString() {
			return name;
		}
	}
	
	public void run() {
		Items items = new Items();
		ItemsGenerator generator = new ItemsGenerator();
		ItemSorter sorter = new ItemSorter();
		items.addObserver(generator);
		items.addObserver(sorter);
		Menu menu = new Menu();
		menu.add(new ConsoleCmd(items, "'v'iew", 'v'){
			@Override
			public void execute() {
				System.out.println(items.getItems());
			}
		});
		menu.add(new ConsoleCmd(items, "'a'dd", 'a') {
			@Override
			public void execute() {
				items.add("");
			}
		});
		menu.add(new ConsoleCmd(items, "'d'el", 'd'){
			@Override
			public void execute() {
				items.del((int)Math.round(Math.random()*(items.getItems().size() - 1)));
			}
		});
		menu.execute();
	}	

	public static void main(String[] args) {
		new Main().run();
	}

}
