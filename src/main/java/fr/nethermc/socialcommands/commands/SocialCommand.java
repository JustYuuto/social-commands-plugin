package fr.nethermc.socialcommands.commands;

import fr.nethermc.socialcommands.Main;
import fr.nethermc.socialcommands.regexs.Regexs;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
            Player player = (Player) sender;
            File configFile = new File(main.getDataFolder(), "config.yml");
            YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
            List<String> platforms = Regexs.PLATFORMS;

            if (config.get("links." + platform) != null) {
                player.sendMessage("§9Here the " + platform + " of this server: " + getFullLink((String) config.get("links." + platform)));
            } else if (!platforms.contains(platform)) {
                player.sendMessage("§cThe platform \"" + platform + "\" doesn't exist. Error code: PLATFORM_DOESNT_EXIST");
            } else {
                String message = "§cThe platform \"" + platform + "\" is not defined.";
                if (player.hasPermission("socialcommands.socialset")) {
                    message += " Define it with \"/socialset " + platform + " <link>\".";
                }
                message += " Error code: PLATFORM_NOT_DEFINED";

                player.sendMessage(message);
            }

            return true;
        }

        return false;
    }

    @Nullable
    private String getFullLink(String link) {
        switch (platform) {
            case "discord":
                if (link.matches(Regexs.DISCORD_REGEX)) return "https://" + link;
                if (link.matches("([a-zA-Z0-9]+)")) return "https://discord.gg/" + link;
            case "twitter":
                if (link.matches(Regexs.TWITTER_REGEX)) return "https://twitter.com/" + link.replace("@", "");
            case "instagram":
                if (link.matches(Regexs.INSTAGRAM_REGEX)) return "https://www.instagram.com/" + link.replace("@", "");
            case "website":
                if (link.matches("(([a-z.]+)\\.)?(([a-z]+)\\.([a-z]+))(/([a-zA-Z0-9_\\-.]+)?)?")) return "https://" + link;
                if (link.matches(Regexs.WEBSITE_REGEX)) return link;
            default:
                return null;
        }
    }

}
