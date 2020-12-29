package me.fishergee.peppermintpickaxe.listeners;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTEntity;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.NBTTileEntity;
import me.fishergee.peppermintpickaxe.managers.PlayerTaskManager;
import me.fishergee.peppermintpickaxe.tasks.PlayerTask;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractListener implements Listener {
    
    private PlayerTaskManager playerTaskManager;
    
    public PlayerInteractListener(PlayerTaskManager playerTaskManager){
        this.playerTaskManager = playerTaskManager;    
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        Action a = e.getAction();

        if(!a.equals(Action.RIGHT_CLICK_BLOCK) && !a.equals(Action.RIGHT_CLICK_AIR)){
            return;
        }

        if(e.getHand() != EquipmentSlot.HAND){
            return;
        }

        ItemStack itemClicked = e.getPlayer().getInventory().getItemInMainHand();

        NBTItem nbtItem = new NBTItem(itemClicked);

        if(!nbtItem.getString("id").equals("peppermint")){
            return;
        }


        Player p = e.getPlayer();
        int sugarAmount = 0;

        for (ItemStack item : p.getInventory()) {
            if(item == null){
                continue;
            }

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
                PlayerTask playerTask = new PlayerTask(p, playerTaskManager);

                playerTask.start();

                p.getInventory().removeItem(new ItemStack(Material.SUGAR, 16));

                playerTaskManager.addPlayer(playerTask);

                p.sendMessage(ChatColor.GREEN + "You have activated the peppermint effect!");
                return;
            }
        }else{
            p.sendMessage(ChatColor.RED + "You do not have enough sugar (16)!");
            return;
        }
    }
}
