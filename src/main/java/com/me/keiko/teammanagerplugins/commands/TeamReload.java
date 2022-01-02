package com.me.keiko.teammanagerplugins.commands;

import com.me.keiko.teammanagerplugins.file.CustomConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TeamReload implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        CustomConfig.reload();

        return true;
    }
}
