package net.jkx.lab.designpattern.factory.simplefactory;

public abstract class AbstractPizza implements Pizza {
	public abstract String getDescription();
	
	@Override
	public void prepare() {
		System.out.println("preparing...");
	}

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

}
