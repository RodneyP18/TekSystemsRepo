package org.rodneyparshall.rightrx.service;


import org.rodneyparshall.rightrx.model.User;

import java.util.List;

public interface UserService {
    User create(User user);
    User get(Long userId);
    User update(User user);
    Boolean delete(Long userId);

    List<User> getAll();
}
