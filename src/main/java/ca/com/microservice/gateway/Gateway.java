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
        log.info("Gateway Constructor Called");
    }


    /**
     * NOTES: The @Observes Router executes first than StartupEvent.
     * @param route
     */
    public void initRoute(@Observes Router route) {
        log.info("Gateway initRoute called");
        Gateway.router = route;
        router.route(HttpMethod.GET, "/microservice-inbound").handler(msgHandler::handleMessage);
        router.route(HttpMethod.GET, "/status").handler(msgHandler::handleStatus);

        router.route(HttpMethod.POST, "/initialize").handler(msgHandler::handleInitialization);


    }

}
