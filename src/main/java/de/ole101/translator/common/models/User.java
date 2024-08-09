package de.ole101.translator.common.models;

import de.ole101.translator.common.enums.Language;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.minestom.server.entity.Player;
import net.minestom.server.network.player.PlayerConnection;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Getter
@Setter
@Accessors(fluent = true)
public class User extends Player {

    @NotNull
    private Language language = Language.ENGLISH;

    public User(@NotNull UUID uuid, @NotNull String username, @NotNull PlayerConnection playerConnection) {
        super(uuid, username, playerConnection);
    }
}
