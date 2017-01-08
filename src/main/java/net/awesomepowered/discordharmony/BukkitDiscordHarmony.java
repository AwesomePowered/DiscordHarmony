package net.awesomepowered.discordharmony;

import net.awesomepowered.discordharmony.Discord.Discord;
import net.awesomepowered.discordharmony.Discord.Harmony;
import net.awesomepowered.discordharmony.Listeners.BukkitListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import sx.blah.discord.util.DiscordException;

/**
 * Created by Lax on 4/24/2016.
 */
public class BukkitDiscordHarmony extends JavaPlugin {

    public void onEnable() {
        Harmony.type = "BUKKIT";
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new BukkitListener(), this);
        Harmony.discordChannel = getConfig().getString("discord.channel");
        Harmony.chatFormat = getConfig().getString("ChatFormat");
        Harmony.bot = new Discord(getConfig().getString("discord.username"),getConfig().getString("discord.password"));
        try {
            Harmony.bot.login();
        } catch (DiscordException e) {
            e.printStackTrace();
        }
    }

    public void onDisable() {
        Harmony.bot.terminate();
    }



}
