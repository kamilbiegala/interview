package com.company.recruitment.dto;

import jakarta.validation.constraints.*;

public record CreateBookRequest(
        @NotBlank String title,
        @NotBlank @Size(min = 10, max = 17) String isbn,
        @Min(1400) @Max(2100) int publishedYear,
        @NotBlank String authorName
) {}
