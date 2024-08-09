package de.ole101.translator.common;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import de.ole101.translator.services.ChatService;
import de.ole101.translator.services.impl.ChatServiceImpl;
import io.github.cdimascio.dotenv.Dotenv;

public class GuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ChatService.class).to(ChatServiceImpl.class).asEagerSingleton();

        Dotenv dotenv = Dotenv.configure()
                .directory("src/main/resources")
                .load();

        bind(String.class)
                .annotatedWith(Names.named("deeplApiKey"))
                .toInstance(dotenv.get("DEEPL_API_KEY"));
    }
}
