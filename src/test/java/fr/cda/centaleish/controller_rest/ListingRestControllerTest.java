package fr.cda.centaleish.controller_rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.hamcrest.Matchers.hasSize;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ListingRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testListingList() throws Exception {
        ResultActions resultActions = mockMvc.perform(
            get("/api/listing"));

        resultActions.andExpect(status().is2xxSuccessful())
            .andExpectAll(
                jsonPath("$").isArray(),
                jsonPath("$", hasSize(1000)),
                jsonPath("$[0].uuid").exists(),
                jsonPath("$[0].title").exists(),
                jsonPath("$[0].price").exists(),
                jsonPath("$[0].mileage").exists(),
                jsonPath("$[0].producedAt").exists(),
                jsonPath("$[0].priceDecimal").exists(),
                jsonPath("$[0].*", hasSize(6))
            );
    }

}