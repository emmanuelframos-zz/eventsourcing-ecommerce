package com.iam.parser;

import com.iam.api.dto.UserDTO;
import com.iam.domain.User;
import org.springframework.beans.BeanUtils;
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