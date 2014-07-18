package es.getdat.model.plugin;

import java.util.List;

import es.getdat.model.accounting.enums.ChannelType;

public class PluginDefinition {
	private String id;
	private String name;
	private boolean systemDefault;
	private ChannelType channelType;
	private List<String> properties;
}
