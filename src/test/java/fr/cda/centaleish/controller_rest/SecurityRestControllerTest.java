package fr.cda.centaleish.controller_rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import fr.cda.centaleish.dto.user.UserCreateDTO;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

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

    @Test
    public void testRegisterSuccess() throws Exception {
        ResultActions resultActions = mockMvc.perform(
            post("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getRegisterJsonFromDTO(new UserCreateDTO(
                    "toto3@gmail.com",
                    "Toto",
                    "TEAUTEAU",
                    "0475869234",
                    LocalDate.now().minusYears(24),
                    "12345",
                    "12345"
                ))));

        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.uuid").exists())
                .andExpect(jsonPath("$.email").exists())
                .andExpect(jsonPath("$.firstName").exists())
                .andExpect(jsonPath("$.lastName").exists())
                .andExpect(jsonPath("$.phone").exists())
                .andExpect(jsonPath("$.siret").isEmpty())
                .andExpect(jsonPath("$.photo").isEmpty())
                .andExpect(jsonPath("$.birthAt").exists())
                .andExpect(jsonPath("$.createdAt").exists())
                .andExpect(jsonPath("$.favorites").exists())
                .andExpect(jsonPath("$.isAdmin").value(Boolean.FALSE))
                .andExpect(jsonPath("$.isActive").value(Boolean.FALSE))
                .andExpect(jsonPath("$.password").doesNotExist())
                .andExpect(jsonPath("$.roles").doesNotExist())
                .andExpect(jsonPath("$.activationCode").doesNotExist())
                .andExpect(jsonPath("$.activationCodeSentAt").doesNotExist());
}

    @Test
    public void testRegisterFailNotEmail() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                post("/api/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(getRegisterJsonFromDTO(new UserCreateDTO(
                        "totoIsNotAnEmail",
                        "Toto",
                        "TEAUTEAU",
                        "0475869234",
                        LocalDate.now().minusYears(24),
                        "12345",
                        "12345"
                    ))));

        resultActions.andExpect(status().is4xxClientError());
    }

    private String getLoginJsonFromData(String email, String pwd) {
        JSONObject object = new JSONObject();
        object.put("email", email);
        object.put("password", pwd);
        return object.toString();
    }

    private String getRegisterJsonFromDTO(UserCreateDTO user) {
        JSONObject object = new JSONObject();
        object.put("email", user.getEmail());
        object.put("password", user.getPassword());
        object.put("confirmedPassword", user.getConfirmedPassword());
        object.put("phone", user.getPhone());
        object.put("birthAt", user.getBirthAt());
        object.put("firstName", user.getFirstName());
        object.put("lastName", user.getLastName());
        return object.toString();
    }

}