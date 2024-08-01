package de.ole101.translator.services.impl;

import com.deepl.api.DeepLException;
import com.deepl.api.TextResult;
import com.deepl.api.Translator;
import de.ole101.translator.ChatTranslator;
import de.ole101.translator.common.models.Translation;
import de.ole101.translator.services.ChatService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class ChatServiceImpl implements ChatService {

    private final List<Translation> translations = new ArrayList<>();

    @Override
    public String translate(String message, Translation.Language language) {
        Optional<Translation> translation = this.translations.stream()
                .filter(translate -> translate.sourceText().equals(message) && translate.targetLanguage() == language)
                .findFirst();
        if (translation.isPresent()) {
            log.info("Retrieved translation from cache");
            return translation.get().translatedText();
        }

        Translator translator = new Translator(ChatTranslator.DEEPL_API_KEY);
        TextResult result;
        try {
            result = translator.translateText(message, null, language.code());
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

        log.info("Translated message: {}", resultText);

        return resultText;
    }
}
