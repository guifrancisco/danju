package org.github.guifrancisco.danju.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.github.guifrancisco.danju.domain.dto.DataRegisterUser;
import org.github.guifrancisco.danju.domain.dto.DataUpdateUser;
import org.github.guifrancisco.danju.domain.dto.DataUser;
import org.github.guifrancisco.danju.domain.entity.User;
import org.github.guifrancisco.danju.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("v1/user")
@RestController
@Slf4j
public class UserController {

    final private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> registerUser(@RequestBody @Valid DataRegisterUser dataRegisterUser){
        log.info("[UserController.registerUser] - [Controller]");
        userService.registerUser(dataRegisterUser);
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<String> updateUser(@PathVariable String id,@RequestBody @Valid DataUpdateUser dataUpdateUser){
        log.info("[UserController.registerUser] - [Controller]");
        userService.updateUser(id,dataUpdateUser);
        return new ResponseEntity<>("User updated successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<DataUser>> findAllUsers(@PageableDefault(size = 10) Pageable pageable){
        log.info("[UserController.registerUser] - [Controller]");
        Page<DataUser> users = userService.findAllUsers(pageable);
        return ResponseEntity.ok().body(users);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        log.info("[UserController.deleteUser] - [Controller]");
        userService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }


}
