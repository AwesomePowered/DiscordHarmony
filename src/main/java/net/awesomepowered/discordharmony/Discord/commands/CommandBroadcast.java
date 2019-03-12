package net.awesomepowered.discordharmony.Discord.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import net.awesomepowered.discordharmony.Discord.Harmony;
import net.awesomepowered.discordharmony.Global.Broadcaster;
import org.javacord.api.entity.message.MessageAuthor;

public class CommandBroadcast implements CommandExecutor {

    @Command(aliases = {"!broadcast", "!alert"}, usage = "!broadcast", description = "Broadcast a message")
    public void onCommand(String[] args, MessageAuthor messageAuthor) {
        if ((messageAuthor.canKickUsersFromServer() || Harmony.admins.contains(messageAuthor.getIdAsString())) && args.length >= 1) {
            String msg = String.join(" ", args);
            if (!msg.startsWith("&h")) {
                msg = "&8[&4Alert&8]&r " + msg;
            } else {
                msg = msg.substring(2);
            }
            Broadcaster.broadcast(msg);
        }
    }
}
