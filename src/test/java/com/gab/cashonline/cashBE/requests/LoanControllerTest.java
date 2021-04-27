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
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.hackerrank.test.utility.OrderedTestRunner;

@RunWith(OrderedTestRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoanControllerTest {
    @ClassRule
    public static final SpringClassRule springClassRule = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void testGetLoans() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/loans?page=2&size=5"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(jsonPath("$.paging.page").value(2))
            .andExpect(jsonPath("$.paging.size").value(5))
            .andExpect(jsonPath("$.paging.total").value(2))
			.andExpect(jsonPath("$.items").isArray())
			.andExpect(jsonPath("$.items", Matchers.hasSize(2)))
			.andExpect(jsonPath("$.items[*].total", Matchers.containsInAnyOrder(65120.25, 11.00)))
			.andExpect(jsonPath("$.items[*].userId", Matchers.containsInAnyOrder(1, 2)));

    }
    
    @Test
    @Order(2)
    public void testGetFilteredLoans() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/loans?page=1&size=5&user_id=1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(jsonPath("$.paging.page").value(1))
            .andExpect(jsonPath("$.paging.size").value(5))
            .andExpect(jsonPath("$.paging.total").value(1))
			.andExpect(jsonPath("$.items").isArray())
			.andExpect(jsonPath("$.items", Matchers.hasSize(4)))
			.andExpect(jsonPath("$.items[*].total", Matchers.containsInAnyOrder(65120.75, 123.75, 2223.25, 11.00)))
			.andExpect(jsonPath("$.items[*].userId", Matchers.containsInAnyOrder(1, 1, 1, 1)));

    }

}
