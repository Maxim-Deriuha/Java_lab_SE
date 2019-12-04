package ex06;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public abstract class AnotatedObserver implements Observer{
	private Map<Object, Method> handlers = new HashMap<Object,Method>();
	
	public AnotatedObserver() {
		for(Method m: this.getClass().getMethods()) {
			if(m.isAnnotationPresent(Event.class)) {
				handlers.put(m.getAnnotation(Event.class).value(),m);
			}
		}
	}
	
	@Override
	public void handleEvent(Observable observable, Object event) {
		Method m = handlers.get(event);
		try {
			if(m != null) m.invoke(this, observable);
		}catch(Exception e) {
			System.err.println(e);
		}
		
	};
}
