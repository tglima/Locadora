package br.edu.tglima.locadora.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public final class TempoUtil {

	public final static Long calcDifAnos(Date dtInicial, Date dtFinal) {
		LocalDate ldInicial = dtInicial.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate ldFinal = dtFinal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		return ldInicial.until(ldFinal, ChronoUnit.YEARS);

	}

	public final static Long calcDifDias(Date dtInicial, Date dtFinal) {
		LocalDate ldInicial = dtInicial.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate ldFinal = dtFinal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		return ldInicial.until(ldFinal, ChronoUnit.DAYS);
	}

}
