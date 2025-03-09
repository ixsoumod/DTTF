package fr.ixsou.dodgetothefinish.commands;

import fr.ixsou.dodgetothefinish.DodgeToTheFinish;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandManager implements CommandExecutor {

    private final DodgeToTheFinish plugin;

    public CommandManager(DodgeToTheFinish plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(plugin.getMessage("no-console"));
            return true;
        }

        Player player = (Player) sender;


        if (args.length == 0) {
            player.sendMessage(plugin.getMessage("help"));
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (!player.hasPermission("dttf.reload")) {
                player.sendMessage(plugin.getMessage("no-permission"));
                return true;
            }

            plugin.messages.reloadConfig();
            player.sendMessage(plugin.getMessage("config-reloaded"));
            return true;
        }


        return true;
    }
}
