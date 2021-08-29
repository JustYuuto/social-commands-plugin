package fr.nethermc.socialcommands.commands;

import fr.nethermc.socialcommands.Main;
import fr.nethermc.socialcommands.SocialRegexs;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SocialSetCommand implements CommandExecutor {

    private final Main main;

    public SocialSetCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0 || args.length == 1) {
                player.sendMessage("§cPlease specify the platform and the link.");

                return true;
            }

            File configFile = new File(main.getDataFolder(), "config.yml");
            YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
            List<String> platforms = SocialRegexs.PLATFORMS;

            String platform = args[0];
            String link = args[1];

            if (!platforms.contains(platform)) {
                player.sendMessage(
                    "§cThe platform \"" + platform + "\" does not exist. §rAvailable platforms: https://bit.ly/3gIkrjk"
                );
                return true;
            }

            config.set("links." + platform, link);

            try {
                config.save(configFile);
                player.sendMessage("§aSuccessfully saved the config for \"" + platform + "\"!§r");
                return true;
            } catch (IOException e) {
                player.sendMessage("§cCannot save the config. Check the console for more details.§r");
                e.printStackTrace();
                return false;
            }
        }

        return false;
    }

}
