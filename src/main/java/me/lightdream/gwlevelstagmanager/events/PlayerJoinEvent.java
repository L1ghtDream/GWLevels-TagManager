package me.lightdream.gwlevelstagmanager.events;

import me.lightdream.gwlevelstagmanager.GwlevelsTagManager;
import me.lightdream.gwlevelstagmanager.Utils;
import me.lightdream.gwlevelstagmanager.managers.TagManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerJoinEvent implements Listener {

    @EventHandler
    private void onPlayerJoin (org.bukkit.event.player.PlayerJoinEvent event)
    {
        GwlevelsTagManager.getChat().setPlayerSuffix(event.getPlayer(), Utils.color(TagManager.getRank(GwlevelsTagManager.getTagGlobally(event.getPlayer().getName()), event.getPlayer().getName())));
    }


}
