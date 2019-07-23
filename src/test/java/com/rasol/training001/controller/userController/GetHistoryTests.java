package com.rasol.training001.controller.userController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rasol.training001.model.dto.User;
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

import static org.hamcrest.Matchers.hasSize;
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
public class GetHistoryTests {
    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private MockHttpSession session;
    private final User user = new User().setUserId("testId").setPassword("testPassword");
    private final String keyword = "test";

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
    public void getBookAndGetHistory_200() throws Exception{

        // set history 1
        mockMvc.perform(get("/books")
                .session(session)
                .param("keyword", keyword)
                .contentType(MediaType.APPLICATION_JSON));

        final ResultActions resultActions = mockMvc.perform(get("/histories/users/" + user.getUserId())
                .session(session)
                .param("keyword", keyword)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body", hasSize(1)));
    }

    @Test
    public void getBookAndGetHistory_otherUsers_200() throws Exception{

        // set history 1
        mockMvc.perform(get("/books")
                .session(session)
                .param("keyword", keyword)
                .contentType(MediaType.APPLICATION_JSON));

        final ResultActions resultActions = mockMvc.perform(get("/histories/users/" + user.getUserId() + "!")
                .session(session)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isForbidden());
    }

    @Test
    public void onlyGetBook_200() throws Exception{
        final ResultActions resultActions = mockMvc.perform(get("/histories/users/" + user.getUserId())
                .session(session)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body", hasSize(0)));
    }
}
