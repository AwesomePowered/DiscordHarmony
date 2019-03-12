package net.awesomepowered.discordharmony.Global;

import java.util.List;

public interface Server {

    /*
    Returns an online count.
     */
    int getOnlineCount();

    /*
    Returns a list of all online player names.
     */
    List<String> getPlayerList();

    /*
    Gets the amount of registered bungee servers.
    returns 1 for bukkit.
     */
    int getServerCount();

    /*
    Kicks the player from the server
     */
    void kickPlayer(String name, String reason);

    /*
    Executes a command in the console.
     */
    void consoleCommand(String command);

}
