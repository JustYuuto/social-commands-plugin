package fr.nethermc.socialcommands.commands;

import fr.nethermc.socialcommands.*;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class SocialSetCommand implements CommandExecutor {

    private Main main;

    public SocialSetCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            File configFile = new File(main.getDataFolder(), "config.yml");
            YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
            SocialRegexs socialRegexs = new SocialRegexs();

            System.out.println(socialRegexs.regexMatch(socialRegexs.discord, args[1]));

            config.set(args[0], args[1]);

            try {
                config.save(configFile);
                player.sendMessage("§aSuccessfully saved the config for \"" + args[0] + "\"!§r");
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
