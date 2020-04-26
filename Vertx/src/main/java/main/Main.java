package main;

import com.google.inject.Guice;
import com.google.inject.Injector;
import module.BasicModule;
import rest.MyServer;

/**
 * @author Yael Nisanov
 * @since 03/03/2020
 */
public class Main {

    public static void main(String[] args) {
        final Injector injector = Guice.createInjector(new BasicModule());
        final MyServer vert = injector.getInstance(MyServer.class);
        vert.start();
    }
}
