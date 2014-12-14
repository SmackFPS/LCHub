package me.mike1665.gadgets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;
import me.mike1665.Main.Main;
import me.mike1665.ammo.EnderDogeAmmoManager;
import me.mike1665.particles18.ParticleLib18;
import me.mike1665.particles18.ParticleLib18.ParticleType;
import me.mike1665.utils.UtilBlock;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
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

public class EnderDoge
  implements Listener
{
  private ArrayList<UUID> _coolDown = new ArrayList();
  private HashSet<Projectile> _balls = new HashSet();
  
  @EventHandler
  public void snowballParticle(ProjectileHitEvent event)
  {
    if (!_balls.remove(event.getEntity())) {
      return;
    }
    Location loc = event.getEntity().getLocation().add(event.getEntity().getVelocity());
    loc.getWorld().playSound(loc, Sound.WOLF_BARK, 1.0F, 1.0F);
    ParticleLib18 portal = new ParticleLib18(ParticleLib18.ParticleType.PORTAL, 0.0D, 20, 0.0D);
    ParticleLib18 spark = new ParticleLib18(ParticleLib18.ParticleType.FIREWORKS_SPARK, 0.0D, 10, 0.0D);
    portal.sendToLocation(loc.add(0.0D, 1.0D, 0.0D));
    spark.sendToLocation(loc.add(0.0D, 1.0D, 0.0D));
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
      (disName(p.getItemInHand()) != null) && (disName(p.getItemInHand()).equalsIgnoreCase(ChatColor.DARK_AQUA + "EnderDoge " + ChatColor.DARK_RED + EnderDogeAmmoManager.balaceEnderDogeAmmo(p))))
    {
      Player player = e.getPlayer();
      e.setCancelled(true);
      
      _coolDown.add(p.getUniqueId());
      Bukkit.getScheduler().runTaskLater(Main.schedule, new Runnable()
      {
        public void run()
        {
          _coolDown.remove(p.getUniqueId());
        }
      }, 5L);
      Projectile proj = player.launchProjectile(Snowball.class);
      proj.setVelocity(proj.getVelocity().multiply(2));
      _balls.add(proj);
      player.getWorld().playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1.5F, 1.5F);
      EnderDogeAmmoManager.takeEnderDogeAmmo(p, 1);
      if (EnderDogeAmmoManager.balaceEnderDogeAmmo(p) < 1)
      {
        player.getInventory().setItemInHand(null);
        player.sendMessage("testdsdas");
        return;
      }
      ItemStack snow = new ItemStack(Material.FIREWORK_CHARGE, 1);
      ItemMeta sno = snow.getItemMeta();
      sno.setDisplayName(ChatColor.DARK_AQUA + "EnderDoge " + ChatColor.DARK_RED + EnderDogeAmmoManager.balaceEnderDogeAmmo(p));
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
 * Qualified Name:     me.mike1665.gadgets.EnderDoge
 * JD-Core Version:    0.7.0.1
 */