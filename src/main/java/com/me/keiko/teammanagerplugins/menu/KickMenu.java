package com.me.keiko.teammanagerplugins.menu;

import com.me.keiko.teammanagerplugins.TeamManagerPlugins;
import com.me.keiko.teammanagerplugins.method.TeamMethod;
import com.me.keiko.teammanagerplugins.permissions.Permissions;
import com.me.keiko.teammanagerplugins.systems.Menu;
import com.me.keiko.teammanagerplugins.systems.PlayerMenuUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;


import static org.bukkit.Material.PLAYER_HEAD;


public class KickMenu extends Menu {
    public KickMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return ChatColor.GREEN + "點擊踢出成員 ...";
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();
        String S = e.getCurrentItem().getItemMeta().getDisplayName();
        Player s  =  Bukkit.getPlayer(S);
        TeamMethod.kick(p, s);
        if(s.hasPermission("Team.Leader")){
            Permissions.removeLeaderPermission(s);
        }
        p.closeInventory();

    }

    @Override
    public void setMenuItems() {
        
        new KickMenu(playerMenuUtility);
        Player p = playerMenuUtility.getOwner();

        for(int i = 0; i < TeamManagerPlugins.getTeamMemberList(p).size(); i++) {
            inventory.setItem(i, makeItem(PLAYER_HEAD,
                    TeamManagerPlugins.getTeamMemberList(p).get(i).getDisplayName()));
                    
        }

    }

    
}
