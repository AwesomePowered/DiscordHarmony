package net.awesomepowered.discordharmony.Discord;

import de.btobastian.sdcf4j.CommandHandler;
import de.btobastian.sdcf4j.handler.JavacordHandler;
import net.awesomepowered.discordharmony.Discord.commands.CommandBroadcast;
import net.awesomepowered.discordharmony.Discord.commands.CommandKick;
import net.awesomepowered.discordharmony.Discord.commands.CommandList;
import net.awesomepowered.discordharmony.Global.Keywords;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lax on 4/24/2016.
 */
public class Discord {

    static DiscordApi discord;

    public static void main(String[] args) {
        botLogin();
    }

    public static void botLogin() {
        try {
            discord = new DiscordApiBuilder().setToken(Harmony.token).login().join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        discord.addListener(new DiscordListener());
        spoolStatus();

        CommandHandler commandHandler = new JavacordHandler(discord);

        commandHandler.registerCommand(new CommandList());
        commandHandler.registerCommand(new CommandBroadcast());
        commandHandler.registerCommand(new CommandKick());
    }

    private static void spoolStatus() {
        discord.getThreadPool().getScheduler().scheduleAtFixedRate(() -> discord.updateActivity(ActivityType.PLAYING,
                new Keywords(Harmony.presence.get(ThreadLocalRandom.current().nextInt(0,Harmony.presence.size()))).replace()), 0, 1, TimeUnit.MINUTES);
    }

}