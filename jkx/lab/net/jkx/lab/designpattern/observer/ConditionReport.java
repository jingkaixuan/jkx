package net.jkx.lab.designpattern.observer;

public class ConditionReport implements Observer {

	@Override
	public void update(Object obj) {
		if(obj instanceof WeatherData) {
			WeatherData wd = (WeatherData)obj;
			System.out.println("当前温度：" + wd.getTemperature());
			System.out.println("当前湿度：" + wd.getHumidity());
			System.out.println("当前气压：" + wd.getPressure());
		}
	}
}
