package net.jkx.designpattern.factory.simplefactory;

public class PizzaStore {
	SimplePizzaFactory factory = null;

	public PizzaStore(SimplePizzaFactory factory) {
		if (factory != null) {
			this.factory = factory;
		}
	}
	
	
	private void sleep1Second() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Pizza orderPizza(String type) {
		Pizza pizza;
		
		pizza = this.factory.createPizza(type);
		
		pizza.prepare();
		this.sleep1Second();
		pizza.bake();
		this.sleep1Second();
		pizza.cut();
		this.sleep1Second();
		pizza.box();
		this.sleep1Second();
		
		return pizza;
	}
}
