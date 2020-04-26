package module;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

/**
 * @author Yael Nisanov
 * @since 10/03/2020
 */
public class BasicModule extends AbstractModule {

    @Override
    protected final void configure() {
        bind(String.class).annotatedWith(Names.named("filePath")).toInstance("soldiers.json");
    }

}
