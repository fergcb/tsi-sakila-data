package uk.fergcb.sakila.data.resources.language;

import java.util.Optional;

public class LanguageDTO {
    private String name;

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public void setName(String name) {
        this.name = name;
    }
}
