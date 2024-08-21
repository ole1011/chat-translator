package de.ole101.translator.events;

import com.google.inject.Inject;
import de.ole101.translator.common.models.User;
import de.ole101.translator.services.ChatService;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.event.EventFilter;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.event.player.PlayerChatEvent;
import net.minestom.server.event.player.PlayerDisconnectEvent;
import net.minestom.server.event.player.PlayerSpawnEvent;
import net.minestom.server.event.trait.PlayerEvent;

import java.util.stream.Collectors;

import static de.ole101.translator.ChatTranslator.INSTANCE;
import static de.ole101.translator.common.Colors.BLUE;
import static de.ole101.translator.common.Colors.GRAY;
import static de.ole101.translator.common.Colors.SKY_BLUE;
import static de.ole101.translator.common.Colors.WHITE;
import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.event.ClickEvent.runCommand;
import static net.kyori.adventure.text.format.TextDecoration.ITALIC;
import static net.minestom.server.adventure.audience.Audiences.all;

public class PlayerEventListener {

    private final ChatService chatService;

    @Inject
    public PlayerEventListener(ChatService chatService) {
        this.chatService = chatService;
    }

    public EventNode<PlayerEvent> playerNode() {
        return EventNode.type("player", EventFilter.PLAYER)
                .addListener(AsyncPlayerConfigurationEvent.class, event -> {
                    event.setSpawningInstance(INSTANCE);
                    event.getPlayer().setRespawnPoint(new Pos(0, 42, 0));
                })
                .addListener(PlayerSpawnEvent.class, event -> {
                    if (!event.isFirstSpawn()) {
                        return;
                    }
                    Player player = event.getPlayer();
                    all().sendMessage(text(player.getUsername()).append(text(" has joined.")).color(WHITE));

                    player.sendMessage(text("Select your language with ", SKY_BLUE)
                            .append(text("/language", BLUE))
                            .append(text(".", SKY_BLUE))
                            .hoverEvent(text("/language", BLUE))
                            .clickEvent(runCommand("/language")));
                })
                .addListener(PlayerDisconnectEvent.class, event -> all().sendMessage(text(event.getPlayer().getUsername()).append(text(" has left.")).color(WHITE)))
                .addListener(PlayerChatEvent.class, event -> {
                    User user = (User) event.getPlayer();
                    String message = event.getMessage();

                    event.setCancelled(true);

                    INSTANCE.getPlayers().stream()
                            .map(onlinePlayer -> (User) onlinePlayer)
                            .collect(Collectors.groupingBy(User::language))
                            .forEach((language, users) -> {
                                if (language == user.language()) {
                                    users.forEach(onlineUser -> onlineUser.sendMessage(user.getName()
                                            .append(text(": " + message)).color(WHITE)));
                                    return;
                                }
                                String translation = this.chatService.translate(message, language);
                                users.forEach(onlineUser -> onlineUser.sendMessage(user.getName()
                                        .append(text(": " + translation)).color(WHITE).appendSpace()
                                        .append(text("ℹ", GRAY).hoverEvent(text("This message has been translated automatically. (Original message: \"" + message + "\")", GRAY, ITALIC)))));
                            });
                });
    }
}
