package com.me.keiko.teammanagerplugins.permissions;

import com.me.keiko.teammanagerplugins.TeamManagerPlugins;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.HashMap;
import java.util.UUID;

public class TeamChannelPermissions {

    public static HashMap<UUID, PermissionAttachment> channelPermission = new HashMap<>();


    public static void setChannelPermission(Player p){
        PermissionAttachment attachment = p.addAttachment(TeamManagerPlugins.getPlugins());
        channelPermission.put(p.getUniqueId(), attachment);
        channelPermissionSetter(p.getUniqueId());
        p.sendMessage( ChatColor.GOLD + "隊伍頻道已開啟 !");
    }

    public static void removeChannelPermission(Player p){
        setChannelPermissionRemover(p.getUniqueId());
        channelPermission.remove(p.getUniqueId());
        p.sendMessage( ChatColor.GOLD + "隊伍頻道已關閉 !");
    }

    public static void channelPermissionSetter(UUID uuid) {

        PermissionAttachment attachment = channelPermission.get(uuid);

        for (String channels : TeamManagerPlugins.getPlugins().getConfig().getConfigurationSection("Channel").getKeys(false)){
            for (String permissions : TeamManagerPlugins.getPlugins().getConfig().getStringList("Channel." + channels + ".permissions")){
                    attachment.setPermission(permissions, true);

            }
        }
    }

    public static void setChannelPermissionRemover(UUID uuid) {
        PermissionAttachment attachment = channelPermission.get(uuid);
        for (String channels : TeamManagerPlugins.getPlugins().getConfig().getConfigurationSection("Channel").getKeys(true)){
            for (String permissions : TeamManagerPlugins.getPlugins().getConfig().getStringList("Channel." + channels + ".permissions")){
                attachment.setPermission(permissions, false);

            }
        }

    }


}
