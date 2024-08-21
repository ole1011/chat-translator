package de.ole101.translator.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@AllArgsConstructor
@Accessors(fluent = true)
public enum Language {

    ENGLISH("en-US"),
    GERMAN("de");

    private final String code;
}
