package de.ole101.translator.services;

import de.ole101.translator.common.enums.Language;

@FunctionalInterface
public interface ChatService {

    String translate(String message, Language language);
}
