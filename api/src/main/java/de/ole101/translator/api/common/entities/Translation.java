package de.ole101.translator.api.common.entities;

import de.ole101.translator.api.common.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
@Accessors(fluent = true)
@NoArgsConstructor
@AllArgsConstructor
public class Translation {

    @Id
    private String id;

    @NotNull
    private Language targetLanguage;

    @NotNull
    private String sourceText;

    @NotNull
    private String translatedText;
}
