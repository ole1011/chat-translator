package de.ole101.translator.services;

import de.ole101.translator.common.models.Translation;

@FunctionalInterface
public interface ChatService {

    String translate(String message, Translation.Language language);
}
