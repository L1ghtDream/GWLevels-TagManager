package me.lightdream.gwlevelstagmanager;

import me.lightdream.gwlevelstagmanager.database.DatabaseConnector;
import me.lightdream.gwlevelstagmanager.events.InventoryClickEvent;
import me.lightdream.gwlevelstagmanager.events.PlayerJoinEvent;
import me.lightdream.gwlevelstagmanager.exceptions.NoUserFound;
import me.lightdream.gwlevelstagmanager.items.ItemsInit;
import me.lightdream.gwlevelstagmanager.managers.CommandManager;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class GwlevelsTagManager extends JavaPlugin {

    public static final List<Integer> tagManagerInv_color1 = Arrays.asList(0,1,2,6,7,8,9,10,11,15,16,17,18,26,27,31,35,36,40,44,45,46,47,48,50,51,52,53);
    public static final List<Integer> tagManagerInv_color2 = Arrays.asList(3,4,5,12,14,19,20,21,22,23,24,25,28,30,32,34,37,38,39,41,42,43);

    public static final String PLUGIN_VERSION = "Beta 0.4 (GP) Build 1";
    public static final String MINION_VERSION = "Beta 1.1 Build 1";

    private static GwlevelsTagManager plugin;

    private static Chat chat = null;


    @Override
    public void onEnable() {
        plugin = this;

        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            System.out.println("Could not find Vault! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        this.saveDefaultConfig();
        this.getConfig().options().copyDefaults(true);


        getServer().getConsoleSender().sendMessage("\n  _____       _             __         _     ______                                           \n |_   _|     (_)           [  |       / |_  |_   _ `.                                         \n   | |       __     .--./)  | |--.   `| |-'   | | `. \\  _ .--.   .---.   ,--.    _ .--..--.   \n   | |   _  [  |   / /'`\\;  | .-. |   | |     | |  | | [ `/'`\\] / /__\\\\ `'_\\ :  [ `.-. .-. |  \n  _| |__/ |  | |   \\ \\._//  | | | |   | |,   _| |_.' /  | |     | \\__., // | |,  | | | | | |  \n |________| [___]  .',__`  [___]|__]  \\__/  |______.'  [___]     '.__.' \\'-;__/ [___||__||__] \n                  ( ( __))                                                                    ");

        Objects.requireNonNull(this.getCommand("gwtag")).setExecutor(new CommandManager());
        Objects.requireNonNull(this.getCommand("dev-minion")).setExecutor(new CommandManager());


        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();

        this.getServer().getPluginManager().registerEvents(new InventoryClickEvent(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerJoinEvent(), this);

        DatabaseConnector.sqlSetup();

    }

    @Override
    public void onDisable() {
        this.saveDefaultConfig();
    }

    // ------------------------------ Getters ------------------------------

    public static Inventory getTagManagerInventory(Player player)
    {
        Inventory tagManagerInv = Bukkit.createInventory(null, 54, "Tag Manager");

        for(int i : tagManagerInv_color1)
            tagManagerInv.setItem(i, ItemsInit.getPaneColor1());
        for(int i : tagManagerInv_color2)
            tagManagerInv.setItem(i, ItemsInit.getPaneColor2());

        tagManagerInv.setItem(13, ItemsInit.getBedWarsTag(player.getName()));
        tagManagerInv.setItem(29, ItemsInit.getParkourTag(player.getName()));
        tagManagerInv.setItem(33, ItemsInit.getLevelTag(player.getName()));

        return tagManagerInv;
    }

    public static GwlevelsTagManager getPlugin()
    {
        return plugin;
    }

    public static Chat getChat(){
        return chat;
    }


    // ------------------------------ Database OPS ------------------------------

    //Tags
    public static int getRankBedWars(String player) throws NoUserFound
    {
        try {
            PreparedStatement st = DatabaseConnector.con.prepareStatement("SELECT lvlTbl.ID FROM (SELECT NAME, BEDWARS, CONCAT(row_number() OVER(ORDER BY BEDWARS DESC)) AS 'ID' FROM " + DatabaseConnector.points +    ") as lvlTbl WHERE lvlTbl.NAME='" + player + "'");
            System.out.println("SELECT lvlTbl.ID FROM (SELECT NAME, BEDWARS, CONCAT(row_number() OVER(ORDER BY BEDWARS DESC)) AS 'ID' FROM " + DatabaseConnector.points +    ") as lvlTbl WHERE lvlTbl.NAME='" + player + "'");
            ResultSet rs = st.executeQuery();
            if (rs.next())
                return rs.getInt("ID");
            throw new NoUserFound();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NoUserFound();
        }
    }

    //TODO: Create a minon & hook the db
    public static int getRankParkour(String player) throws NoUserFound
    {
        return 0;
    }

    public static int getRankLevel(String player) throws NoUserFound
    {
        try {
            PreparedStatement st = DatabaseConnector.con.prepareStatement("SELECT lvlTbl.ID FROM (SELECT NAME, XP, CONCAT(row_number() OVER(ORDER BY XP DESC)) AS 'ID' FROM " + DatabaseConnector.levels +    ") as lvlTbl WHERE lvlTbl.NAME='" + player + "'");
            ResultSet rs = st.executeQuery();
            if (rs.next())
                return rs.getInt("ID");
            throw new NoUserFound();
        } catch (SQLException e) {
            throw new NoUserFound();
        }
    }

    public static int getTotalRanksBedWars()
    {
        try {
            PreparedStatement st = DatabaseConnector.con.prepareStatement("SELECT COUNT(*) FROM " + DatabaseConnector.points + " WHERE BEDWARS != 0");
            System.out.println("SELECT COUNT(*) FROM " + DatabaseConnector.points + " WHERE BEDWARS != 0");
            ResultSet rs = st.executeQuery();
            if (rs.next())
                return rs.getInt("COUNT(*)");
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NoUserFound();
        }
    }

    //TODO: Create a minon & hook the db
    public static int getTotalRanksParkour()
    {
        return 0;
    }

    public static int getTotalRanksLevel()
    {
        try {
            PreparedStatement st = DatabaseConnector.con.prepareStatement("SELECT COUNT(*) FROM " + DatabaseConnector.levels);
            ResultSet rs = st.executeQuery();
            if (rs.next())
                return rs.getInt("COUNT(*)");
            return 0;
        } catch (SQLException e) {
            throw new NoUserFound();
        }
    }

    public static String getTagGlobally(String player)
    {
        try {
            PreparedStatement st = DatabaseConnector.con.prepareStatement("SELECT COUNT(*) FROM " + DatabaseConnector.tags + " WHERE NAME = '" + player + "'");
            ResultSet rs = st.executeQuery();
            if (rs.next())
            {
                if (rs.getInt("COUNT(*)") == 1)
                {
                    st = DatabaseConnector.con.prepareStatement("SELECT TAG FROM " + DatabaseConnector.tags + " WHERE NAME = '" + player + "'");
                    rs = st.executeQuery();
                    if (rs.next())
                    {
                        String output = rs.getString("TAG");
                        System.out.println(output);
                        return output;
                    }
                }
            }
            return "";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void setTagGlobally(String player, String tag)
    {
        try {
            PreparedStatement st = DatabaseConnector.con.prepareStatement("SELECT COUNT(*) FROM " + DatabaseConnector.tags + " WHERE NAME = '" + player + "'");
            ResultSet rs = st.executeQuery();
            if (rs.next())
            {
                if (rs.getInt("COUNT(*)") == 1)
                    st = DatabaseConnector.con.prepareStatement("UPDATE " + DatabaseConnector.tags + " SET TAG='" + tag + "' WHERE NAME='" + player + "'");
                else
                    st = DatabaseConnector.con.prepareStatement("INSERT INTO " + DatabaseConnector.tags + " VALUES('" + player + "', '" + tag + "')");

                st.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
