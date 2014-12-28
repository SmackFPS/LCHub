package com.lightcraftmc.event.handlers.menu;

import java.util.ArrayList;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import com.lightcraftmc.hub.main.Main;
import com.lightcraftmc.menu.CosmeticsMenu;
import com.lightcraftmc.menu.MusicMenu;
import com.lightcraftmc.particles18.ParticleLib18;
import com.lightcraftmc.particles18.ParticleLib18.ParticleType;

public class MusicClick
  implements Listener
{
  public static Main plugin;
  ArrayList<Player> blocks = new ArrayList();
  ArrayList<Player> chirp = new ArrayList();
  ArrayList<Player> far = new ArrayList();
  ArrayList<Player> mall = new ArrayList();
  ArrayList<Player> mellohi = new ArrayList();
  ArrayList<Player> stal = new ArrayList();
  ArrayList<Player> strad = new ArrayList();
  ArrayList<Player> ward = new ArrayList();
  ArrayList<Player> eleven = new ArrayList();
  ArrayList<Location> thirteen = new ArrayList();
  ArrayList<Player> wait = new ArrayList();
  
  public static void initalize()
  {
    MusicClick.plugin = Main.getInstance();
  }
  
  @EventHandler
  public void onClick(InventoryClickEvent event)
  {
    Player p = (Player)event.getWhoClicked();
    if (event.getInventory().getName().equalsIgnoreCase(MusicMenu.name))
    {
      event.setCancelled(true);
      if (event.getCurrentItem() == null) {
        return;
      }
      if (!event.getCurrentItem().hasItemMeta()) {
        return;
      }
      if (!event.getCurrentItem().hasItemMeta()) {
        return;
      }
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§b§lBlocks Disk"))
      {
        p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.RECORD_3, 0);
        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
        p.closeInventory();
      //  spawnBlocksJuke(p);
      }
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§b§lChirp Disk"))
      {
        p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.RECORD_4, 0);
        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
        p.closeInventory();
       //  spawnChirpJuke(p);
      }
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§b§lFar Disk"))
      {
        p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.RECORD_5, 0);
        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
        p.closeInventory();
       //  spawnFarJuke(p);
      }
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§b§lMall Disk"))
      {
        p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.RECORD_6, 0);
        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
        p.closeInventory();
       //  spawnMallJuke(p);
      }
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§b§lMellohi Disk"))
      {
        p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.RECORD_7, 0);
        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
        p.closeInventory();
       //  spawnMellohiJuke(p);
      }
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§b§lStal Disk"))
      {
        p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.RECORD_8, 0);
        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
        p.closeInventory();
       //  spawnStalJuke(p);
      }
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§b§lStrad Disk"))
      {
        p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.RECORD_9, 0);
        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
        p.closeInventory();
       //  spawnStradJuke(p);
      }
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§b§lWard Disk"))
      {
        p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.RECORD_10, 0);
        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
        p.closeInventory();
      //   spawnWardJuke(p);
      }
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§b§l11 Disk"))
      {
        p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.RECORD_11, 0);
        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
        p.closeInventory();
      //   spawn11Juke(p);
      }
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§b§lWait Disk"))
      {
        p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.RECORD_12, 0);
        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
        p.closeInventory();
      //   spawnWaitJuke(p);
      }
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§b§l13 Disk"))
      {
        p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.GOLD_RECORD, 0);
        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
        p.closeInventory();
       //  spawn13Juke(p);
      }
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§b§lCat Disk"))
      {
        p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.GREEN_RECORD, 0);
        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
        p.closeInventory();
       //  spawnCatJuke(p);
      }
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cGo Back")) {
        p.openInventory(CosmeticsMenu.cosmenu(p));
      }
    }
  }
  
  public void spawnBlocksJuke(Player p)
  {
	
    final Location l = p.getEyeLocation();
    l.add(0.0D, -1.0D, 0.0D).getBlock().setType(Material.JUKEBOX);
    new BukkitRunnable()
    {
      int count = 1;
      
      public void run()
      {
        l.getBlock().setType(Material.AIR);
        MusicClick.runHelix(l);
        count -= 1;
        if (count == 0) {
          cancel();
        }
      }
    }.runTaskTimer(Main.getInstance(), 6540L, 1L);
  }
  
  public void spawnChirpJuke(Player p)
  {
    final Location l = p.getEyeLocation();
    l.add(0.0D, -1.0D, 0.0D).getBlock().setType(Material.JUKEBOX);
    new BukkitRunnable()
    {
      int count = 1;
      
      public void run()
      {
        l.getBlock().setType(Material.AIR);
        MusicClick.runHelix(l);
        count -= 1;
        if (count == 0) {
          cancel();
        }
      }
    }.runTaskTimer(Main.getInstance(), 3660L, 1L);
  }
  
  public void spawnFarJuke(Player p)
  {
    final Location l = p.getEyeLocation();
    l.add(0.0D, -1.0D, 0.0D).getBlock().setType(Material.JUKEBOX);
    new BukkitRunnable()
    {
      int count = 1;
      
      public void run()
      {
        l.getBlock().setType(Material.AIR);
        MusicClick.runHelix(l);
        count -= 1;
        if (count == 0) {
          cancel();
        }
      }
    }.runTaskTimer(Main.getInstance(), 3048L, 1L);
  }
  
  public void spawnMallJuke(Player p)
  {
    final Location l = p.getEyeLocation();
    l.add(0.0D, -1.0D, 0.0D).getBlock().setType(Material.JUKEBOX);
    new BukkitRunnable()
    {
      int count = 1;
      
      public void run()
      {
        l.getBlock().setType(Material.AIR);
        MusicClick.runHelix(l);
        count -= 1;
        if (count == 0) {
          cancel();
        }
      }
    }.runTaskTimer(Main.getInstance(), 3804L, 1L);
  }
  
  public void spawnMellohiJuke(Player p)
  {
    final Location l = p.getEyeLocation();
    l.add(0.0D, -1.0D, 0.0D).getBlock().setType(Material.JUKEBOX);
    new BukkitRunnable()
    {
      int count = 1;
      
      public void run()
      {
        l.getBlock().setType(Material.AIR);
        MusicClick.runHelix(l);
        count -= 1;
        if (count == 0) {
          cancel();
        }
      }
    }.runTaskTimer(Main.getInstance(), 16320L, 1L);
  }
  
  public void spawnStalJuke(Player p)
  {
    final Location l = p.getEyeLocation();
    l.add(0.0D, -1.0D, 0.0D).getBlock().setType(Material.JUKEBOX);
    new BukkitRunnable()
    {
      int count = 1;
      
      public void run()
      {
        l.getBlock().setType(Material.AIR);
        MusicClick.runHelix(l);
        count -= 1;
        if (count == 0) {
          cancel();
        }
      }
    }.runTaskTimer(Main.getInstance(), 2760L, 1L);
  }
  
  public void spawnStradJuke(Player p)
  {
    final Location l = p.getEyeLocation();
    l.add(0.0D, -1.0D, 0.0D).getBlock().setType(Material.JUKEBOX);
    new BukkitRunnable()
    {
      int count = 1;
      
      public void run()
      {
        l.getBlock().setType(Material.AIR);
        MusicClick.runHelix(l);
        count -= 1;
        if (count == 0) {
          cancel();
        }
      }
    }.runTaskTimer(Main.getInstance(), 3696L, 1L);
  }
  
  public void spawnWardJuke(Player p)
  {
    final Location l = p.getEyeLocation();
    l.add(0.0D, -1.0D, 0.0D).getBlock().setType(Material.JUKEBOX);
    new BukkitRunnable()
    {
      int count = 1;
      
      public void run()
      {
        l.getBlock().setType(Material.AIR);
        MusicClick.runHelix(l);
        count -= 1;
        if (count == 0) {
          cancel();
        }
      }
    }.runTaskTimer(Main.getInstance(), 4932L, 1L);
  }
  
  public void spawn11Juke(Player p)
  {
    final Location l = p.getEyeLocation();
    l.add(0.0D, -1.0D, 0.0D).getBlock().setType(Material.JUKEBOX);
    new BukkitRunnable()
    {
      int count = 1;
      
      public void run()
      {
        l.getBlock().setType(Material.AIR);
        MusicClick.runHelix(l);
        count -= 1;
        if (count == 0) {
          cancel();
        }
      }
    }.runTaskTimer(Main.getInstance(), 1332L, 1L);
  }
  
  public void spawnWaitJuke(Player p)
  {
    final Location l = p.getEyeLocation();
    l.add(0.0D, -1.0D, 0.0D).getBlock().setType(Material.JUKEBOX);
    new BukkitRunnable()
    {
      int count = 1;
      
      public void run()
      {
        l.getBlock().setType(Material.AIR);
        MusicClick.runHelix(l);
        count -= 1;
        if (count == 0) {
          cancel();
        }
      }
    }.runTaskTimer(Main.getInstance(), 4296L, 1L);
  }
  
  public void spawn13Juke(final Player p)
  {
    final Location l = p.getEyeLocation();
    l.add(0.0D, -1.0D, 0.0D).getBlock().setType(Material.JUKEBOX);
    thirteen.add(l);
    new BukkitRunnable()
    {
      int count = 1;
      
      public void run()
      {
        l.getBlock().setType(Material.AIR);
        MusicClick.runHelix(l);
        thirteen.remove(p);
        count -= 1;
        if (count == 0) {
          cancel();
        }
      }
    }.runTaskTimer(Main.getInstance(), 3096L, 1L);
  }
  
  public void spawnCatJuke(final Player p)
  {
    final Location l = p.getEyeLocation();
    l.add(0.0D, -1.0D, 0.0D).getBlock().setType(Material.JUKEBOX);
    new BukkitRunnable()
    {
      int count = 1;
      
      public void run()
      {
        l.getBlock().setType(Material.AIR);
        MusicClick.runHelix(l);
        thirteen.remove(p);
        count -= 1;
        if (count == 0) {
          cancel();
        }
      }
    }.runTaskTimer(Main.getInstance(), 3660L, 1L);
  }
  
  public void removeJuke(Player p)
  {
    Location l = p.getLocation();
    if (thirteen.contains(l)) {
      l.getBlock().setType(Material.AIR);
    }
  }
  
  public static void runHelix(Location loc)
  {
    double radius = 5.0D;
    for (double y = 5.0D; y >= 0.0D; y -= 0.1D)
    {
      radius = y / 3.0D;
      double x = radius * Math.cos(3.0D * y);
      double z = radius * Math.sin(3.0D * y);
      
      double y2 = 5.0D - y;
      
      ParticleLib18 notes = new ParticleLib18(ParticleLib18.ParticleType.NOTE, 0.0D, 1, 5.0D);
      Location loc2 = new Location(loc.getWorld(), loc.getX() + x, loc.getY() + y2, loc.getZ() + z);
      
      notes.sendToLocation(loc2);
    }
    for (double y = 5.0D; y >= 0.0D; y -= 0.1D)
    {
      radius = y / 3.0D;
      double x = -(radius * Math.cos(3.0D * y));
      double z = -(radius * Math.sin(3.0D * y));
      
      double y2 = 5.0D - y;
      
      ParticleLib18 notes = new ParticleLib18(ParticleLib18.ParticleType.NOTE, 0.0D, 1, 5.0D);
      Location loc2 = new Location(loc.getWorld(), loc.getX() + x, loc.getY() + y2, loc.getZ() + z);
      
      notes.sendToLocation(loc2);
    }
  }
}



/* Location:           A:\LC\Lobby\Main.getInstance()s\HubMain.getInstance().jar

 * Qualified Name:     com.lightcraftmc.click.MusicClick

 * JD-Core Version:    0.7.0.1

 */
