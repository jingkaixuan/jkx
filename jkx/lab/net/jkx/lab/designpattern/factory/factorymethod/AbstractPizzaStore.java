package net.jkx.lab.designpattern.factory.factorymethod;

import net.jkx.lab.designpattern.factory.simplefactory.Pizza;

public abstract class AbstractPizzaStore {
	private void sleep1Second() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public abstract Pizza createPizza(String type);
	
	public Pizza orderPizza(String type) {
		Pizza pizza;
		
		pizza = this.createPizza(type);
		
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
