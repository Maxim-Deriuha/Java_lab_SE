package ex07;

import ex02.View;
import ex02.Viewable;

public class ViewableWindow implements Viewable{
	@Override
	public View getView() {
		return new ViewWindow();
	}
}
