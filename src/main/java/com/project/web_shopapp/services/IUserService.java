package com.project.web_shopapp.services;

import com.project.web_shopapp.dtos.UserDTO;
import com.project.web_shopapp.exceptions.DataNotFoundException;
import com.project.web_shopapp.models.User;

public interface IUserService {
    User createUser(UserDTO userDTO) throws DataNotFoundException;
    String login(String phoneNumber, String password);
}
