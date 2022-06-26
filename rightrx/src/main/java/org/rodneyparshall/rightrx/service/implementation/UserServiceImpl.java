package org.rodneyparshall.rightrx.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.rodneyparshall.rightrx.constant.UserServiceImplConstants;
import org.rodneyparshall.rightrx.domain.CustomUserDetails;
import org.rodneyparshall.rightrx.enumeration.Role;
import org.rodneyparshall.rightrx.exception.domain.UserNotFoundException;
import org.rodneyparshall.rightrx.exception.domain.UsernameExistsException;
import org.rodneyparshall.rightrx.domain.User;
import org.rodneyparshall.rightrx.repo.UserRepo;
import org.rodneyparshall.rightrx.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;

    // Used to encode the password
    public String encoder(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPass = encoder.encode(password);
        return hashedPass;
    }

    // loadUserByUsername() is used to return a UserDetails object loaded with a user's info.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByUsername(username);

        if(user == null){
            log.error("User was not found." + username);
            throw new UsernameNotFoundException("User was not found. " + username);
        }else {
            log.info("Returning found user by username "+ username);
        }
        return new CustomUserDetails(user);
    }

    // register() currently automatically assigns any new users with the standard USER_ROLE.
    @Override
    public User register(String username, String password) throws UserNotFoundException, UsernameExistsException {
        validateNewUserAndUsername(StringUtils.EMPTY, username);
        User user = new User();
        user.setPassword(encoder(password));
        user.setUsername(username);
        user.setRole(Role.ROLE_USER.name());
        user.setAuthorities(Role.ROLE_USER.getAuthorities());
        return userRepo.save(user);
    }

    // validateNewUserAndUsername() is both used to validate a user and validate there is no duplicates in the database.
    private User validateNewUserAndUsername(String currentUserName, String newUserName) throws UserNotFoundException, UsernameExistsException{
        User userByUsername = findUserByUsername(newUserName);
        if(StringUtils.isNotBlank(currentUserName)){
            User currentUser = findUserByUsername(currentUserName);
            if(currentUser == null){
                throw new UserNotFoundException(UserServiceImplConstants.NO_USER_FOUND_BY_USERNAME + currentUserName);
            }

            if(userByUsername != null && currentUser.getUserId() == userByUsername.getUserId()){
                throw new UsernameExistsException(UserServiceImplConstants.USERNAME_ALREADY_EXISTS);
            }
            return currentUser;
        }else {
            if(userByUsername != null){
                throw new UsernameExistsException(UserServiceImplConstants.USERNAME_ALREADY_EXISTS);
            }
        }
        return null;
    }

    //findUserByUsername() is used to return a User
    @Override
    public User findUserByUsername(String username) {
        return userRepo.findUserByUsername(username);
    }

    //Update method shares the controllers parameters even though they are required in the controller, but provides the checks needed.
    @Override
    public User updateUser(String currentUsername, String newUsername, String newPassword) throws UserNotFoundException, UsernameExistsException {
        User currentUser = validateNewUserAndUsername(currentUsername, newUsername);
        if(newUsername != null){
            currentUser.setUsername(newUsername);
        }
        if(newPassword != null){
            currentUser.setPassword(encoder(newPassword));
        }
        return userRepo.save(currentUser);
    }

    //The delete response does not return a User, so I utilized Boolean for verification.
    @Override
    public Boolean deleteUser(Long userId) {
        userRepo.deleteById(userId);
        return Boolean.TRUE;
    }

    //Gets all users
    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }

    private Role getRoleEnumName(String role){
        return Role.valueOf(role.toUpperCase());
    }

}
