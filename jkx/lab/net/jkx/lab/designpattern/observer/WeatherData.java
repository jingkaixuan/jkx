package net.jkx.lab.designpattern.observer;

import java.util.ArrayList;

public class WeatherData implements Subject {

	private double temperature;
	private double humidity;
	private double pressure;
	
	private boolean hasChanaged = false;

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
		
		//在对hasChanaged赋值的时候可以给予一种条件判断，比如温度变化大于多少度时才将hasChanaged设置为true
		//从而使得“变化”具有一定的弹性，而不是“敏感”到一个很小的变化都能引起“观察者”们的注意。
		this.hasChanaged = true;
		
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
		if(!this.hasChanaged()) {
			return;
		}
		
		for(Observer o : this.observers) {
			o.update(this);
		}
	}

	@Override
	public boolean hasChanaged() {
		return this.hasChanaged;
	}

	@Override
	public int countOfObservers() {
		return this.observers.size();
	}
}
