package de.ole101.translator.common.models;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(fluent = true)
public class Translation {

    private final Language targetLanguage;
    private final String sourceText;
    private final String translatedText;

    @Getter
    public enum Language {

        ENGLISH("en-US"),
        GERMAN("de");

        private final String code;

        Language(String code) {
            this.code = code;
        }
    }
}
