package com.me.keiko.teammanagerplugins.commands;

import com.me.keiko.teammanagerplugins.TeamManagerPlugins;
import com.me.keiko.teammanagerplugins.file.CustomConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestMessage implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if(sender instanceof Player) {
            Player p = (Player) sender;
            p.sendMessage(TeamManagerPlugins.getPlugins().getConfig().getString("Groups.member.permissions"));
        }

        return true;
    }
}
