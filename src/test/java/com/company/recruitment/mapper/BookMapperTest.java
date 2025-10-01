package com.company.recruitment.mapper;

import com.company.recruitment.author.Author;
import com.company.recruitment.book.Book;
import com.company.recruitment.dto.BookDTO;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class BookMapperTest {

    private final BookMapper mapper = Mappers.getMapper(BookMapper.class);

    @Test
    void toDto_mapsAuthorName() {
        var author = new Author("Tester");
        var book = new Book("Title", "1234567890", 2000, author);

        BookDTO dto = mapper.toDto(book);

        assertThat(dto.authorName()).isEqualTo("Tester");
        assertThat(dto.title()).isEqualTo("Title");
        assertThat(dto.isbn()).isEqualTo("1234567890");
        assertThat(dto.publishedYear()).isEqualTo(2000);
    }
}
