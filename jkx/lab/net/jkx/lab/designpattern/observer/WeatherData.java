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
		
		//�ڶ�hasChanaged��ֵ��ʱ����Ը���һ�������жϣ������¶ȱ仯���ڶ��ٶ�ʱ�Ž�hasChanaged����Ϊtrue
		//�Ӷ�ʹ�á��仯������һ���ĵ��ԣ������ǡ����С���һ����С�ı仯�������𡰹۲��ߡ��ǵ�ע�⡣
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
