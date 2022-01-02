package com.me.keiko.teammanagerplugins.menu;

import com.me.keiko.teammanagerplugins.TeamManagerPlugins;
import com.me.keiko.teammanagerplugins.systems.Menu;
import com.me.keiko.teammanagerplugins.systems.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;


import static org.bukkit.Material.PLAYER_HEAD;

public class TeamMemberMenu extends Menu {

    public TeamMemberMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return ChatColor.translateAlternateColorCodes
                ( '§' , TeamManagerPlugins.getPlugins().getConfig().getString("message.TeamMemberMenu_Config"));
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {



    }

    @Override
    public void setMenuItems() {

     new TeamMemberMenu(playerMenuUtility);
     Player p = playerMenuUtility.getOwner();

        for(int i = 0; i < TeamManagerPlugins.getTeamMemberList(p).size(); i++) {
            inventory.setItem(i, makeItem(PLAYER_HEAD,
                    TeamManagerPlugins.getTeamMemberList(p).get(i).getDisplayName(),
                    ChatColor.RED + "Player Health :" + ChatColor.GOLD + TeamManagerPlugins.getTeamMemberList(p).get(i).getHealth(),
                    ChatColor.GRAY + "Player Level :" + ChatColor.GOLD + TeamManagerPlugins.getTeamMemberList(p).get(i).getLevel()));
        }

    }
}
