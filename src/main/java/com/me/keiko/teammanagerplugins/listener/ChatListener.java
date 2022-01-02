package com.me.keiko.teammanagerplugins.listener;

import com.me.keiko.teammanagerplugins.TeamManagerPlugins;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

//    @EventHandler
    public void chatListener(AsyncPlayerChatEvent event){

        Player p = event.getPlayer();

        if(p.hasPermission("Team.Channel")){
            event.setCancelled(true);
            String S = event.getMessage();

            for (int i = 0; i < TeamManagerPlugins.getTeamMemberList(p).size(); i++){
                Player player = TeamManagerPlugins.getTeamMemberList(p).get(i);
                player.sendMessage(ChatColor.GREEN + ("【隊伍頻道】: ") + ChatColor.WHITE + S);
            }
        }
    }

}
