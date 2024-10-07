package fr.cda.centaleish.controller_rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLoginSuccess() throws Exception {
        ResultActions resultActions = mockMvc.perform(
            post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getLoginJsonFromData("kylian.bourgeois@gmail.com", "12345")));

        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    public void testLoginFailed() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getLoginJsonFromData("kylian.bourgeois@gmail.com", "12346")));

        resultActions.andExpect(status().is4xxClientError());
    }

    @Test
    public void testLoginValidationFailed() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getLoginJsonFromData("kylian.bourgeois@gmail.com", "")));

        resultActions.andExpect(status().is4xxClientError());
    }

    private String getLoginJsonFromData(String email, String pwd) {
        return "{" +
                "\"email\":\"" + email + "\"," +
                "\"password\":\"" + pwd + "\"" +
                "}";
    }

}