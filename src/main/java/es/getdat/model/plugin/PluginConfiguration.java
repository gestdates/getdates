package es.getdat.model.plugin;

import java.io.Serializable;
import java.util.Map;

import es.getdat.model.accounting.enums.ChannelType;

public class PluginConfiguration implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String PlguinDefinitionId;
	private ChannelType channelType;
	private Map<String, String> properties;
	private String location;
}
