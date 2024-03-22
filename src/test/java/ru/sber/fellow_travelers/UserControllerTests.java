package ru.sber.fellow_travelers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ru.sber.fellow_travelers.controller.UserController;
import ru.sber.fellow_travelers.mapper.UserMapper;
import ru.sber.fellow_travelers.service.impl.UserServiceImpl;

import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = UserController.class)
@AutoConfigureMockMvc
public class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private UserMapper userMapper;

    @BeforeEach
    void setup() {
        openMocks(this);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setSuffix(".html");
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userService, userMapper))
                .setViewResolvers(viewResolver)
                .build();
    }


    @Test
    public void testDeleteUser() throws Exception {
        long userId = 1L;
        mockMvc.perform(post("/deleteUser/{id}", userId).secure(false))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/registeredUsers"));
    }

    @Test
    public void testShowAddUserPage() throws Exception {
        mockMvc.perform(get("/addUser"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("admin/addUser"));
    }

    @Test
    public void testAddUser() throws Exception {
        mockMvc.perform(post("/addUser")
                        .param("firstName", "Максим")
                        .param("lastName", "Давыдов")
                        .param("phoneNumber", "89106336789")
                        .param("birthDate", "11.12.2000")
                        .param("email", "ssss@ii.com")
                        .param("password", "111"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/registeredUsers"));
    }
}