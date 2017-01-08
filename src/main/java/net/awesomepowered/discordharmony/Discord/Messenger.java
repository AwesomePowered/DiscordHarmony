package net.awesomepowered.discordharmony.Discord;

import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.HTTP429Exception;
import sx.blah.discord.util.MissingPermissionsException;

/**
 * Created by Lax on 4/24/2016.
 */
public class Messenger {

    public void sendMessage(String message, String channel) {
        try {
            Harmony.bot.getClient().getChannelByID(channel).sendMessage(message);
        } catch (MissingPermissionsException e) {
            e.printStackTrace();
        } catch (HTTP429Exception e) {
            e.printStackTrace();
        } catch (DiscordException e) {
            e.printStackTrace();
        }
    }

}
