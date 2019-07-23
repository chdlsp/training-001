package com.rasol.training001.controller.userController;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GetBooksTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private MockHttpSession session;
    private final User user = new User().setUserId("testId").setPassword("testPassword");

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
    public void defaultKeyword_200() throws Exception{

        String keyword = "test";

        final ResultActions resultActions = mockMvc.perform(get("/books")
                .session(session)
                .param("keyword", keyword)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body", hasSize(10)));

        // caching test
        assertEquals(10, bookRepository.findAll().size());
    }

    @Test
    public void notLogin_403() throws Exception{

        String keyword = "test";

        final ResultActions resultActions = mockMvc.perform(get("/books")
//                .session(session)
                .param("keyword", keyword)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isForbidden());

        // caching test
        assertEquals(0, bookRepository.findAll().size());
    }

    @Test
    public void emptyKeyword_400() throws Exception{
        String keyword = "";

        final ResultActions resultActions = mockMvc.perform(get("/books")
                .session(session)
                .param("keyword", keyword)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isBadRequest());
    }

    @Test
    public void pageOverMax_400() throws Exception{
        String keyword = "test";
        String page = "101";

        final ResultActions resultActions = mockMvc.perform(get("/books")
                .session(session)
                .param("keyword", keyword)
                .param("page", page)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isBadRequest());
    }

    @Test
    public void pageUnderMin_400() throws Exception{
        String keyword = "test";
        String page = "0";

        final ResultActions resultActions = mockMvc.perform(get("/books")
                .session(session)
                .param("keyword", keyword)
                .param("page", page)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isBadRequest());
    }

    @Test
    public void sizeOverMax_400() throws Exception{
        String keyword = "test";
        String size = "51";

        final ResultActions resultActions = mockMvc.perform(get("/books")
                .session(session)
                .param("keyword", keyword)
                .param("size", size)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isBadRequest());
    }

    @Test
    public void sizeUnderMin_400() throws Exception{
        String keyword = "test";
        String size = "0";

        final ResultActions resultActions = mockMvc.perform(get("/books")
                .session(session)
                .param("keyword", keyword)
                .param("size", size)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isBadRequest());
    }
}
