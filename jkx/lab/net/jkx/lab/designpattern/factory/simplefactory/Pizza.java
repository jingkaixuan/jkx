package net.jkx.lab.designpattern.factory.simplefactory;

public interface Pizza {
	void prepare();
	void bake();
	void cut();
	void box();
	String getDescription();
}
