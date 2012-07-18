package net.jkx.designpattern.factory.abstractfactory;

import net.jkx.designpattern.factory.factorymethod.AbstractPizzaStore;
import net.jkx.designpattern.factory.simplefactory.Pizza;

public class NYStylePizzaStore extends AbstractPizzaStore {

	@Override
	public Pizza createPizza(String type) {
		Pizza pizza = null;
		
		PizzaIngredientFactory factory = new NYPizzaIngredientFactory();
		
		if(type.equalsIgnoreCase("Cheese")) {
			System.out.println("Create New York Cheese Pizza.");
			
			pizza = new CheesePizza(factory);
		} else if(type.equalsIgnoreCase("Pepperoni")) {
			System.out.println("Create New York Pepperoni Pizza.");
			pizza = new PepperoniPizza(factory);
		}
		
		return pizza;
	}

}
