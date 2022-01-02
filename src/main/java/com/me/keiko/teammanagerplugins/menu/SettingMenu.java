package com.me.keiko.teammanagerplugins.menu;

import com.me.keiko.teammanagerplugins.TeamManagerPlugins;
import com.me.keiko.teammanagerplugins.systems.Menu;
import com.me.keiko.teammanagerplugins.systems.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

public class SettingMenu extends Menu {
    public SettingMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return ChatColor.translateAlternateColorCodes
                ( '§' , TeamManagerPlugins.getPlugins().getConfig().getString("message.TeamMemberMenu_Config"));
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {


    }

    @Override
    public void setMenuItems() {
        for (int i = 0; i < 9; i++) {
            inventory.setItem(i, makeItem(Material.BLACK_STAINED_GLASS_PANE, " "));
        }
        for (int i = 18; i < 27; i++) {
            inventory.setItem(i, makeItem(Material.BLACK_STAINED_GLASS_PANE, " "));
        }
        inventory.setItem(9, makeItem(Material.BLACK_STAINED_GLASS_PANE, " "));
        inventory.setItem(17, makeItem(Material.BLACK_STAINED_GLASS_PANE, " "));

        inventory.setItem(10,makeItem(Material.NAME_TAG,
                ChatColor.GOLD + "更改隊伍名稱",
                ChatColor.RED + "預設為無隊伍名稱"));
    }
}
