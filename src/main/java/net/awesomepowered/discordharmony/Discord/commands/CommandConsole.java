package net.awesomepowered.discordharmony.Discord.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import net.awesomepowered.discordharmony.Discord.Harmony;
import org.javacord.api.entity.message.MessageAuthor;

public class CommandConsole implements CommandExecutor {

    @Command(aliases = {"!consolecommand", "!cc", "!console"}, usage = "!cc command", description = "Executes command on the console")
    public void onCommand(String[] args, MessageAuthor messageAuthor) {
        if ((messageAuthor.canBanUsersFromServer() || Harmony.admins.contains(messageAuthor.getIdAsString())) && args.length >= 1) {
            String command = String.join(" ", args);
            Harmony.server.consoleCommand(command.startsWith("/") ? command.substring(1) : command);
        }
    }
}
