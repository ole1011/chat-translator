package de.ole101.translator.common;

import com.google.inject.AbstractModule;
import de.ole101.translator.services.ChatService;
import de.ole101.translator.services.impl.ChatServiceImpl;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ChatService.class).to(ChatServiceImpl.class).asEagerSingleton();
    }
}
