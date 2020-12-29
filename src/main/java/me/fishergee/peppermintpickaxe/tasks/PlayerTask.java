package me.fishergee.peppermintpickaxe.tasks;

import me.fishergee.peppermintpickaxe.Core;
import me.fishergee.peppermintpickaxe.managers.PlayerTaskManager;
import net.md_5.bungee.api.ChatColor;import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerTask{

    private Player player;
    private Plugin plugin;
    private boolean isCooldown;
    private PlayerTaskManager playerTaskManager;

    public PlayerTask(Player player, PlayerTaskManager playerTaskManager){
        this.player = player;
        this.plugin = Core.plugin;
        isCooldown = false;
        this.playerTaskManager = playerTaskManager;
    }

    public Player getPlayer(){
        return player;
    }

    public boolean start(){
        if (player.hasPotionEffect(PotionEffectType.FAST_DIGGING)){
           return false;
        }

        if (isCooldown == false) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20 * 120, 3));
            isCooldown = true;

            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                
                //when the potion effect goes off.
                isCooldown = false;
                playerTaskManager.removePlayer(this);

                player.sendMessage(ChatColor.GOLD + "Your peppermint effect has ended.");
            }, 20 * 100);

            return true;
        }

        return false;
    }
}
