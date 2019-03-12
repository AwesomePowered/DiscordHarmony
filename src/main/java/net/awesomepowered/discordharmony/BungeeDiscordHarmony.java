package net.awesomepowered.discordharmony;

import net.awesomepowered.discordharmony.Discord.Discord;
import net.awesomepowered.discordharmony.Discord.Harmony;
import net.awesomepowered.discordharmony.Global.Server;
import net.awesomepowered.discordharmony.Listeners.BungeeListener;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class BungeeDiscordHarmony extends Plugin implements Server {

    public Configuration config;
    public static BungeeDiscordHarmony instance;

    public void onEnable() {
        instance = this;
        loadConfig();
        Harmony.type = "BUNGEE";
        Harmony.discordChannel = config.getString("discord.channel");
        Harmony.modChannel = config.getString("discord.modchan");
        Harmony.chatFormat = config.getString("ChatFormat");
        Harmony.token = config.getString("discord.token");
        Harmony.useToken = config.getBoolean("UseToken");
        Harmony.admins = new ArrayList<>(config.getStringList("discord.admins"));
        Harmony.presence = new ArrayList<>(config.getStringList("BotStatus"));
        Harmony.server = this;
        getProxy().getPluginManager().registerListener(this, new BungeeListener());
        logIn();
    }

    public void logIn() {
        Discord.botLogin();
    }

    public void loadConfig() {
        if (!getDataFolder().exists())
            getDataFolder().mkdir();
        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()) {
            try {
                Files.copy(getResourceAsStream("config.yml"), file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getOnlineCount() {
         return getProxy().getOnlineCount();
    }

    @Override
    public List<String> getPlayerList() {
        List<String> players = new ArrayList<>();
        for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
            players.add(player.getDisplayName());
        }
        return players;
    }

    @Override
    public int getServerCount() {
        return ProxyServer.getInstance().getServers().size();
    }

    @Override
    public void kickPlayer(String name, String reason) {
        ProxiedPlayer p = ProxyServer.getInstance().getPlayer(name);
        if (p != null) {
            p.disconnect(ChatColor.translateAlternateColorCodes('&', reason));
        }
    }

    @Override
    public void consoleCommand(String command) {
        getProxy().getPluginManager().dispatchCommand(getProxy().getConsole(), command);
    }
}
