package br.edu.tglima.locadora.validators;

import static br.edu.tglima.locadora.util.TempoUtil.calcDifDias;
import static br.edu.tglima.locadora.util.TempoUtil.plusDays;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class ValidadeCnhValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		Date validadeCnh = (Date) value;
		validadeCnh = plusDays(validadeCnh, 15);
		Date today = new Date();
		long dif = calcDifDias(today, validadeCnh);
		if (dif < 1) {
			String errorDescription = "CNH vencida há mais de 15 dias!";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, errorDescription);
			throw new ValidatorException(message);
		}

	}

}
