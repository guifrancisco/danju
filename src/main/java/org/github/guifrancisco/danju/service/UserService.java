package org.github.guifrancisco.danju.service;

import lombok.extern.slf4j.Slf4j;
import org.github.guifrancisco.danju.domain.dto.DataRegisterUser;
import org.github.guifrancisco.danju.domain.dto.DataUpdateUser;
import org.github.guifrancisco.danju.domain.dto.DataUser;
import org.github.guifrancisco.danju.domain.entity.User;
import org.github.guifrancisco.danju.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private UserRepository userRepository;

    public User findByLogin(String login) {
        log.info("[UserService.findByLogin] - [Service]");
        return userRepository.findByLogin(login);
    }

    public User registerUser(DataRegisterUser dataRegisterUser){
        log.info("[UserService.registerUser] - [Service]");
        User user = new User(dataRegisterUser);
        return userRepository.save(user);
    }

    public User updateUser(DataUpdateUser dataUpdateUser){
        log.info("[UserService.updateUser] - [Service]");
        User user = userRepository.findById(dataUpdateUser.id())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.updateData(dataUpdateUser);
        return  userRepository.save(user);
    }

    public void deleteEmployee(String id){
        log.info("[UserService.deleteUser] - [Service]");
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        userRepository.delete(user);
    }

    public Page<DataUser> findAllUsers(Pageable pageable) {
        log.info("[UserService.findAllUser] - [Service]");
        Page<User> users = userRepository.findAll(pageable);
        return users.map(DataUser::new);
    }


}
