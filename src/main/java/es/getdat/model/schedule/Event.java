package es.getdat.model.schedule;

import java.util.Date;

import es.getdat.model.accounting.enums.ChannelType;

public class Event {
	private String accountId;
	private ChannelType channelType;
	private String activationId;
	private Content parameters;
	private Date date;
	private boolean executed;
}
