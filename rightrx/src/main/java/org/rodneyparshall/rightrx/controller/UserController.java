package org.rodneyparshall.rightrx.controller;

import lombok.extern.slf4j.Slf4j;
import org.rodneyparshall.rightrx.constant.UserControllerConstants;
import org.rodneyparshall.rightrx.domain.HttpResponse;
import org.rodneyparshall.rightrx.exception.ExceptionHandling;
import org.rodneyparshall.rightrx.exception.domain.UserNotFoundException;
import org.rodneyparshall.rightrx.exception.domain.UsernameExistsException;
import org.rodneyparshall.rightrx.domain.User;
import org.rodneyparshall.rightrx.service.UserService;
import org.rodneyparshall.rightrx.domain.CustomUserDetails;
import org.rodneyparshall.rightrx.utility.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import static org.rodneyparshall.rightrx.constant.SecurityConstant.JWT_TOKEN_HEADER;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = {"/","/user"})
@Slf4j
public class UserController extends ExceptionHandling {
    UserService userService;
    AuthenticationManager authenticationManager;
    JWTTokenProvider jwtTokenProvider;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager, JWTTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws UsernameExistsException, UserNotFoundException {
        User newUser = userService.register(user.getUsername(), user.getPassword());
        log.info(UserControllerConstants.USER_WAS_CREATED);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    //All authorization and headers generated with Login method.
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user){
        authenticate(user.getUsername(), user.getPassword());
        User loginUser = userService.findUserByUsername(user.getUsername());
        CustomUserDetails customUserDetails = new CustomUserDetails(loginUser);
        HttpHeaders jwtHeader = getJwtHeaders(customUserDetails);
        log.info(UserControllerConstants.USER_LOGGED_IN);
        return new ResponseEntity<>(loginUser, jwtHeader, OK);
    }

    //Update method that works when user is updated password and/or username.
    @PutMapping(path = "/update")
    public ResponseEntity<User> updateUser(@RequestParam(value = "username")String username,
                                           @RequestParam(value = "newUsername", required= false)String newUsername,
                                           @RequestParam(value = "newPassword", required= false)String newPassword)
                                            throws UserNotFoundException, UsernameExistsException {
        User updatedUser = userService.updateUser(username, newUsername, newPassword);
        log.info(UserControllerConstants.USER_WAS_UPDATED);
        return new ResponseEntity<>(updatedUser, OK);
    }

    //Used @PreAuthorize annotation which limits access to this function to Users with qualifications.
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('user:delete')")
    public ResponseEntity<HttpResponse> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        log.info(UserControllerConstants.USER_DELETED_SUCCESSFULLY);
        return response(HttpStatus.NO_CONTENT, UserControllerConstants.USER_DELETED_SUCCESSFULLY);
    }

    //Utilized the User's username for ease of use with the frontend.
    @GetMapping("/find/{username}")
    public ResponseEntity<User> getUser(@PathVariable("username") String username){
        User user = userService.findUserByUsername(username);
        log.info(UserControllerConstants.FOUND_USER_BY_USERNAME + username);
        return new ResponseEntity<>(user, OK);
    }

    //Custom response() method to return with the Delete function above since I wanted to still send a response.
    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message){
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus,
                httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase()), httpStatus);
    }

    //Used to authenticate users logging in.
    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    //Creates Jwt headers required when logging in.
    private HttpHeaders getJwtHeaders(CustomUserDetails customUserDetails) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(customUserDetails));
        return httpHeaders;
    }
}
