package fr.cda.centaleish.controller_rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
public class FavoriteRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "toto@gmail.com")
    public void testAddFavoriteSuccess() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                post("/api/favorite")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJsonByData(
                                "005e38fb-5403-47bc-9989-9a289fdb1185",
                                "010bff98-50e5-4b6a-a3c1-8c167c0acc7b"
                        )));

        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.createdAt").exists())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    public void testAddFavoriteFailed() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                post("/api/favorite")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(getJsonByData(
                            "005e38fb-5403-47bc-9989-9a289fdb1185",
                            "010bff98-50e5-4b6a-a3c1-8c167c0acc7b"
                    )));

        resultActions.andExpect(status().is4xxClientError())
                .andExpect(status().isForbidden());
    }

    private String getJsonByData(String listingId, String userId) {
        JSONObject object = new JSONObject();
        object.put("listingId", listingId);
        object.put("userId", userId);
        return object.toString();
    }

}