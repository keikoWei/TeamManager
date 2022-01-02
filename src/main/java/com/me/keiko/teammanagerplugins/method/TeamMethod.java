package com.me.keiko.teammanagerplugins.method;

import com.me.keiko.teammanagerplugins.permissions.Permissions;
import com.me.keiko.teammanagerplugins.TeamManagerPlugins;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class TeamMethod {


    public static void creat(Player p) {

        if(TeamManagerPlugins.serverPlayerInTeamMap.containsKey(p)){
            p.sendMessage(ChatColor.RED +"你已經有隊伍 !");

        }else {
            TeamManagerPlugins.getTeam(p).put(p,"Team_Leader");
            TeamManagerPlugins.serverPlayerInTeamMap.put(p, TeamManagerPlugins.getTeam(p));
            TeamManagerPlugins.getTeamMemberList(p).add(p);

            //設置隊長權限
            Permissions.setLeaderPermission(p);

            p.sendMessage(ChatColor.GOLD + "你創建了一個隊伍");
        }
    }

    //隊長邀請玩家
    public static void invite(Player p, Player inviteP) {

        if(TeamManagerPlugins.serverPlayerInTeamMap.containsKey(p)) {
            if(TeamManagerPlugins.serverPlayerInTeamMap.containsKey(inviteP)){
                if(TeamManagerPlugins.getTeamMemberList(p).size() == 4){
                    p.sendMessage(ChatColor.RED + "隊伍已滿 !");
                    return;
                }
                p.sendMessage(ChatColor.RED + "你邀請的玩家已經有隊伍了 !");
                inviteP.sendMessage(ChatColor.RED + "你已經有隊伍 !");


            }else {
                TeamManagerPlugins.getTeam(p).put(inviteP, "Team_Member");
                TeamManagerPlugins.serverPlayerInTeamMap.put(inviteP, TeamManagerPlugins.getTeam(p));
                TeamManagerPlugins.getTeamMemberList(p).add(inviteP);
                p.sendMessage(ChatColor.GOLD + "邀請成功 !");
                inviteP.sendMessage(ChatColor.GOLD + "你加入了一個隊伍!");
                teamMemberJoinMessage(inviteP);
                }
            }else {
            p.sendMessage(ChatColor.RED + "你必須先創建一個隊伍!");
        }
    }


    //玩家離開隊伍
    public static void leave(Player p) {

        if(TeamManagerPlugins.serverPlayerInTeamMap.containsKey(p)){

            teamMemberLeaveMessage(p);

            TeamManagerPlugins.serverPlayerInTeamMap.remove(p);
            TeamManagerPlugins.getTeam(p).remove(p);
            TeamManagerPlugins.getTeamMemberList(p).remove(p);

            //移除隊長權限
            Permissions.removeLeaderPermission(p);

            p.sendMessage(ChatColor.RED + "你離開了隊伍 !");
        }
        else {
            p.sendMessage(ChatColor.RED + "你沒有隊伍 !");
        }
    }

    public static void information(Player p){

        for (int i = 0; i < TeamManagerPlugins.getPlugins().getConfig().getList("message.TeamInformation").size(); i++){
            p.sendMessage(TeamManagerPlugins.getPlugins().getConfig().getList("message.TeamInformation").get(i).toString());
        }
    }


    //交換隊長
    public static void changeTeamLeader(Player p, Player changeP) {
        if(p.hasPermission("Team.Leader")){

            //移除原來隊長權限
            Permissions.removeLeaderPermission(p);
            p.sendMessage(ChatColor.RED + "隊長權限轉移 !" + ChatColor.YELLOW + "你不再是隊伍隊長" );
            //增加轉移隊員隊長權限
            Permissions.setLeaderPermission(changeP);
            changeP.sendMessage(ChatColor.RED + "隊長權限轉移 !" + ChatColor.YELLOW + "你現在是隊伍隊長");

        }else {
            p.sendMessage(ChatColor.RED + "你不是隊長 !" + ChatColor.YELLOW + "無法轉移隊長權限");
        }
    }


    //隊伍玩家加入訊息,必須寫在最'後面'
    public static void teamMemberJoinMessage(Player joinPlayer) {
        if(TeamManagerPlugins.getTeamMemberList(joinPlayer).size() > 0){
            for (int i = 0; i < TeamManagerPlugins.getTeamMemberList(joinPlayer).size(); i++) {
              Player p = TeamManagerPlugins.getTeamMemberList(joinPlayer).get(i);
                p.sendMessage(ChatColor.GOLD + joinPlayer.getName() + ChatColor.GREEN + "已經加入隊伍了!");
            }
        }

    }

    //隊伍玩家離隊訊息,必須寫在最'前面'
    public static void teamMemberLeaveMessage(Player leavePlayer) {

        if(TeamManagerPlugins.getTeamMemberList(leavePlayer).size() > 0){
            for (int i = 0; i < TeamManagerPlugins.getTeamMemberList(leavePlayer).size(); i++){
                Player p = TeamManagerPlugins.getTeamMemberList(leavePlayer).get(i);
                p.sendMessage(ChatColor.GOLD + leavePlayer.getName() + ChatColor.GREEN + "已經離開隊伍了!");
            }
        }
    }

    //隊伍玩家踢出
    public static void kick(Player p, Player kickP) {

        if(p.hasPermission("Team.Leader")){
            if(TeamManagerPlugins.getTeamMemberList(p).contains(kickP)){

                if(TeamManagerPlugins.getTeamMemberList(kickP).size() > 0){
                    for (int i = 0; i < TeamManagerPlugins.getTeamMemberList(kickP).size(); i++){
                        p = TeamManagerPlugins.getTeamMemberList(kickP).get(i);
                        p.sendMessage(ChatColor.GOLD + kickP.getName() + ChatColor.GREEN + "已經被踢出隊伍!");
                    }
                }

                TeamManagerPlugins.getTeamMemberList(p).remove(kickP);
                TeamManagerPlugins.serverPlayerInTeamMap.remove(kickP);
                TeamManagerPlugins.getTeam(p).remove(kickP);
                kickP.sendMessage(ChatColor.RED + "你被請離了隊伍");

            }else {
                p.sendMessage(ChatColor.RED + "你所要剃除的玩家不再隊伍中");
            }
        }else {
            p.sendMessage(ChatColor.RED + "你不是隊伍的隊長");

        }
    }


    public static void teamChannel(AsyncPlayerChatEvent e) {
      
    }
}
