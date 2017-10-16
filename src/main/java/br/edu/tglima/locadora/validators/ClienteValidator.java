package br.edu.tglima.locadora.validators;

import java.util.Date;
import br.edu.tglima.locadora.util.TempoUtil;

public final class ClienteValidator {
	
	public boolean idadeParaDirigir(Date dataNasc){
		Date today = new Date();
		Long dif = TempoUtil.calcDifAnos(dataNasc, today);
		return (dif >= 23 && dif <= 70);
	}
	
	
	public boolean cnhNaValidade(Date validadeHab){
		Date dataValidaMinima = new Date();
		Long dif = TempoUtil.calcDifDias(dataValidaMinima, validadeHab);
		return (dif >= 0);
	}

}
