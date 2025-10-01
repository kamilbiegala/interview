package com.company.recruitment.book;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerIT {

    @Autowired MockMvc mvc;

    @Test
    void list_returnsSeededData() throws Exception {
        mvc.perform(get("/api/books")
                        .param("page", "0")
                        .param("size", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", not(empty())))
                .andExpect(jsonPath("$.content[0].title", notNullValue()));
    }

    @Disabled("TASK: enable and make this pass by ensuring validation & error handling")
    @Test
    void create_invalidPayload_returns400() throws Exception {
        var json = """
            {"title":"", "isbn":"short", "publishedYear":1200, "authorName":""}
        """;
        mvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message", containsString("title")))
                .andExpect(jsonPath("$.path").value("/api/books"));
    }
}
