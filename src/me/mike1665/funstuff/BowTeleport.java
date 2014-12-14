package me.mike1665.funstuff;

import me.mike1665.particles18.ParticleLib18;
import me.mike1665.particles18.ParticleLib18.ParticleType;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class BowTeleport
  implements Listener
{
  @EventHandler
  public void join(PlayerJoinEvent event)
  {
    Player player = event.getPlayer();
    ItemStack arrow = new ItemStack(Material.ARROW, 1);
    PlayerInventory inventory = player.getInventory();
    inventory.remove(arrow);
    inventory.setItem(35, arrow);
  }
  
  public static void giveBow(Player player)
  {
    PlayerInventory inventory = player.getInventory();
    ItemStack bow = new ItemStack(Material.BOW, 1);
    ItemMeta bowmeta = bow.getItemMeta();
    bowmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bTeleport Bow"));
    bow.setItemMeta(bowmeta);
    
    inventory.remove(bow);
    inventory.setItem(2, bow);
  }
  
  @EventHandler(priority=EventPriority.HIGHEST)
  public void onPlayerInteractArrow(PlayerInteractEvent event)
  {
    Player player = event.getPlayer();
    PlayerInventory inventory = player.getInventory();
    if (player.getItemInHand().getType() != Material.BOW) {
      return;
    }
    if (event.getAction() == Action.RIGHT_CLICK_AIR)
    {
      ItemStack arrow = new ItemStack(Material.ARROW, 2);
      inventory.setItem(35, arrow);
    }
  }
  
  @EventHandler(priority=EventPriority.HIGHEST)
  public void onInventoryClickEvent111(InventoryClickEvent event)
  {
    if (event.getCurrentItem() == null) {
      return;
    }
    event.getWhoClicked();
    if (event.getSlot() == 35) {
      event.setCancelled(true);
    }
    if (event.getSlot() == 5) {
      event.setCancelled(true);
    }
  }
  
  @EventHandler(priority=EventPriority.HIGHEST)
  public void onPlayerDropItem111(PlayerDropItemEvent event)
  {
    int current_slot = event.getPlayer().getInventory().getHeldItemSlot();
    if (current_slot == 35) {
      event.setCancelled(true);
    }
    if (current_slot == 5) {
      event.setCancelled(true);
    }
  }
  
  @EventHandler
  public void arrowShoot(ProjectileLaunchEvent e)
  {
    if (e.getEntityType() != EntityType.ARROW) {
      return;
    }
    final Arrow arrow = (Arrow)e.getEntity();
    if (arrow.getShooter() == null) {
      return;
    }
    if (!(arrow.getShooter() instanceof Player)) {
      return;
    }
    new BukkitRunnable()
    {
      public void run()
      {
        if ((arrow.isDead()) || (!arrow.isValid()))
        {
          cancel();
          return;
        }
        ParticleLib18 effect = new ParticleLib18(ParticleLib18.ParticleType.DRIP_LAVA, 5.0D, 1, 0.0D);
        effect.sendToLocation(arrow.getLocation());
      }
    };
  }
  
  @EventHandler
  public void arrow(EntityDamageByEntityEvent e)
  {
    if ((e.getDamager() instanceof Arrow)) {
      e.setCancelled(true);
    }
  }
  
  @EventHandler(priority=EventPriority.HIGHEST)
  public void arrowtp(ProjectileHitEvent e)
  {
    Projectile proj = e.getEntity();
    if ((proj instanceof Arrow))
    {
      Arrow arrow = (Arrow)proj;
      if ((arrow.getShooter() instanceof Player))
      {
        Player p = (Player)arrow.getShooter();
        p.getItemInHand().setDurability((short)0);
        p.teleport(new Location(arrow.getWorld(), arrow.getLocation().getX(), arrow.getLocation().getY(), arrow.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch()));
        arrow.remove();
        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10.0F, 1.0F);
        p.playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 2);
      }
    }
  }
}


/* Location:           A:\LC\Lobby\plugins\HubPlugin.jar
 * Qualified Name:     me.mike1665.funstuff.BowTeleport
 * JD-Core Version:    0.7.0.1
 */