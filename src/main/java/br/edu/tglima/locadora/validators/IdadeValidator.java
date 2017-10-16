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
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		Date dataNasc = (Date) value;
		Date today = new Date();
		Long dif = calcDifAnos(dataNasc, today);

		if (dif < 19 || dif > 80) {
			String errorDescription = "A data de nascimento é inválida! Só serão aceitas idades entre 19 e 80 anos.";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, errorDescription);
			throw new ValidatorException(message);
		}

	}

}
