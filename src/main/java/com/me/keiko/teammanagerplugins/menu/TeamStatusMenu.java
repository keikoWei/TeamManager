package com.me.keiko.teammanagerplugins.menu;

import com.me.keiko.teammanagerplugins.TeamManagerPlugins;
import com.me.keiko.teammanagerplugins.method.TeamMethod;
import com.me.keiko.teammanagerplugins.systems.Menu;
import com.me.keiko.teammanagerplugins.systems.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class TeamStatusMenu extends Menu {

    public TeamStatusMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "隊伍資訊";
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        switch (e.getCurrentItem().getType()){
            case PAPER:
                p.closeInventory();
                if(TeamManagerPlugins.serverPlayerInTeamMap.containsKey(p)){
                    TeamMethod.information(p);
                }else {
                    p.sendMessage(ChatColor.RED + "你沒有隊伍 !");
                }
                break;
            case PLAYER_HEAD:
                p.closeInventory();
                if(TeamManagerPlugins.serverPlayerInTeamMap.containsKey(p)){
                    TeamMemberMenu m = new TeamMemberMenu(TeamManagerPlugins.getPlayerMenuUtility(p));
                    m.open();
                }else {
                    p.sendMessage(ChatColor.RED + "你沒有隊伍 !");
                }
                break;
        }


    }

    @Override
    public void setMenuItems() {

        inventory.setItem(0,makeItem(Material.PAPER, ChatColor.GOLD + "隊伍資訊",
                ChatColor.DARK_GRAY + "點即獲得你的隊伍詳細資訊..."));

        inventory.setItem(1,makeItem(Material.PLAYER_HEAD, ChatColor.translateAlternateColorCodes
                ( '§' ,TeamManagerPlugins.getPlugins().getConfig().getString("message.TeamMemberIcon")),
                 ChatColor.DARK_GRAY + "點擊查看你的隊伍成員名單"));

        for (int i = 2; i < 9; i++){
            inventory.setItem(i, makeItem(Material.BLACK_STAINED_GLASS_PANE, " "));
        }

    }
}
