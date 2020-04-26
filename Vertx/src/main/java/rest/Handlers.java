package rest;

import com.google.inject.Inject;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import soldier.Soldier;
import soldier.SoldierFile;

import java.util.Optional;

public class Handlers {

    private static final String SOLDIERS_LIST = "Soldiers list returned.\n";
    private static final String SOLDIER_NOT_FOUND = "Soldier not found.\n";
    private static final String SOLDIER_FOUND = "Soldier found.\n";
    private static final String SOLDIER_EXIST = "Soldier is already exist.\n";
    private static final String SOLDIER_ADDED = "The following soldier has been added.\n";
    private static final String SOLDIER_REMOVED = "Soldier removed.\n";
    private static final String SOLDIER_UPDATED = "Soldier updated.\n";

    private static final Logger logger = LogManager.getLogger(Handlers.class);

    @Inject
    private SoldierFile soldierFile;

    protected void getSoldier(RoutingContext routingContext) {
        String id = routingContext.request().getParam("id");
        Optional<Soldier> optionalSoldier = soldierFile.getSoldier(id);
        if (optionalSoldier.isPresent()) {
            logger.info(SOLDIER_FOUND);
            logger.debug(optionalSoldier.get().toJsonString());
            routingContext.response().end(optionalSoldier.get().toJsonString());
        } else {
            logger.info(SOLDIER_NOT_FOUND);
            routingContext.response().end(SOLDIER_NOT_FOUND);
        }
    }

    protected void getAllSoldiers(RoutingContext routingContext) {
        logger.info(SOLDIERS_LIST);
        logger.debug(soldierFile.getAllSoldiers().toJsonString());
        routingContext.response().end(soldierFile.getAllSoldiers().toJsonString());
    }

    protected void addSoldier(RoutingContext routingContext) {
        JsonObject jsonObject = routingContext.getBodyAsJson();
        HttpServerResponse response = routingContext.response();
        response.setChunked(true);
        if (soldierFile.addSoldier(jsonObject)) {
            logger.info(SOLDIER_ADDED);
            logger.debug(jsonObject.encodePrettily());
            response.write(SOLDIER_ADDED);
            response.end(jsonObject.encodePrettily());
        } else {
            logger.info(SOLDIER_EXIST);
            response.end(SOLDIER_EXIST);
        }
    }

    public void removeSoldier(RoutingContext routingContext) {
        String id = routingContext.request().getParam("id");
        HttpServerResponse response = routingContext.response();
        response.setChunked(true);
        if (soldierFile.removeSoldier(id)) {
            logger.info(SOLDIER_REMOVED);
            response.write(SOLDIER_REMOVED);
        } else {
            logger.info(SOLDIER_NOT_FOUND);
            response.write(SOLDIER_NOT_FOUND);
        }
        //getAllSoldiers(routingContext);
    }

    public void updateSoldier(RoutingContext routingContext) {
        JsonObject jsonObject = routingContext.getBodyAsJson();
        HttpServerResponse response = routingContext.response();
        response.setChunked(true);
        if (soldierFile.updateSoldier(jsonObject)) {
            logger.info(SOLDIER_UPDATED);
            response.write(SOLDIER_UPDATED);
        } else {
            logger.info(SOLDIER_NOT_FOUND);
            response.write(SOLDIER_NOT_FOUND);
        }
        //getAllSoldiers(routingContext);
    }
}
