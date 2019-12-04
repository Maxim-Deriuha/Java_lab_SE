package ex06;

public class Item implements Comparable<Item>{
	private String data;
	public Item(String data) {
		this.data = data;
	}
	public String setData(String data) {
		return this.data = data;
	}
	public String getData() {
		return data;
	}
	@Override
	public int compareTo(Item o) {
		return data.compareTo(o.data);
	}
	@Override
	public String toString() {
		return data;
	}
}
