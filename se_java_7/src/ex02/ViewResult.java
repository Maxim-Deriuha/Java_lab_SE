package ex02;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import ex01.Calc;

public class ViewResult implements View{
	private static final String FNAME = "items.bin";
	private static final int DEFAULT_NUM = 10;
	private ArrayList<Calc> items = new ArrayList<Calc>();
	
	public ViewResult() {
		this(DEFAULT_NUM);
	}
	
	public ViewResult(int n) {
		for(int ctr = 0; ctr < n; ctr++) {
			items.add(new Calc());
		}
	}
	public ArrayList<Calc> getItems(){
		return items;
	}

	public void init(double stepX) {
		double x = 0.0;
		for(Calc item: items) {
			item.init(x);
			x += stepX;
		}
	}
	
	@Override
	public void viewInit() {
		init(Math.random()*360.00);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void viewSave() throws IOException {
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FNAME));
		os.writeObject(items);
		os.flush();
		os.close();
	}
	
	@Override
	public void viewRestore() throws Exception {
		ObjectInputStream is = new ObjectInputStream(new FileInputStream(FNAME));
		items = (ArrayList<Calc>)is.readObject();
		is.close();
	}
		
	@Override
	public void viewHeader() {
		System.out.println("Results:");
	}

	@Override
	public void viewBody() {
		for(Calc item: items) {
			item.show();
		}
		System.out.println();
	}

	@Override
	public void viewFooter() {
		System.out.println("End.");
	}

	@Override
	public void viewShow() {
		viewHeader();
		viewBody();
		viewFooter();
	}



}
