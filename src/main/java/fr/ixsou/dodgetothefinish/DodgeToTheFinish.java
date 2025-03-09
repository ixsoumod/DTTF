package fr.ixsou.dodgetothefinish;

import fr.ixsou.dodgetothefinish.commands.CommandManager;
import fr.ixsou.dodgetothefinish.files.messages;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class DodgeToTheFinish extends JavaPlugin {

    public messages messages;

    @Override
    public void onEnable() {
        this.messages = new messages(this, "messages.yml");

        messages.reloadConfig();

        getCommand("dttf").setExecutor(new CommandManager(this));

        saveConfig();
        getLogger().info(getPrefix() + getMessage("plugin-enabled"));
    }


    @Override
    public void onDisable() {
        messages.saveConfig();
        saveConfig();
        getLogger().info(getPrefix() + getMessage("plugin-disabled"));
    }


    public String getMessage(String path) {
        if (!messages.getConfig().contains(path)) {
            getLogger().warning("Message introuvable: " + path);
            return ChatColor.translateAlternateColorCodes('&', "&cMessage introuvable !");
        }

        Object messageObject = messages.getConfig().get(path);

        if (messageObject instanceof String) {
            return ChatColor.translateAlternateColorCodes('&', (String) messageObject);
        }

        // Retourne un message par défaut si la clé n'est pas une chaîne
        return getMessage("no-found-message");
    }


    public String getPrefix() {
        return getMessage("prefix");
    }

    public String Help() {
        return getMessage("help.title") + "\n" + getMessage("help.1") + "\n" + getMessage("help.2");

    }


}
