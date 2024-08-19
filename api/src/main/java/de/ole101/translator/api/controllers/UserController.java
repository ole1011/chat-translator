package de.ole101.translator.api.controllers;

import de.ole101.translator.api.common.entities.User;
import de.ole101.translator.api.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping("/{minecraftUuid}")
    public User getUser(@PathVariable UUID minecraftUuid) {
        return this.userService.getUserByMinecraftUuid(minecraftUuid);
    }

    @PutMapping
    public void updateUser(@RequestBody User user) {
        this.userService.updateUser(user);
    }
}
