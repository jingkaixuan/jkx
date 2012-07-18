package net.jkx.designpattern.factory.abstractfactory;

public class CheesePizza extends BrandPizza {

	public CheesePizza(PizzaIngredientFactory pizzIngredientFactory) {
		super(pizzIngredientFactory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void prepare() {
		this.cheese = this.pizzIngredientFactory.createCheese();
		this.pepperoni = this.pizzIngredientFactory.createPepperoni();
	}

	@Override
	public String getDescription() {
		return "Cheese Pizza use " + this.pizzIngredientFactory.getDescription();
	}

}
