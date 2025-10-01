package com.company.recruitment.book;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ValidationTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Disabled("TASK: enable after you add extra constraints")
    @Test
    void bookTitle_cannotBeBlank() {
        var dto = new Dummy("  ", "1234567890", 2000, "Author X");
        var violations = validator.validate(dto);
        assertThat(violations).isNotEmpty();
    }

    record Dummy(
            String title, String isbn, int publishedYear, String authorName
    ) {}
}
