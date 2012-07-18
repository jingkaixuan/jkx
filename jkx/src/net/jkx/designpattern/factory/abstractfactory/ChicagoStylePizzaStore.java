package net.jkx.designpattern.factory.abstractfactory;

import net.jkx.designpattern.factory.factorymethod.AbstractPizzaStore;
import net.jkx.designpattern.factory.simplefactory.Pizza;

public class ChicagoStylePizzaStore extends AbstractPizzaStore {

	@Override
	public Pizza createPizza(String type) {
		Pizza pizza = null;

		PizzaIngredientFactory factory = new ChicagoIngredientFactory();

		if (type.equalsIgnoreCase("Cheese")) {
			System.out.println("Create Chicago Cheese Pizza.");

			pizza = new CheesePizza(factory);
		} else if (type.equalsIgnoreCase("Pepperoni")) {
			System.out.println("Create Chicago Pepperoni Pizza.");
			pizza = new PepperoniPizza(factory);
		}

		return pizza;
	}

}
