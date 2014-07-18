package es.getdat.service.rs;

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;

import es.getdat.model.PaginatedListWrapper;
import es.getdat.model.accounting.enums.ChannelType;
import es.getdat.model.schedule.Content;

@Path("/v1/")
@Stateless
@LocalBean
public class EventRest implements Serializable {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(this.getClass());

	@POST
	@Path("/subscribe")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String subscribe() {

		return "";
	}

	@POST
	@Path("/account/{account}/events")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String scheduleEvent(String accountId, ChannelType channelType,
			Content parameters, String date) {

		return "";
	}

	@GET
	@Path("/account/{account}/events")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PaginatedListWrapper<String> getScheduledEvents() throws Exception {

		return null;
	}

	@GET
	@Path("/account/{account}/events/{event}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PaginatedListWrapper<String> getScheduledEvent() throws Exception {

		return null;
	}

}
