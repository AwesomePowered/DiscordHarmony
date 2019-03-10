package net.awesomepowered.discordharmony.Discord;

import net.awesomepowered.discordharmony.Global.Broadcaster;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

/**
 * Created by Lax on 7/27/2017.
 */
public class DiscordListener implements MessageCreateListener {

    @Override
    public void onMessageCreate(MessageCreateEvent ev) {
        if (ev.getMessageAuthor().isYourself() || ev.getMessage().getContent().startsWith("!")) return;
        if (ev.getChannel().getIdAsString().equalsIgnoreCase(Harmony.discordChannel)) {
            String sender = ev.getMessage().getAuthor().getDisplayName();
            String message;

            if (!Harmony.isAdmin(ev.getMessage().getAuthor().getIdAsString())) {
                message = ev.getMessage().getContent().replace("&","");
            } else {
                message = ev.getMessage().getContent();
            }
            Broadcaster.broadcast(Harmony.chatFormat.replace("%SENDER%", sender).replace("%MESSAGE%", message.replace("\u00a7","")));
        }
    }
}
