package net.jkx.lab.designpattern.factory.abstractfactory;

import net.jkx.lab.designpattern.factory.simplefactory.Pizza;

public abstract class BrandPizza implements Pizza {
	
	protected PizzaIngredientFactory pizzIngredientFactory = null;
	
	protected Cheese cheese;
	protected Pepperoni pepperoni;
	
	public BrandPizza(PizzaIngredientFactory pizzIngredientFactory) {
		if(pizzIngredientFactory != null) {
			this.pizzIngredientFactory = pizzIngredientFactory;
		}
	}
	
	@Override
	public abstract void prepare();

	@Override
	public void bake() {
		System.out.println("baking...");
	}

	@Override
	public void cut() {
		System.out.println("cutting...");
	}

	@Override
	public void box() {
		System.out.println("boxing...");
	}

	@Override
	public abstract String getDescription();

}
