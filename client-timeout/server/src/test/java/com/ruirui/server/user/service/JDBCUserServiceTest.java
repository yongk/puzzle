package com.ruirui.server.user.service;

import com.ruirui.server.user.dto.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class JDBCUserServiceTest {

    @Autowired
    private JDBCUserService userService;


    @Test
    public void givenUser_whenInsert_thenSuccess() throws InterruptedException {
        UserDTO user = new UserDTO();
        user.setName("Jacky");
        user.setAge(30);
        userService.createUser(user);

        List<UserDTO> users = userService.listUsers();

        assertThat(users).hasSize(1);
    }
}
