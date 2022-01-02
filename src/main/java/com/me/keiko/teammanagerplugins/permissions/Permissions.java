package com.me.keiko.teammanagerplugins.permissions;

import com.me.keiko.teammanagerplugins.TeamManagerPlugins;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.HashMap;

import java.util.UUID;

public class Permissions {


    public static HashMap<UUID, PermissionAttachment> leaderPermission = new HashMap<>();



    public static void setLeaderPermission(Player p) {
        PermissionAttachment attachment = p.addAttachment(TeamManagerPlugins.getPlugins());
        leaderPermission.put(p.getUniqueId(), attachment);
        leaderPermissionSetter(p.getUniqueId());
    }

    public static void removeLeaderPermission(Player p) {
        setLeaderPermissionRemover(p.getUniqueId());
        leaderPermission.remove(p.getUniqueId());

    }                                                               
                                                                                                                                                                            

    public static void leaderPermissionSetter(UUID uuid) {

        PermissionAttachment attachment =  leaderPermission.get(uuid);

        for (String groups : TeamManagerPlugins.getPlugins().getConfig().getConfigurationSection("Groups").getKeys(false)){
            for (String permissions : TeamManagerPlugins.getPlugins().getConfig().getStringList("Groups." + groups + ".permissions")){
                attachment.setPermission(permissions, true);
            }
        }
    }

    public static void setLeaderPermissionRemover(UUID uuid) {
        PermissionAttachment attachment = leaderPermission.get(uuid);
        for (String groups : TeamManagerPlugins.getPlugins().getConfig().getConfigurationSection("Groups").getKeys(true)){
            for (String permissions : TeamManagerPlugins.getPlugins().getConfig().getStringList("Groups." + groups + ".permissions")){
                attachment.setPermission(permissions, false);
            }
        }

    }

}
