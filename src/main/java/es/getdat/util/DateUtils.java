package es.getdat.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jboss.logging.Logger;

import es.getdat.model.PaginatedListWrapper;

public class DateUtils {

	static Logger logger = Logger.getLogger(DateUtils.class);
	static DateFormat dateFormat = new SimpleDateFormat(
			"dd/MM/yyyy HH:mm:ss.SSS");

	public static PaginatedListWrapper<String> between(Date from, Date to,
			List<Date> dates) throws Exception {
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

	public static Date getDateFromString(String dateString) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date date = df.parse(dateString);
		return date;
	}

	public static String getStringFromDate(Date data) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		String date = df.format(data);
		return date;
	}

	public static List<String> getStringFromDates(List<Date> dates) {
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			List<String> result = new ArrayList<>();
			for (Date date : dates) {
				String data = df.format(date);
				result.add(data);
			}
			return result;
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
