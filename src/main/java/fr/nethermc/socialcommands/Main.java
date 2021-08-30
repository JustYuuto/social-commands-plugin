package fr.nethermc.socialcommands;

import fr.nethermc.socialcommands.commands.SocialCommand;
import fr.nethermc.socialcommands.commands.SocialSetCommand;
import fr.nethermc.socialcommands.regexs.Regexs;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        List<String> platforms = Regexs.PLATFORMS;

        for (String platform : platforms) {
            getCommand(platform).setExecutor(new SocialCommand(this, platform));
        }

        getCommand("socialset").setExecutor(new SocialSetCommand(this));
        getCommand("socialset").setTabCompleter(new SocialSetCommand(this));
    }

}