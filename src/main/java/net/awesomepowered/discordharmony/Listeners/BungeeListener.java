package net.awesomepowered.discordharmony.Listeners;

import net.awesomepowered.discordharmony.BungeeDiscordHarmony;
import net.awesomepowered.discordharmony.Discord.Harmony;
import net.awesomepowered.discordharmony.Discord.Messenger;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Created by Lax on 4/24/2016.
 */
public class BungeeListener implements Listener {

    Messenger m = new Messenger();

    @EventHandler
    public void onChat(ChatEvent ev) {
        ProxiedPlayer p = (ProxiedPlayer) ev.getSender();
        String message = ev.getMessage();
        if (message.startsWith("/")) return;
        String name = p.getName();
        String server = p.getServer().getInfo().getName();
        m.sendMessage("[" + name + "]" + "(" + server + ") " + message, Harmony.discordChannel);
        if (BungeeDiscordHarmony.instance.config.getBoolean("BungeeBridge", true)) {
            for (ProxiedPlayer pp : BungeeDiscordHarmony.instance.getProxy().getPlayers()) {
                if (!pp.getServer().getInfo().getName().equalsIgnoreCase(server)) {
                    pp.sendMessage("[" + name + "]" + "(" + server + ") " + ChatColor.stripColor(message));
                }
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerDisconnectEvent ev) {
        String name = ev.getPlayer().getName();
        m.sendMessage("[-] " + name, Harmony.discordChannel);
    }

    @EventHandler
    public void onLogin(LoginEvent ev) {
        PendingConnection pc = ev.getConnection();
        String name = pc.getName();
        String host = pc.getVirtualHost().getHostString();
        String pIP = pc.getAddress().getAddress().toString();
        m.sendMessage("[+] " + name + "("+pIP+")" + " using " + host, Harmony.modChannel);
    }

    @EventHandler
    public void onSwitch(ServerSwitchEvent ev) {
        String name = ev.getPlayer().getName();
        m.sendMessage("[+] " + name + " - "+ ev.getPlayer().getServer().getInfo().getName(), Harmony.discordChannel);
    }

}
