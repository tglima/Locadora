package br.edu.tglima.locadora.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
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
		LocalDate ldInicial = convParaLocalDate(dtInicial);
		LocalDate ldFinal = convParaLocalDate(dtFinal);
		return ldInicial.until(ldFinal, ChronoUnit.DAYS);
	}

	public final static Long calcDifDias(LocalDate ldInicial, LocalDate ldFinal) {
		return ldInicial.until(ldFinal, ChronoUnit.DAYS);
	}

	public final static Date plusDays(Date date, long qtdDays) {
		return convParaDate(convParaLocalDate(date).plusDays(qtdDays));
	}

	public final static Date convParaDate(LocalDate localDate) {
		Instant i = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		return Date.from(i);
	}

	public final static LocalDate convParaLocalDate(Date date) {
		return LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(date));
	}

}
