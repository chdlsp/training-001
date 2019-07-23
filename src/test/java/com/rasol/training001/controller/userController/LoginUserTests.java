package com.rasol.training001.controller.userController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rasol.training001.code.ErrorCodes;
import com.rasol.training001.model.dto.User;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class LoginUserTests {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private User user = new User().setUserId("testId").setPassword("testPassword");

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void validUser_200() throws Exception{

        mockMvc.perform(post("/users")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        final ResultActions resultActions = mockMvc.perform(post("/users/login")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.userId").value(user.getUserId()));
    }

    @Test
    public void userNotFound_404() throws Exception{

        final ResultActions resultActions = this.mockMvc.perform(post("/users/login")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(ErrorCodes.Constants.USER_NOT_FOUND_ERROR));
    }

    @Test
    public void passwordWrong_401() throws Exception{

        User user = new User().setUserId("testId").setPassword("testPassword");
        User loginUser = new User().setUserId("testId").setPassword("wrongPassword");

        mockMvc.perform(post("/users")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        final ResultActions resultActions = mockMvc.perform(post("/users/login")
                .content(objectMapper.writeValueAsString(loginUser))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isForbidden());
    }

    @Test
    public void emptyUserId_400() throws Exception{

        User user = new User().setUserId("").setPassword("testPassword");
//        User resultUser = new User().setUserId("testId");

        final ResultActions resultActions = mockMvc.perform(post("/users/login")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isBadRequest());
    }

    @Test
    public void emptyUserPassword_400() throws Exception{

        User user = new User().setUserId("test").setPassword("");
//        User resultUser = new User().setUserId("testId");

        final ResultActions resultActions = mockMvc.perform(post("/users/login")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isBadRequest());
    }

}
