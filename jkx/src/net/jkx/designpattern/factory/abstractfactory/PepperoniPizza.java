package net.jkx.designpattern.factory.abstractfactory;

public class PepperoniPizza extends BrandPizza {

	public PepperoniPizza(PizzaIngredientFactory pizzIngredientFactory) {
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
		return "Pepperoni Pizza use " + this.pizzIngredientFactory.getDescription();
	}

}
