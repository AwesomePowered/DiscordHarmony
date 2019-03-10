package net.awesomepowered.discordharmony.Global;

import net.awesomepowered.discordharmony.Discord.Harmony;

public class Keywords {

    private String template;
    private Server server;

    public Keywords(String template) {
        this.template = template;
        this.server = Harmony.server;
    }

    public String replace() {
        return template
                .replace("{ONLINE}", String.valueOf(server.getOnlineCount()))
                .replace("{PLAYERLIST}", String.join(", ", server.getPlayerList()))
                .replace("{SERVERCOUNT}", String.valueOf(server.getServerCount()));
    }
}
