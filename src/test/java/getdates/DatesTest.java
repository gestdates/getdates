package getdates;

import java.util.Date;
import java.util.List;

import org.jboss.logging.Logger;
import org.junit.Assert;
import org.junit.Test;

import es.getdat.util.CronUtils;
import es.getdat.util.DateUtils;
import es.getdat.util.QuarzUtils;

public class DatesTest {

	static Logger logger = Logger.getLogger(DatesTest.class);

	@Test
	public void cron() throws Exception {
		String cron = "0 0 12 * * ?";
		String from = "2014-07-16T00:00:00";
		String to = "2014-07-26T00:00:00";
		int max = 10;

		Date dateFrom = DateUtils.getDateFromString(from);
		Assert.assertNotNull(dateFrom);
		Date dateTo = DateUtils.getDateFromString(to);
		Assert.assertNotNull(dateTo);
		List<Date> dates = QuarzUtils.getDates(cron.toString(), dateFrom,
				dateTo, max);
		Assert.assertNotNull(dates);
		Assert.assertEquals(dates.size(), 10);
		logger.info(cron);
		logger.info(DateUtils.getStringFromDates(dates));
	}

	@Test
	public void dailyIncrement() throws Exception {
		String times = "15/3:30:00";
		String from = "2014-07-16T00:00:00";
		String to = "2014-07-26T00:00:00";
		int max = 10;

		Date dateFrom = DateUtils.getDateFromString(from);
		Assert.assertNotNull(dateFrom);
		Date dateTo = DateUtils.getDateFromString(to);
		Assert.assertNotNull(dateTo);
		String cronTimes = CronUtils.getHours(times);
		String cron = CronUtils.daily(cronTimes);
		Assert.assertNotNull(cron);
		List<Date> dates = QuarzUtils.getDates(cron, dateFrom, dateTo, max);
		Assert.assertNotNull(dates);
		Assert.assertEquals(dates.size(), 10);
		logger.info(cron);
		logger.info(DateUtils.getStringFromDates(dates));
	}

	@Test
	public void dailyRange() throws Exception {
		String times = "15-18:30:00";
		String from = "2014-07-16T00:00:00";
		String to = "2014-07-26T00:00:00";
		int max = 10;

		Date dateFrom = DateUtils.getDateFromString(from);
		Assert.assertNotNull(dateFrom);
		Date dateTo = DateUtils.getDateFromString(to);
		Assert.assertNotNull(dateTo);
		String cronTimes = CronUtils.getHours(times);
		String cron = CronUtils.daily(cronTimes);
		Assert.assertNotNull(cron);
		List<Date> dates = QuarzUtils.getDates(cron, dateFrom, dateTo, max);
		Assert.assertNotNull(dates);
		Assert.assertEquals(dates.size(), 10);
		logger.info(cron);
		logger.info(DateUtils.getStringFromDates(dates));
	}

	@Test
	public void dailyAdditionalValues() throws Exception {
		String times = "15,18:30:00";
		String from = "2014-07-16T00:00:00";
		String to = "2014-07-26T00:00:00";
		int max = 10;

		Date dateFrom = DateUtils.getDateFromString(from);
		Assert.assertNotNull(dateFrom);
		Date dateTo = DateUtils.getDateFromString(to);
		Assert.assertNotNull(dateTo);
		String cronTimes = CronUtils.getHours(times);
		String cron = CronUtils.daily(cronTimes);
		Assert.assertNotNull(cron);
		List<Date> dates = QuarzUtils.getDates(cron, dateFrom, dateTo, max);
		Assert.assertNotNull(dates);
		Assert.assertEquals(dates.size(), 10);
		logger.info(cron);
		logger.info(DateUtils.getStringFromDates(dates));
	}

	@Test
	public void weekly() throws Exception {
		String days = "1";
		String times = "15:30:00";
		String from = "2014-07-16T00:00:00";
		String to = "2014-12-26T00:00:00";
		int max = 10;
		Date dateFrom = DateUtils.getDateFromString(from);
		Assert.assertNotNull(dateFrom);
		Date dateTo = DateUtils.getDateFromString(to);
		Assert.assertNotNull(dateTo);
		String cronTimes = CronUtils.getHours(times);
		String cron = CronUtils.weekly(cronTimes, days);

		Assert.assertNotNull(cron);
		List<Date> dates = QuarzUtils.getDates(cron.toString(), dateFrom,
				dateTo, max);
		Assert.assertNotNull(dates);
		Assert.assertEquals(dates.size(), 10);
		logger.info(cron);
		logger.info(DateUtils.getStringFromDates(dates));
	}

	@Test
	public void montly() throws Exception {
		String days = "1";
		String from = "2014-07-16T00:00:00";
		String to = "2015-12-26T00:00:00";
		int max = 10;
		Date dateFrom = DateUtils.getDateFromString(from);
		Assert.assertNotNull(dateFrom);
		Date dateTo = DateUtils.getDateFromString(to);
		Assert.assertNotNull(dateTo);

		String cron = CronUtils.monthly(days);

		List<Date> dates = QuarzUtils.getDates(cron, dateFrom, dateTo, max);
		Assert.assertNotNull(dates);
		Assert.assertEquals(dates.size(), 10);
		logger.info(cron);
		logger.info(DateUtils.getStringFromDates(dates));
	}

	@Test
	public void annualy() throws Exception {
		String days = "1";
		String months = "1";
		String from = "2014-07-16T00:00:00";
		String to = "2034-12-26T00:00:00";
		int max = 10;
		Date dateFrom = DateUtils.getDateFromString(from);
		Assert.assertNotNull(dateFrom);
		Date dateTo = DateUtils.getDateFromString(to);
		Assert.assertNotNull(dateTo);

		String cron = CronUtils.annually(days, months);
		List<Date> dates = QuarzUtils.getDates(cron, dateFrom, dateTo, max);
		Assert.assertNotNull(dates);
		Assert.assertEquals(dates.size(), 10);
		logger.info(cron);
		logger.info(DateUtils.getStringFromDates(dates));
	}
}
