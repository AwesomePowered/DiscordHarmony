package net.awesomepowered.discordharmony.Discord.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import net.awesomepowered.discordharmony.Global.Keywords;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.user.User;

public class CommandList implements CommandExecutor {

    @Command(aliases = {"!list"}, usage = "!list", description = "Shows all the online players")
    public void onCommand(TextChannel channel, User user) {
        channel.sendMessage(user.getMentionTag() + new Keywords("\nOnline Players ({ONLINE}): {PLAYERLIST}").replace());
    }

}
