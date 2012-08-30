package net.jkx.lab.designpattern.factory.abstractfactory;

public class ChicagoIngredientFactory implements PizzaIngredientFactory {

	@Override
	public Cheese createCheese() {
		System.out.println("Preparing Chicago Cheese...");
		return new ChicagoCheese();
	}

	@Override
	public Pepperoni createPepperoni() {
		System.out.println("Preparing Chicago Pepperoni...");
		return new ChicagoPepperoni();
	}

	@Override
	public String getDescription() {
		return "ingredient from Chicago factory";
	}

}
