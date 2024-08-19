package de.ole101.translator.services.impl;

import com.deepl.api.DeepLException;
import com.deepl.api.TextResult;
import com.deepl.api.Translator;
import com.google.inject.Inject;
import de.ole101.translator.common.enums.Language;
import de.ole101.translator.common.models.Translation;
import de.ole101.translator.services.ChatService;
import jakarta.inject.Named;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class ChatServiceImpl implements ChatService {

    private final List<Translation> translations = new ArrayList<>();
    private final Translator translator;

    @Inject
    public ChatServiceImpl(@Named("deeplApiKey") String apiKey) {
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalArgumentException("DeepL API key must not be null or empty");
        }
        this.translator = new Translator(apiKey);
    }

    @Override
    public String translate(String message, Language language) {
        Optional<Translation> optionalTranslation = this.translations.stream()
                .filter(translate -> translate.sourceText().equals(message) && translate.targetLanguage() == language)
                .findFirst();
        if (optionalTranslation.isPresent()) {
            Translation translation = optionalTranslation.get();

            log.debug("Retrieving translation from cache: {} - {}", message, translation.translatedText());
            return translation.translatedText();
        }

        TextResult result;
        try {
            result = this.translator.translateText(message, null, language.code());
        } catch (DeepLException | InterruptedException e) {
            log.error("Error while translating message", e);
            return message;
        }

        String resultText = result.getText();
        this.translations.add(Translation.builder()
                .targetLanguage(language)
                .sourceText(message)
                .translatedText(resultText)
                .build());

        log.debug("Translated message: {} -> {}", message, resultText);

        return resultText;
    }
}
