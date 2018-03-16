package org.kylecorp;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.jaxrs.xml.JacksonJaxbXMLProvider;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ErrorHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;
import org.kylecorp.config.JerseyConfiguration;
import org.kylecorp.resource.ParkingResource;
import org.kylecorp.service.ParkingService;
import org.kylecorp.util.exception.ParkingRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.StringJoiner;

public class Application {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        ServletContextHandler servletHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletHandler.setContextPath("/");

        //ServletContainer servletContainer = new ServletContainer(new JerseyConfiguration());
        //ServletHolder jerseyServlet = new ServletHolder(new ServletContainer(new JerseyConfiguration()));
        //servletHandler.addServlet(jerseyServlet, "/*");
        ServletHolder jerseyServlet = servletHandler.addServlet(ServletContainer.class, "/api/*");
        jerseyServlet.setInitOrder(0);

        jerseyServlet.setInitParameter(
                ServerProperties.PROVIDER_CLASSNAMES,
                new StringJoiner(",")
                        .add(ParkingResource.class.getCanonicalName())
                        .add(ParkingService.class.getCanonicalName())
                        .add(ParkingRuntimeException.class.getCanonicalName())
                        //.add(JacksonJsonProvider.class.getCanonicalName())
                        .add(JacksonJaxbJsonProvider.class.getCanonicalName())
                        .add(JacksonJaxbXMLProvider.class.getCanonicalName())
                        .add(ApiListingResource.class.getCanonicalName())
                        .add(SwaggerSerializers.class.getCanonicalName())
                        .toString());
        jerseyServlet.setInitParameter("javax.ws.rs.Application", JerseyConfiguration.class.getName() );


        ResourceHandler staticHandler = new ResourceHandler();
        //staticHandler.setWelcomeFiles(new String[]{"index.html"});
        staticHandler.setBaseResource(Resource.newClassPathResource("/webapp"));

        HandlerList handlers = new HandlerList();
        // Add two handlers, one for static content and the other one for dynamic content.
        handlers.setHandlers(new Handler[]{staticHandler, servletHandler});

        // The mapping doesn't really matter here.
        ServletHolder swaggerServlet = servletHandler.addServlet(Bootstrap.class, "/");
        swaggerServlet.setInitOrder(2);

        Server jettyServer = new Server(8080);
        jettyServer.setHandler(handlers);

        ErrorHandler errorHandler = new ErrorHandler();
        errorHandler.setShowStacks(true);
        jettyServer.addBean(errorHandler);

        try {
            jettyServer.start();
            jettyServer.join();
        } catch (Exception ex) {
            logger.error("Error occurred while starting Jetty", ex);
            System.exit(1);
        } finally {
            jettyServer.destroy();
        }
    }

    public static class Bootstrap extends HttpServlet {
        @Override
        public void init(ServletConfig config) throws ServletException {
            super.init(config);

            BeanConfig beanConfig = new BeanConfig();
            beanConfig.setTitle("Parking API");
            beanConfig.setVersion("1.0.0");
            beanConfig.setSchemes(new String[]{"http"});
            beanConfig.setBasePath("/api");
            beanConfig.setResourcePackage("org.kylecorp");
            beanConfig.setScan(true);
        }
    }
}
