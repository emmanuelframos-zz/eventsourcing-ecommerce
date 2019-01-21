package com.ecommerce.iam.parser;

import com.ecommerce.iam.api.dto.UserDTO;
import com.ecommerce.iam.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserParser {

    public User toDomain(UserDTO userDTO){
        User user = new User();
        user.setUsername(userDTO.username);
        user.setPassword(userDTO.password);
        return user;
    }
}