package me.fishergee.peppermintpickaxe.listeners;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
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

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        Action a = e.getAction();

        if(!a.equals(Action.RIGHT_CLICK_BLOCK) || !a.equals(Action.RIGHT_CLICK_AIR)){
            return;
        }

        ItemStack itemClicked = e.getItem();

        NBTCompound nbtCompound = NBTItem.convertItemtoNBT(itemClicked);

        if(nbtCompound.hasKey("id")){
            if(!nbtCompound.getString("id").equalsIgnoreCase("peppermint")){
                return;
            }
        }else{
            return;
        }

        Player p = e.getPlayer();

        //List<ItemStack> playerSugar = new ArrayList<ItemStack>();

        
        for(ItemStack item : p.getInventory()){
            if(item.getType().equals(Material.SUGAR)){
                if(item.getAmount() > 16){
                    item.setAmount(item.getAmount() - 16);
                }
            }
        }



    }
}
