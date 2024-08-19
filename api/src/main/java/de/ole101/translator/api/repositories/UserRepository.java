package de.ole101.translator.api.repositories;

import de.ole101.translator.api.common.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByMinecraftUuid(UUID minecraftUuid);
}
