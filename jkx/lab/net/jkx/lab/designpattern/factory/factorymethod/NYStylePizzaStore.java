package net.jkx.lab.designpattern.factory.factorymethod;

import net.jkx.lab.designpattern.factory.simplefactory.Pizza;

public class NYStylePizzaStore extends AbstractPizzaStore {

	@Override
	public Pizza createPizza(String type) {
		Pizza pizza = null;
		
		if(type.equalsIgnoreCase("Cheese")) {
			System.out.println("Create New York Cheese Pizza.");
			pizza = new NYCheesePizza();
		} else if(type.equalsIgnoreCase("Pepperoni")) {
			System.out.println("Create New York Pepperoni Pizza.");
			pizza = new NYPepperoniPizza();
		}
		
		return pizza;
	}

}
