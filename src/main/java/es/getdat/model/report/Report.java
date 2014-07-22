package es.getdat.model.report;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class Report implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private Map<String, String> values;
	private Date begin;
	private Date end;

}
