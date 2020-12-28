package me.fishergee.peppermintpickaxe;

import me.fishergee.peppermintpickaxe.managers.PlayerTaskManager;
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

    }

    private void registerCmds(){

    }

    public PlayerTaskManager getPlayerTaskManager(){
        return playerTaskManager;
    }

}
