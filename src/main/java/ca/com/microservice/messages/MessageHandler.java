package ca.com.microservice.messages;

import ca.com.microservice.utility.Logger;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.RoutingContext;

/**
 * The MessageHandler class will process all incoming messages from the
 * Gateway's endpoints.
 */
public class MessageHandler {
    Logger log = new Logger();
    MessageValidator validator = new MessageValidator();

    public MessageHandler() {
        log.info("MessageHandler Constructor Called");
    }

    //////////////////////////////////////////////////////////////
    public void handleMessageBody(Buffer buffer) {
        String b = buffer.toString();
        log.info("[/microservice-inbound] : Message Body : " + b);
    }
    public void handleMessage(RoutingContext rc) {
        log.info("[MessageHandler] : Handling '/microservice-inbound' Context");
        rc.request().bodyHandler(this::handleMessageBody);
        rc.response().end("Success");
    }
    //////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////
    /**
     * FUTURE IMPLEMENTATOIN: This handler will perform health checks on the status
     * of the service
     * 
     * @param rc RoutingContext
     */
    public void handleStatus(RoutingContext rc) {
        log.info("[MessageHandler] : Handling '/status' Context");
        rc.response().end("Success");
    }
    //////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////
    /**
     * 
     * @param body
     */
    public void handleInitializationBody(Buffer body) {
        String b = body.toString();
        log.info("[/initialize] : Message Body : " + b);
    }
    /*
     * This handler is called when a microservice POSTs the adapter an initialization call
     * at endpoint "/initialize"
     */
    public void handleInitialization(RoutingContext rc) {

        // Log the handler
        log.info("[MessageHandler] : Handling '/initialize' Context");

        // Add a body handler
        rc.request().bodyHandler(this::handleInitializationBody);
        
        // Return a response with string "Success"
        rc.response().end("Success");
    }
    //////////////////////////////////////////////////////
}