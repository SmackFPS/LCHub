package me.mike1665.gadgets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

import me.mike1665.Main.Main;
import me.mike1665.ammo.FunCreeperAmmoManager;
import net.lightcraftmc.fusebox.util.UtilBlock;
import net.lightcraftmc.fusebox.util.strings.MessageType;
import net.lightcraftmc.fusebox.util.strings.StringManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

public class FunCreepers
  implements Listener
{
  private ArrayList<UUID> _coolDown = new ArrayList();
  private HashSet<Projectile> _balls = new HashSet();
  
  @EventHandler
  public void creperhit(ProjectileHitEvent event)
  {
    if (!_balls.remove(event.getEntity())) {
      return;
    }
    Location loc = event.getEntity().getLocation().add(event.getEntity().getVelocity());
    loc.getWorld().playSound(loc, Sound.BURP, 1.0F, 1.0F);
    spawnCreeper(loc.add(0.0D, 1.0D, 0.0D));
  }
  
  @EventHandler
  public void throwSnowball(PlayerInteractEvent e)
  {
    final Player p = e.getPlayer();
    if (UtilBlock.usable(e.getClickedBlock())) {
      return;
    }
    if (e.getPlayer().getItemInHand() == null) {
      return;
    }
    if (e.getPlayer().getItemInHand().getType() != Material.FIREWORK_CHARGE) {
      return;
    }
    if (_coolDown.contains(p.getUniqueId())) {
      return;
    }
    if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) && 
      (disName(p.getItemInHand()) != null) && (disName(p.getItemInHand()).equalsIgnoreCase(ChatColor.AQUA + "Fun Creeper " + ChatColor.DARK_RED + FunCreeperAmmoManager.balaceCreeperAmmo(p))))
    {
      Player player = e.getPlayer();
      e.setCancelled(true);
      
      _coolDown.add(p.getUniqueId());
      Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable()
      {
        public void run()
        {
          _coolDown.remove(p.getUniqueId());
        }
      }, 5L);
      
      Projectile proj = player.launchProjectile(Egg.class);
      proj.setVelocity(proj.getVelocity().multiply(2));
      _balls.add(proj);
      player.getWorld().playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1.5F, 1.5F);
      FunCreeperAmmoManager.takeCreeperAmmo(p, 1);
      if (FunCreeperAmmoManager.balaceCreeperAmmo(p) < 1)
      {
        player.getInventory().setItemInHand(null);
		player.sendMessage(StringManager.getPrefix(MessageType.ERROR) + ChatColor.AQUA+ "You ran out of ammo!");
        return;
      }
      ItemStack snow = new ItemStack(Material.FIREWORK_CHARGE, 1);
      ItemMeta sno = snow.getItemMeta();
      sno.setDisplayName(ChatColor.AQUA + "Fun Creeper " + ChatColor.DARK_RED + FunCreeperAmmoManager.balaceCreeperAmmo(p));
      snow.setItemMeta(sno);
      player.getInventory().setItemInHand(snow);
    }
  }
  
  public void spawnCreeper(Location l)
  {
    final Creeper creeper = (Creeper)l.getWorld().spawn(l, Creeper.class);
    Bukkit.getServer().getScheduler()
      .scheduleSyncRepeatingTask(Main.getInstance(), new Runnable()
      {
        int num = 1;
        
        public void run()
        {
          if (num == 1)
          {
            creeper.setCustomName(ChatColor.DARK_RED + 
              "Fun Creeper");
            creeper.setCustomNameVisible(true);
            num += 1;
          }
          else if (num == 2)
          {
            creeper.setCustomName(ChatColor.RED + 
              "Fun Creeper");
            creeper.setCustomNameVisible(true);
            num += 1;
          }
          else if (num == 3)
          {
            creeper.setCustomName(ChatColor.GOLD + 
              "Fun Creeper");
            creeper.setCustomNameVisible(true);
            num += 1;
          }
          else if (num == 4)
          {
            creeper.setCustomName(ChatColor.YELLOW + 
              "Fun Creeper");
            creeper.setCustomNameVisible(true);
            num += 1;
          }
          else if (num == 5)
          {
            creeper.setCustomName(ChatColor.DARK_GREEN + 
              "Fun Creeper");
            creeper.setCustomNameVisible(true);
            num += 1;
          }
          else if (num == 6)
          {
            creeper.setCustomName(ChatColor.GREEN + 
              "Fun Creeper");
            creeper.setCustomNameVisible(true);
            num += 1;
          }
          else if (num == 7)
          {
            creeper.setCustomName(ChatColor.BLUE + 
              "Fun Creeper");
            creeper.setCustomNameVisible(true);
            num = 1;
          }
          else if (num == 8)
          {
            creeper.setCustomName(ChatColor.DARK_AQUA + 
              "Fun Creeper");
            creeper.setCustomNameVisible(true);
            num = 1;
          }
          else if (num == 9)
          {
            creeper.setCustomName(ChatColor.DARK_BLUE + 
              "Fun Creeper");
            creeper.setCustomNameVisible(true);
            num = 1;
          }
          else if (num == 10)
          {
            creeper.setCustomName(ChatColor.BLUE + 
              "Fun Creeper");
            creeper.setCustomNameVisible(true);
            num = 1;
          }
          else if (num == 11)
          {
            creeper.setCustomName(ChatColor.LIGHT_PURPLE + 
              "Fun Creeper");
            creeper.setCustomNameVisible(true);
            num = 1;
          }
          else if (num == 12)
          {
            creeper.setCustomName(ChatColor.DARK_PURPLE + 
              "Fun Creeper");
            creeper.setCustomNameVisible(true);
            num = 1;
          }
          else
          {
            creeper.isValid();
          }
        }
      }, 0L, 2L);
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
 * Qualified Name:     me.mike1665.gadgets.FunCreepers
 * JD-Core Version:    0.7.0.1
 */