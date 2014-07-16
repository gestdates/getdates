package es.getdat.provider;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

@Provider
@PreMatching
public class HeaderRequestFilter implements ContainerRequestFilter {

	Logger logger = Logger.getLogger(this.getClass());

	// X-Mashape-Proxy-Secret:
	// X-Mashape-User:
	// X-Mashape-Subscription:
	// X-Mashape-Version: The
	// X-Forwarded-For:

	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		String secret = requestContext.getHeaders().getFirst(
				"X-Mashape-Proxy-Secret");
		logger.info("X-Mashape-Proxy-Secret:" + secret);
		String user = requestContext.getHeaders().getFirst("X-Mashape-User");
		logger.info("X-Mashape-User:" + user);
		String subscription = requestContext.getHeaders().getFirst(
				"X-Mashape-Subscription");
		logger.info("X-Mashape-Subscription:" + subscription);
		String version = requestContext.getHeaders().getFirst(
				"X-Mashape-Version");
		logger.info("X-Mashape-Version:" + version);
		String forwarded = requestContext.getHeaders().getFirst(
				"X-Forwarded-For");
		logger.info("X-Forwarded-For:" + forwarded);
	}
}