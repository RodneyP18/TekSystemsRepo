package org.rodneyparshall.rightrx.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rodneyparshall.rightrx.model.User;
import org.rodneyparshall.rightrx.repo.UserRepo;
import org.rodneyparshall.rightrx.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public User create(User user) {
        user.setRole("USER");
        return userRepo.save(user);
    }

    @Override
    public User get(Long userId) {
        return userRepo.findById(userId).get();
    }

    @Override
    public User update(User user) {
        return userRepo.save(user);
    }

    @Override
    public Boolean delete(Long userId) {
        userRepo.deleteById(userId);
        return Boolean.TRUE;
    }

    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }

    public User getUserByUsername(String username){
        return userRepo.findByUsername(username);
    }
}
