package me.fishergee.peppermintpickaxe.managers;

import me.fishergee.peppermintpickaxe.tasks.PlayerTask;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class PlayerTaskManager {

    Set<PlayerTask> playersOnCooldown;

    public PlayerTaskManager() {
        playersOnCooldown = new HashSet<PlayerTask>();
    }

    public boolean addPlayer(PlayerTask p) {
        if (!playerExists(p)) {
            playersOnCooldown.add(p);
            return true;
        }

        return false;
    }

    public boolean removePlayer(PlayerTask p) {
        if (playerExists(p)) {
            playersOnCooldown.remove(p);
            return true;
        }

        return false;
    }

    public boolean playerExists(PlayerTask p) {
        return playersOnCooldown.contains(p);
    }

    public boolean isPlayerCooldown(Player p) {
        System.out.println(playersOnCooldown.size());
        for(PlayerTask pt : playersOnCooldown){
            if(pt.getPlayerUUID().equals(p.getUniqueId())){
                return true;
            }
        }
        return false;
    }
}
