package me.lightdream.gwlevelstagmanager.managers;


import me.lightdream.gwlevelstagmanager.GwlevelsTagManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CommandManager implements CommandExecutor {



    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("dev-minion")) {
            Player player = (Player) sender;
            sender.sendMessage("MainPlugin: " + GwlevelsTagManager.PLUGIN_VERSION + "\nMinionPlugin: " + GwlevelsTagManager.MINION_VERSION);
        }
        if(label.equalsIgnoreCase("gwtag")) {
                ((Player)sender).openInventory(GwlevelsTagManager.getTagManagerInventory((Player) sender));
        }
        return true;
    }

}
