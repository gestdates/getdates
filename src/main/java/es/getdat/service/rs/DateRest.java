package es.getdat.service.rs;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;
import org.quartz.Trigger;

import es.getdat.model.PaginatedListWrapper;
import es.getdat.util.CronUtils;
import es.getdat.util.DateUtils;
import es.getdat.util.StringUtils;

@Path("/v1/")
@Stateless
@LocalBean
public class DateRest implements Serializable {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());

	// - cron (expression, from, to, max)
	/*
	 * es1: 0 0 12 * * ?
	 */
	@GET
	@Path("/cron")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PaginatedListWrapper<String> cron(@QueryParam("cron") String cron,
			@QueryParam("from") String from, @QueryParam("to") String to,
			@QueryParam("max") int max) throws Exception {
		if (StringUtils.isNullOrEmpty(cron) || StringUtils.isNullOrEmpty(from)
				|| StringUtils.isNullOrEmpty(to) || max == 0) {
			throw new Exception("parametri non validi");
		}
		logger.info("cron:" + cron);
		logger.info("from:" + from);
		logger.info("to:" + to);
		logger.info("max:" + max);

		Date dateFrom = DateUtils.getDateFromString(from);
		Date dateTo = DateUtils.getDateFromString(to);
		Trigger t = newTrigger()
				.withIdentity("test", "test")
				.withSchedule(
						cronSchedule(cron).inTimeZone(
								TimeZone.getTimeZone("Europe/Rome")))
				.startAt(dateFrom).endAt(dateTo).build();
		List<Date> dates = DateUtils.nextTrigger(max, t);
		logger.info("dates" + dates != null ? dates.size() : "null");
		return DateUtils.between(dateFrom, dateTo, dates);
	}

	/*
	 * daily(times, from, to, max)
	 * 
	 * es1: HH/HH:mm/mm:ss/ss
	 * 
	 * es2 HH-HH:mm-mm:ss-ss
	 * 
	 * es3: HH-HH:mm
	 * 
	 * es4: HH:mm-mm
	 * 
	 * es5: HH:mm:ss-ss
	 * 
	 * es6: HH-HH
	 * 
	 * * es7: HH-HH
	 */
	@GET
	@Path("/daily")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PaginatedListWrapper<String> daily(
			@QueryParam("times") String times, @QueryParam("from") String from,
			@QueryParam("to") String to, @QueryParam("max") int max)
			throws Exception {
		if (StringUtils.isNullOrEmpty(times) || StringUtils.isNullOrEmpty(from)
				|| StringUtils.isNullOrEmpty(to) || max == 0) {
			throw new Exception("parametri non validi");
		}
		logger.info("times:" + times);
		logger.info("from:" + from);
		logger.info("to:" + to);
		logger.info("max:" + max);

		Date dateFrom = DateUtils.getDateFromString(from);
		Date dateTo = DateUtils.getDateFromString(to);
		StringBuffer cron = CronUtils.getHours(times).append("* * ?");
		Trigger t = newTrigger()
				.withIdentity("test", "test")
				.withSchedule(
						cronSchedule(cron.toString()).inTimeZone(
								TimeZone.getTimeZone("Europe/Rome")))
				.startAt(dateFrom).endAt(dateTo).build();
		List<Date> dates = DateUtils.nextTrigger(max, t);
		logger.info("dates" + dates != null ? dates.size() : "null");
		return DateUtils.between(dateFrom, dateTo, dates);
	}

	// - weekly(days, times, from, to, max)
	@GET
	@Path("/weekly")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PaginatedListWrapper<String> weekly(@QueryParam("days") String days,
			@QueryParam("times") String times, @QueryParam("from") String from,
			@QueryParam("to") String to, @QueryParam("max") int max)
			throws Exception {
		if (StringUtils.isNullOrEmpty(days) || StringUtils.isNullOrEmpty(from)
				|| StringUtils.isNullOrEmpty(to) || max == 0) {
			throw new Exception("parametri non validi");
		}
		logger.info("times:" + times);
		logger.info("days:" + days);
		logger.info("from:" + from);
		logger.info("to:" + to);
		logger.info("max:" + max);

		Date dateFrom = DateUtils.getDateFromString(from);
		Date dateTo = DateUtils.getDateFromString(to);
		StringBuffer cron = CronUtils.getHours(times).append(
				CronUtils.getDays(days));

		Trigger t = newTrigger()
				.withIdentity("test", "test")
				.withSchedule(
						cronSchedule(cron.toString()).inTimeZone(
								TimeZone.getTimeZone("Europe/Rome")))
				.startAt(dateFrom).endAt(dateTo).build();
		List<Date> dates = DateUtils.nextTrigger(max, t);
		logger.info("dates" + dates != null ? dates.size() : "null");
		return DateUtils.between(dateFrom, dateTo, dates);
	}

	// - montly(days[], from, to, max)
	@GET
	@Path("/montly")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PaginatedListWrapper<String> montly(
			@QueryParam("days") List<String> days,
			@QueryParam("from") String from, @QueryParam("to") String to,
			@QueryParam("max") int max) throws Exception {
		if (days == null || days.size() == 0 || StringUtils.isNullOrEmpty(from)
				|| StringUtils.isNullOrEmpty(to) || max == 0) {
			throw new Exception("parametri non validi");
		}
		logger.info("days:" + days);
		logger.info("from:" + from);
		logger.info("to:" + to);
		logger.info("max:" + max);

		Date dateFrom = DateUtils.getDateFromString(from);
		Date dateTo = DateUtils.getDateFromString(to);
		StringBuffer cron = new StringBuffer();
		for (String day : days) {

		}
		Trigger t = newTrigger()
				.withIdentity("test", "test")
				.withSchedule(
						cronSchedule(cron.toString()).inTimeZone(
								TimeZone.getTimeZone("Europe/Rome")))
				.startAt(dateFrom).endAt(dateTo).build();
		List<Date> dates = DateUtils.nextTrigger(max, t);
		logger.info("dates" + dates != null ? dates.size() : "null");
		return DateUtils.between(dateFrom, dateTo, dates);
	}

	// - annualy(months[], months[], from, to, max)
	@GET
	@Path("/annualy")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PaginatedListWrapper<String> annualy(
			@QueryParam("months") List<String> months,
			@QueryParam("from") String from, @QueryParam("to") String to,
			@QueryParam("max") int max) throws Exception {
		if (months == null || months.size() == 0
				|| StringUtils.isNullOrEmpty(from)
				|| StringUtils.isNullOrEmpty(to) || max == 0) {
			throw new Exception("parametri non validi");
		}
		logger.info("days:" + months);
		logger.info("from:" + from);
		logger.info("to:" + to);
		logger.info("max:" + max);

		Date dateFrom = DateUtils.getDateFromString(from);
		Date dateTo = DateUtils.getDateFromString(to);
		StringBuffer cron = new StringBuffer();
		for (String month : months) {

		}
		Trigger t = newTrigger()
				.withIdentity("test", "test")
				.withSchedule(
						cronSchedule(cron.toString()).inTimeZone(
								TimeZone.getTimeZone("Europe/Rome")))
				.startAt(dateFrom).endAt(dateTo).build();
		List<Date> dates = DateUtils.nextTrigger(max, t);
		logger.info("dates" + dates != null ? dates.size() : "null");
		return DateUtils.between(dateFrom, dateTo, dates);
	}

}
