package net.jkx.designpattern.factory.factorymethod;

import net.jkx.designpattern.factory.simplefactory.Pizza;

public class ChicagoStylePizzaStore extends AbstractPizzaStore {

	@Override
	public Pizza createPizza(String type) {
		Pizza pizza = null;

		if (type.equalsIgnoreCase("Cheese")) {
			System.out.println("Create Chicago Cheese Pizza.");
			pizza = new ChicagoCheesePizza();
		} else if (type.equalsIgnoreCase("Pepperoni")) {
			System.out.println("Create Chicago Pepperoni Pizza.");
			pizza = new ChicagoPepperoniPizza();
		}

		return pizza;
	}

}
