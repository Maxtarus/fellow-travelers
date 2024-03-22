package ru.sber.fellow_travelers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTests {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void testLoginPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    void testSuccessfulAuthorizationWhenAdmin() throws Exception {
        String email = "admin@mail.ru";
        String password = "admin";

        mockMvc.perform(post("/signIn")
                        .param("email", email)
                        .param("password", password))
                        .andExpect(status().is3xxRedirection())
                        .andExpect(redirectedUrl("/registeredUsers"));
    }

    @Test
    void testSuccessfulAuthorizationWhenPassenger() throws Exception {
        String email = "www@gmail.com";
        String password = "zaq1";

        mockMvc.perform(post("/signIn")
                        .param("email", email)
                        .param("password", password))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/activeRequests"));
    }

    @Test
    void testSuccessfulAuthorizationWhenDriver() throws Exception {
        String email = "user@hh.com";
        String password = "1qaz";

        mockMvc.perform(post("/signIn")
                        .param("email", email)
                        .param("password", password))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/createdTrips"));
    }

    @Test
    void testChooseRolePageWhenUserHasSeveralRoles() throws Exception {
        String email = "starmax@yandex.ru";
        String password = "1";

        mockMvc.perform(post("/signIn")
                        .param("email", email)
                        .param("password", password))
                .andExpect(view().name("chooseRole"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    public void testFailedLogin() throws Exception {
        String email = "invalidUser";
        String password = "invalidPassword";

        mockMvc.perform(post("/signIn")
                        .param("email", email)
                        .param("password", password))
                .andExpect(view().name("login"));
    }
}