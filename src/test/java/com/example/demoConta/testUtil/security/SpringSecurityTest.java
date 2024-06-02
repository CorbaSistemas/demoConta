package com.example.demoConta.testUtil.security;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Alfredo Janke on 21/10/2020
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@WebAppConfiguration
//@ImportAutoConfiguration({ RibbonAutoConfiguration.class })
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SpringSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testErro401() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/faturamentos"))
            .andExpect((status().isUnauthorized()));
    }

    @Test
    @WithMockUser(username = "alfredo", password = "123")
    public void test200Ok() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/faturamentos"))
            .andExpect((status().isOk()));
    }

}
