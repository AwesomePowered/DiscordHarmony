package net.awesomepowered.discordharmony.Discord;

import net.awesomepowered.discordharmony.Global.Server;
import org.javacord.api.DiscordApi;

import java.util.List;

/**
 * Created by Lax on 4/24/2016.
 */
public class /*interface*/ Harmony {

    public static String chatFormat;
    public static String type;
    public static String discordChannel;
    public static String modChannel;
    public static String token;
    public static boolean useToken;
    public static List<String> admins;
    public static List<String> presence;
    public static Server server;

    public static DiscordApi getBot() {
        return Discord.discord;
    }

    public static Boolean isAdmin(String s) {
        return admins.contains(s);
    }

}
