package me.lightdream.gwlevelstagmanager.managers;

import me.lightdream.gwlevels.DataHolder;
import me.lightdream.gwlevels.Gwlevels;
import me.lightdream.gwlevels.Utils;
import me.lightdream.gwlevels.exceptions.NoUserFound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CommandManager implements CommandExecutor {



    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("dev"))
        {
            Player player = (Player) sender;
            sender.sendMessage(Gwlevels.VERSION);

            //player.openInventory(Gwlevels.getTagManagerInventory(player));
            System.out.println(player.getName());
            Gwlevels.getRankLevel(player.getName());
            //Gwlevels.getChat().setPlayerSuffix(player, Utils.color(" &c[DEVELOPING]"));
            //System.out.println(Gwlevels.getChat().getPlayerSuffix(player));

            System.out.println(TagManager.getLevelRank(player.getName()));

           }

        //Moving to /gw admin
        if(label.equalsIgnoreCase("admin"))
        {
            if(sender.hasPermission("gw.admin"))
            {
                if(args.length == 0)
                {
                    sender.sendMessage("/admin addLevel [nume] [nivel]\n/admin setLevel [nume] [nivel]\n/admin addXP [nume] [xp]\n/admin setXP [nume] [xp]");
                }
                else {
                    if(args[0].equalsIgnoreCase("setLevel"))
                    {
                        if(args.length==3)
                        {
                            try {
                                Gwlevels.setLevel(args[1], Integer.parseInt(args[2]));
                            }
                            catch (NoUserFound e) {
                                sender.sendMessage(e.getMessage());
                            } catch ( NumberFormatException e) {
                                sender.sendMessage("The entered number is invalid");
                            }
                        }
                        else
                            sender.sendMessage("/admin setLevel [nume] [nivel]");

                    }
                    else if(args[0].equalsIgnoreCase("addLevel"))
                    {
                        if(args.length==3)
                        {
                            try {
                                Gwlevels.addLevel(args[1], Integer.parseInt(args[2]));
                            }
                            catch (NoUserFound e) {
                                sender.sendMessage(e.getMessage());
                            } catch ( NumberFormatException e) {
                                sender.sendMessage("The entered number is invalid");
                            }
                        }
                        else
                            sender.sendMessage("/admin addLevel [nume] [nivel]");
                    }
                    else if(args[0].equalsIgnoreCase("setXP"))
                    {
                        if(args.length==3)
                        {
                            try {
                                Gwlevels.setXP(args[1], Integer.parseInt(args[2]));
                            }
                            catch (NoUserFound e) {
                                sender.sendMessage(e.getMessage());
                            } catch ( NumberFormatException e) {
                                sender.sendMessage("The entered number is invalid");
                            }
                        }
                        else
                            sender.sendMessage("/admin setXP [nume] [xp]");
                    }
                    else if(args[0].equalsIgnoreCase("addXP"))
                    {
                        if(args.length==3)
                        {
                            try {
                                Gwlevels.addXP(args[1], Integer.parseInt(args[2]));
                            }
                            catch (NoUserFound e) {
                                sender.sendMessage(e.getMessage());
                            } catch ( NumberFormatException e) {
                                sender.sendMessage("The entered number is invalid");
                            }
                        }
                        else
                            sender.sendMessage("/admin addXP [nume] [xp]");
                    }
                    else if(args[0].equalsIgnoreCase("info"))
                    {
                        if(args.length==2)
                        {
                            try {
                                sender.sendMessage(Utils.color(Gwlevels.getInfo(args[1])));
                            }
                            catch (NoUserFound e) {
                                sender.sendMessage(e.getMessage());
                            }
                        }
                        else
                            sender.sendMessage("/admin addXP [nume] [xp]");
                    }
                    else if(args[0].equalsIgnoreCase("version"))
                    {
                        sender.sendMessage(Gwlevels.VERSION);
                    }
                }
            }
        }

        if(label.equalsIgnoreCase("gw"))
        {
            if(args.length == 0)
            {
                sender.sendMessage(Utils.color("&f/admin setLevel [nume]\n/admin addLevel [nume]\n/admin setXP [nume]\n/admin addXP [nume]\n/gw level\n/gw level top\n/gw level help"));
            }
            else
            {
                if(args[0].equalsIgnoreCase("level"))
                {
                    if(args.length == 1)
                    {
                        if(sender instanceof Player)
                            ((Player) sender).openInventory(Objects.requireNonNull(Gwlevels.getLevelInventory((Player) sender, 0)));
                        else
                            sender.sendMessage("Only players can use this command");
                    }
                    else if(args[1].equalsIgnoreCase("top"))
                    {
                        if(sender instanceof Player)
                            ((Player) sender).openInventory(DataHolder.getTopLevelInventory(sender.getName()));
                        else
                            sender.sendMessage("Only players can use this command");
                    }
                    else if(args[1].equalsIgnoreCase("help"))
                    {
                        if(sender instanceof Player)
                            ((Player) sender).openInventory(Gwlevels.getHelpInventory((Player) sender));
                        else
                            sender.sendMessage("Only players can use this command");
                    }
                }
                else if(args[0].equalsIgnoreCase("tag"))
                {
                    ((Player)sender).openInventory(Gwlevels.getTagManagerInventory((Player) sender));
                }

            }
        }
        return true;
    }

}
