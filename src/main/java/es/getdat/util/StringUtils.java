package es.getdat.util;

public class StringUtils {
	public static boolean isNullOrEmpty(String value) {
		if (value == null || value.trim().isEmpty())
			return true;
		return false;

	}
}
