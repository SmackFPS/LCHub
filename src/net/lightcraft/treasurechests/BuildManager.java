package net.lightcraft.treasurechests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import me.mike1665.Main.Main;
import me.mike1665.particles18.ParticleLib18;
import net.lightcraft.blocks.BlockRestore;
import net.lightcraftmc.fusebox.util.UtilServer;
import net.lightcraftmc.fusebox.util.UtilityBlock;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class BuildManager
  implements Listener
{
  public static HashMap<UUID, Block> treasureChestLocation = new HashMap<UUID, Block>();
  private Player p;
  private Material m;
  private byte data;
  private Location location;

  public BuildManager(Player p, Location location, Material m, byte data)
  {
    this.p = p;
    this.m = m;
    this.data = data;
    this.location = location;
  }

  @SuppressWarnings("deprecation")
public void buildRectangle2()
  {
    Location l = new Location(this.location.getWorld(), this.location.getX(), this.location.getY(), this.location.getZ());
    l.setY(l.getY() - 1.0D);

    Location l10 = new Location(this.p.getWorld(), l.getX(), l.getY() - 1.0D, l.getZ());
    Location l2 = new Location(this.p.getWorld(), l.getX() + 1.0D, l.getY(), l.getZ());
    Location l3 = new Location(this.p.getWorld(), l.getX() - 1.0D, l.getY(), l.getZ());
    Location l4 = new Location(this.p.getWorld(), l.getX(), l.getY(), l.getZ() - 1.0D);
    Location l5 = new Location(this.p.getWorld(), l.getX(), l.getY(), l.getZ() + 1.0D);
    Location l6 = new Location(this.p.getWorld(), l.getX() + 1.0D, l.getY(), l.getZ() + 1.0D);
    Location l7 = new Location(this.p.getWorld(), l.getX() + 1.0D, l.getY(), l.getZ() - 1.0D);
    Location l8 = new Location(this.p.getWorld(), l.getX() - 1.0D, l.getY(), l.getZ() - 1.0D);
    Location l9 = new Location(this.p.getWorld(), l.getX() - 1.0D, l.getY(), l.getZ() + 1.0D);

    BlockRestore.save(this.p, l);
    BlockRestore.save(this.p, l2);
    BlockRestore.save(this.p, l3);
    BlockRestore.save(this.p, l4);
    BlockRestore.save(this.p, l5);
    BlockRestore.save(this.p, l6);
    BlockRestore.save(this.p, l7);
    BlockRestore.save(this.p, l8);
    BlockRestore.save(this.p, l9);
    BlockRestore.save(this.p, l10);

    l.getBlock().setType(Material.REDSTONE_LAMP_OFF);
    l2.getBlock().setType(this.m);
    l3.getBlock().setType(this.m);
    l4.getBlock().setType(this.m);
    l5.getBlock().setType(this.m);
    l6.getBlock().setType(this.m);
    l7.getBlock().setType(this.m);
    l8.getBlock().setType(this.m);
    l9.getBlock().setType(this.m);
    l10.getBlock().setType(Material.REDSTONE_BLOCK);
    treasureChestLocation.put(this.p.getUniqueId(), l10.getBlock());

    l2.getBlock().setData(this.data);
    l3.getBlock().setData(this.data);
    l4.getBlock().setData(this.data);
    l5.getBlock().setData(this.data);
    l6.getBlock().setData(this.data);
    l7.getBlock().setData(this.data);
    l8.getBlock().setData(this.data);
    l9.getBlock().setData(this.data);

    for (Player p2 : UtilServer.getPlayers()) {
      UtilityBlock.sendBreak(p2, 123, (byte)0, l.add(0.0D, 0.6000000238418579D, 0.0D));
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l2.add(0.0D, 0.6000000238418579D, 0.0D));
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l3.add(0.0D, 0.6000000238418579D, 0.0D));
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l4.add(0.0D, 0.6000000238418579D, 0.0D));
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l5.add(0.0D, 0.6000000238418579D, 0.0D));
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l6.add(0.0D, 0.6000000238418579D, 0.0D));
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l7.add(0.0D, 0.6000000238418579D, 0.0D));
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l8.add(0.0D, 0.6000000238418579D, 0.0D));
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l9.add(0.0D, 0.6000000238418579D, 0.0D));
    }
  }

  @SuppressWarnings("deprecation")
