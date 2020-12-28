package me.fishergee.peppermintpickaxe.managers;

import org.bukkit.entity.Player;

import java.util.HashSet;

public class PlayerTaskManager {

    HashSet<Player> playersOnCooldown;

    public PlayerTaskManager(){
        playersOnCooldown = new HashSet<Player>();
    }

    public void addPlayer(Player p){
        playersOnCooldown.add(p);
    }

    public void removePlayer(Player p){
        playersOnCooldown.remove(p);
    }

    public boolean isPlayerCooldown(Player p){
        if(playersOnCooldown.contains(p)){
            return true;
        }
        return false;
    }
}
