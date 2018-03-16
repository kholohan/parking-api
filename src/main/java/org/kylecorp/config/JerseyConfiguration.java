package org.kylecorp.config;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.kylecorp.resource.ParkingResource;

//https://github.com/sofien-hamdi/rest-mongo
public class JerseyConfiguration extends ResourceConfig {

    public JerseyConfiguration() {
        super();
        register(new ApplicationBinder());
        packages("org.kylecorp");
    }
}
