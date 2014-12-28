package me.mike1665.mount.mounts;

import java.util.UUID;

import me.mike1665.coinapi.LcCoinsAPI;
import me.mike1665.mount.MountManager;
import me.mike1665.particles18.ParticleLib18;
import net.lightcraftmc.fusebox.util.UtilityBlock;
import net.lightcraftmc.fusebox.util.strings.MessageType;
import net.lightcraftmc.fusebox.util.strings.StringList;
import net.lightcraftmc.fusebox.util.strings.StringManager;
import net.minecraft.server.v1_8_R1.AttributeInstance;
import net.minecraft.server.v1_8_R1.EntityInsentient;
import net.minecraft.server.v1_8_R1.GenericAttributes;
import net.minecraft.server.v1_8_R1.PathEntity;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.lightcraftmc.hub.main.Main;

public class AngelRider
  implements Listener
{
  private static Main plugin;
  
  public static void initialize()
  {
    AngelRider.plugin = Main.getInstance();
  }
  
  public static boolean playAngelRider(Player p)
  {
    UUID pn = p.getPlayer().getUniqueId();
    boolean check = plugin.getConfig().getBoolean(pn + ".AngelMount");
    if ((!check) && (LcCoinsAPI.hasEnough(p, 10000)))
    {
      LcCoinsAPI.takePoints(p, 10000);
      plugin.getConfig().set(pn + ".AngelMount", Boolean.valueOf(true));
      plugin.saveFile();
      p.sendMessage(StringManager.getPrefix(MessageType.INFO) + 
        ChatColor.GREEN + ChatColor.BOLD + 
        "Mount Purchased!");
      p.sendMessage(StringManager.getPrefix(MessageType.INFO) + 
        ChatColor.AQUA + 
        "Note: Click on your mount again to spawn your new mount! ");
      return true;
    }
    if (check)
    {
      if (Bukkit.getWorld(StringList.mainWorld) != null)
      {
        World w = p.getWorld();
        double x = plugin.getConfig().getDouble("mount.x");
        double y = plugin.getConfig().getDouble("mount.y");
        double z = plugin.getConfig().getDouble("mount.z");
        Location mountspawnloc = new Location(w, x, y, z);
        
        MountManager.removeCurrentPet(p, false);
        
        Horse horse = (Horse)p.getWorld().spawn(mountspawnloc, 
          Horse.class);
        
        org.bukkit.entity.Entity entity = horse;
        Horse entityHorse = (Horse)entity;
        entityHorse.getInventory().setSaddle(
          new ItemStack(Material.SADDLE));
        
        horse.setCustomName(ChatColor.AQUA+ "" + ChatColor.BOLD + 
          p.getPlayer().getName() + ChatColor.RESET + 
          "'s Horse");
        horse.setCustomNameVisible(true);
        horse.setOwner(p);
        horse.setVariant(Horse.Variant.HORSE);
        horse.setColor(Horse.Color.WHITE);
        horse.setAdult();
        horse.setPassenger(p);
        
        horse.setMetadata("angelrider", new FixedMetadataValue(
          Main.getInstance(), "angelrider"));
        MountManager.pet.put(p.getUniqueId(), horse);
        PetFollow(p.getPlayer(), horse, 0.3D);
      }
      else
      {
        p.sendMessage(StringManager.getPrefix(MessageType.ERROR) + 
          ChatColor.DARK_RED + 
          "You cannot spawn mounts outside of the Hub world! ");
      }
    }
    else {
      p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Mounts" + ChatColor.RESET + ChatColor.DARK_GRAY + "> " + ChatColor.RED + "Insufficient Funds!");
    }
    return false;
  }
  
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		if ((event.getFrom().getBlockX() != event.getTo().getBlockX())
				|| (event.getFrom().getBlockZ() != event.getTo().getBlockZ())) {
			Player p = event.getPlayer();
			Entity e = p.getVehicle();

			if ((UtilityBlock.frostyBlock.contains(p.getLocation().getBlock()))
					&& (!p.isInsideVehicle())) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100,
						2));
				p.addPotionEffect(new PotionEffect(
						PotionEffectType.SLOW_DIGGING, 100, 5));
        	  	ParticleLib18  snow = new ParticleLib18(me.mike1665.particles18.ParticleLib18.ParticleType.SNOW_SHOVEL, 0.1000000014901161D, 10, 2.0D);
        	  	ParticleLib18  snow2 = new ParticleLib18(me.mike1665.particles18.ParticleLib18.ParticleType.SNOWBALL, 0.1000000014901161D, 10, 2.0D);
        	  	snow.sendToLocation(p.getLocation().add(0.0D, 1.0D, 0.0D));
        	  	snow2.sendToLocation(p.getLocation().add(0.0D, 1.0D, 0.0D));
				UtilityBlock.sendBreak(p, 80, (byte) 0, p.getLocation()
						.getBlock().getLocation());
			}

			if ((e instanceof Horse)) {
				if (e.hasMetadata("angelrider")) {
					int id = 80;
					int height = -1;
					double r = Math.random();
					if (r > 0.8D) {
						id = 79;
						height = -1;
					} else if (r > 0.6D) {
						id = 174;
						height = -1;
					} else if (r > 0.4D) {
						id = 78;
						height = 0;
					}
					for (Block block : UtilityBlock
							.getInRadius(
									((Horse) MountManager.pet.get(p
											.getUniqueId())).getLocation(),
									3.5D, true).keySet()) {
						if ((p.getLocation().getBlock().getType() != Material.WATER)
								&& (p.getLocation().getBlock().getType() != Material.STATIONARY_WATER)
								&& (p.getLocation().getBlock().getType() != Material.SNOW_BLOCK)) {
							if (UtilityBlock.solid(block)) {
								if (!UtilityBlock.blockToRestore
										.contains(block)) {
									Block b = UtilityBlock.getHighest(block
											.getWorld(), block.getLocation()
											.getBlockX(), block.getLocation()
											.getBlockZ());

									Location l = b.getLocation().add(0.0D,
											height, 0.0D);
									UtilityBlock.setBlockToRestore(
											l.getBlock(), id, (byte) 0, 4L,
											true, true, false);
								}
							}
						}
					}
				}
			}
		}
	}
	
	public static void PetFollow(final Player player , final Entity pet , final double speed){
		new BukkitRunnable(){
		public void run(){
		if ((!pet.isValid() || (!player.isOnline()))){
		this.cancel();}
		net.minecraft.server.v1_8_R1.Entity pett = ((CraftEntity) pet).getHandle();
		((EntityInsentient) pett).getNavigation().a(2);
		Object petf = ((CraftEntity) pet).getHandle();
		Location targetLocation = player.getLocation();
		PathEntity path;
		path = ((EntityInsentient) petf).getNavigation().a(targetLocation.getX() + 1, targetLocation.getY(), targetLocation.getZ() + 1);
		if (path != null) {
		((EntityInsentient) petf).getNavigation().a(path, 1.0D);
		((EntityInsentient) petf).getNavigation().a(2.0D);}
		int distance = (int) Bukkit.getPlayer(player.getName()).getLocation().distance(pet.getLocation());
		if (distance > 10 && !pet.isDead() && player.isOnGround()) {
		pet.teleport(player.getLocation());}
		AttributeInstance attributes = ((EntityInsentient)((CraftEntity)pet).getHandle()).getAttributeInstance(GenericAttributes.d);
		attributes.setValue(speed);}}.runTaskTimer(Main.getInstance(), 0L, 20L);}
}


/* Location:           A:\LC\Lobby\plugins\HubPlugin.jar
 * Qualified Name:     me.mike1665.mount.mounts.AngelRider
 * JD-Core Version:    0.7.0.1
 */