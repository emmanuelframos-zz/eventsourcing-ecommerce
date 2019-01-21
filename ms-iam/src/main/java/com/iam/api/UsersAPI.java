package com.iam.api;

import com.ecommerce.exception.AuthenticationException;
import com.ecommerce.exception.AuthorizationException;
import com.iam.api.dto.AuthResponseDTO;
import com.iam.api.dto.UserDTO;
import com.iam.parser.UserParser;
import com.iam.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/users",
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UsersAPI {

    private final UserParser userParser;
    private final UserService userService;

    public UsersAPI(UserService userService, UserParser userParser){
        this.userService = userService;
        this.userParser = userParser;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody UserDTO userDTO) {
        userService.register(userParser.toDomain(userDTO));
    }

    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponseDTO authenticate(@RequestBody UserDTO userDTO) throws AuthenticationException {
        return userService.authenticate(userDTO);
    }

    @PostMapping("/authorize")
    @ResponseStatus(HttpStatus.OK)
    public void authorize(@RequestBody AuthResponseDTO authResponseDTO) throws AuthorizationException {
        userService.authorize(authResponseDTO);
    }
}