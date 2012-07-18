package net.jkx.designpattern.observer;

import java.util.ArrayList;

public class WeatherData implements Subject {

	private double temperature;
	private double humidity;
	private double pressure;

	private ArrayList<Observer> observers = new ArrayList<Observer>();

	public double getTemperature() {
		return this.temperature;
	}

	public double getHumidity() {
		return this.humidity;
	}

	public double getPressure() {
		return this.pressure;
	}
	
	public void setCondition(double temperature, double humidity, double pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		
		this.notifyObservers();
	}

	@Override
	public void registerObserver(Observer o) {
		if (o != null) {
			this.observers.add(o);
		}
	}

	@Override
	public void removeObserver(Observer o) {
		if(o != null) {
			this.observers.remove(o);
		}
	}

	@Override
	public void notifyObservers() {
		for(Observer o : this.observers) {
			o.update(this);
		}
	}
}
