package net.awesomepowered.discordharmony.Listeners;

import net.awesomepowered.discordharmony.Discord.Harmony;
import net.awesomepowered.discordharmony.Discord.Messenger;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
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
        try {
            m.sendMessage("[" + name + "]" + "(" + server + ") " + message, Harmony.discordChannel);
        } catch (Exception e) {
            m.sendMessage(e.toString(), Harmony.discordChannel);
        }
    }

    @EventHandler
    public void onQuit(PlayerDisconnectEvent ev) {
        String name = ev.getPlayer().getName();
        m.sendMessage("[-] " + name, Harmony.discordChannel);
    }

//    @EventHandler
//    public void onLogin(LoginEvent ev) {
//        String name = ev.getConnection().getName();
//        String host = ev.getConnection().getVirtualHost().getHostString();
//        String pIP = ev.getConnection().getAddress().getAddress().toString();
//        m.sendMessage("[+] " + name + "("+pIP+")" + " using " + host, Harmony.discordChannel);
//    }

    @EventHandler
    public void onSwitch(ServerSwitchEvent ev) {
        String name = ev.getPlayer().getName();
        m.sendMessage("[+] " + name + " - "+ ev.getPlayer().getServer().getInfo().getName(), Harmony.discordChannel);
    }

}
