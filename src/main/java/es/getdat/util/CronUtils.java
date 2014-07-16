package es.getdat.util;

import org.jboss.logging.Logger;

public class CronUtils {

	static Logger logger = Logger.getLogger(CronUtils.class);

	public static StringBuffer getDays(String days) {
		StringBuffer cron = new StringBuffer();

		return cron;
	}

	public static StringBuffer getHours(String times) {
		StringBuffer cron = new StringBuffer();
		String[] blocks = times.split(":");
		switch (blocks.length) {
		case 0:
			logger.info("error: 0 blocks [" + times + "]");
			break;
		case 1:
			logger.info("only hours [" + times + "]");
			break;
		case 2:
			logger.info("HH:mm [" + times + "]");
			break;
		case 3:
			logger.info("HH:mm:ss [" + times + "]");
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
		return cron;
	}
}
