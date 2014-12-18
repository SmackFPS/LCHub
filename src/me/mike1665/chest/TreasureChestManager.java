package me.mike1665.chest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import me.mike1665.Main.Main;
import me.mike1665.block.BlockRestore;
import me.mike1665.particles18.ParticleLib18;
import me.mike1665.update.UpdateType;
import me.mike1665.update.event.UpdateEvent;
import me.mike1665.utils.UtilBlock;
import me.mike1665.utils.UtilEnt;
import me.mike1665.utils.UtilServer;
import me.mike1665.utils.UtilVelocity;
import me.mike1665.utils.UtilityBlock;
import net.minecraft.server.v1_8_R1.Packet;
import net.minecraft.server.v1_8_R1.PacketPlayOutBlockAction;
import net.minecraft.server.v1_8_R1.WorldServer;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.craftbukkit.v1_8_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.util.Vector;

public class TreasureChestManager
  implements Listener
{
  public static HashMap<UUID, ArrayList<org.bukkit.block.Block>> treasureChest = new HashMap<UUID, ArrayList<org.bukkit.block.Block>>();
  public static HashMap<UUID, ArrayList<org.bukkit.block.Block>> chest = new HashMap<UUID, ArrayList<org.bukkit.block.Block>>();

  static int visibleDistance = Bukkit.getServer().getViewDistance() * 16;

  public static HashMap<Player, Vector> playerpos = new HashMap();

  public static void playSpiral(final Location loc, final Player p, final ParticleLib18.ParticleType particle, final ParticleLib18.ParticleType placeParticle)
  {
    final int i = Bukkit.getScheduler().runTaskTimer(Main.schedule, new Runnable()
    {
      float k = 0.0F;
      float heightPosition = 4.0F;
      float rayon = 0.3F;
      float particleDistance = 0.1F;

      public void run() { 
    	  if (p.isValid()) {
          Location l = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());
          Location l2 = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());

          Vector v = new Vector(this.k * Math.sin(this.k * 3.141592653589793D / 2.0D) * this.rayon, this.k + this.heightPosition, this.k * Math.cos(this.k * 3.141592653589793D / 2.0D) * this.rayon);
          l.add(v);
          //new UtilParticle(particle, 0.0D, 1, 0.0D).sendToLocation(l);

          Vector v2 = new Vector(this.k * Math.sin(this.k * 3.141592653589793D / 2.0D) * -this.rayon, this.k + this.heightPosition, this.k * Math.cos(this.k * 3.141592653589793D / 2.0D) * -this.rayon);
          l2.add(v2);
          //new UtilParticle(particle, 0.0D, 1, 0.0D).sendToLocation(l2);

          this.k -= this.particleDistance;

          if (this.k <= -4.0F) this.k = 0.0F;
        }
      }
    }
    , 1L, 1L).getTaskId();
    Bukkit.getServer().getScheduler()
      .runTaskLater(Main.schedule, new Runnable()
    {
      public void run() {
        Bukkit.getScheduler().cancelTask(i);
        //new UtilParticle(placeParticle, 0.1000000014901161D, 10, 0.300000011920929D).sendToLocation(loc.add(0.0D, 0.699999988079071D, 0.0D));
      }
    }
    , 40L);
  }

  public static boolean chestSpawned(Player p)
  {
    if (treasureChest.containsKey(p.getUniqueId()))
    {
      return true;
    }
    return false;
  }

  /*public static void treasureChestDataSetup(Player p, Main plugin)
  {
    File file = new File("plugins/ProdigyGadget/stats", p.getUniqueId() + ".yml");
    if (!file.exists())
      try
      {
        file.createNewFile();
        try {
          plugin.getConfig().load(file);
          plugin.getConfig().set("treasurechest", Integer.valueOf(0));
          plugin.getConfig().set("treasurekey", Integer.valueOf(0));
          plugin.getConfig().set("batblaster", Integer.valueOf(0));
          plugin.getConfig().set("paintballgun", Integer.valueOf(0));
          plugin.getConfig().set("quakegun", Integer.valueOf(0));
          plugin.getConfig().save(file);
        }
        catch (InvalidConfigurationException e1) {
          e1.printStackTrace();
        }
      }
      catch (IOException ev)
      {
        Bukkit.getServer().broadcastMessage(ev.getMessage());
      }
  }

  public static int getTreasureKey(Player player)
    throws FileNotFoundException, IOException, InvalidConfigurationException
  {
    treasureChestDataSetup(player, Main.schedule);
    File file = new File("plugins/ProdigyGadget/stats", player.getUniqueId() + ".yml");
    Main.schedule.getConfig().load(file);
    return Integer.valueOf(Main.schedule.getConfig().getInt("treasurekey")).intValue();
  }

  public static boolean hasTreasureKey(Player p) throws FileNotFoundException, IOException, InvalidConfigurationException {
    if (getTreasureKey(p) > 0) {
      return true;
    }
    return false;
  }

  public static void addTreasureKey(Player p, int amount)
    throws IOException, InvalidConfigurationException
  {
    treasureChestDataSetup(p, ProdigyGadget.schedule);
    File file = new File("plugins/ProdigyGadget/stats", p.getUniqueId() + ".yml");
    ProdigyGadget.schedule.getConfig().load(file);
    ProdigyGadget.schedule.getConfig().set("treasurekey", Integer.valueOf(ProdigyGadget.schedule.getConfig().getInt("treasurechest") + amount));
    ProdigyGadget.schedule.getConfig().save(file);
  }

  public static void removeTreasureKey(Player p, int amount) throws IOException, InvalidConfigurationException
  {
    treasureChestDataSetup(p, ProdigyGadget.schedule);
    File file = new File("plugins/ProdigyGadget/stats", p.getUniqueId() + ".yml");
    ProdigyGadget.schedule.getConfig().load(file);
    ProdigyGadget.schedule.getConfig().set("treasurekey", Integer.valueOf(ProdigyGadget.schedule.getConfig().getInt("treasurechest") - amount));
    ProdigyGadget.schedule.getConfig().save(file);
  }

  public static int getTreasureChest(Player player)
    throws FileNotFoundException, IOException, InvalidConfigurationException
  {
    treasureChestDataSetup(player, ProdigyGadget.schedule);
    File file = new File("plugins/ProdigyGadget/stats", player.getUniqueId() + ".yml");
    ProdigyGadget.schedule.getConfig().load(file);
    return Integer.valueOf(ProdigyGadget.schedule.getConfig().getInt("treasurechest")).intValue();
  }

  public static boolean hasTreasureChest(Player p) throws FileNotFoundException, IOException, InvalidConfigurationException {
    if (getTreasureChest(p) > 0) {
      return true;
    }
    return false;
  }

  public static void addTreasureChest(Player p, int amount)
    throws IOException, InvalidConfigurationException
  {
    treasureChestDataSetup(p, ProdigyGadget.schedule);
    File file = new File("plugins/ProdigyGadget/stats", p.getUniqueId() + ".yml");
    ProdigyGadget.schedule.getConfig().load(file);
    ProdigyGadget.schedule.getConfig().set("treasurechest", Integer.valueOf(ProdigyGadget.schedule.getConfig().getInt("treasurechest") + amount));
    ProdigyGadget.schedule.getConfig().save(file);
  }

  public static void removeTreasureChest(Player p, int amount) throws IOException, InvalidConfigurationException
  {
    treasureChestDataSetup(p, ProdigyGadget.schedule);
    File file = new File("plugins/ProdigyGadget/stats", p.getUniqueId() + ".yml");
    ProdigyGadget.schedule.getConfig().load(file);
    ProdigyGadget.schedule.getConfig().set("treasurechest", Integer.valueOf(ProdigyGadget.schedule.getConfig().getInt("treasurechest") - amount));
    ProdigyGadget.schedule.getConfig().save(file);
  } */

  @SuppressWarnings({ "unchecked", "rawtypes" })
