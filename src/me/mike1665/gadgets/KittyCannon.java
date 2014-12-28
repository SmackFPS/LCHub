package me.mike1665.gadgets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import me.mike1665.Main.Main;
import me.mike1665.ammo.KittyCannonAmmoManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Builder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Ocelot.Type;
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
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

import net.lightcraftmc.fusebox.util.strings.MessageType;
import net.lightcraftmc.fusebox.util.strings.StringManager;

public class KittyCannon
  implements Listener
{
  private final Random random = new Random();
  private ArrayList<UUID> _coolDown = new ArrayList();
  HashMap<Player, BukkitRunnable> _cdRunnable = new HashMap();
  
  @EventHandler(priority=EventPriority.HIGH)
  public void onPlayerUse(PlayerInteractEvent event)
  {
    final Player p = event.getPlayer();
    if (p.getItemInHand() == null) {
      return;
    }
    if (p.getItemInHand().getType() != Material.STICK) {
      return;
    }
    if (_coolDown.contains(p.getUniqueId())) {
      return;
    }
    if (((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)) && 
      (disName(p.getItemInHand()) != null) && (disName(p.getItemInHand()).equalsIgnoreCase(ChatColor.GOLD + "Kitty Cannon " + ChatColor.DARK_RED + KittyCannonAmmoManager.balaceCatAmmo(p))))
    {
      _coolDown.add(p.getUniqueId());
      Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable()
      {
        public void run()
        {
          _coolDown.remove(p.getUniqueId());
        }
      }, 8L);
      
      KittyCannonAmmoManager.takeCatAmmo(p, 1);
      if (KittyCannonAmmoManager.balaceCatAmmo(p) < 1)
      {
        p.getInventory().setItemInHand(null);
		p.sendMessage(StringManager.getPrefix(MessageType.ERROR) + ChatColor.AQUA+ "You ran out of ammo!");
        return;
      }
      ItemStack snow = new ItemStack(Material.STICK, 1);
      ItemMeta sno = snow.getItemMeta();
      sno.setDisplayName(ChatColor.GOLD + "Kitty Cannon " + ChatColor.DARK_RED + KittyCannonAmmoManager.balaceCatAmmo(p));
      snow.setItemMeta(sno);
      p.getInventory().setItemInHand(snow);
      Entity ent = (Ocelot)p.getWorld().spawnEntity(p.getLocation(), EntityType.OCELOT);
      final Ocelot ocelot = (Ocelot)ent;
      if (ocelot == null) {
        return;
      }
      int i = random.nextInt(Ocelot.Type.values().length);
      ocelot.setCatType(Ocelot.Type.values()[i]);
      ocelot.setTamed(true);
      ocelot.setBaby();
      ocelot.setVelocity(p.getEyeLocation().getDirection().multiply(2));
      new BukkitRunnable()
      {
        int count = 1;
        
        public void run()
        {
          Location loc = ocelot.getLocation();
          ocelot.remove();
          Firework fw = (Firework)ocelot.getWorld().spawnEntity(loc.add(0.5D, 1.2D, 0.5D), EntityType.FIREWORK);
          FireworkEffect.Builder builder = FireworkEffect.builder();
          FireworkMeta m = fw.getFireworkMeta();
          builder.trail(random.nextBoolean()).flicker(random.nextBoolean());
          builder.withColor(Color.fromRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
          builder.with(org.bukkit.FireworkEffect.Type.values()[random.nextInt(org.bukkit.FireworkEffect.Type.values().length)]);
          builder.withFade(Color.fromRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
          m.addEffect(builder.build());
          fw.setFireworkMeta(m);
          fw.detonate();
          count -= 1;
          if (count == 0) {
            cancel();
          }
        }
      }.runTaskTimer(Main.getInstance(), 12L, 1L);
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
  
  public static double arrondi(double A, int B)
  {
    return (int)(A * Math.pow(10.0D, B) + 0.5D) / Math.pow(10.0D, B);
  }
}


/* Location:           A:\LC\Lobby\plugins\HubPlugin.jar
 * Qualified Name:     me.mike1665.gadgets.KittyCannon
 * JD-Core Version:    0.7.0.1
 */