package net.awesomepowered.discordharmony;

import net.awesomepowered.discordharmony.Discord.Discord;
import net.awesomepowered.discordharmony.Discord.Harmony;
import net.awesomepowered.discordharmony.Global.Server;
import net.awesomepowered.discordharmony.Listeners.BukkitListener;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lax on 4/24/2016.
 */
public class BukkitDiscordHarmony extends JavaPlugin implements Server {

    public static BukkitDiscordHarmony instance;

    public void onEnable() {
        instance = this;
        Harmony.type = "BUKKIT";
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new BukkitListener(), this);
        Harmony.discordChannel = getConfig().getString("discord.channel");
        Harmony.modChannel = getConfig().getString("discord.modchan");
        Harmony.chatFormat = getConfig().getString("ChatFormat");
        Harmony.token = getConfig().getString("discord.token");
        Harmony.useToken = getConfig().getBoolean("UseToken");
        Harmony.admins = new ArrayList<>(getConfig().getStringList("discord.admins"));
        Harmony.presence = new ArrayList<>(getConfig().getStringList("BotStatus"));
        Harmony.server = this;
        logIn();
    }

    public void logIn() {
        Discord.botLogin();
    }

    @Override
    public int getOnlineCount() {
        return Bukkit.getOnlinePlayers().size();
    }

    @Override
    public List<String> getPlayerList() {
        List<String> players = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            players.add(player.getDisplayName());
        }
        return players;
    }

    @Override
    public int getServerCount() {
        return 1;
    }

    @Override
    public void kickPlayer(String name, String reason) {
        Player p = Bukkit.getPlayer(name);
        if (p != null) {
            p.kickPlayer(ChatColor.translateAlternateColorCodes('&', reason));
        }
    }

    @Override
    public void consoleCommand(String command) {
        getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
    }
}
