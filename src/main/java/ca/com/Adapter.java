package ca.com;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import ca.com.microservice.utility.Logger;
import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;

@Startup
@ApplicationScoped
public class Adapter {

    Logger log = new Logger();
    
    /**
     * Observes Quarkus StartupEvent, then runs our initialization function.
     */
    public void onStartup(@Observes StartupEvent ev) {
     
    }
}