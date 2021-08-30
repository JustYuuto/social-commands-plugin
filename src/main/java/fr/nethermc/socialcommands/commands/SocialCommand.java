package fr.nethermc.socialcommands.commands;

import fr.nethermc.socialcommands.Main;
import fr.nethermc.socialcommands.regexs.Regexs;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

public class SocialCommand implements CommandExecutor {

    private final Main main;
    private final String platform;

    public SocialCommand(Main main, String platform) {
        this.main = main;
        this.platform = platform;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (sender instanceof Player) {
            System.out.println(command.tabComplete(sender, alias, args));

            Player player = (Player) sender;
            File configFile = new File(main.getDataFolder(), "config.yml");
            YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
            List<String> platforms = Regexs.PLATFORMS;

            if (config.get("links." + platform) != null) {
                player.sendMessage("§9Here the " + platform + " of this server: " + config.get("links." + platform));
            } else if (!platforms.contains(platform)) {
                player.sendMessage("§cThe platform \"" + platform + "\" doesn't exist. Error code: PLATFORM_DOESNT_EXIST");
            } else {
                String message = "§cThe platform \"" + platform + "\" is not defined.";
                if (player.hasPermission("socialcommands.socialset.use")) {
                    message += " Define it with \"/socialset " + platform + " <link>\".";
                }
                message += " Error code: PLATFORM_NOT_DEFINED";

                player.sendMessage(message);
            }

            return true;
        }

        return false;
    }

}
