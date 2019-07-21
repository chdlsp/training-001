package com.rasol.training001.controller.userController;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class getBooksTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void defaultKeyword_200() throws Exception{

        String keyword = "test";

        final ResultActions resultActions = mockMvc.perform(get("/books")
                .param("keyword", keyword)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body", hasSize(10)));
//                .andExpect(jsonPath("$.body[*].title", contains(equalToIgnoringCase("test"))));
    }

    @Test
    public void emptyKeyword_400() throws Exception{
        String keyword = "";

        final ResultActions resultActions = mockMvc.perform(get("/books")
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
                .param("keyword", keyword)
                .param("size", size)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isBadRequest());
    }
}