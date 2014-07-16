package es.getdat.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jboss.logging.Logger;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.spi.OperableTrigger;

import es.getdat.model.PaginatedListWrapper;

public class DateUtils {

	static Logger logger = Logger.getLogger(DateUtils.class);
	static DateFormat dateFormat = new SimpleDateFormat(
			"dd/MM/yyyy HH:mm:ss.SSS");

	public static List<Date> nextTrigger(int num, Trigger t) {
		try {
			List<Date> times = TriggerUtils.computeFireTimes(
					(OperableTrigger) t, null, num);
			return times;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static PaginatedListWrapper<String> between(Date from, Date to,
			List<Date> dates) {
		List<String> result = new ArrayList<>();
		for (Date toControll : dates) {
			if (toControll.after(from) && toControll.before(to)) {
				result.add(DateUtils.getStringFromDate(toControll));
			}
		}
		PaginatedListWrapper<String> paginatedListWrapper = new PaginatedListWrapper<>();
		paginatedListWrapper.setCurrentPage(0);
		paginatedListWrapper.setPageSize(result.size());
		paginatedListWrapper.setTotalResults(result.size());
		paginatedListWrapper.setList(result);
		return paginatedListWrapper;
	}

	public static Date getDateFromString(String dateString) {
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			Date date = df.parse(dateString);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getStringFromDate(Date data) {
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			String date = df.format(data);
			return date;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date toBeginOfDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		logger.debug(dateFormat.format(date) + " -- toBeginOfDay --> "
				+ cal.getTime());
		return cal.getTime();
	}

	public static Date toEndOfDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		logger.debug(dateFormat.format(date) + " -- toEndOfDay ----> "
				+ cal.getTime());
		return cal.getTime();
	}
}
