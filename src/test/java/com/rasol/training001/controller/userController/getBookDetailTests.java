package com.rasol.training001.controller.userController;

import com.rasol.training001.code.ErrorCodes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class getBookDetailTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void defaultIsbn10_200() throws Exception{

        String keyword = "8984465240";

        final ResultActions resultActions = mockMvc.perform(get("/books/isbns/" + keyword)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.isbn", containsString(keyword)));
//                .andExpect(jsonPath("$.body[*].title", contains(equalToIgnoringCase("test"))));
    }

    @Test
    public void defaultIsbn13_200() throws Exception{

        String keyword = "9788984465244";

        final ResultActions resultActions = mockMvc.perform(get("/books/isbns/" + keyword)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.isbn", containsString(keyword)));
//                .andExpect(jsonPath("$.body[*].title", contains(equalToIgnoringCase("test"))));
    }

    @Test
    public void defaultIsbn10AndIsbn13_200() throws Exception{

        String keyword = "8984465240 9788984465244";

        final ResultActions resultActions = mockMvc.perform(get("/books/isbns/" + keyword)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.isbn", containsString("8984465240")))
                .andExpect(jsonPath("$.body.isbn", containsString("9788984465244")));

//                .andExpect(jsonPath("$.body[*].title", contains(equalToIgnoringCase("test"))));
    }

    @Test
    public void emptyIsbn10AndIsbn13_404() throws Exception{

        String keyword = "";

        final ResultActions resultActions = mockMvc.perform(get("/books/isbns/" + keyword)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isNotFound());

//                .andExpect(jsonPath("$.body[*].title", contains(equalToIgnoringCase("test"))));
    }

    @Test
    public void notFoundBook_400() throws Exception{

        String keyword = "123";

        final ResultActions resultActions = mockMvc.perform(get("/books/isbns/" + keyword)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(ErrorCodes.BOOK_NOT_FOUND_ERROR.getErrorMessage()));

//                .andExpect(jsonPath("$.body[*].title", contains(equalToIgnoringCase("test"))));
    }
}
