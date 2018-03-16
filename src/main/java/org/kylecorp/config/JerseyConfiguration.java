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
        this.configureSwagger();
        this.registerEndpoints();

        packages("org.kylecorp.resource");
        packages("io.swagger.jaxrs.listing");
    }


    private void registerEndpoints(){
        this.register(ParkingResource.class);
        this.register(WadlResource.class);
    }

    private void configureSwagger() {
        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);
        BeanConfig config = new BeanConfig();
        config.setTitle("Parking Service RESTful Documentation");
        config.setVersion("v1");
        config.setBasePath("/");
        config.setResourcePackage("com.kylecorp.resource");
        config.setPrettyPrint(true);
        config.setScan(true);
        // http://localhost:9090/v1/swagger.json
    }
}
