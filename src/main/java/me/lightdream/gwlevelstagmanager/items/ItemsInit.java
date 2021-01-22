package me.lightdream.gwlevelstagmanager.items;

import me.lightdream.gwlevelstagmanager.Utils;
import me.lightdream.gwlevelstagmanager.managers.TagManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemsInit {

    public static ItemStack getPaneColor1()
    {
        //light blue
        return new ItemBuilder(Material.STAINED_GLASS_PANE, (short) 3).setDisplayName(" ").build();
    }

    public static ItemStack getPaneColor2()
    {
        //yellow
        return new ItemBuilder(Material.STAINED_GLASS_PANE, (short) 4).setDisplayName(" ").build();
    }

    public static ItemStack getBedWarsTag(String player)
    {
        ArrayList<String> lore = new ArrayList<>();

        lore.add("");
        lore.add(Utils.color("&fRank: " + TagManager.getBedWarsRank(player)));

        ItemStack output = new ItemBuilder(Material.NAME_TAG).setDisplayName(Utils.color("&b&lBedWars &e&lTAG")).setLore(lore).build();
        ItemMeta meta = output.getItemMeta();
        output.setItemMeta(meta);

        return output;
    }

    public static ItemStack getParkourTag(String player)
    {
        ArrayList<String> lore = new ArrayList<>();

        lore.add("");

        ItemStack output = new ItemBuilder(Material.NAME_TAG).setDisplayName(Utils.color("&b&lParkour &e&lTAG")).setLore(lore).build();
        ItemMeta meta = output.getItemMeta();
        output.setItemMeta(meta);

        return output;
    }

    public static ItemStack getLevelTag(String player)
    {
        ArrayList<String> lore = new ArrayList<>();

        lore.add("");
        lore.add(Utils.color("&fRank: " + TagManager.getLevelRank(player)));

        ItemStack output = new ItemBuilder(Material.NAME_TAG).setDisplayName(Utils.color("&b&lLevel &e&lTAG")).setLore(lore).build();
        ItemMeta meta = output.getItemMeta();
        output.setItemMeta(meta);

        return output;
    }

}
