package com.nitesh.biddingPlatform.api;
import com.nitesh.biddingPlatform.model.User;
import com.nitesh.biddingPlatform.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(MockitoJUnitRunner.class)
public class UserResourceTest {
    @Autowired
    private MockMvc mvc;
    @Mock
    private UserService userService;
    @InjectMocks
    private UserResource userResource = new UserResource();

    @Before
    @Deprecated
    public void initMocks() {
        mvc = MockMvcBuilders.standaloneSetup(userResource).build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddUser() throws Exception {
        User user = new User();
        user.setFirstName("Nitesh");
        when(userService.addUser(any(User.class))).thenReturn(user);
        final ResultActions result =
                mvc.perform(
                        post("/user")
                                .content("{ \"firstName\": \"Nitesh\" }")
                                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName", is("Nitesh")));
    }
}