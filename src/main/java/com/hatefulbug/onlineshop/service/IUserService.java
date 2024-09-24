package com.hatefulbug.onlineshop.service;

import com.hatefulbug.onlineshop.dto.UserDto;
import com.hatefulbug.onlineshop.model.User;
import com.hatefulbug.onlineshop.request.CreateUserRequest;
import com.hatefulbug.onlineshop.request.UserUpdateRequest;

public interface IUserService {
    User getUserById(int userId);
    User createUser(CreateUserRequest request);
    User updateUser(UserUpdateRequest request, int userId);
    void deleteUser(int userId);
    UserDto convertUserToDto(User user);
    User getAuthenticatedUser();
}
