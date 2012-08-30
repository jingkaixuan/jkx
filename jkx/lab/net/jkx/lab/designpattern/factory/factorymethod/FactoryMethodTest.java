package net.jkx.lab.designpattern.factory.factorymethod;

import net.jkx.lab.designpattern.factory.simplefactory.Pizza;

public class FactoryMethodTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NYStylePizzaStore nyStore = new NYStylePizzaStore();
		ChicagoStylePizzaStore chicageStore = new ChicagoStylePizzaStore();
		
		Pizza nyCheesePizza = nyStore.orderPizza("Cheese");
		System.out.println(nyCheesePizza.getDescription() + " is ready now.");
		Pizza nyPepperoniPizza = nyStore.orderPizza("pepperoni");
		System.out.println(nyPepperoniPizza.getDescription() + " is ready now.");
		Pizza chicagoCheesePizza = chicageStore.orderPizza("Cheese");
		System.out.println(chicagoCheesePizza.getDescription() + " is ready now.");
		Pizza chicagoPepperoniCheesePizza = chicageStore.orderPizza("pepperoni");
		System.out.println(chicagoPepperoniCheesePizza.getDescription() + " is ready now.");	

	}

}
