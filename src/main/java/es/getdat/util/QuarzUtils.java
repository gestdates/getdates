package es.getdat.util;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.spi.OperableTrigger;

public class QuarzUtils {

	public static List<Date> getDates(String cron, Date from, Date to, int max)
			throws Exception {
		Trigger t = newTrigger()
				.withIdentity("test", "test")
				.withSchedule(
						cronSchedule(cron).inTimeZone(
								TimeZone.getTimeZone("Europe/Rome")))
				.startAt(from).endAt(to).build();
		List<Date> dates = nextTrigger(max, t);
		return dates;
	}

	public static List<Date> nextTrigger(int num, Trigger t) throws Exception {
		List<Date> times = TriggerUtils.computeFireTimes((OperableTrigger) t,
				null, num);
		return times;
	}
}
