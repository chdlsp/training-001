package com.rasol.training001.controller.userController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rasol.training001.code.ErrorCodes;
import com.rasol.training001.model.dto.User;
import com.rasol.training001.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.transaction.Transactional;

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

        User user = new User().setId("testId").setPassword("testPassword");
        User resultUser = new User().setId("testId");

        final ResultActions resultActions = mockMvc.perform(post("/users")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("body").value(objectMapper.writeValueAsString(resultUser)));
    }

    @Test
    public void alreadyExistsUser_409() throws Exception{

        User user = new User().setId("testId").setPassword("testPassword");

        mockMvc.perform(post("/users")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON));

        final ResultActions resultActions = this.mockMvc.perform(post("/users")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value(ErrorCodes.Constants.USER_ID_ALREADY_EXISTS_ERROR));
    }

    @Test
    public void notBlankUserId_400() throws Exception{

        User user = new User().setId("").setPassword("testPassword");
//        User resultUser = new User().setId("testId");

        final ResultActions resultActions = mockMvc.perform(post("/users")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.id").value(ErrorCodes.USER_ID_MANDATORY_ERROR.getErrorMessage()));
    }

    @Test
    public void notBlankUserPassword_400() throws Exception{

        User user = new User().setId("test").setPassword("");
//        User resultUser = new User().setId("testId");

        final ResultActions resultActions = mockMvc.perform(post("/users")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.password").value(ErrorCodes.USER_PASSWORD_MANDATORY_ERROR.getErrorMessage()));
    }

    @Test
    public void notBlankUserPasswordAndId_400() throws Exception{

        User user = new User().setId("").setPassword("");
//        User resultUser = new User().setId("testId");

        final ResultActions resultActions = mockMvc.perform(post("/users")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.id").value(ErrorCodes.USER_ID_MANDATORY_ERROR.getErrorMessage()))
                .andExpect(jsonPath("$.errors.password").value(ErrorCodes.USER_PASSWORD_MANDATORY_ERROR.getErrorMessage()));

    }

    @Test
    public void maxLengthUserId_200() throws Exception{
        String maxLengthUserId = "";
        for(int i = 0;i < 255; i++) {
            maxLengthUserId = maxLengthUserId + "1";
        }

        User user = new User().setId(maxLengthUserId).setPassword("testPassword");
        User resultUser = new User().setId(maxLengthUserId);

        final ResultActions resultActions = mockMvc.perform(post("/users")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("body").value(objectMapper.writeValueAsString(resultUser)));
    }

    @Test
    public void overMaxLengthUserId_400() throws Exception{
        String maxLengthUserId = "";
        for(int i = 0;i < 256; i++) {
            maxLengthUserId = maxLengthUserId + "1";
        }

        User user = new User().setId(maxLengthUserId).setPassword("testPassword");
        User resultUser = new User().setId(maxLengthUserId);

        final ResultActions resultActions = mockMvc.perform(post("/users")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.id").value(ErrorCodes.USER_ID_MAX_LENGTH_ERROR.getErrorMessage()));
    }


}