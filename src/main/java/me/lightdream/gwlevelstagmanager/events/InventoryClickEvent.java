package me.lightdream.gwlevelstagmanager.events;

import me.lightdream.gwlevelstagmanager.GwlevelsTagManager;
import me.lightdream.gwlevelstagmanager.Utils;
import me.lightdream.gwlevelstagmanager.items.ItemsInit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Arrays;
import java.util.List;

public class InventoryClickEvent implements Listener {

    List<String> denyedInv = Arrays.asList("Tag Manager");

    @EventHandler
    private void onInventoryClick(org.bukkit.event.inventory.InventoryClickEvent event) {
        if (denyedInv.contains(event.getView().getTitle())) {
            if(event.getCurrentItem().equals(ItemsInit.getBedWarsTag(event.getWhoClicked().getName())) || event.getCurrentItem().equals(ItemsInit.getParkourTag(event.getWhoClicked().getName())) || event.getCurrentItem().equals(ItemsInit.getLevelTag(event.getWhoClicked().getName())))
            {
                String tag = event.getCurrentItem().getItemMeta().getLore().get(1).replace("Rank:", "");
                GwlevelsTagManager.getChat().setPlayerSuffix((Player) event.getWhoClicked(), Utils.color(tag));
                event.getWhoClicked().closeInventory();
                GwlevelsTagManager.setTagGlobally(event.getWhoClicked().getName(), event.getCurrentItem().getItemMeta().getDisplayName().replace("§b§l", "").replace(" §e§lTAG", ""));
            }
            event.setCancelled(true);
        }
    }
}
