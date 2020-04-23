package com.chumcraft.usefulwanderingtrader;

import javax.swing.text.DefaultStyledDocument.ElementSpec;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class UWTReload implements CommandExecutor {

    private UWTPlugin plugin;

    public UWTReload(UWTPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("uwtreload")) {
                this.plugin.load();
                sender.sendMessage("Configuration Reloaded.");
            }else{
                sender.sendMessage("Permission denied.");
            }
        }else if(sender instanceof ConsoleCommandSender){
            this.plugin.load();
            this.plugin.getLogger().info("Configuration Reloaded.");
        }

        return false;
    }
}