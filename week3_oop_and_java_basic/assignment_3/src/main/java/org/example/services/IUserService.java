package org.example.services;

import org.example.models.User;

public interface IUserService {
    boolean register();
    User login(String username, String password);

}
