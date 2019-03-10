package net.awesomepowered.discordharmony.Discord.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import net.awesomepowered.discordharmony.Discord.Harmony;
import org.javacord.api.entity.message.MessageAuthor;

public class CommandKick implements CommandExecutor {

    @Command(aliases = {"!kick"}, usage = "!kick <player> <reason>", description = "Kicks a player")
    public void onCommand(String[] args, MessageAuthor messageAuthor) {
        if (messageAuthor.canKickUsersFromServer() && args.length >= 1) {
            Harmony.server.kickPlayer(args[0], args.length >= 2 ? String.join(" ").substring(args[0].length()) : "Kicked from the server");
        }
    }
}
