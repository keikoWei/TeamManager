package com.me.keiko.teammanagerplugins.commands;

import com.me.keiko.teammanagerplugins.menu.TeamMenu;
import com.me.keiko.teammanagerplugins.method.TeamMethod;
import com.me.keiko.teammanagerplugins.TeamManagerPlugins;

import com.me.keiko.teammanagerplugins.permissions.TeamChannelPermissions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class TeamCommand implements CommandExecutor {

    private static TeamMethod teamMethod;


    //創建隊伍選單(first)
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if(sender instanceof Player) {
            if(args.length == 0){

                TeamMenu teamMenu = new TeamMenu(TeamManagerPlugins.getPlayerMenuUtility(player));
                teamMenu.open();

            }else if(args.length == 1 && args[0].equalsIgnoreCase("creat")) {
                TeamMethod.creat(player);

            //invite command
            }else if(args.length == 2 && args[0].equalsIgnoreCase("invite")){
                 Player inviteP = Bukkit.getPlayer(args[1]);
                 TeamMethod.invite(player, inviteP);

            //leave command
            }else if(args.length == 1 && args[0].equalsIgnoreCase("leave")){
                TeamMethod.leave(player);

            //channel command
            }else if (args.length == 1 && args[0].equalsIgnoreCase("ch")){

                if(TeamManagerPlugins.serverPlayerInTeamMap.containsKey(player)){

                    if (player.hasPermission("Team.Channel")){
                        TeamChannelPermissions.removeChannelPermission(player);

                    }else {
                        TeamChannelPermissions.setChannelPermission(player);
                    }

                }else {
                    player.sendMessage(ChatColor.RED + "你沒有隊伍 !");
                }

            }
        }
        return true;
    }

}
