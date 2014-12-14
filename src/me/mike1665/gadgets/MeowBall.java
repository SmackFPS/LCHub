package me.mike1665.gadgets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;
import me.mike1665.Main.Main;
import me.mike1665.ammo.MeowAmmoManager;
import me.mike1665.particles18.ParticleLib18;
import me.mike1665.particles18.ParticleLib18.ParticleType;
import me.mike1665.utils.UtilBlock;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
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

public class MeowBall
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
    loc.getWorld().playSound(loc, Sound.CAT_MEOW, 1.0F, 1.0F);
    ParticleLib18 lava = new ParticleLib18(ParticleLib18.ParticleType.LAVA, 0.0D, 20, 0.0D);
    ParticleLib18 hearts = new ParticleLib18(ParticleLib18.ParticleType.HEART, 0.0D, 2, 0.0D);
    ParticleLib18 largesmoke = new ParticleLib18(ParticleLib18.ParticleType.SMOKE_LARGE, 0.0D, 10, 0.0D);
    lava.sendToLocation(loc.add(0.0D, 1.0D, 0.0D));
    hearts.sendToLocation(loc.add(0.0D, 1.0D, 0.0D));
    largesmoke.sendToLocation(loc.add(0.0D, 1.0D, 0.0D));
    loc.getWorld().playEffect(loc.add(0.0D, -1.0D, 0.0D), Effect.MOBSPAWNER_FLAMES, 1);
    loc.getWorld().playEffect(loc.add(0.0D, -1.0D, 0.0D), Effect.MOBSPAWNER_FLAMES, 1);
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
    if (e.getPlayer().getItemInHand().getType() != Material.BLAZE_ROD) {
      return;
    }
    if (_coolDown.contains(p.getUniqueId())) {
      return;
    }
    if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) && 
      (disName(p.getItemInHand()) != null) && (disName(p.getItemInHand()).equalsIgnoreCase(ChatColor.GREEN + "MeowBall " + "§7«§b " + MeowAmmoManager.balaceMeowAmmo(p) + " §7«§b")))
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
      MeowAmmoManager.takeMeowAmmo(player, 1);
      if (MeowAmmoManager.balaceMeowAmmo(player) < 1)
      {
        player.getInventory().setItemInHand(null);
        player.sendMessage("testdsdas");
        return;
      }
      ItemStack snow = new ItemStack(Material.BLAZE_ROD, 1);
      ItemMeta sno = snow.getItemMeta();
      sno.setDisplayName(ChatColor.GREEN + "MeowBall " + ChatColor.DARK_RED + MeowAmmoManager.balaceMeowAmmo(player));
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
 * Qualified Name:     me.mike1665.gadgets.MeowBall
 * JD-Core Version:    0.7.0.1
 */