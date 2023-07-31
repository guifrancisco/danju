package org.github.guifrancisco.danju.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

import org.github.guifrancisco.danju.domain.dto.DataRegisterUser;
import org.github.guifrancisco.danju.domain.dto.DataUpdateUser;

import java.util.UUID;
@Entity
@Getter
@Table(name = "sys_user")
@NoArgsConstructor
public class User {

    @Id
    private String id;
    private String name;
    private String email;
    private String login;
    private String password;

    public User (DataRegisterUser dataRegisterUser){
        this.id = UUID.randomUUID().toString();
        this.name = dataRegisterUser.name();
        this.email = dataRegisterUser.email();
        this.login = dataRegisterUser.login();
        this.password = dataRegisterUser.password();
    }

    public void updateDataUser(DataUpdateUser dataUpdateUser) {
        if(dataUpdateUser.name() != null) {
            this.name = dataUpdateUser.name();
        }
        if(dataUpdateUser.email() != null) {
            this.email = dataUpdateUser.email();
        }
        if(dataUpdateUser.login() != null) {
            this.login = dataUpdateUser.login();
        }
        if(dataUpdateUser.password() != null) {
            this.password = dataUpdateUser.password();
        }
        if(dataUpdateUser.name() == null && dataUpdateUser.email() == null &&
        dataUpdateUser.login() == null &&  dataUpdateUser.password() == null){
            throw new IllegalStateException("You must provide at least one value for the update.");
        }
    }

}
