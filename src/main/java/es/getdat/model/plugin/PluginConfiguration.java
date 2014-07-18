package es.getdat.model.plugin;

import java.util.Map;

import es.getdat.model.accounting.enums.ChannelType;

public class PluginConfiguration {
	private String id;
	private String PlguinDefinitionId;
	private ChannelType channelType;
	private Map<String, String> properties;
	private String location;
}
