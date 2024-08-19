package de.ole101.translator.api.exceptions;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public class EntryNotFoundException extends ApiException {

    public EntryNotFoundException(Class<?> clazz, Class<?> repository, String entry) {
        super(clazz, format("Entry not found in %s with qualifier %s", repository, entry), NOT_FOUND);
    }
}
