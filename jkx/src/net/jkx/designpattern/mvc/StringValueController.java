package net.jkx.designpattern.mvc;

public class StringValueController implements IController {
	IView view;
	IModel model;
	
	public StringValueController(IView view, IModel model) {
		this.view = view;
		this.model = model;
	}
	
	@Override
	public void setValue() {
		this.model.setString(this.view.getValue());
	}

}
