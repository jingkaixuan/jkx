package net.jkx.designpattern.factory.simplefactory;

public class SimplePizzaFactory {
	public Pizza createPizza(String type) {
		Pizza pizza = null;
		
		if(type.equalsIgnoreCase("cheese")) {
			System.out.println("Create Cheese Pizza.");
			pizza = new CheesePizza();
		} else if(type.equalsIgnoreCase("pepperoni")) {
			System.out.println("Create Pepperoni Pizza");
			pizza = new PepperoniPizza();
		}
		
		return pizza;
	}
}
