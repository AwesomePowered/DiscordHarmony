package net.awesomepowered.discordharmony.Discord;

import net.awesomepowered.discordharmony.Broadcasters.Broadcaster;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.EventSubscriber;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.impl.events.DiscordDisconnectedEvent;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.HTTP429Exception;
import sx.blah.discord.util.MissingPermissionsException;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Lax on 4/24/2016.
 */
public class Discord {

    public void log(Object o) {
        System.out.println(o);
    }

    private volatile IDiscordClient client;
    private String email;
    private String password;
    private String token;
    private final AtomicBoolean reconnect = new AtomicBoolean(true);

    public Discord(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Discord(String token) {
        this.token = token;
    }

    public void login() throws DiscordException {
        if (token == null) {
            client = new ClientBuilder().withLogin(email, password).login();
        } else {
            client = new ClientBuilder().withToken(token).login();
        }
        client.getDispatcher().registerListener(this);
    }

    public IDiscordClient getClient() {
        return this.client;
    }

    @EventSubscriber
    public void onReady(ReadyEvent event) {
        log("Connecting to NSA servers using " + Harmony.type);
    }

    @EventSubscriber
    public void onDisconnect(DiscordDisconnectedEvent event) {
        log("Bot disconnected.");
        CompletableFuture.runAsync(() -> {
            if (reconnect.get()) {
                log("Reconnecting bot");
                try {
                    login();
                } catch (DiscordException e) {
                    log("Failed to reconnect bot " + e);
                }
            }
        });
    }

    @EventSubscriber
    public void onMessage(MessageReceivedEvent ev) throws HTTP429Exception, DiscordException, MissingPermissionsException, InvocationTargetException, IllegalAccessException {
        String sender = ev.getMessage().getAuthor().getName();
        String message;
        if (!ev.getMessage().getAuthor().getID().equalsIgnoreCase("171458204167831552")) {
            message = ev.getMessage().getContent().replace("&","");
        } else {
            message = ev.getMessage().getContent();
        }
        if (ev.getMessage().getChannel().getID().equalsIgnoreCase(Harmony.discordChannel)) {
            Broadcaster.broadcast(Harmony.chatFormat.replace("%SENDER%", sender).replace("%MESSAGE%", message.replace("\u00a7","")));
        }
    }

    public void terminate() {
        reconnect.set(false);
        try {
            client.logout();
        } catch (HTTP429Exception | DiscordException ev) {
            log("Logout failed " + ev);
        }
    }

}