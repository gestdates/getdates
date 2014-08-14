package es.getdat.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;

@WebServlet(name = "FallbackServlet", value = { "/fallback/*" }, asyncSupported = true)
public class FallbackServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(getClass().getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		logger.info("----------");
		for (String key : req.getParameterMap().keySet()) {
			String value = req.getParameter(key);
			logger.info(key + ": " + value);
		}
		logger.info("----------");
		resp.setContentType("text/html");

		// Actual logic goes here.
		PrintWriter out = resp.getWriter();
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<Response></Response>");
	}
}
