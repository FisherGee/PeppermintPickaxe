package me.fishergee.peppermintpickaxe.cmds;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PeppermintCmd implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!cmd.getName().equalsIgnoreCase("peppermint")){
            return true;
        }

        Player p = (Player) sender;

        if(args.length == 1){
            if(args[0].equalsIgnoreCase("give")){

                ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
                ItemMeta im = pickaxe.getItemMeta();

                im.addEnchant(Enchantment.DIG_SPEED, 7, true);
                im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&clPEPPERMINT &f&lPICKAXE"));

                NBTItem peppermintNBT = new NBTItem(pickaxe);
                peppermintNBT.setString("id", "peppermint");

                if(p.getInventory().firstEmpty() == -1){
                    p.getWorld().dropItem(p.getLocation(), peppermintNBT.getItem());
                }else{
                    p.getInventory().addItem(peppermintNBT.getItem());
                }
            }
        }

        return true;
    }
}
