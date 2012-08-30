package net.jkx.lab.designpattern.mvc;

public interface IModel {
	void setString(String value);
	String getString();
	
	void addObserver(Observer o);
	void removeObserver(Observer o);
	void notifyObservers();	
}
