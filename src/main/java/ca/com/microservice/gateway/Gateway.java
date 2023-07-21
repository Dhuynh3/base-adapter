package ca.com.microservice.gateway;

import javax.enterprise.event.Observes;

import ca.com.microservice.messages.MessageHandler;
import ca.com.microservice.utility.Logger;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;

/**
 * The Gateway class will create one or multiple
 * endpoints for messages to enter the adapter.
 */
public class Gateway {
    Logger log = new Logger();
    MessageHandler msgHandler = new MessageHandler();

    private static Router router;

    public Gateway() {
        log.info("[Gateway] : Constructor Called");
    }

    /**
     * NOTES: The @Observes Router executes first than StartupEvent.
     * @param route
     */
    public void initRoute(@Observes Router route) {
        log.info("[Gateway] : initRoute called");

        // Store the route
        Gateway.router = route;
        
        // Required routes to setup on adapter startup
        // "/initialize" For microservice to initialize the adapter
        router.route(HttpMethod.POST, "/initialize").handler(msgHandler::handleInitialization);

        // "/status" For microservice to get status of the adapter
        router.route(HttpMethod.GET, "/status").handler(msgHandler::handleStatus);

        // "/microservice-inbound" For microservice to send the message
        router.route(HttpMethod.POST, "/microservice-inbound").handler(msgHandler::handleMessage);
    }

}
