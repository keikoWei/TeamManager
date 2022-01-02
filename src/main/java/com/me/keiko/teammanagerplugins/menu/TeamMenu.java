package com.me.keiko.teammanagerplugins.menu;

import com.me.keiko.teammanagerplugins.TeamManagerPlugins;
import com.me.keiko.teammanagerplugins.method.TeamMethod;
import com.me.keiko.teammanagerplugins.systems.Menu;
import com.me.keiko.teammanagerplugins.systems.PlayerMenuUtility;
import org.bukkit.ChatColor;


import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import static org.bukkit.Material.*;

public class TeamMenu extends Menu {
    public TeamMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return ChatColor.translateAlternateColorCodes
                ( '§' , TeamManagerPlugins.getPlugins().getConfig().getString("message.TeamMenu_Config"));
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
                TeamStatusMenu statusMenu = new TeamStatusMenu(TeamManagerPlugins.getPlayerMenuUtility(p));
                statusMenu.open();
                break;

            case SPRUCE_SIGN:
                p.closeInventory();
                if (p.hasPermission("Team.Leader")){
                    SettingMenu s = new SettingMenu(TeamManagerPlugins.getPlayerMenuUtility(p));
                    s.open();
                }else{
                    p.sendMessage(ChatColor.RED + "只有隊長才能操作這個選項");
                }
                break;

            case PLAYER_HEAD:
                if(p.hasPermission("Team.Leader")){
                    p.closeInventory();
                    KickMenu kickMenu = new KickMenu(TeamManagerPlugins.getPlayerMenuUtility(p));
                    kickMenu.open();
                }else {
                    p.sendMessage(ChatColor.RED + "只有隊長才能操作這個選項");
                }
                break;

            case TOTEM_OF_UNDYING:
                if(p.hasPermission("Team.Leader")){
                    p.closeInventory();
                    LeaderChangeMenu leaderChangeMenu = new LeaderChangeMenu(TeamManagerPlugins.getPlayerMenuUtility(p));
                    leaderChangeMenu.open();
                }else {
                    p.sendMessage(ChatColor.RED + "只有隊長才能操作這個選項");
                }

                break;

            case BARRIER :
                p.closeInventory();
                TeamMethod.leave(p);
                break;
        }
    }

    @Override
    public void setMenuItems() {

        //設定隊伍選單ICON
        inventory.setItem(0,  makeItem(PAPER,
                ChatColor.translateAlternateColorCodes
                        ( '§' ,TeamManagerPlugins.getPlugins().getConfig().getString("message.TeamInformationIcon")),
                ChatColor.GOLD + "你可以在這裡找到隊伍相關資訊..."));

        //設定隊伍設定ICON
        inventory.setItem(1, makeItem(SPRUCE_SIGN,
                ChatColor.translateAlternateColorCodes
                        ( '§' ,TeamManagerPlugins.getPlugins().getConfig().getString("message.SettingIcon")),
                ChatColor.GOLD + "你可以在這裡找到隊伍設定選項",
                ChatColor.RED + "(注意)"+ChatColor.GOLD + "只有隊長才可以執行設定 !"));

        //設定踢出隊伍成員ICON
        inventory.setItem(2,makeItem(PLAYER_HEAD,
                ChatColor.GREEN + "踢出成員選項",
                ChatColor.RED + "點擊選擇你要踢出的成員... "));

        //設定交換隊長Icon
        inventory.setItem(3,makeItem(TOTEM_OF_UNDYING,
                ChatColor.GREEN + "交換隊長選項",
                ChatColor.GOLD + "點擊選擇你要交付的隊員..."));

        //設定離開隊伍ICON
        inventory.setItem(8,makeItem(BARRIER,ChatColor.translateAlternateColorCodes
                ( '§' ,TeamManagerPlugins.getPlugins().getConfig().getString("message.ExitIcon"))
                ,ChatColor.GOLD + "確定要離開隊伍嗎?"));

        //設定黑玻璃ICON
        for (int i = 4 ; i < 8 ;  i++ ){
            inventory.setItem(i,makeItem(BLACK_STAINED_GLASS_PANE,ChatColor.translateAlternateColorCodes
                    ( '§' ,TeamManagerPlugins.getPlugins().getConfig().getString("message.NothingIcon"))));
        }



    }
}
