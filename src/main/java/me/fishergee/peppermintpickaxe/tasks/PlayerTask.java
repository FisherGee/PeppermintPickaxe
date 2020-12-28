package me.fishergee.peppermintpickaxe.tasks;

import me.fishergee.peppermintpickaxe.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerTask{

    Player player;
    Plugin plugin;
    boolean cooldown;

    public PlayerTask(Player player){
        this.player = player;
        this.plugin = Core.plugin;
        cooldown = false;
    }

    public void start(){
        if(cooldown == false){
            player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20 * 120, 3));
            cooldown = true;

            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){

                @Override
                public void run() {
                    cooldown = false;

                }
            }, 20 * 120);

        }
        else{
            player.sendMessage(ChatColor.RED + "You are currently on cooldown!");
        }
    }
}
