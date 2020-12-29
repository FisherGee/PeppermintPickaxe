package me.fishergee.peppermintpickaxe.listeners;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import me.fishergee.peppermintpickaxe.managers.PlayerTaskManager;
import me.fishergee.peppermintpickaxe.tasks.PlayerTask;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PlayerInteractListener implements Listener {
    
    private PlayerTaskManager playerTaskManager;
    
    public PlayerInteractListener(PlayerTaskManager playerTaskManager){
        this.playerTaskManager = playerTaskManager;    
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        Action a = e.getAction();

        if(!a.equals(Action.RIGHT_CLICK_BLOCK) || !a.equals(Action.RIGHT_CLICK_AIR)){
            return;
        }

        ItemStack itemClicked = e.getItem();

        NBTCompound nbtCompound = NBTItem.convertItemtoNBT(itemClicked);

        if(nbtCompound.hasKey("id")) {
            if(!nbtCompound.getString("id").equalsIgnoreCase("peppermint")) {
                return;
            }
        } else {
            return;
        }

        Player p = e.getPlayer();
        int sugarAmount = 0;

        
        for (ItemStack item : p.getInventory()) {
            if (item.getType().equals(Material.SUGAR)) {
                sugarAmount += item.getAmount();
            }
        }

        if(sugarAmount >= 16){
            if(playerTaskManager.isPlayerCooldown(p)){
                p.sendMessage(ChatColor.RED + "You are on cooldown.");
                return;
            }
            else{
                p.getInventory().remove(new ItemStack(Material.SUGAR, 16));
                PlayerTask playerTask = new PlayerTask(p, playerTaskManager);
                
                playerTaskManager.addPlayer(playerTask);
                playerTask.start();
                
                p.sendMessage(ChatColor.GREEN + "You have activated the peppermint effect!");
                return;
            }
        }else{
            p.sendMessage(ChatColor.RED + "You do not have enough sugar (16)!");
            return;
        }
    }
}