public void buildRectangle3()
  {
    Location l = new Location(this.location.getWorld(), this.location.getX(), this.location.getY(), this.location.getZ());

    Location l2 = new Location(this.p.getWorld(), l.getX() + 2.0D, l.getY() - 1.0D, l.getZ());
    Location l3 = new Location(this.p.getWorld(), l.getX(), l.getY() - 1.0D, l.getZ() + 2.0D);
    Location l4 = new Location(this.p.getWorld(), l.getX(), l.getY() - 1.0D, l.getZ() - 2.0D);
    Location l5 = new Location(this.p.getWorld(), l.getX() - 2.0D, l.getY() - 1.0D, l.getZ());
    Location l6 = new Location(this.p.getWorld(), l.getX() + 2.0D, l.getY() - 1.0D, l.getZ() - 2.0D);
    Location l7 = new Location(this.p.getWorld(), l.getX() - 2.0D, l.getY() - 1.0D, l.getZ() + 2.0D);
    Location l8 = new Location(this.p.getWorld(), l.getX() - 2.0D, l.getY() - 1.0D, l.getZ() - 2.0D);
    Location l9 = new Location(this.p.getWorld(), l.getX() + 2.0D, l.getY() - 1.0D, l.getZ() + 2.0D);

    Location l10 = new Location(this.p.getWorld(), l.getX() + 2.0D, l.getY() - 1.0D, l.getZ() - 1.0D);
    Location l11 = new Location(this.p.getWorld(), l.getX() + 2.0D, l.getY() - 1.0D, l.getZ() + 1.0D);
    Location l12 = new Location(this.p.getWorld(), l.getX() + 1.0D, l.getY() - 1.0D, l.getZ() + 2.0D);
    Location l13 = new Location(this.p.getWorld(), l.getX() - 1.0D, l.getY() - 1.0D, l.getZ() + 2.0D);
    Location l14 = new Location(this.p.getWorld(), l.getX() + 1.0D, l.getY() - 1.0D, l.getZ() - 2.0D);
    Location l15 = new Location(this.p.getWorld(), l.getX() - 1.0D, l.getY() - 1.0D, l.getZ() - 2.0D);
    Location l16 = new Location(this.p.getWorld(), l.getX() - 2.0D, l.getY() - 1.0D, l.getZ() - 1.0D);
    Location l17 = new Location(this.p.getWorld(), l.getX() - 2.0D, l.getY() - 1.0D, l.getZ() + 1.0D);

    BlockRestore.save(this.p, l);
    BlockRestore.save(this.p, l2);
    BlockRestore.save(this.p, l3);
    BlockRestore.save(this.p, l4);
    BlockRestore.save(this.p, l5);
    BlockRestore.save(this.p, l6);
    BlockRestore.save(this.p, l7);
    BlockRestore.save(this.p, l8);
    BlockRestore.save(this.p, l9);

    BlockRestore.save(this.p, l10);
    BlockRestore.save(this.p, l11);
    BlockRestore.save(this.p, l12);
    BlockRestore.save(this.p, l13);
    BlockRestore.save(this.p, l14);
    BlockRestore.save(this.p, l15);
    BlockRestore.save(this.p, l16);
    BlockRestore.save(this.p, l17);

    l2.getBlock().setType(this.m);
    l3.getBlock().setType(this.m);
    l4.getBlock().setType(this.m);
    l5.getBlock().setType(this.m);
    l6.getBlock().setType(this.m);
    l7.getBlock().setType(this.m);
    l8.getBlock().setType(this.m);
    l9.getBlock().setType(this.m);

    l10.getBlock().setType(this.m);
    l11.getBlock().setType(this.m);
    l12.getBlock().setType(this.m);
    l13.getBlock().setType(this.m);
    l14.getBlock().setType(this.m);
    l15.getBlock().setType(this.m);
    l16.getBlock().setType(this.m);
    l17.getBlock().setType(this.m);

    l2.getBlock().setData(this.data);
    l3.getBlock().setData(this.data);
    l4.getBlock().setData(this.data);
    l5.getBlock().setData(this.data);
    l6.getBlock().setData(this.data);
    l7.getBlock().setData(this.data);
    l8.getBlock().setData(this.data);
    l9.getBlock().setData(this.data);

    l10.getBlock().setData(this.data);
    l11.getBlock().setData(this.data);
    l12.getBlock().setData(this.data);
    l13.getBlock().setData(this.data);
    l14.getBlock().setData(this.data);
    l15.getBlock().setData(this.data);
    l16.getBlock().setData(this.data);
    l17.getBlock().setData(this.data);

    for (Player p2 : UtilServer.getPlayers()) {
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l2.add(0.0D, 0.6000000238418579D, 0.0D));
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l3.add(0.0D, 0.6000000238418579D, 0.0D));
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l4.add(0.0D, 0.6000000238418579D, 0.0D));
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l5.add(0.0D, 0.6000000238418579D, 0.0D));
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l6.add(0.0D, 0.6000000238418579D, 0.0D));
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l7.add(0.0D, 0.6000000238418579D, 0.0D));
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l8.add(0.0D, 0.6000000238418579D, 0.0D));
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l9.add(0.0D, 0.6000000238418579D, 0.0D));

      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l10.add(0.0D, 0.6000000238418579D, 0.0D));
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l11.add(0.0D, 0.6000000238418579D, 0.0D));
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l12.add(0.0D, 0.6000000238418579D, 0.0D));
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l13.add(0.0D, 0.6000000238418579D, 0.0D));
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l14.add(0.0D, 0.6000000238418579D, 0.0D));
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l15.add(0.0D, 0.6000000238418579D, 0.0D));
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l16.add(0.0D, 0.6000000238418579D, 0.0D));
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l17.add(0.0D, 0.6000000238418579D, 0.0D));
    }
  }

  @SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
