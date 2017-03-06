package br.com.vendapedido.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
//regexp -> vai fazer com que funcione as duas primeiras letras de A a Z, e depois, os numeros de 4 a 18.
@Pattern(regexp = "([a-zA-Z]{2}\\d{4,18})?")
public @interface SKU {
	
	@OverridesAttribute(constraint = Pattern.class, name = "message")
	String message() default "{br.com.vendapedido.constrainrs.SKU.message}";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
