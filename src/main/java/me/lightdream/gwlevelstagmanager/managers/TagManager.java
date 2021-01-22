package me.lightdream.gwlevelstagmanager.managers;

import me.lightdream.gwlevels.Gwlevels;

import java.util.Arrays;
import java.util.List;

public class TagManager {
        /*
    BedWars:
    SILVER (I,II,III,MASTER,ELITE)
    GOLD (I,II,III,MASTER,ELITE)
    LEGENDARY (I,II,III)
    GLOBAL

    Parkour:
    AMATOR (I,II,III)
    MAESTRU (I,II,III)
    CAMPION (I,II,III)
    LEGENDA

    Level:
    INCEPATOR (I,II,III)
    MEDIOCRU (I,II,III)
    EXPERIMENTAT (I,II,III)
    EXPERT
     */

    private static final List<String> BedWarsTags = Arrays.asList("&8[&7SILVER I&8]", "&8[&7SILVER II&8]", "&8[&7SILVER III&8]", "&8[&7SILVER MASTER&8]",
            "&8[&7SILVER ELITE&8]", "&8[&eGOLD I&8]", "&8[&eGOLD II&8]", "&8[&eGOLD III&8]", "&8[&eGOLD MASTER&8]", "&8[&eGOLD ELITE&8]", "&8[&6LEGENDARY I&8]",
            "&8[&6LEGENDARY II&8]", "&8[&6LEGENDARY III&8]", "&8[&cGLOBAL&8]");
    private static final List<String> ParkourTags = Arrays.asList("&8[&7AMATOR I&8]", "&8[&7AMATOR II&8]", "&8[&7AMATOR III&8]", "&8[&eMAESTRU I&8]",
            "&8[&eMAESTRU II&8]", "&8[&eMAESTRU III&8]", "&8[&6CAMPION I&8]", "&8[&6CAMPION II&8]", "&8[&6CAMPION III&8]", "&8[&cLEGENDA&8]");
    private static final List<String> LevelTags = Arrays.asList("&8[&7INCEPATOR I&8]", "&8[&7INCEPATOR II&8]", "&8[&7INCEPATOR III&8]", "&8[&eMEDIOCRU I&8]",
            "&8[&eMEDIOCRU II&8]", "&8[&eMEDIOCRU III&8]", "&8[&6EXPERIMENTAT I&8]", "&8[&6EXPERIMENTAT II&8]", "&8[&6EXPERIMENTAT III&8]", "&8[&cEXPERT&8]");

    public static String getBedWarsRank (int index, int total)
    {
        //System.out.println((int)Math.ceil((100.0-(index * 1.0/total*250.0))/100.0*BedWarsTags.size()));
        //return BedWarsTags.get((int)Math.ceil((100.0-(index * 1.0/total*250.0))/100.0*BedWarsTags.size()));
        return ParkourTags.get(index - 1);
    }

    public static String getParkourRank (int index, int total)
    {
        return ParkourTags.get(index - 1);
    }

    public static String getLevelRank (String player)
    {
        return LevelTags.get((int)Math.ceil((100.0-(Gwlevels.getRankLevel(player) * 1.0/Gwlevels.getTotalRanksLevel()*100.0))/100.0*LevelTags.size()));
    }

    public static String getRank(String type, String player)
    {
        switch (type)
        {
            case "Level":
                return getLevelRank(player);
            case "BedWars":
                break;
            case "Parkour":
                break;
        }
        return "";
    }

}
