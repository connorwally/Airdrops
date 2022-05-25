package me.wally.airdrops;

import de.slikey.effectlib.effect.VortexEffect;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import de.slikey.effectlib.EffectManager;

public class AirdropEvent implements Listener {

    private final Airdrops airdrops;
    private final EffectManager effectmanager;

    public AirdropEvent(Airdrops airdrops, EffectManager effectmanager) {
        this.airdrops = airdrops;
        this.effectmanager = effectmanager;
    }

    @EventHandler
    public void onRightClickAirdrop(PlayerInteractEvent event) {

        //Store the player variable
        Player player = event.getPlayer();

        //If the player right clicks and is holding the airdrop item
        if (player.getInventory().getItemInMainHand().getType() == Material.NETHER_STAR) {
            if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "" + ChatColor.BOLD + "AIRDROP")) {
                if (event.getAction() == Action.RIGHT_CLICK_AIR) {

                    //Store the location where the player tried to summon an airdrop
                    Location loc = player.getLocation();
                    Location particlelocation = player.getLocation().add(0,30,0);
                    particlelocation.setPitch(90);

                    //Block spawning if there is a block in the way
                    if (loc.getBlock().getType() != Material.AIR) {
                        player.sendMessage(ChatColor.RED + "Cannot drop a chest here.");
                        return;
                    }

                    //If everything is okay, inform the player
                    player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "An airdrop will land in 10 seconds");
                    //Remove the item from player inventory
                    player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);

                    VortexEffect vortex = new VortexEffect(effectmanager);
                    vortex.setLocation(particlelocation);
                    vortex.iterations = 10*20;
                    vortex.start();

                    //Wait 200 ticks and then spawn the chest
                    Bukkit.getScheduler().runTaskLater(airdrops, () -> {

                        ItemStack[] items = {new ItemStack(Material.DIAMOND, 8),
                                new ItemStack(Material.IRON_INGOT, 16),
                                new ItemStack(Material.EMERALD, 8),
                                new ItemStack(Material.LEATHER, 8)};

                        Block block = loc.getBlock();
                        block.setType(Material.CHEST);
                        Chest chest = (Chest) block.getState();
                        chest.getInventory().setContents(items);
                    }, 200L);

                }
            }
        }

    }
}
