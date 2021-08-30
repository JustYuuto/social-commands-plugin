package fr.nethermc.socialcommands.commands;

import fr.nethermc.socialcommands.Main;
import fr.nethermc.socialcommands.regexs.Regexs;
import fr.nethermc.socialcommands.regexs.Verifications;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SocialSetCommand implements CommandExecutor, TabExecutor {

    private final Main main;

    public SocialSetCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0 || args.length == 1) {
                player.sendMessage("§cPlease specify the platform and the link. Error code: MISSING_ARGS");

                return true;
            }

            File configFile = new File(main.getDataFolder(), "config.yml");
            YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
            List<String> platforms = Regexs.PLATFORMS;

            String platform = args[0];
            String link = args[1];

            if (!platforms.contains(platform)) {
                player.sendMessage(
                    "§cThe platform \"" + platform + "\" doesn't exist. Error code: PLATFORM_DOESNT_EXIST§r\nAvailable platforms: https://bit.ly/3gIkrjk"
                );

                return false;
            }

            if (new Verifications().verifyPlatform(platform, link)) {
                config.set("links." + platform, link);
            } else {
                player.sendMessage("§cSorry, but your link doesn't match to the regex. Error code: LINK_DOESNT_MATCH_TO_REGEX");

                return false;
            }

            try {
                config.save(configFile);
                player.sendMessage("§aSuccessfully saved the config for \"" + platform + "\"!");
                return true;
            } catch (IOException e) {
                player.sendMessage("§cCannot save the config. Check the console for more details. Error code: CANNOT_SAVE_CONFIG");
                e.printStackTrace();
                return false;
            }
        }

        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        final List<String> completions = new ArrayList<>();
        StringUtil.copyPartialMatches(args[0], Regexs.PLATFORMS, completions);
        Collections.sort(completions);

        return completions;
    }

}
