package net.awesomepowered.discordharmony.Broadcasters;

import net.awesomepowered.discordharmony.Discord.Harmony;
import net.md_5.bungee.api.ProxyServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

/**
 * Created by Lax on 4/25/2016.
 */
public class Broadcaster {

    public static void broadcast(Object o) {
        if (Harmony.type.equalsIgnoreCase("bukkit")) {
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', String.valueOf(o)));
            return;
        }
        if (Harmony.type.equalsIgnoreCase("bungee")) {
            ProxyServer.getInstance().broadcast(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', o.toString()));
        }
    }
}
