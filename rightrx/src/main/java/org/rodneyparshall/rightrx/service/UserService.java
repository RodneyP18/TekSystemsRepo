package org.rodneyparshall.rightrx.service;


import org.rodneyparshall.rightrx.exception.domain.UserNotFoundException;
import org.rodneyparshall.rightrx.exception.domain.UsernameExistsException;
import org.rodneyparshall.rightrx.domain.User;

import java.util.List;

public interface UserService {
    User register(String username, String password) throws UserNotFoundException, UsernameExistsException;
    User findUserByUsername(String username);
    User updateUser(String currentUsername, String newUsername, String newPassword) throws UserNotFoundException, UsernameExistsException;
    Boolean deleteUser(Long userId);
    List<User> getAll();
}
