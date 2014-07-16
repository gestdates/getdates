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
import es.getdat.util.DateUtils;
import es.getdat.util.StringUtils;

@Path("/v1/")
@Stateless
@LocalBean
public class DateRest implements Serializable {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());

	// -list<Date> = daily(times[], from, to, max)
	// - weekly(days[], from, to, max)
	// - montly(days[], from, to, max)
	// - annualy(days[], months[], from, to, max)
	// - cron (expression, from, to, max)

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
		logger.info("max" + max);

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
}
