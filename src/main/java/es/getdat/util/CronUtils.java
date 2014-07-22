package es.getdat.util;

import org.jboss.logging.Logger;

public class CronUtils {

	static Logger logger = Logger.getLogger(CronUtils.class);

	static String ss_mm_hh = "0 0 0 ";
	static String A = "* ";
	static String Q = "? ";
	static String S = "  ";

	public static StringBuffer getDays(String days) {
		StringBuffer cron = new StringBuffer();

		return cron;
	}

	public static String getHours(String times) {
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
		for (int i = blocks.length - 1; i >= 0; i--) {
			cron.append(blocks[i]).append(" ");
			if (blocks[i].contains("-")) {
				logger.info("range [" + blocks[i] + "]");
			}
			if (blocks[i].contains("/")) {
				logger.info("increment [" + blocks[i] + "]");
			}
			if (blocks[i].contains(",")) {
				logger.info("additional values [" + blocks[i] + "]");
			}
		}

		for (int i = 0; i < toAdd; i++) {
			cron.append(A);
		}
		return cron.toString();
	}

	public static String annually(String days, String months) {
		return new StringBuffer(ss_mm_hh).append(days).append(S).append(months)
				.append(S).append(Q).toString();
	}

	public static String monthly(String days) {
		return new StringBuffer(ss_mm_hh).append(days).append(S).append(A)
				.append(Q).toString();
	}

	public static String weekly(String times, String days) {
		return new StringBuffer(times).append(S).append(Q).append(S).append(A)
				.append(days).toString();
	}

	public static String daily(String times) {
		return new StringBuffer(times).append(A).append(A).append(Q).toString();
	}
}
