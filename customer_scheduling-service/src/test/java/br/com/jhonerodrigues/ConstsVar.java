package br.com.jhonerodrigues;

import java.time.LocalDate;

import br.com.jhonerodrigues.core.domain.enums.StandardTimes;

public class ConstsVar {

	// client
	public static final Long CLIENT_ID = 2L;
	public static final String CLIENT_PHONE = "(00) 90001-0002";
	public static final String CLIENT_NAME = "Gerson";
	public static final LocalDate CLIENT_BIRTHDAY = LocalDate.parse("2000-02-02");

	// job
	public static final Long JOB_ID = 1L;
	public static final String JOB_NAME = "Corte";
	public static final Double JOB_PRICE = 30.00;
	public static final Integer JOB_DURATION = 30;

	// scheduling
	public static final Long SCHEDULING_ID = 5L;
	public static final Long SCHEDULING_PROF_ID = 4L;
	public static final StandardTimes SCHEDULING_TIME = StandardTimes.T09_11;
	public static final LocalDate SCHEDULING_DAY = LocalDate.parse("2023-02-02");

}
