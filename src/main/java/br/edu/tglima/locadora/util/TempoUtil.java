package br.edu.tglima.locadora.util;

import java.util.Calendar;
import java.util.Date;

public class TempoUtil {

	public final static Date setDateNow(){
		Date dt = new Date();
		return dt;
	}
	
	public final static Date setDateLastMouth(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		return	c.getTime();
	}
	
	public final static Date setDate20yearsAgo(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, -20);
		return	c.getTime();
	}
	
	public final static Date setDate2Yearslater(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, 2);
		return	c.getTime();
	}
	
}
