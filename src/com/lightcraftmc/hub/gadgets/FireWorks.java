package com.lightcraftmc.hub.gadgets;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import me.mike1665.ammo.FireWorksAmmoManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Builder;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;

import com.lightcraftmc.hub.main.Main;

import net.lightcraftmc.fusebox.util.strings.MessageType;
import net.lightcraftmc.fusebox.util.strings.StringManager;

public class FireWorks
  implements Listener
{
  public final Random random = new Random();
  private ArrayList<UUID> _coolDown = new ArrayList();
  
  @EventHandler(priority=EventPriority.HIGHEST)
  public void onPlayerPlaceBlock(PlayerInteractEvent event)
  {
    final Player player = event.getPlayer();
    if (player.getItemInHand() == null) {
      return;
    }
    if (player.getItemInHand().getType() != Material.FIREWORK) {
      return;
    }
    if (_coolDown.contains(player.getUniqueId())) {
      return;
    }
    if (((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)) && 
      (disName(player.getItemInHand()) != null) && (disName(player.getItemInHand()).equalsIgnoreCase(ChatColor.RED + "Fireworks " + ChatColor.DARK_RED + FireWorksAmmoManager.balaceFireWorkAmmo(player))))
    {
      event.setCancelled(true);
      _coolDown.add(player.getUniqueId());
      Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable()
      {
        public void run()
        {
          _coolDown.remove(player.getUniqueId());
        }
      }, 5L);
      
      Firework fw = (Firework)player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
      FireworkEffect.Builder builder = FireworkEffect.builder();
      FireworkMeta m = fw.getFireworkMeta();
      builder.trail(random.nextBoolean()).flicker(random.nextBoolean());
      builder.withColor(Color.fromRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
      builder.with(org.bukkit.FireworkEffect.Type.values()[random.nextInt(org.bukkit.FireworkEffect.Type.values().length)]);
      builder.withFade(Color.fromRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
      m.addEffect(builder.build());
      m.setPower(random.nextInt(3) + 1);
      fw.setFireworkMeta(m);
      event.setCancelled(true);
      
      FireWorksAmmoManager.takeFireWorkAmmo(player, 1);
      if (FireWorksAmmoManager.balaceFireWorkAmmo(player) < 1)
      {
        player.getInventory().setItemInHand(null);
		player.sendMessage(StringManager.getPrefix(MessageType.ERROR) + ChatColor.AQUA+ "You ran out of ammo!");
        return;
      }
      ItemStack snow = new ItemStack(Material.FIREWORK, 1);
      ItemMeta sno = snow.getItemMeta();
      sno.setDisplayName(ChatColor.RED + "Fireworks " + ChatColor.DARK_RED + FireWorksAmmoManager.balaceFireWorkAmmo(player));
      snow.setItemMeta(sno);
      player.getInventory().setItemInHand(snow);
    }
  }
  
  public String disName(ItemStack i)
  {
    if (i == null) {
      return null;
    }
    if (!i.hasItemMeta()) {
      return null;
    }
    if (!i.getItemMeta().hasDisplayName()) {
      return null;
    }
    return i.getItemMeta().getDisplayName();
  }
}


/* Location:           A:\LC\Lobby\plugins\HubPlugin.jar
 * Qualified Name:     me.mike1665.gadgets.FireWorks
 * JD-Core Version:    0.7.0.1
 */