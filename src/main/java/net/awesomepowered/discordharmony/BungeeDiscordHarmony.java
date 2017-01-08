package net.awesomepowered.discordharmony;

import net.awesomepowered.discordharmony.Discord.Discord;
import net.awesomepowered.discordharmony.Discord.Harmony;
import net.awesomepowered.discordharmony.Listeners.BungeeListener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import sx.blah.discord.util.DiscordException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by Lax on 4/24/2016.
 */
public class BungeeDiscordHarmony extends Plugin {

    public Configuration config;
    public static BungeeDiscordHarmony instance;

    public void onEnable() {
        instance = this;
        loadConfig();
        Harmony.type = "BUNGEE";
        Harmony.discordChannel = config.getString("discord.channel");
        Harmony.chatFormat = config.getString("ChatFormat");
        getProxy().getPluginManager().registerListener(this, new BungeeListener());
        Harmony.bot = new Discord(config.getString("discord.username"),config.getString("discord.password"));
        try {
            Harmony.bot.login();
        } catch (DiscordException e) {
            e.printStackTrace();
        }
    }

    public void onDisable() {
        Harmony.bot.terminate();
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
}
