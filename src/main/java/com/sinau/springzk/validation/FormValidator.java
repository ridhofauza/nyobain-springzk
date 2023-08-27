package com.sinau.springzk.validation;

import java.util.Map;

import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

public class FormValidator extends AbstractValidator {

	public void validate(ValidationContext ctx) {
		//all the bean properties
		Map<String, Property> beanProps = ctx.getProperties(ctx.getProperty().getBase());
		
		validateName(ctx, (String) beanProps.get("name").getValue());
		validateGender(ctx, (String) beanProps.get("gender").getValue());
	}
	
	private void validateName(ValidationContext ctx, String name) {
		if(name == null || name.equals("")) {
			this.addInvalidMessage(ctx, "name", "Name is required");
		}
	}
	
	private void validateGender(ValidationContext ctx, String gender) {
		if(gender == null || gender.equals("")) {
			this.addInvalidMessage(ctx, "gender", "Gender is required");
		}
	}

}
