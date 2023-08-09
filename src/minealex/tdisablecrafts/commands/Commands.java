package minealex.tdisablecrafts.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.configuration.file.FileConfiguration;

public class Commands implements CommandExecutor {
    private final Plugin plugin;
    private final FileConfiguration config;
    
    public Commands(Plugin plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("tdc")) {
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("tdc.reload")) {
                    reloadConfig();
                    String reloadMessage = config.getString("messages.reload", "&5TDisableCrafts &e> &aConfiguration reloaded.");
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', reloadMessage));
                } else {
                    String noPermissionMessage = config.getString("messages.no-permission", "&5TDisableCrafts &e> &cYou don't have permission to use this command.");
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', noPermissionMessage));
                }
                return true;
            }
        }
        return false;
    }
    
    private void reloadConfig() {
        plugin.reloadConfig();
    }
}
