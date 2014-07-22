package es.getdat.model.schedule;

import java.io.Serializable;
import java.util.Date;

import es.getdat.model.accounting.enums.ChannelType;

public class Event implements Serializable {

	private static final long serialVersionUID = 1L;

	private String accountId;
	private ChannelType channelType;
	private String activationId;
	private Content parameters;
	private Date date;
	private boolean executed;
}
