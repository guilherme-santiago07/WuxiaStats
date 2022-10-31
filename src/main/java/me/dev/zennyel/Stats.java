package me.dev.zennyel;

import me.dev.zennyel.commands.SetStats;
import me.dev.zennyel.commands.StatsCommand;
import me.dev.zennyel.database.MySQL;
import me.dev.zennyel.listeners.PlayerListeners;
import me.dev.zennyel.stats.Cultivator;
import me.dev.zennyel.stats.CultivatorAPI;
import me.lemonypancakes.bukkit.common.actionbarapi.ActionBarAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Stats extends JavaPlugin {

    private static Stats instance;

    @Override
    public void onEnable() {
        instance = this;
        registerCommands();
        registerEvents();
        loadConfiguration();
        loadCultivators();
        MySQL.createTable();
    }

    @Override
    public void onDisable() {
         saveCultivators();
    }

    public void registerCommands(){
        getCommand("stats").setExecutor(new StatsCommand());
        getCommand("setstats").setExecutor(new SetStats());
    }

    public void registerEvents(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerListeners(), this);
    }

    public void loadConfiguration(){
        getConfig().options().copyDefaults(false);
        saveDefaultConfig();
    }

    public static Stats getInstance() {
        return instance;
    }

    public void loadCultivators(){
        for(Player player : Bukkit.getOnlinePlayers()){
            CultivatorAPI.putCultivator(player, MySQL.getCultivator(player));
        }
    }

    public void saveCultivators() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            MySQL.updateCultivator(player, CultivatorAPI.getCultivator(player));
        }
    }
    }



