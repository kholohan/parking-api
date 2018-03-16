package org.kylecorp.config;

import org.glassfish.jersey.server.ResourceConfig;

//https://github.com/sofien-hamdi/rest-mongo
public class JerseyConfiguration extends ResourceConfig {

    public JerseyConfiguration() {
        super();
        register(new ApplicationBinder());
        packages("org.kylecorp");
    }
}
