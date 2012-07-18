package net.jkx.designpattern.factory.abstractfactory;

public class NYPizzaIngredientFactory implements PizzaIngredientFactory {

	@Override
	public Cheese createCheese() {
		System.out.println("peparing New York Cheese...");
		return new NYCheese();
	}

	@Override
	public Pepperoni createPepperoni() {
		System.out.println("peparing New York Pepperoni...");
		return new NYPepperoni();
	}

	@Override
	public String getDescription() {
		return "ingredient from New York Factory";
	}

}
