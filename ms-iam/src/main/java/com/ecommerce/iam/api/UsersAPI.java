package com.ecommerce.iam.api;

import com.ecommerce.commons.contract.dto.AuthResponseDTO;
import com.ecommerce.commons.exceptions.exception.AuthenticationException;
import com.ecommerce.commons.exceptions.exception.AuthorizationException;
import com.ecommerce.iam.api.dto.UserDTO;
import com.ecommerce.iam.parser.UserParser;
import com.ecommerce.iam.service.UserService;
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