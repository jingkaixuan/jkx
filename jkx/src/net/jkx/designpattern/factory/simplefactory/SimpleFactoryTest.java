package net.jkx.designpattern.factory.simplefactory;

public class SimpleFactoryTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimplePizzaFactory factory = new SimplePizzaFactory();
		PizzaStore ps = new PizzaStore(factory);
		
		Pizza cheesePizza = ps.orderPizza("cheese");
		System.out.println(cheesePizza.getDescription() + " is ready now.");
		
		Pizza pepperoniPizza = ps.orderPizza("pepperoni");
		System.out.println(pepperoniPizza.getDescription() + " is ready now");

	}

}