public void buildRectangle4()
  {
    Location l = new Location(this.location.getWorld(), this.location.getX(), this.location.getY(), this.location.getZ());
    l.setY(l.getY() + 1.0D);

    Location l6 = new Location(this.p.getWorld(), l.getX() + 2.0D, l.getY() - 1.0D, l.getZ() - 2.0D);
    Location l7 = new Location(this.p.getWorld(), l.getX() - 2.0D, l.getY() - 1.0D, l.getZ() + 2.0D);
    Location l8 = new Location(this.p.getWorld(), l.getX() - 2.0D, l.getY() - 1.0D, l.getZ() - 2.0D);
    Location l9 = new Location(this.p.getWorld(), l.getX() + 2.0D, l.getY() - 1.0D, l.getZ() + 2.0D);

    Location l10 = new Location(this.p.getWorld(), l.getX() + 2.0D, l.getY() - 1.0D, l.getZ() - 1.0D);
    Location l11 = new Location(this.p.getWorld(), l.getX() + 2.0D, l.getY() - 1.0D, l.getZ() + 1.0D);
    Location l12 = new Location(this.p.getWorld(), l.getX() + 1.0D, l.getY() - 1.0D, l.getZ() + 2.0D);
    Location l13 = new Location(this.p.getWorld(), l.getX() - 1.0D, l.getY() - 1.0D, l.getZ() + 2.0D);
    Location l14 = new Location(this.p.getWorld(), l.getX() + 1.0D, l.getY() - 1.0D, l.getZ() - 2.0D);
    Location l15 = new Location(this.p.getWorld(), l.getX() - 1.0D, l.getY() - 1.0D, l.getZ() - 2.0D);
    Location l16 = new Location(this.p.getWorld(), l.getX() - 2.0D, l.getY() - 1.0D, l.getZ() - 1.0D);
    Location l17 = new Location(this.p.getWorld(), l.getX() - 2.0D, l.getY() - 1.0D, l.getZ() + 1.0D);

    BlockRestore.save(this.p, l);
    BlockRestore.save(this.p, l6);
    BlockRestore.save(this.p, l7);
    BlockRestore.save(this.p, l8);
    BlockRestore.save(this.p, l9);

    BlockRestore.save(this.p, l10);
    BlockRestore.save(this.p, l11);
    BlockRestore.save(this.p, l12);
    BlockRestore.save(this.p, l13);
    BlockRestore.save(this.p, l14);
    BlockRestore.save(this.p, l15);
    BlockRestore.save(this.p, l16);
    BlockRestore.save(this.p, l17);

    l6.getBlock().setType(this.m);
    l7.getBlock().setType(this.m);
    l8.getBlock().setType(this.m);
    l9.getBlock().setType(this.m);

    l10.getBlock().setType(this.m);
    l11.getBlock().setType(this.m);
    l12.getBlock().setType(this.m);
    l13.getBlock().setType(this.m);
    l14.getBlock().setType(this.m);
    l15.getBlock().setType(this.m);
    l16.getBlock().setType(this.m);
    l17.getBlock().setType(this.m);

    l6.getBlock().setData(this.data);
    l7.getBlock().setData(this.data);
    l8.getBlock().setData(this.data);
    l9.getBlock().setData(this.data);

    l10.getBlock().setData(this.data);
    l11.getBlock().setData(this.data);
    l12.getBlock().setData(this.data);
    l13.getBlock().setData(this.data);
    l14.getBlock().setData(this.data);
    l15.getBlock().setData(this.data);
    l16.getBlock().setData(this.data);
    l17.getBlock().setData(this.data);

    for (Player p2 : UtilServer.getPlayers())
    {
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l6.add(0.0D, 0.6000000238418579D, 0.0D));
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l7.add(0.0D, 0.6000000238418579D, 0.0D));
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l8.add(0.0D, 0.6000000238418579D, 0.0D));
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l9.add(0.0D, 0.6000000238418579D, 0.0D));

      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l10.add(0.0D, 0.6000000238418579D, 0.0D));
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l11.add(0.0D, 0.6000000238418579D, 0.0D));
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l12.add(0.0D, 0.6000000238418579D, 0.0D));
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l13.add(0.0D, 0.6000000238418579D, 0.0D));
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l14.add(0.0D, 0.6000000238418579D, 0.0D));
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l15.add(0.0D, 0.6000000238418579D, 0.0D));
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l16.add(0.0D, 0.6000000238418579D, 0.0D));
      UtilityBlock.sendBreak(p2, this.m.getId(), this.data, l17.add(0.0D, 0.6000000238418579D, 0.0D));
    }

    TreasureChestManager.treasureChest.put(this.p.getUniqueId(), new ArrayList());
  }

  public void buildChest(final ParticleLib18.ParticleType particle, final ParticleLib18.ParticleType placeParticle)
  {
    Location l = new Location(this.location.getWorld(), this.location.getX(), this.location.getY(), this.location.getZ());

    final Location l2 = new Location(this.p.getWorld(), l.getX() + 2.0D, l.getY(), l.getZ());
    final Location l3 = new Location(this.p.getWorld(), l.getX(), l.getY(), l.getZ() + 2.0D);
    final Location l4 = new Location(this.p.getWorld(), l.getX(), l.getY(), l.getZ() - 2.0D);
    final Location l5 = new Location(this.p.getWorld(), l.getX() - 2.0D, l.getY(), l.getZ());

    BlockRestore.save(this.p, l);
    BlockRestore.save(this.p, l2);
    BlockRestore.save(this.p, l3);
    BlockRestore.save(this.p, l4);
    BlockRestore.save(this.p, l5);
    if (this.p.isValid()) {
      TreasureChestManager.playSpiral(l2, this.p, particle, placeParticle);
      Bukkit.getServer().getScheduler()
        .runTaskLater(Main.getInstance(), new Runnable()
      {
        @SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
		public void run() {
          if (BuildManager.this.p.isValid())
          {
            l2.getBlock().setType(BuildManager.this.m);
            Chest chest = (Chest)l2.getBlock().getState();
            UtilityBlock.blockToRestore.add(l2.getBlock());
            l2.getWorld().playSound(l2, Sound.ANVIL_LAND, 1.0F, 1.0F);
            UtilityBlock.sendBreak(BuildManager.this.p, 54, (byte)0, l2);
            chest.getBlock().setData((byte)4);
            ((ArrayList)TreasureChestManager.treasureChest.get(BuildManager.this.p.getUniqueId())).add(l2.getBlock());
            TreasureChestManager.playSpiral(l3, BuildManager.this.p, particle, placeParticle);
          }
        }
      }
      , 40L);
      Bukkit.getServer().getScheduler()
        .runTaskLater(Main.getInstance(), new Runnable()
      {
        @SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
		public void run() {
          if (BuildManager.this.p.isValid()) {
            l3.getBlock().setType(BuildManager.this.m);
            Chest chest = (Chest)l3.getBlock().getState();
            UtilityBlock.blockToRestore.add(l3.getBlock());
            l3.getWorld().playSound(l3, Sound.ANVIL_LAND, 1.0F, 1.0F);
            UtilityBlock.sendBreak(BuildManager.this.p, 54, (byte)0, l3);
            chest.getBlock().setData((byte)2);
            ((ArrayList)TreasureChestManager.treasureChest.get(BuildManager.this.p.getUniqueId())).add(l3.getBlock());
            TreasureChestManager.playSpiral(l4, BuildManager.this.p, particle, placeParticle);
          }
        }
      }
      , 80L);
      Bukkit.getServer().getScheduler()
        .runTaskLater(Main.getInstance(), new Runnable()
      {
        @SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
		public void run() {
          if (BuildManager.this.p.isValid()) {
            l4.getBlock().setType(BuildManager.this.m);
            Chest chest = (Chest)l4.getBlock().getState();
            UtilityBlock.blockToRestore.add(l4.getBlock());
            l4.getWorld().playSound(l4, Sound.ANVIL_LAND, 1.0F, 1.0F);
            UtilityBlock.sendBreak(BuildManager.this.p, 54, (byte)0, l4);
            chest.getBlock().setData((byte)3);
            ((ArrayList)TreasureChestManager.treasureChest.get(BuildManager.this.p.getUniqueId())).add(l4.getBlock());
            TreasureChestManager.playSpiral(l5, BuildManager.this.p, particle, placeParticle);
          }
        }
      }
      , 120L);

      Bukkit.getServer().getScheduler()
        .runTaskLater(Main.getInstance(), new Runnable()
      {
        @SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
		public void run() {
          if (BuildManager.this.p.isValid()) {
            l5.getBlock().setType(BuildManager.this.m);
            Chest chest = (Chest)l5.getBlock().getState();
            UtilityBlock.blockToRestore.add(l5.getBlock());
            l4.getWorld().playSound(l5, Sound.ANVIL_LAND, 1.0F, 1.0F);
            UtilityBlock.sendBreak(BuildManager.this.p, 54, (byte)0, l5);
            chest.getBlock().setData((byte)5);
            ((ArrayList)TreasureChestManager.treasureChest.get(BuildManager.this.p.getUniqueId())).add(l5.getBlock());
          }
        }
      }
      , 160L);
    }
  }
}