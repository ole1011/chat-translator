package de.ole101.translator;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.ole101.translator.command.IndexedCommand;
import de.ole101.translator.common.GuiceModule;
import de.ole101.translator.common.models.User;
import de.ole101.translator.events.PlayerEventListener;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandManager;
import net.minestom.server.command.builder.Command;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.server.ServerListPingEvent;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.LightingChunk;
import net.minestom.server.instance.block.Block;
import net.minestom.server.ping.ResponseData;
import org.atteo.classindex.ClassIndex;

import static de.ole101.translator.common.Colors.RED;
import static de.ole101.translator.common.Colors.SKY_BLUE;
import static java.lang.System.currentTimeMillis;
import static net.kyori.adventure.text.Component.text;

@Slf4j
public class ChatTranslator {

    public static Instance INSTANCE;
    public static String DEEPL_API_KEY;

    private static final List<Command> COMMANDS = List.of(
            new LanguageCommand()
    );

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure()
                .directory("src/main/resources")
                .load();

        DEEPL_API_KEY = dotenv.get("DEEPL_API_KEY");

        long startTime = currentTimeMillis();
        Injector injector = Guice.createInjector(new GuiceModule());

        MinecraftServer minecraftServer = MinecraftServer.init();
        InstanceManager instanceManager = MinecraftServer.getInstanceManager();

        INSTANCE = instanceManager.createInstanceContainer();
        INSTANCE.enableAutoChunkLoad(true);
        INSTANCE.setGenerator(unit -> unit.modifier().fillHeight(0, 40, Block.GRASS_BLOCK));
        INSTANCE.setChunkSupplier(LightingChunk::new);

        MinecraftServer.setBrandName("Minestom");

        MojangAuth.init();

        /*
         * Register events
         */
        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        globalEventHandler.addListener(ServerListPingEvent.class, event -> {
            ResponseData responseData = event.getResponseData();
            responseData.setMaxPlayer(100);
            responseData.setDescription(text("Welcome to a server with automatic chat translation!", SKY_BLUE));
        });
        globalEventHandler.addChild(injector.getInstance(PlayerEventListener.class).playerNode());

        /*
         * Register commands
         */
        CommandManager commandManager = MinecraftServer.getCommandManager();
        commandManager.setUnknownCommandCallback((sender, command) -> sender.sendMessage(text("Unknown command: /" + command, RED)));
        ClassIndex.getAnnotated(IndexedCommand.class).forEach(command -> {
            try {
                commandManager.register((Command) injector.getInstance(command));
                log.debug("Registered command: {}", command.getSimpleName());
            } catch (Exception e) {
                log.error("Failed to register command: {}", command.getSimpleName(), e);
            }
        });

        MinecraftServer.getConnectionManager().setPlayerProvider(User::new);

        minecraftServer.start("0.0.0.0", 25565);

        log.info("Server started in {}ms", currentTimeMillis() - startTime);
    }
}
