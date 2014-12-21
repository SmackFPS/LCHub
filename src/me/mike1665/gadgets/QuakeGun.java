package me.mike1665.gadgets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import me.mike1665.Main.Main;
import me.mike1665.ammo.GadgetAmmo;
import me.mike1665.ammo.MeowAmmoManager;
import net.lightcraftmc.fusebox.util.UtilBlock;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import net.lightcraftmc.fusebox.strings.MessageType;
import net.lightcraftmc.fusebox.strings.StringManager;

public class QuakeGun implements Listener {

	  private HashMap<Player, Long> _active = new HashMap();
	  private HashMap<Player, Double> _time = new HashMap();
	  HashMap<Player, BukkitRunnable> _cdRunnable = new HashMap();
	  private ArrayList<UUID> _coolDown = new ArrayList();


	  public static void playQuakeGun(Player player) {
	    final Pig p1 = (Pig)player.getWorld().spawnEntity(
	      player.getLocation(), EntityType.PIG);
	    final Pig p2 = (Pig)player.getPlayer().getWorld()
	      .spawnEntity(player.getLocation(), EntityType.PIG);
	    final Pig p3 = (Pig)player.getPlayer().getWorld()
	      .spawnEntity(player.getLocation(), EntityType.PIG);
	    final Pig p4 = (Pig)player.getPlayer().getWorld()
	      .spawnEntity(player.getLocation(), EntityType.PIG);
	    final Pig p5 = (Pig)player.getPlayer().getWorld()
	      .spawnEntity(player.getLocation(), EntityType.PIG);
	    
	    final Firework fw = (Firework)player.getWorld().spawnEntity(
	      player.getLocation(), EntityType.FIREWORK);
	    fw.setVelocity(player.getLocation().getDirection());
	    FireworkMeta meta = fw.getFireworkMeta();
	    meta.addEffect(FireworkEffect.builder().withColor(Color.BLUE).build());
	    fw.setFireworkMeta(meta);

	    fw.setPassenger(p1);

	    final Firework fw2 = (Firework)player.getWorld().spawnEntity(
	      player.getLocation().add(1.0D, 2.0D, 0.0D), EntityType.FIREWORK);
	    fw2.setVelocity(player.getLocation().getDirection());
	    FireworkMeta meta2 = fw2.getFireworkMeta();
	    meta2.addEffect(FireworkEffect.builder().withColor(Color.FUCHSIA)
	      .build());
	    fw2.setFireworkMeta(meta2);

	    fw2.setPassenger(p2);

	    final Firework fw3 = (Firework)player.getWorld().spawnEntity(
	      player.getLocation().add(0.0D, 2.0D, 1.0D), EntityType.FIREWORK);
	    fw3.setVelocity(player.getLocation().getDirection());
	    FireworkMeta meta3 = fw3.getFireworkMeta();
	    meta3.addEffect(FireworkEffect.builder().withColor(Color.AQUA).build());
	    fw3.setFireworkMeta(meta3);

	    fw3.setPassenger(p3);

	    final Firework fw4 = (Firework)player.getWorld().spawnEntity(
	      player.getLocation().add(0.0D, 2.0D, -1.0D), EntityType.FIREWORK);
	    fw4.setVelocity(player.getLocation().getDirection());
	    FireworkMeta meta4 = fw4.getFireworkMeta();
	    meta4.addEffect(FireworkEffect.builder().withColor(Color.PURPLE)
	      .build());
	    fw4.setFireworkMeta(meta4);

	    fw4.setPassenger(p4);

	    final Firework fw5 = (Firework)player.getWorld().spawnEntity(
	      player.getLocation().add(-1.0D, 2.0D, 0.0D), EntityType.FIREWORK);
	    fw5.setVelocity(player.getLocation().getDirection());
	    FireworkMeta meta5 = fw5.getFireworkMeta();
	    meta5.addEffect(FireworkEffect.builder().withColor(Color.NAVY).build());
	    fw5.setFireworkMeta(meta3);

	    fw5.setPassenger(p5);

	    Bukkit.getServer()
	      .getScheduler()
	      .scheduleSyncDelayedTask(Main.getInstance(), 
	      new Runnable() {
	      public void run() {
	        fw.eject();
	        fw2.eject();
	        fw3.eject();
	        fw4.eject();
	        fw5.eject();
	        p1.remove();
	        p2.remove();
	        p3.remove();
	        p4.remove();
	        p5.remove();
	      }
	    }
	    , 20L);
	    player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0F, 2.0F);
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
	    if (e.getPlayer().getItemInHand().getType() != Material.DIAMOND_HOE) {
	      return;
	    }
	    if (_coolDown.contains(p.getUniqueId())) {
	      return;
	    }
	    if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) && 
	      (disName(p.getItemInHand()) != null) && (disName(p.getItemInHand()).equalsIgnoreCase(ChatColor.GREEN + "QuakeGun " + ChatColor.DARK_RED + GadgetAmmo.balanceGadgetAmo(p, "QuakeGunAmmo"))))
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
	      }, 20L);
	      
	      playQuakeGun(player);
	      player.getWorld().playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1.5F, 1.5F);
	      GadgetAmmo.removeGadgetAmo(player, "QuakeGunAmmo", 1);
	      if (GadgetAmmo.balanceGadgetAmo(player, "QuakeGunAmmo") < 1)
	      {
	        player.getInventory().setItemInHand(null);
			player.sendMessage(StringManager.getPrefix(MessageType.ERROR) + ChatColor.AQUA+ "You ran out of ammo!");
	        return;
	      }
	      ItemStack snow = new ItemStack(Material.DIAMOND_HOE, 1);
	      ItemMeta sno = snow.getItemMeta();
	      sno.setDisplayName(ChatColor.GREEN + "QuakeGun " + ChatColor.DARK_RED + GadgetAmmo.balanceGadgetAmo(player, "QuakeGunAmmo"));
	      snow.setItemMeta(sno);
	      player.getInventory().setItemInHand(snow);
	    }
	  }
	  
	  public static double arrondi(double A, int B)
	  {
	    return (int)(A * Math.pow(10.0D, B) + 0.5D) / Math.pow(10.0D, B);
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
