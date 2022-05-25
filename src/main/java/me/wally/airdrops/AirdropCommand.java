package me.wally.airdrops;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class AirdropCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //If a player sends the airdrop command
        if (label.equalsIgnoreCase("airdrop")) {
            if (sender instanceof Player) {
                if (sender.hasPermission("Airdrops.airdrop")) {
                    //Give the player the airdrop item
                    Player player = (Player) sender;
                    player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "You receive an Airdrop Item");
                    player.getInventory().addItem(getAirdrop());
                }
            }
        }
        return true;
    }

    public ItemStack getAirdrop(){
        //Create the airdrop itemstack for giving to the player
        ItemStack item = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "AIRDROP");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Right-click to start an airdrop at your location.");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}
