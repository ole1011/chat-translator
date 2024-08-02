package de.ole101.translator.command;

import de.ole101.translator.common.models.Translation;
import de.ole101.translator.common.models.User;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentEnum;
import net.minestom.server.command.builder.condition.Conditions;

import static de.ole101.translator.common.Color.GREEN;
import static de.ole101.translator.common.Color.RED;
import static net.kyori.adventure.text.Component.text;
import static net.minestom.server.command.builder.arguments.ArgumentType.Enum;

public class LanguageCommand extends Command {

    public LanguageCommand() {
        super("language", "lang");

        setCondition(Conditions::playerOnly);

        ArgumentEnum<Translation.Language> language = Enum("language", Translation.Language.class);

        // TODO: consider auto-generating this message
        setDefaultExecutor((sender, context) -> sender.sendMessage(text("Usage: /language <language>", RED)));

        addSyntax((sender, context) -> {
            User user = (User) sender;

            Translation.Language languageType = context.get(language);
            user.language(languageType);

            user.sendMessage(text("Language set to " + languageType.name() + "!", GREEN));
        }, language);
    }
}
