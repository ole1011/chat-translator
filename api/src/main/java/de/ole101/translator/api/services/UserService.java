package de.ole101.translator.api.services;

import de.ole101.translator.api.common.entities.User;
import de.ole101.translator.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * Retrieves a list of all users from the database.
     *
     * @return A list of User objects representing all users in the database.
     */
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    /**
     * Retrieves a User object from the database based on the Minecraft UUID. If no User with the given Minecraft UUID is found, a new
     * User object is created with the given Minecraft UUID.
     *
     * @param minecraftUuid The Minecraft UUID of the User to retrieve.
     *
     * @return The User object with the given Minecraft UUID, or a new User object if no User is found.
     */
    public User getUserByMinecraftUuid(UUID minecraftUuid) {
        return this.userRepository.findByMinecraftUuid(minecraftUuid)
                .orElseGet(() -> User.builder()
                        .minecraftUuid(minecraftUuid)
                        .build());
    }

    /**
     * Updates the user information in the database.
     *
     * @param user The User object containing the updated information.
     */
    public void updateUser(User user) {
        this.userRepository.save(user);
    }
}
