package es.getdat.service.rs;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;

import es.getdat.model.PaginatedListWrapper;
import es.getdat.util.CronUtils;
import es.getdat.util.DateUtils;
import es.getdat.util.QuarzUtils;
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
		StringUtils.evaluateNullOrEmpty(cron, "cron");
		StringUtils.evaluateNullOrEmpty(from, "from");
		StringUtils.evaluateNullOrEmpty(to, "to");
		if (max == 0) {
			max = 10;
		}
		logger.info("max:" + max);
		Date dateFrom = DateUtils.getDateFromString(from);
		Date dateTo = DateUtils.getDateFromString(to);
		if (dateFrom.after(dateTo))
			throw new Exception("date from is after date to");
		List<Date> dates = QuarzUtils.getDates(cron, dateFrom, dateTo, max);
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
		StringUtils.evaluateNullOrEmpty(times, "times");
		StringUtils.evaluateNullOrEmpty(from, "from");
		StringUtils.evaluateNullOrEmpty(to, "to");
		if (max == 0) {
			max = 10;
		}
		logger.info("max:" + max);
		Date dateFrom = DateUtils.getDateFromString(from);
		Date dateTo = DateUtils.getDateFromString(to);
		if (dateFrom.after(dateTo))
			throw new Exception("date from is after date to");
		String cron = CronUtils.daily(CronUtils.getHours(times));
		StringUtils.evaluateNullOrEmpty(cron, "cron");
		List<Date> dates = QuarzUtils.getDates(cron, dateFrom, dateTo, max);
		logger.info("dates" + dates != null ? dates.size() : "null");
		return DateUtils.between(dateFrom, dateTo, dates);
	}

	// - weekly(days, times, from, to, max)
	// Day of week YES 1-7 or SUN-SAT , - * ? / L #
	@GET
	@Path("/weekly")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PaginatedListWrapper<String> weekly(@QueryParam("days") String days,
			@QueryParam("times") String times, @QueryParam("from") String from,
			@QueryParam("to") String to, @QueryParam("max") int max)
			throws Exception {
		StringUtils.evaluateNullOrEmpty(days, "days");
		StringUtils.evaluateNullOrEmpty(from, "from");
		StringUtils.evaluateNullOrEmpty(to, "to");
		if (max == 0) {
			max = 10;
		}
		logger.info("max:" + max);
		Date dateFrom = DateUtils.getDateFromString(from);
		Date dateTo = DateUtils.getDateFromString(to);
		if (dateFrom.after(dateTo))
			throw new Exception("date from is after date to");
		String cron = CronUtils.weekly(CronUtils.getHours(times), days);
		StringUtils.evaluateNullOrEmpty(cron, "cron");
		List<Date> dates = QuarzUtils.getDates(cron, dateFrom, dateTo, max);
		logger.info("dates" + dates != null ? dates.size() : "null");
		return DateUtils.between(dateFrom, dateTo, dates);
	}

	// - montly(days, from, to, max)
	@GET
	@Path("/monthly")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PaginatedListWrapper<String> monthly(
			@QueryParam("days") String days, @QueryParam("from") String from,
			@QueryParam("to") String to, @QueryParam("max") int max)
			throws Exception {
		StringUtils.evaluateNullOrEmpty(days, "days");
		StringUtils.evaluateNullOrEmpty(from, "from");
		StringUtils.evaluateNullOrEmpty(to, "to");
		if (max == 0) {
			max = 10;
		}
		Date dateFrom = DateUtils.getDateFromString(from);
		Date dateTo = DateUtils.getDateFromString(to);
		if (dateFrom.after(dateTo))
			throw new Exception("date from is after date to");
		String cron = CronUtils.monthly(days);
		StringUtils.evaluateNullOrEmpty(cron, "cron");
		List<Date> dates = QuarzUtils.getDates(cron, dateFrom, dateTo, max);
		logger.info("dates" + dates != null ? dates.size() : "null");
		return DateUtils.between(dateFrom, dateTo, dates);
	}

	// - annualy(months[], months[], from, to, max)
	@GET
	@Path("/annually")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PaginatedListWrapper<String> annually(
			@QueryParam("months") String months,
			@QueryParam("days") String days, @QueryParam("from") String from,
			@QueryParam("to") String to, @QueryParam("max") int max)
			throws Exception {
		StringUtils.evaluateNullOrEmpty(months, "months");
		StringUtils.evaluateNullOrEmpty(from, "from");
		StringUtils.evaluateNullOrEmpty(to, "to");
		if (max == 0) {
			max = 10;
		}
		logger.info("max:" + max);
		Date dateFrom = DateUtils.getDateFromString(from);
		Date dateTo = DateUtils.getDateFromString(to);
		if (dateFrom.after(dateTo))
			throw new Exception("date from is after date to");
		String cron = CronUtils.annually(days, months);
		StringUtils.evaluateNullOrEmpty(cron, "cron");
		List<Date> dates = QuarzUtils.getDates(cron, dateFrom, dateTo, max);

		logger.info("dates" + dates != null ? dates.size() : "null");
		return DateUtils.between(dateFrom, dateTo, dates);
	}
}
