package ex06;

import java.util.ArrayList;
import java.util.List;

//import jdk.nashorn.internal.codegen.CompilerConstants.Call;

import java.util.Iterator;

public class Items extends Observable implements Iterable<Item>{
	public static final String ITEMS_CHANGED = "ITEMS_CHANGED";
	public static final String ITEMS_EMPTY = "ITEMS_EMPTY";
	public static final String ITEMS_REMOVED = "ITEMS_REMOVED";
	
	private List<Item> items = new ArrayList<Item>();
	
	public void add(Item item) {
		items.add(item);
		if(item.getData().isEmpty()) call(ITEMS_EMPTY);
		else call(ITEMS_CHANGED);
	}
	public void add(String s) {
		add(new Item(s));
	}
	public void add(int n) {
		if(n > 0) {
			while(n-- > 0) items.add(new Item(""));
			call(ITEMS_EMPTY);
		}
	}
	public void del(Item item) {
		if(item != null) {
			items.remove(item);
			call(ITEMS_REMOVED);
		}
	}
	public void del(int index) {
		if((index >= 0) && (index < items.size())) {
			items.remove(index);
			call(ITEMS_REMOVED);
		}
	}
	public  List<Item> getItems(){
		return items;
	}
	
	@Override
	public Iterator<Item> iterator(){
		return items.iterator();
	}
}
