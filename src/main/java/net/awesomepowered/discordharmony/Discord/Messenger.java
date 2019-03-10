package net.awesomepowered.discordharmony.Discord;

/**
 * Created by Lax on 4/24/2016.
 */
public class Messenger {

    public void sendMessage(String message, String channel) {
        Harmony.getBot().getTextChannelById(channel).get().sendMessage(message);
    }

}
