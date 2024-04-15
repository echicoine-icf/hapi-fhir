package ca.uhn.fhir.rest.server;

import ca.uhn.fhir.rest.server.interceptor.LoggingInterceptor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(
	urlPatterns = {"/fhir/*"},
	displayName = "FHIR Server")
public class LoggingInterceptorRestfulServer extends RestfulServer {

	@Override
	protected void initialize() throws ServletException {

		// Now register the logging interceptor
		LoggingInterceptor loggingInterceptor = new LoggingInterceptor();
		registerInterceptor(loggingInterceptor);

		// The SLF4j logger "test.accesslog" will receive the logging events
		loggingInterceptor.setLoggerName("test.accesslog");

		// This is the format for each line. A number of substitution variables may
		// be used here. See the JavaDoc for LoggingInterceptor for information on
		// what is available.
		loggingInterceptor.setMessageFormat(
			"Source[${remoteAddr}] Operation[${operationType} ${idOrResourceName}] UA[${requestHeader.user-agent}] Params[${requestParameters}]");
	}
}
