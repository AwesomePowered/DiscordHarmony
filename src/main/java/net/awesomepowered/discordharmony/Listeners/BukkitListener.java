package net.awesomepowered.discordharmony.Listeners;

import net.awesomepowered.discordharmony.Discord.Harmony;
import net.awesomepowered.discordharmony.Discord.Messenger;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Lax on 4/24/2016.
 */
public class BukkitListener implements Listener {

    Messenger m = new Messenger();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent ev) {
        Player p = ev.getPlayer();
        String message = ev.getMessage();
        m.sendMessage("[" + p.getName() + "] " + message, Harmony.discordChannel);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent ev) {
        m.sendMessage("[-] " + ev.getPlayer().getName(), Harmony.discordChannel);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent ev) {
        m.sendMessage("[+] " + ev.getPlayer().getName(), Harmony.discordChannel);
    }

    @EventHandler
    public void onConnect(PlayerLoginEvent ev) {
        String name = ev.getPlayer().getName();
        String host = ev.getAddress().getHostAddress();
        String pIP = ev.getAddress().getAddress().toString();
        String result = ev.getResult().toString();
        m.sendMessage("[+] " + name + "("+pIP+")" + " using " + host + " " + result, Harmony.modChannel);
    }

}
