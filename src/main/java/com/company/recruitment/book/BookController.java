package com.company.recruitment.book;

import com.company.recruitment.dto.BookDTO;
import com.company.recruitment.dto.CreateBookRequest;
import com.company.recruitment.mapper.BookMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService service;
    private final BookMapper mapper;

    public BookController(BookService service, BookMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    // GET /api/books?q=wizard&page=0&size=10&sort=title,asc
    @GetMapping
    public Page<BookDTO> list(
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) @Max(100) int size,
            @RequestParam(defaultValue = "title,asc") String sort
    ) {
        String[] sortParts = sort.split(",", 2);
        Sort s = Sort.by(sortParts[0]).ascending();
        if (sortParts.length > 1 && "desc".equalsIgnoreCase(sortParts[1])) {
            s = s.descending();
        }
        var pageable = PageRequest.of(page, size, s);
        return service.list(q, pageable).map(mapper::toDto);
    }

    // GET /api/books/{id}
    @GetMapping("/{id}")
    public BookDTO get(@PathVariable Long id) {
        return mapper.toDto(service.get(id));
    }

    // POST /api/books
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO create(@Valid @RequestBody CreateBookRequest req) {
        var saved = service.create(req.title(), req.isbn(), req.publishedYear(), req.authorName());
        return mapper.toDto(saved);
    }
}
