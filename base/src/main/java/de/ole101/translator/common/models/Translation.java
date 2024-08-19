package de.ole101.translator.common.models;

import de.ole101.translator.common.enums.Language;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(fluent = true)
public class Translation {

    private final Language targetLanguage;
    private final String sourceText;
    private final String translatedText;
}
