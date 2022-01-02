package com.me.keiko.teammanagerplugins;

import com.me.keiko.teammanagerplugins.commands.TeamReload;
import com.me.keiko.teammanagerplugins.commands.TestMessage;
import com.me.keiko.teammanagerplugins.file.CustomConfig;
import com.me.keiko.teammanagerplugins.commands.TeamCommand;

import com.me.keiko.teammanagerplugins.listener.ChatListener;
import com.me.keiko.teammanagerplugins.listener.MenuListener;
import com.me.keiko.teammanagerplugins.systems.PlayerMenuUtility;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

import static com.me.keiko.teammanagerplugins.permissions.Permissions.leaderPermission;


public final class TeamManagerPlugins extends JavaPlugin {

    public static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();

    public static HashMap<Player, HashMap> serverPlayerInTeamMap = new HashMap<>();

    public static HashMap<Player, String> Team = new HashMap<>(4);

    public static ArrayList<Player> teamMemberList = new ArrayList<>(4) ;


    private static TeamManagerPlugins plugins;


    @Override
    public void onEnable() {

        plugins = this;

        CustomConfig.setFile();
        CustomConfig.get().addDefault("ServerTeamList", "team list");
        CustomConfig.get().createSection("PlayersInTeam");
        CustomConfig.get().options().copyDefaults(true);
        CustomConfig.save();

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new MenuListener(), this);
        getServer().getPluginManager().registerEvents(new ChatListener(), this);

        getCommand("Team").setExecutor(new TeamCommand());
        getCommand("TestMessage").setExecutor(new TestMessage());
        getCommand("TeamReload").setExecutor(new TeamReload());
    }

    @Override
    public void onDisable() {
        leaderPermission.clear();
    }

    public static TeamManagerPlugins getPlugins() {
        return plugins;
    }


    public static PlayerMenuUtility getPlayerMenuUtility(Player p) {
        PlayerMenuUtility playerMenuUtility;

        if (playerMenuUtilityMap.containsKey(p)) {
            return playerMenuUtilityMap.get(p);
        } else {
            playerMenuUtility = new PlayerMenuUtility(p);
            playerMenuUtilityMap.put(p, playerMenuUtility);
            return playerMenuUtility;
        }
    }


    public static HashMap<Player, String> getTeam(Player p) {

        if(Team.containsKey(p)){
            return Team;
        }else {
            Team = new HashMap<Player,String>();
            Team.put(p, "Team_Leader");
            return Team;
        }
    }

    public static ArrayList<Player> getTeamMemberList(Player p) {

        if(teamMemberList.contains(p)){
            return teamMemberList;
        }else {
            teamMemberList = new ArrayList<>(4);
            return teamMemberList;
        }
    }



}


