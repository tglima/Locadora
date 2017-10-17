package br.edu.tglima.locadora.validators;

import static br.edu.tglima.locadora.util.TempoUtil.calcDifAnos;
import static br.edu.tglima.locadora.util.TempoUtil.calcDifDias;

import java.util.Date;

public final class ClienteValidator {

	public boolean idadeParaDirigir(Date dataNasc) {
		Date today = new Date();
		Long dif = calcDifAnos(dataNasc, today);
		return (dif >= 23 && dif <= 70);
	}

	public boolean cnhNaValidade(Date validadeHab) {
		Date dataValidaMinima = new Date();
		Long dif = calcDifDias(dataValidaMinima, validadeHab);
		return (dif >= 0);
	}

}
