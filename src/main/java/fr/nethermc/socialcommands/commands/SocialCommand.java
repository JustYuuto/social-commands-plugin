package fr.nethermc.socialcommands.commands;

import fr.nethermc.socialcommands.Main;
import fr.nethermc.socialcommands.SocialRegexs;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.List;

public class SocialCommand implements CommandExecutor {

    private Main main;
    private String platform;

    public SocialCommand(Main main, String platform) {
        this.main = main;
        this.platform = platform;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            File configFile = new File(main.getDataFolder(), "config.yml");
            YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
            List<String> platforms = (new SocialRegexs()).platforms;

            if (config.get(platform) != null) {
                player.sendMessage("§9Here the " + platform + " of this server: " + config.get(platform));
            } else if (!platforms.contains(platform)) {
                player.sendMessage("§cThe platform \"" + platform + "\" does not exist.");
            } else {
                player.sendMessage(
                    "§cThe platform \"" + platform + "\" is not defined. Please define it with \"/socialset " + platform + " <link>\"."
                );
            }

            return true;
        }

        return false;
    }

}
