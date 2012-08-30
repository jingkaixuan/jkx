package net.jkx.lab.designpattern.observer;

public interface Subject {
	void registerObserver(Observer o);
	void removeObserver(Observer o);
	void notifyObservers();
	boolean hasChanaged();
	int countOfObservers();
}
