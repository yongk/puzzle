package com.ruirui.server.user.service;

import com.ruirui.server.user.dto.UserDTO;

import java.util.List;

public interface UserService {
    void createUser(UserDTO user) throws InterruptedException;

    List<UserDTO> listUsers();
}
