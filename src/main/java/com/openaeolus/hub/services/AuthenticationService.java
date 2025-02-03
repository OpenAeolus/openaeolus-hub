package com.openaeolus.hub.services;

import com.openaeolus.hub.exceptions.UsernameAlreadyTakenException;
import com.openaeolus.hub.exceptions.WrongCredentialsException;
import com.openaeolus.hub.models.User;
import com.openaeolus.hub.models.dtos.users.UserCreationDTO;
import com.openaeolus.hub.models.dtos.users.UserLoginDTO;
import com.openaeolus.hub.utils.AuthenticationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);

    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    public AuthenticationService(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    public User createAccount(UserCreationDTO usercreationDTO){
        if(userService.getUserByEmail(usercreationDTO.getEmail()) == null){
            User userToRegister = new User(usercreationDTO);
            userToRegister.setPassword(AuthenticationUtils.hashPassword(usercreationDTO.getPassword()));
            return userService.save(userToRegister);
        }else{
            throw new UsernameAlreadyTakenException(usercreationDTO.getEmail());
        }
    }

    public String login(UserLoginDTO loginDTO){
        User user = userService.getUserByEmail(loginDTO.getUsername());
        if(user != null && AuthenticationUtils.checkPassword(loginDTO.getPassword(), user.getPassword())){
            return tokenService.generateToken(user.getId().toString()).getToken();
        }else {
            throw new WrongCredentialsException();
        }
    }

    public void logout(String token){
        tokenService.deleteToken(token);
    }
}
