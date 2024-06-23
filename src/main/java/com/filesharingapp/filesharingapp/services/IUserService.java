package com.filesharingapp.filesharingapp.services;

import com.filesharingapp.filesharingapp.models.User;

import java.util.List;

public interface IUserService {
    User getUser(Long senderId);

    User createUser(String username, String email, String password);

    List<User> getAllUsers();
}
