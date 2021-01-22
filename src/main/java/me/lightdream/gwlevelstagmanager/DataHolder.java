package me.lightdream.gwlevelstagmanager;

import me.lightdream.gwlevelstagmanager.managers.TagManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class DataHolder
{
    private static Inventory topLevelInventory;


    // ------------------------------ Updater ------------------------------
    public static void updateData()
    {
        Bukkit.getScheduler().runTaskTimer(GwlevelsTagManager.getPlugin(), () -> {
            for(Player player : Bukkit.getServer().getOnlinePlayers())
                GwlevelsTagManager.getChat().setPlayerSuffix(player, Utils.color(TagManager.getRank(GwlevelsTagManager.getTagGlobally(player.getName()), player.getName())));
        }, 0L, 2000L);
    }





}