public static List<Entity> getClosestEntitiesFromLocation(Location location, double radius)
  {
    double chunkRadius = radius < 16.0D ? 1.0D : (radius - radius % 16.0D) / 16.0D;
    List radiusEntities = new ArrayList();
    for (double chX = 0.0D - chunkRadius; chX <= chunkRadius; chX += 1.0D) {
      for (double chZ = 0.0D - chunkRadius; chZ <= chunkRadius; chZ += 1.0D) {
        int x = (int)location.getX(); int y = (int)location.getY(); int z = (int)location.getZ();
        for (Entity e : new Location(location.getWorld(), x + chX * 16.0D, y, z + chZ * 16.0D).getChunk().getEntities()) {
          if ((e.getLocation().distance(location) <= radius) && (e.getLocation().getBlock() != location.getBlock()) && ((e instanceof Entity))) radiusEntities.add(e);
        }
      }
    }
    return radiusEntities;
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
public static List<Player> getClosestPlayersFromLocation(Location location, double distance)
  {
    List result = new ArrayList();
    double d2 = distance * distance;
    for (Player player : location.getWorld().getPlayers()) {
      if (player.getLocation().add(0.0D, 0.85D, 0.0D).distanceSquared(location) <= d2) {
        result.add(player);
      }
    }
    return result;
  }

  public static void playOpenChestAnimation(Location location)
  {
    WorldServer ws = ((CraftWorld)location.getWorld()).getHandle();
    int x = location.getBlockX();
    int y = location.getBlockY();
    int z = location.getBlockZ();
    Location loc = new Location(Bukkit.getWorld("world"), x, y, z);
    Block block = loc.getBlock();
    Packet chestOpen = new PacketPlayOutBlockAction();
    for (Player player : getClosestPlayersFromLocation(location, visibleDistance))
      ((CraftPlayer)player).getHandle().playerConnection.sendPacket(chestOpen);
  }

  public static boolean isInTreasureChest(Player p)
  {
    if (BlockRestore.saves.containsKey(p.getUniqueId())) {
      return true;
    }
    return false;
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
@EventHandler
  public void onPlayerInteract(PlayerInteractEvent event)
    throws FileNotFoundException, IOException, InvalidConfigurationException
  {
    if (event.getAction() == Action.RIGHT_CLICK_AIR) return;
    if ((event.getAction() == Action.RIGHT_CLICK_BLOCK) && (event.getClickedBlock().getType() == Material.CHEST))
    {
      final Player p = event.getPlayer();

      if ((UtilityBlock.blockToRestore.contains(event.getClickedBlock())) && 
        (!chestSpawned(p))) {
        event.setCancelled(true);
      }

      if (isInTreasureChest(p)) {
        event.setCancelled(true);
        if (chestSpawned(p)) {
          if (!chest.containsKey(p.getUniqueId())) {
            chest.put(p.getUniqueId(), new ArrayList());
          }
          ArrayList b = (ArrayList)chest.get(p.getUniqueId());
          if (!b.contains(event.getClickedBlock())) {
            playOpenChestAnimation(event.getClickedBlock().getLocation());
            event.getClickedBlock().getLocation().getWorld().playSound(event.getClickedBlock().getLocation(), Sound.CHEST_OPEN, 1.0F, 1.0F);
            if (((ArrayList)chest.get(p.getUniqueId())).size() < 3) {
              ((ArrayList)chest.get(p.getUniqueId())).add(event.getClickedBlock());
              RandomManager.giveRandomThing(p, event.getClickedBlock().getLocation());
            } else {
              RandomManager.giveRandomBetweenRareAndNormalThing(p, event.getClickedBlock().getLocation());
              ((ArrayList)chest.get(p.getUniqueId())).add(event.getClickedBlock());
              Bukkit.getServer().getScheduler()
                .runTaskLater(Main.schedule, new Runnable()
              {
                @SuppressWarnings("rawtypes")
				public void run() {
                  if (p.isValid())
                  {
                    Iterator localIterator2;
                    for (Iterator localIterator1 = UtilEnt.flyingEntities.keySet().iterator(); 
                      localIterator1.hasNext(); 
                      localIterator2.hasNext())
                    {
                      Player p = (Player)localIterator1.next();
                      ArrayList entList = (ArrayList)UtilEnt.flyingEntities.get(p);
                      localIterator2 = entList.iterator(); 
                      //continue; 
                      Entity e = (Entity) localIterator1.next();
                      if (e.isValid()) {
                        e.remove();
                      }

                    }

                    BlockRestore.restore(p);
                    TreasureChestManager.chest.remove(p.getUniqueId());
                    TreasureChestManager.treasureChest.remove(p.getUniqueId());
                    TreasureChestManager.playerpos.remove(p);
                  }
                }
              }
              , 120L);
            }
            ((ArrayList)treasureChest.get(p.getUniqueId())).remove(event.getClickedBlock().getLocation());
          }
        }
      }
    }
  }

  @EventHandler
  public void UpdateEvent(UpdateEvent e)
  {
    if (e.getType() == UpdateType.TICK)
      for (Player p : UtilServer.getPlayers())
        if (isInTreasureChest(p))
        {
          for (Entity victim : UtilEnt.getNearbyEntities(((org.bukkit.block.Block)BuildManager.treasureChestLocation.get(p.getUniqueId())).getLocation(), 4))
            if ((!(victim instanceof WitherSkull)) && 
              (!(victim instanceof Item)) && 
              (p != victim))
            {
              UtilVelocity.bumpEntity(victim, ((org.bukkit.block.Block)BuildManager.treasureChestLocation.get(p.getUniqueId())).getLocation(), 1.0D);
            }
        }
  }

  @EventHandler
  public void onPlayerMove(PlayerMoveEvent e)
  {
    Player p = e.getPlayer();

    if (isInTreasureChest(p)) {
      if (playerpos.get(p) == null) {
        playerpos.put(p, p.getLocation().toVector());
        return;
      }
      Location l = p.getLocation();
      Vector v = (Vector)playerpos.get(p);
      if ((l.getBlockX() != v.getBlockX()) || (l.getBlockZ() != v.getBlockZ())) {
        l.setX(v.getBlockX() + 0.5D);
        l.setZ(v.getBlockZ() + 0.5D);
        l.setYaw(p.getLocation().getYaw());
        l.setPitch(p.getLocation().getPitch());
        p.teleport(l);
      }
    }
  }

  @SuppressWarnings("rawtypes")
@EventHandler
  public void OnPlayerLeft(PlayerQuitEvent event)
  {
    Player p2 = event.getPlayer();
    if (isInTreasureChest(p2))
    {
      Iterator localIterator2;
      for (Iterator localIterator1 = UtilEnt.flyingEntities.keySet().iterator(); localIterator1.hasNext(); 
        localIterator2.hasNext())
      {
        Player p = (Player)localIterator1.next();
        ArrayList entList = (ArrayList)UtilEnt.flyingEntities.get(p);
        localIterator2 = entList.iterator(); 
        //continue;
        Entity e = (Entity)localIterator2.next();
        if (e.isValid()) {
          e.remove();
        }

      }

      BlockRestore.restore(p2);
      chest.remove(p2.getUniqueId());
      treasureChest.remove(p2.getUniqueId());
      playerpos.remove(p2);
    }
  }

  @SuppressWarnings("rawtypes")
@EventHandler
  public void onLeave(PlayerKickEvent event)
  {
    Player p2 = event.getPlayer();
    if (isInTreasureChest(p2))
    {
      Iterator localIterator2;
      for (Iterator localIterator1 = UtilEnt.flyingEntities.keySet().iterator(); localIterator1.hasNext(); 
        localIterator2.hasNext())
      {
        Player p = (Player)localIterator1.next();
        ArrayList entList = (ArrayList)UtilEnt.flyingEntities.get(p);
        localIterator2 = entList.iterator(); 
        //continue; 
        Entity e = (Entity) localIterator2.next();
        if (e.isValid()) {
          e.remove();
        }

      }

      BlockRestore.restore(p2);
      chest.remove(p2.getUniqueId());
      treasureChest.remove(p2.getUniqueId());
      playerpos.remove(p2);
    }
  }

  @EventHandler
  public void InventoryOpen(InventoryOpenEvent event)
  {
    Player p = (Player)event.getPlayer();
    if (isInTreasureChest(p))
      event.setCancelled(true);
  }
}