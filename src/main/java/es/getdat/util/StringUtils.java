package es.getdat.util;

import org.jboss.logging.Logger;

public class StringUtils {

	static Logger logger = Logger.getLogger(StringUtils.class);

	public static boolean isNullOrEmpty(String value) {
		if (value == null || value.trim().isEmpty())
			return true;
		return false;

	}

	public static void evaluateNullOrEmpty(String value, String name)
			throws Exception {
		if (value == null)
			throw new Exception(name + " is null");
		if (value.trim().isEmpty())
			throw new Exception(name + " is empty");
		logger.info(name + ": " + value);
	}
}
