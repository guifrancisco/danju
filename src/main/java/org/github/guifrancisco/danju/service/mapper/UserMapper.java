package org.github.guifrancisco.danju.service.mapper;

import org.github.guifrancisco.danju.domain.dto.DataRegisterUser;
import org.github.guifrancisco.danju.domain.dto.DataUpdateUser;
import org.github.guifrancisco.danju.domain.dto.DataUser;
import org.github.guifrancisco.danju.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(DataRegisterUser dataRegisterUser){
        User user = new User();
        user.setName(dataRegisterUser.name());
        user.setLogin(dataRegisterUser.login());
        user.setEmail(dataRegisterUser.email());
        user.setPassword(dataRegisterUser.password());
        return user;
    }

    public DataUser toDto(User user){
        return new DataUser(
                user.getId(),
                user.getName(),
                user.getLogin(),
                user.getEmail(),
                user.getPassword()
        );
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
