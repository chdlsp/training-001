package com.rasol.training001.controller.userController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rasol.training001.code.ErrorCodes;
import com.rasol.training001.model.dto.User;
import com.rasol.training001.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GetBookDetailTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private MockHttpSession session;
    private static User user = new User().setUserId("testId").setPassword("testPassword");

    @Before
    public void setLoginSession() throws Exception {
        // create user
        mockMvc.perform(post("/users")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON));

        // login user
        this.session = (MockHttpSession)mockMvc.perform(post("/users/login")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getRequest().getSession();
    }

    @Test
    public void defaultIsbn10_200() throws Exception{

        String keyword = "8984465240";

        final ResultActions resultActions = mockMvc.perform(get("/books/isbns/" + keyword)
                .session(session)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.isbn", containsString(keyword)));

        // caching test
        assertEquals(1, bookRepository.findAll().size());
    }

    @Test
    public void defaultIsbn13_200() throws Exception{

        String keyword = "9788984465244";

        final ResultActions resultActions = mockMvc.perform(get("/books/isbns/" + keyword)
                .session(session)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.isbn", containsString(keyword)));

        // caching test
        assertEquals(1, bookRepository.findAll().size());
    }

    @Test
    public void defaultIsbn10AndIsbn13_200() throws Exception{

        String keyword = "8984465240 9788984465244";

        final ResultActions resultActions = mockMvc.perform(get("/books/isbns/" + keyword)
                .session(session)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.isbn", containsString("8984465240")))
                .andExpect(jsonPath("$.body.isbn", containsString("9788984465244")));

        // caching test
        assertEquals(1, bookRepository.findAll().size());
    }

    @Test
    public void emptyIsbn10AndIsbn13_404() throws Exception{

        String keyword = "";

        final ResultActions resultActions = mockMvc.perform(get("/books/isbns/" + keyword)
                .session(session)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isNotFound());

        // caching test
        assertEquals(0, bookRepository.findAll().size());
    }

    @Test
    public void notFoundBook_400() throws Exception{

        String keyword = "123";

        final ResultActions resultActions = mockMvc.perform(get("/books/isbns/" + keyword)
                .session(session)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isNotFound());

        // caching test
        assertEquals(0, bookRepository.findAll().size());

    }
}
