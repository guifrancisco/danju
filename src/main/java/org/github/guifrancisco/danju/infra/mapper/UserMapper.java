package org.github.guifrancisco.danju.infra.mapper;

import org.github.guifrancisco.danju.domain.dto.DataRegisterUser;
import org.github.guifrancisco.danju.domain.dto.DataUpdateUser;
import org.github.guifrancisco.danju.domain.dto.DataUser;
import org.github.guifrancisco.danju.domain.entity.User;
import org.github.guifrancisco.danju.domain.enums.UserRole;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper {

    public User toEntity(DataRegisterUser dataRegisterUser){
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(dataRegisterUser.name());
        user.setLogin(dataRegisterUser.login());
        user.setEmail(dataRegisterUser.email());
        user.setPassword(dataRegisterUser.password());
        user.setRole(convertToUserRole(dataRegisterUser.role()));
        return user;
    }

    private UserRole convertToUserRole(String roleString) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.getRole().equalsIgnoreCase(roleString)) {
                return userRole;
            }
        }
        throw new IllegalArgumentException("Invalid role: " + roleString);
    }

    public DataUser toDto(User user){
        return new DataUser(user);
    }

    public void updateEntityFromDto(User user, DataUpdateUser dataUpdateUser){
        if(dataUpdateUser.name() != null){
            user.setName(dataUpdateUser.name());
        }
        if(dataUpdateUser.login()!= null){
            user.setLogin(dataUpdateUser.login());
        }
        if(dataUpdateUser.email() != null){
            user.setEmail(dataUpdateUser.email());
        }
        if(dataUpdateUser.password() != null){
            user.setPassword(dataUpdateUser.password());
        }
    }

}
