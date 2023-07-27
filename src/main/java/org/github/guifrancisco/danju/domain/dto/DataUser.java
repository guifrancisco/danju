package org.github.guifrancisco.danju.domain.dto;

import org.github.guifrancisco.danju.domain.entity.User;

public record DataUser(
        String id,
        String name,
        String email,
        String login,
        String password
) {
    public DataUser(User user){
        this(user.getId(), user.getName(), user.getEmail(), user.getLogin(), user.getPassword());
    }
}
