package net.jkx.lab.designpattern.observer;

public class ConditionReport implements Observer {

	@Override
	public void update(Object obj) {
		if(obj instanceof WeatherData) {
			WeatherData wd = (WeatherData)obj;
			System.out.println("��ǰ�¶ȣ�" + wd.getTemperature());
			System.out.println("��ǰʪ�ȣ�" + wd.getHumidity());
			System.out.println("��ǰ��ѹ��" + wd.getPressure());
		}
	}
}
