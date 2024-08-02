package de.ole101.translator.common.models;

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
    private Translation.Language language = Translation.Language.ENGLISH;

    public User(@NotNull UUID uuid, @NotNull String username, @NotNull PlayerConnection playerConnection) {
        super(uuid, username, playerConnection);
    }
}
