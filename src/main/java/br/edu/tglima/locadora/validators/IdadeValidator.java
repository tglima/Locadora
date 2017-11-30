package br.edu.tglima.locadora.validators;

import static br.edu.tglima.locadora.util.TempoUtil.calcDifAnos;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class IdadeValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		Date dataNasc = (Date) value;
		Date today = new Date();
		Long dif = calcDifAnos(dataNasc, today);

		if (dif < 23 || dif > 71) {
			String errorDescription = "Idade inválida! Só serão aceitas idades entre 23 e 70 anos.";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, errorDescription);
			throw new ValidatorException(message);
		}

	}

}
