package fr.cda.centaleish.controller_rest.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BrandRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "toto@gmail.com")
    public void testAccessBrandListFailed() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                get("/api/admin/brand"));

        resultActions.andExpect(status().is4xxClientError())
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "toto@admin.com", roles = "ADMIN")
    public void testAccessBrandListSuccess() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                get("/api/admin/brand"));

        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(authenticated());
    }

}