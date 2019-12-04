package ex06;

import java.util.HashSet;
import java.util.Set;

public class Observable {
	private Set<Observer> observers = new HashSet<Observer>();
	public void addObserver(Observer observer) {
		observers.add(observer);
	}
	public void delObserver(Observer observer) {
		observers.remove(observer);
	}
	public void call(Object event) {
		for(Observer observer: observers) {
			observer.handleEvent(this,event);
		}
	}

}
