package com.company.recruitment.book;

import com.company.recruitment.author.Author;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(max = 255)
    @Column(nullable = false)
    private String title;

    @NotBlank @Size(min = 10, max = 17)
    @Column(nullable = false, unique = true)
    private String isbn;

    @Min(1400) @Max(2100)
    @Column(name = "published_year", nullable = false)
    private int publishedYear;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    public Book() { }

    public Book(String title, String isbn, int publishedYear, Author author) {
        this.title = title;
        this.isbn = isbn;
        this.publishedYear = publishedYear;
        this.author = author;
    }

    // getters / setters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getIsbn() { return isbn; }
    public int getPublishedYear() { return publishedYear; }
    public Author getAuthor() { return author; }

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public void setPublishedYear(int year) { this.publishedYear = year; }
    public void setAuthor(Author author) { this.author = author; }
}
