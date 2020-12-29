package me.fishergee.peppermintpickaxe;

import me.fishergee.peppermintpickaxe.cmds.PeppermintCmd;
import me.fishergee.peppermintpickaxe.listeners.PlayerInteractListener;
import me.fishergee.peppermintpickaxe.managers.PlayerTaskManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    public static Core plugin;

    private PlayerTaskManager playerTaskManager;

    @Override
    public void onEnable(){
        this.plugin = this;
        playerTaskManager = new PlayerTaskManager();
    }

    private void registerListeners(){
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(playerTaskManager), this);
    }

    private void registerCmds(){
        getCommand("peppermint").setExecutor(new PeppermintCmd());

    }

    public PlayerTaskManager getPlayerTaskManager(){
        return playerTaskManager;
    }

}
