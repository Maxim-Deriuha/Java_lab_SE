package ex06;

import java.util.Collections;

public class ItemSorter extends AnotatedObserver{
	public static final String ITEMS_SORTED = "ITEMS_SORTED";
	
	@Event(Items.ITEMS_CHANGED)
	public void itemsChanged(Items observable) {
		Collections.sort(observable.getItems());
		observable.call(ITEMS_SORTED);
	}
	@Event(ITEMS_SORTED)
	public void itemsSorted(Items observable) {
		System.out.println(observable.getItems());
	}
	
	
}
