package com.company.recruitment.mapper;

import com.company.recruitment.book.Book;
import com.company.recruitment.dto.BookDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "authorName", source = "author.name")
    BookDTO toDto(Book book);
}
