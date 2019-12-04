package ex04;

import ex01.Calc;

public class ChangeItemCommand implements Command{
	private Calc item;
	private double offset;
	public Calc setItem(Calc item) {
		return this.item = item;
	}
	public Calc getItem() {
		return item;
	}
	public double setOffset(double offset) {
		return this.offset = offset;
	}
	public double getOffset() {
		return offset;
	}
	@Override
	public void execute() {
		item.setResult(item.getResult()*offset);
	}
}
