package me.fishergee.peppermintpickaxe.tasks;

import me.fishergee.peppermintpickaxe.Core;
import me.fishergee.peppermintpickaxe.managers.PlayerTaskManager;
import net.md_5.bungee.api.ChatColor;import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class PlayerTask{

    private UUID playerUUID;
    private Player player;
    private Plugin plugin;
    private PlayerTaskManager playerTaskManager;

    /*
    Player object goes null after player leaves.
     */
    public PlayerTask(Player player,  PlayerTaskManager playerTaskManager){
        this.playerUUID = player.getUniqueId();
        this.player = player;
        this.plugin = Core.plugin;
        this.playerTaskManager = playerTaskManager;
    }

    public UUID getPlayerUUID(){
        return playerUUID;
    }

    public void start(){
        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20 * 120, 3));

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {

            //when the potion effect goes off.
            playerTaskManager.removePlayer(this);

            player.sendMessage(ChatColor.GOLD + "Your peppermint effect has ended.");
        }, 20 * 121);

    }
}
