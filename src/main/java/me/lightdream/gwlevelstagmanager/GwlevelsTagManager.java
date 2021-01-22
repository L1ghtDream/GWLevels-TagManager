package me.lightdream.gwlevelstagmanager;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class GwlevelsTagManager extends JavaPlugin {

    @Override
    public void onEnable() {
        Objects.requireNonNull(this.getCommand("gw")).setExecutor(new CommandManager());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
