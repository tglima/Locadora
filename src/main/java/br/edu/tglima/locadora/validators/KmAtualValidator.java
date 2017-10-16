package br.edu.tglima.locadora.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import com.sun.faces.util.MessageFactory;

public class KmAtualValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		Integer kmInicial = (Integer) component.getAttributes().get("kmInicial");
		Integer kmAtual = (Integer) value;

		if (kmAtual < kmInicial) {
			Object label = MessageFactory.getLabel(context, component);
			String errorDescription = "A " + label + " não pode ser inferior a Km inicial!";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, errorDescription);
			throw new ValidatorException(message);
		}

	}

}
