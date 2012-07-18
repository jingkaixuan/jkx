package net.jkx.designpattern.factory.simplefactory;

public interface Pizza {
	void prepare();
	void bake();
	void cut();
	void box();
	String getDescription();
}
