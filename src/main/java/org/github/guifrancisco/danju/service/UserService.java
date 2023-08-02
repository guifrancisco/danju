package org.github.guifrancisco.danju.service;

import lombok.extern.slf4j.Slf4j;

import org.github.guifrancisco.danju.domain.dto.DataCustomer;
import org.github.guifrancisco.danju.domain.dto.DataRegisterUser;
import org.github.guifrancisco.danju.domain.dto.DataUpdateUser;
import org.github.guifrancisco.danju.domain.dto.DataUser;
import org.github.guifrancisco.danju.domain.entity.User;
import org.github.guifrancisco.danju.repository.UserRepository;

import org.github.guifrancisco.danju.service.mapper.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void registerUser(DataRegisterUser dataRegisterUser){
        log.info("[UserService.registerUser] - [Service]");
        User user = userMapper.toEntity(dataRegisterUser);
        userRepository.save(user);
    }

    public void updateUser(String id,DataUpdateUser dataUpdateUser){
        log.info("[UserService.updateUser] - [Service]");
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found"));
        userMapper.updateEntityFromDto(user, dataUpdateUser);
        userRepository.save(user);
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
        return users.map(userMapper::toDto);
    }
}
