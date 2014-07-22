package es.getdat.model.accounting;

import java.io.Serializable;
import java.util.Date;

import es.getdat.model.accounting.enums.ChannelType;

public class Activation implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String accountId;
	private String pluginConfigurationId;
	private ChannelType channelType;
	private boolean userDefault;
	private Date init;
	private Date end;

}
