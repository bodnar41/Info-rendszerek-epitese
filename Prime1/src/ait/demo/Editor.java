package ait.demo;

import javax.inject.Named;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;

@Named
@SessionScoped
public class Editor implements Serializable {
	private static final long serialVersionUID = -5624515565475797217L;
	private String value = "This editor is provided by PrimeFaces";

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}