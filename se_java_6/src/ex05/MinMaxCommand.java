package ex05;

import java.util.concurrent.TimeUnit;
import ex01.Calc;
import ex02.ViewResult;
import ex04.Command;

public class MinMaxCommand implements Command{
	private int resultMin = -1;
	private int resultMax = -1;
	private int progress = 0;
	
	private ViewResult viewResult;
	
	public ViewResult getViewResult(ViewResult viewResult) {
		return viewResult;
	}
	
	public ViewResult setViewResult(ViewResult viewResult) {
		return this.viewResult = viewResult;
	}
	public MinMaxCommand(ViewResult viewResult) {
		this.viewResult = viewResult;
	}
	public int getResultMin() {
		return resultMin;
	}
	public int getResultMax() {
		return resultMax;
	}
	public boolean running() {
		return progress < 100;
	}
	@Override
	public void execute() {
		progress = 0;
		System.out.println("MinMax execute...");
		int idx = 0, size = viewResult.getItems().size();
		for(Calc item: viewResult.getItems()) {
			if(item.getResult() < 0) {
				if((resultMax == -1) || (viewResult.getItems().get(resultMax).getResult() < item.getResult())) {
					resultMax = idx;
				}
			}else {
				if((resultMin == -1) || (viewResult.getItems().get(resultMax).getResult() > item.getResult())) {
					resultMin = idx;
				}
			}
			idx++;
			progress = idx * 100 / size;
			if(idx % (size / 5) == 0) {
				System.out.println("MinMax " + progress +"%");
			}
			try {
				TimeUnit.MILLISECONDS.sleep(5000 / size);
			}catch(InterruptedException e){
//				System.err.println(e);
			}
		}
		System.out.print("MinMax done.");
		if(resultMin > -1) {
			System.out.println("Min positive #" + resultMin + "found: " + String.format("%.2f.", viewResult.getItems().get(resultMin).getResult()));
		}else {
			System.out.println("Min positive not found.");
		}
		if(resultMax > -1) {
			System.out.println("Max negative #" + resultMin + "found: " + String.format("%.2f.", viewResult.getItems().get(resultMin).getResult()));
		}else {
			System.out.println("Max negative not found.");
		}
		progress = 100;
	}
}
