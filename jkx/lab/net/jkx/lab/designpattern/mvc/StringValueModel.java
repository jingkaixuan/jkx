package net.jkx.lab.designpattern.mvc;

import java.util.ArrayList;

public class StringValueModel implements IModel {
	private String value;
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	@Override
	public void setString(String value) {
		this.value = value;
		this.notifyObservers();
	}

	@Override
	public String getString() {
		return this.value;
	}

	@Override
	public void addObserver(Observer o) {
		this.observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		this.observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		for(Observer o : this.observers) {
			o.update(this);
		}
	}

}
