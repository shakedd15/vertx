package rest;

import com.google.inject.Inject;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * @author Yael Nisanov
 * @since 10/03/2020
 */
public class MyServer extends AbstractVerticle {

    @Inject
    private Handlers handlers;

    private Vertx vertx = Vertx.vertx();

    private Router router = Router.router(vertx);

    @Override
    public void start() {
        HttpServer server = vertx.createHttpServer();
        router.route().handler(BodyHandler.create());
        registerRoutes();
        server.requestHandler(router::accept).listen(8080, result -> {
            if(result.succeeded()){
                System.out.println("Listening on port 8080");
            } else {
                System.out.println("Try to reload server");
            }
        });
    }

    private void registerRoutes() {
        router.get("/getSoldier/:id").handler(handlers::getSoldier);
        router.get("/getAllSoldiers").handler(handlers::getAllSoldiers);
        router.post("/addSoldier").handler(handlers::addSoldier);
        router.delete("/removeSoldier/:id").handler(handlers::removeSoldier);
        router.put("/updateSoldier").handler(handlers::updateSoldier);
    }


}
