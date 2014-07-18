package es.getdat.model.accounting;

import java.util.Date;

import es.getdat.model.accounting.enums.ChannelType;

public class Activation {
	private String id;
	private String accountId;
	private String pluginConfigurationId;
	private ChannelType channelType;
	private boolean userDefault;
	private Date init;
	private Date end;

}
