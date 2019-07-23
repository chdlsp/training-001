package com.rasol.training001.controller.userController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rasol.training001.constant.Constants;
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
public class CreateUserTests {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void validUser_200() throws Exception{

        User user = new User().setUserId("testId").setPassword("testPassword");

        final ResultActions resultActions = mockMvc.perform(post("/users")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.userId").value(user.getUserId()));
    }

    @Test
    public void alreadyExistsUser_409() throws Exception{

        User user = new User().setUserId("testId").setPassword("testPassword");

        mockMvc.perform(post("/users")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON));

        final ResultActions resultActions = this.mockMvc.perform(post("/users")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value(Constants.USER_ID_ALREADY_EXISTS_ERROR));
    }

    @Test
    public void emptyUserId_400() throws Exception{

        User user = new User().setUserId("").setPassword("testPassword");

        final ResultActions resultActions = mockMvc.perform(post("/users")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isBadRequest());
    }

    @Test
    public void emptyUserPassword_400() throws Exception{

        User user = new User().setUserId("test").setPassword("");

        final ResultActions resultActions = mockMvc.perform(post("/users")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isBadRequest());
    }

    @Test
    public void maxLengthUserId_200() throws Exception{
        StringBuilder maxLengthUserId = new StringBuilder();
        for(int i = 0;i < 255; i++) {
            maxLengthUserId.append("1");
        }

        User user = new User().setUserId(maxLengthUserId.toString()).setPassword("testPassword");

        final ResultActions resultActions = mockMvc.perform(post("/users")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isOk());
    }

    @Test
    public void overMaxLengthUserId_400() throws Exception{
        StringBuilder maxLengthUserId = new StringBuilder();
        for(int i = 0;i < 256; i++) {
            maxLengthUserId.append("1");
        }

        User user = new User().setUserId(maxLengthUserId.toString()).setPassword("testPassword");

        final ResultActions resultActions = mockMvc.perform(post("/users")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isBadRequest());
    }


}
