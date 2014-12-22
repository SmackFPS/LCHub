package net.lightcraft.treasurechests;

import me.mike1665.Main.Main;
import me.mike1665.particles18.ParticleLib18;
import net.lightcraftmc.fusebox.util.UtilMath;
import net.lightcraftmc.fusebox.util.UtilityMath;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class BuildPlateform
{
		
  public static boolean isBuildable(Player p)
  {
    Location l = p.getLocation();
    Location l6 = new Location(p.getWorld(), l.getX() + 2.0D, l.getY(), l.getZ() - 2.0D);
    Location l7 = new Location(p.getWorld(), l.getX() - 2.0D, l.getY(), l.getZ() + 2.0D);
    Location l8 = new Location(p.getWorld(), l.getX() - 2.0D, l.getY(), l.getZ() - 2.0D);
    Location l9 = new Location(p.getWorld(), l.getX() + 2.0D, l.getY(), l.getZ() + 2.0D);

    Location l10 = new Location(p.getWorld(), l.getX() + 2.0D, l.getY(), l.getZ() - 1.0D);
    Location l11 = new Location(p.getWorld(), l.getX() + 2.0D, l.getY(), l.getZ() + 1.0D);
    Location l12 = new Location(p.getWorld(), l.getX() + 1.0D, l.getY(), l.getZ() + 2.0D);
    Location l13 = new Location(p.getWorld(), l.getX() - 1.0D, l.getY(), l.getZ() + 2.0D);
    Location l14 = new Location(p.getWorld(), l.getX() + 1.0D, l.getY(), l.getZ() - 2.0D);
    Location l15 = new Location(p.getWorld(), l.getX() - 1.0D, l.getY(), l.getZ() - 2.0D);
    Location l16 = new Location(p.getWorld(), l.getX() - 2.0D, l.getY(), l.getZ() - 1.0D);
    Location l17 = new Location(p.getWorld(), l.getX() - 2.0D, l.getY(), l.getZ() + 1.0D);

    Location l18 = new Location(p.getWorld(), l.getX() + 2.0D, l.getY() + 1.0D, l.getZ());
    Location l19 = new Location(p.getWorld(), l.getX(), l.getY() + 1.0D, l.getZ() + 2.0D);
    Location l20 = new Location(p.getWorld(), l.getX(), l.getY() + 1.0D, l.getZ() - 2.0D);
    Location l21 = new Location(p.getWorld(), l.getX() - 2.0D, l.getY() + 1.0D, l.getZ());
    Location l22 = new Location(p.getWorld(), l.getX() + 2.0D, l.getY() + 1.0D, l.getZ() - 2.0D);
    Location l23 = new Location(p.getWorld(), l.getX() - 2.0D, l.getY() + 1.0D, l.getZ() + 2.0D);
    Location l24 = new Location(p.getWorld(), l.getX() - 2.0D, l.getY() + 1.0D, l.getZ() - 2.0D);
    Location l25 = new Location(p.getWorld(), l.getX() + 2.0D, l.getY() + 1.0D, l.getZ() + 2.0D);

    Location l26 = new Location(p.getWorld(), l.getX() + 2.0D, l.getY() + 1.0D, l.getZ() - 1.0D);
    Location l27 = new Location(p.getWorld(), l.getX() + 2.0D, l.getY() + 1.0D, l.getZ() + 1.0D);
    Location l28 = new Location(p.getWorld(), l.getX() + 1.0D, l.getY() + 1.0D, l.getZ() + 2.0D);
    Location l29 = new Location(p.getWorld(), l.getX() - 1.0D, l.getY() + 1.0D, l.getZ() + 2.0D);
    Location l30 = new Location(p.getWorld(), l.getX() + 1.0D, l.getY() + 1.0D, l.getZ() - 2.0D);
    Location l31 = new Location(p.getWorld(), l.getX() - 1.0D, l.getY() + 1.0D, l.getZ() - 2.0D);
    Location l32 = new Location(p.getWorld(), l.getX() - 2.0D, l.getY() + 1.0D, l.getZ() - 1.0D);
    Location l33 = new Location(p.getWorld(), l.getX() - 2.0D, l.getY() + 1.0D, l.getZ() + 1.0D);

    if ((l6.getBlock().isEmpty()) && 
      (l7.getBlock().isEmpty()) && 
      (l8.getBlock().isEmpty()) && 
      (l10.getBlock().isEmpty()) && 
      (l11.getBlock().isEmpty()) && 
      (l12.getBlock().isEmpty()) && 
      (l13.getBlock().isEmpty()) && 
      (l14.getBlock().isEmpty()) && 
      (l15.getBlock().isEmpty()) && 
      (l16.getBlock().isEmpty()) && 
      (l17.getBlock().isEmpty()) && 
      (l18.getBlock().isEmpty()) && 
      (l19.getBlock().isEmpty()) && 
      (l20.getBlock().isEmpty()) && 
      (l21.getBlock().isEmpty()) && 
      (l22.getBlock().isEmpty()) && 
      (l23.getBlock().isEmpty()) && 
      (l24.getBlock().isEmpty()) && 
      (l25.getBlock().isEmpty()) && 
      (l26.getBlock().isEmpty()) && 
      (l27.getBlock().isEmpty()) && 
      (l28.getBlock().isEmpty()) && 
      (l29.getBlock().isEmpty()) && 
      (l30.getBlock().isEmpty()) && 
      (l31.getBlock().isEmpty()) && 
      (l32.getBlock().isEmpty()) && 
      (l33.getBlock().isEmpty()) && 
      (l17.getBlock().isEmpty()) && 
      (l9.getBlock().isEmpty()))
    {
      return true;
    }
    p.sendMessage("Area Must be Clear!");
    return false;
  }

  public static void randomPlateform(Player p, Location l)
  {
    int r = UtilMath.randInt(0, 4);
    switch (r){
    
    case 1:
        buildPlateform(p, l, Plateform.MUSHROOM);
        break;
    case 2:
        buildPlateform(p, l, Plateform.NETHER);
        break;
    case 3:
        buildPlateform(p, l, Plateform.WOOD);
        break;
    default:
    	buildPlateform(p, l, Plateform.FROST);
    	break;
    	
    }
  }
  
  public static void buildPlateform(final Player p, final Location l, Plateform plateform)
  {
    if (plateform == Plateform.WOOD) {
        new BuildManager(p, l, Material.LOG, (byte)7).buildRectangle2();
        Bukkit.getServer().getScheduler()
          .runTaskLater(Main.getInstance(), new Runnable() {
          public void run() {
            if (p.isValid())
              new BuildManager((Player) p, l, Material.SANDSTONE, (byte)0).buildRectangle3();
          }
        }
        , 20L);

        Bukkit.getServer().getScheduler()
          .runTaskLater(Main.getInstance(), new Runnable() {
          public void run() {
            if (p.isValid())
              new BuildManager((Player) p, l, Material.LEAVES, (byte)3).buildRectangle4();
          }
        }
        , 40L);

        Bukkit.getServer().getScheduler()
          .runTaskLater(Main.getInstance(), new Runnable() {
          public void run() {
            if (p.isValid())
              new BuildManager((Player) p, l, Material.CHEST, (byte)0).buildChest(ParticleLib18.ParticleType.VILLAGER_ANGRY, ParticleLib18.ParticleType.SLIME);
          }
        }
        , 60L);
      }

      if (plateform == Plateform.NETHER) {
        new BuildManager(p, l, Material.NETHERRACK, (byte)0).buildRectangle2();
        Bukkit.getServer().getScheduler()
          .runTaskLater(Main.getInstance(), new Runnable() {
          public void run() {
            if (p.isValid())
              new BuildManager((Player) p, l, Material.NETHER_BRICK, (byte)0).buildRectangle3();
          }
        }
        , 20L);

        Bukkit.getServer().getScheduler()
          .runTaskLater(Main.getInstance(), new Runnable() {
          public void run() {
            if (p.isValid())
              new BuildManager((Player) p, l, Material.NETHER_FENCE, (byte)0).buildRectangle4();
          }
        }
        , 40L);

        Bukkit.getServer().getScheduler()
          .runTaskLater(Main.getInstance(), new Runnable() {
          public void run() {
            if (p.isValid())
              new BuildManager((Player) p, l, Material.CHEST, (byte)0).buildChest(ParticleLib18.ParticleType.FLAME, ParticleLib18.ParticleType.LAVA);
          }
        }
        , 60L);
    }

    if (plateform == Plateform.FROST) {
      new BuildManager(p, l, Material.PACKED_ICE, (byte)0).buildRectangle2();
      Bukkit.getServer().getScheduler()
        .runTaskLater(Main.getInstance(), new Runnable() {
        public void run() {
          if (p.isValid())
            new BuildManager((Player) p, l, Material.SNOW_BLOCK, (byte)0).buildRectangle3();
        }
      }
      , 20L);

      Bukkit.getServer().getScheduler()
        .runTaskLater(Main.getInstance(), new Runnable() {
        public void run() {
          if (p.isValid())
            new BuildManager((Player) p, l, Material.STAINED_GLASS_PANE, (byte)0).buildRectangle4();
        }
      }
      , 40L);

      Bukkit.getServer().getScheduler()
        .runTaskLater(Main.getInstance(), new Runnable() {
        public void run() {
          if (p.isValid())
            new BuildManager((Player) p, l, Material.CHEST, (byte)0).buildChest(ParticleLib18.ParticleType.SNOW_SHOVEL, ParticleLib18.ParticleType.SNOWBALL);
        }
      }
      , 60L);
    }
    if (plateform == Plateform.MUSHROOM) {
      new BuildManager(p, l, Material.MYCEL, (byte)0).buildRectangle2();
      Bukkit.getServer().getScheduler()
        .runTaskLater(Main.getInstance(), new Runnable() {
        public void run() {
          if (p.isValid())
            new BuildManager((Player) p, l, Material.SMOOTH_BRICK, (byte)0).buildRectangle3();
        }
      }
      , 20L);

      Bukkit.getServer().getScheduler()
        .runTaskLater(Main.getInstance(), new Runnable() {
        public void run() {
          if (p.isValid())
            new BuildManager((Player) p, l, Material.COBBLE_WALL, (byte)0).buildRectangle4();
        }
      }
      , 40L);

      Bukkit.getServer().getScheduler()
        .runTaskLater(Main.getInstance(), new Runnable() {
        public void run() {
          if (p.isValid())
            new BuildManager((Player) p, l, Material.CHEST, (byte)0).buildChest(ParticleLib18.ParticleType.SMOKE_LARGE, ParticleLib18.ParticleType.REDSTONE);
        }
      }
      , 60L);
    }
    p.teleport(((Block)BuildManager.treasureChestLocation.get(p.getUniqueId())).getLocation().add(0.5D, 2.2D, 0.5D));
  }

  public static enum Plateform
  {
    WOOD, NETHER, FROST, MUSHROOM;
  }
}