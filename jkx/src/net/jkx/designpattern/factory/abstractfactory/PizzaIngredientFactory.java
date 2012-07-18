package net.jkx.designpattern.factory.abstractfactory;

public interface PizzaIngredientFactory {
	Cheese createCheese();
	Pepperoni createPepperoni();
	
	String getDescription();
}