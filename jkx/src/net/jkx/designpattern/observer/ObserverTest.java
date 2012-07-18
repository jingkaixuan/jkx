package net.jkx.designpattern.observer;

public class ObserverTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConditionReport cr = new ConditionReport();
		
		WeatherData wd = new WeatherData();
		wd.registerObserver(cr);
		wd.setCondition(35, 22, 101);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		wd.setCondition(28, 22, 105);
		
		wd.removeObserver(cr);
		wd.setCondition(29, 20, 109);
	}
}
