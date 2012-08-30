package net.jkx.lab.designpattern.factory.abstractfactory;

public interface PizzaIngredientFactory {
	Cheese createCheese();
	Pepperoni createPepperoni();
	
	String getDescription();
}