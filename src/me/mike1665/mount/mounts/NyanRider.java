package me.mike1665.mount.mounts;

import java.util.Iterator;

import me.mike1665.Main.Main;
import me.mike1665.mount.MountManager;
import me.mike1665.utils.UtilityBlock;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class NyanRider
  implements Listener
{
  public static void playAngelRider(Player p)
  {
      MountManager.removeCurrentPet(p, false);

      Horse horse = (Horse)p.getWorld().spawn(p.getLocation(), 
        Horse.class);
      Entity entity = horse;
      Horse entityHorse = (Horse)entity;
      entityHorse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
      horse.setCustomName(ChatColor.AQUA + "" + ChatColor.BOLD + p.getPlayer().getName() + ChatColor.RESET + "'s Horse");
      horse.setCustomNameVisible(true);
      horse.setOwner(p);
      horse.setVariant(Horse.Variant.DONKEY);
      horse.setAdult();
      horse.setPassenger(p);

      horse.setMetadata("nyanrider", new FixedMetadataValue(
      Main.schedule, "nyanrider"));
      MountManager.pet.put(p.getUniqueId(), horse);
      //UtilPet.makePet(horse, p.getUniqueId());
    }

  @EventHandler
  public void onPlayerMove(PlayerMoveEvent event) {
    if ((event.getFrom().getBlockX() != event.getTo().getBlockX()) || 
      (event.getFrom().getBlockZ() != event.getTo().getBlockZ())) {
      Player p = event.getPlayer();
      Entity e = p.getVehicle();

      if ((e instanceof Horse))
      {
        if (e.hasMetadata("nyanrider")) {
            byte color = 14;
            double r = Math.random();
            if (r > 0.8D)
              color = 2;
            else if (r > 0.6D)
              color = 4;
            else if (r > 0.2D)
              color = 5;
            else if (r > 0.4D)
              color = 6;
            else if (r > 0.8D)
              color = 10;
            else if (r > 0.1D) {
              color = 1;
            }

            Iterator localIterator = UtilityBlock.getInRadius(
              ((Horse)MountManager.pet.get(p.getUniqueId())).getLocation(), 2.5D, true)
              .keySet().iterator();

            while (localIterator.hasNext())
            {
              Block block = (Block)localIterator.next();
              if (UtilityBlock.solid(block))
              {
                if (!UtilityBlock.blockToRestore.contains(block))
                  UtilityBlock.setBlockToRestore(block, 35, 
                    color, 2L, true, false, false);
              }
            }
          }
        }
      }
    }
  }