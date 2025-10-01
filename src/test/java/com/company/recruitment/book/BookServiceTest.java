package com.company.recruitment.book;

import com.company.recruitment.author.Author;
import com.company.recruitment.author.AuthorRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import(BookService.class)
class BookServiceTest {

    @Autowired BookRepository bookRepo;
    @Autowired AuthorRepository authorRepo;
    @Autowired BookService service;

    @BeforeEach
    void seed() {
        Author a = authorRepo.save(new Author("Test Author"));
        bookRepo.save(new Book("Test Title", "1111111111", 2001, a));
        bookRepo.save(new Book("Another Book", "2222222222", 2002, a));
    }

    @Test
    void list_withoutQuery_returnsAll() {
        var page = service.list(null, PageRequest.of(0, 10));
        assertThat(page.getTotalElements()).isGreaterThanOrEqualTo(2);
    }

    @Test
    void create_duplicateIsbn_throws() {
        assertThatThrownBy(() -> service.create("X", "1111111111", 2001, "A"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("ISBN");
    }
}
