package com.company.recruitment.dto;

public record BookDTO(
        Long id,
        String title,
        String isbn,
        int publishedYear,
        String authorName
) {}
