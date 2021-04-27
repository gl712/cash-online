package com.gab.cashonline.cashBE.requests;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.hamcrest.Matchers;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gab.cashonline.cashBE.domain.User;
import com.hackerrank.test.utility.OrderedTestRunner;

@RunWith(OrderedTestRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @ClassRule
    public static final SpringClassRule springClassRule = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();
    
    @Test
    @Order(1)
    public void testGetUser() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(jsonPath("$.firstName").value("Monica"))
            .andExpect(jsonPath("$.lastName").value("Potrelli"))
			.andExpect(jsonPath("$.loans").isArray())
			.andExpect(jsonPath("$.loans", Matchers.hasSize(1)))
			.andExpect(jsonPath("$.loans[*].total", Matchers.contains(65120.75)))
			.andExpect(jsonPath("$.loans[*].userId", Matchers.contains(1)));

    }

    @Test
    @Order(2)
    public void testPostUser() throws Exception {
    	
    	User newUser = new User();
    	newUser.setFirstName("Lando");
    	newUser.setLastName("Fiorini");
    	newUser.setEmail("lando@fiorini.it");
    	
        this.mockMvc.perform(MockMvcRequestBuilders.post("/users")
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(mapper.writeValueAsString(newUser)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(jsonPath("$.firstName").value("Lando"))
            .andExpect(jsonPath("$.lastName").value("Fiorini"))
			.andExpect(jsonPath("$.loans").isArray())
			.andExpect(jsonPath("$.loans", Matchers.hasSize(0)));

    }

    @Test
    @Order(3)
    public void testDeleteUser() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/users/1"))
            .andExpect(MockMvcResultMatchers.status().isOk());

    }
}
