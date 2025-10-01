package com.company.recruitment.book;

import com.company.recruitment.author.Author;
import com.company.recruitment.author.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    private final BookRepository bookRepo;
    private final AuthorRepository authorRepo;

    public BookService(BookRepository bookRepo, AuthorRepository authorRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
    }

    @Transactional(readOnly = true)
    public Page<Book> list(String q, Pageable pageable) {
        if (q == null || q.isBlank()) {
            return bookRepo.findAll(pageable);
        }
        return bookRepo.findByTitleContainingIgnoreCase(q.trim(), pageable);
    }

    @Transactional(readOnly = true)
    public Book get(Long id) {
        return bookRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book " + id + " not found"));
    }

    @Transactional
    public Book create(String title, String isbn, int year, String authorName) {
        if (bookRepo.existsByIsbn(isbn)) {
            throw new IllegalArgumentException("ISBN already exists: " + isbn);
        }
        Author author = authorRepo.findByNameIgnoreCase(authorName)
                .orElseGet(() -> authorRepo.save(new Author(authorName)));
        Book book = new Book(title, isbn, year, author);
        return bookRepo.save(book);
    }
}
