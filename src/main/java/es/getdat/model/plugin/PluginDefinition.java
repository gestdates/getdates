package es.getdat.model.plugin;

import java.io.Serializable;
import java.util.List;

import es.getdat.model.accounting.enums.ChannelType;

public class PluginDefinition implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private boolean systemDefault;
	private ChannelType channelType;
	private List<String> properties;
}
