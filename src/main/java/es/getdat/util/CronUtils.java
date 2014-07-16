package es.getdat.util;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.jboss.logging.Logger;
import org.quartz.Trigger;

public class CronUtils {

	static Logger logger = Logger.getLogger(CronUtils.class);

	public static StringBuffer getDays(String days) {
		StringBuffer cron = new StringBuffer();

		return cron;
	}

	public static StringBuffer getHours(String times) {
		StringBuffer cron = new StringBuffer();
		String[] blocks = times.split(":");
		int toAdd = 0;
		switch (blocks.length) {
		case 0:
			logger.info("error: 0 blocks [" + times + "]");
			toAdd = 3;
			break;
		case 1:
			logger.info("only hours [" + times + "]");
			toAdd = 2;
			break;
		case 2:
			logger.info("HH:mm [" + times + "]");
			toAdd = 1;
			break;
		case 3:
			logger.info("HH:mm:ss [" + times + "]");
			toAdd = 0;
			break;
		default:
			logger.info("error: too many blocks [" + times + "]");
			break;
		}
		for (String block : blocks) {
			cron.append(block).append(" ");
			if (block.contains("-")) {
				logger.info("range [" + block + "]");
			}
			if (block.contains("/")) {
				logger.info("increment [" + block + "]");
			}
			if (block.contains(",")) {
				logger.info("additional values [" + block + "]");
			}
		}
		for (int i = 0; i < toAdd; i++) {
			cron.append("*").append(" ");
		}
		return cron;
	}

	
}
