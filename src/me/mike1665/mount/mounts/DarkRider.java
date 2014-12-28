package me.mike1665.mount.mounts;

import java.util.Iterator;
import java.util.UUID;

import me.mike1665.Main.Main;
import me.mike1665.coinapi.LcCoinsAPI;
import me.mike1665.mount.MountManager;
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
import org.bukkit.scheduler.BukkitRunnable;

public class DarkRider
  implements Listener
{
  private static Main plugin;
  
  public static void initialize()
  {
   DarkRider.plugin = Main.getInstance();
  }
  
  public static boolean playDarkRider(Player p)
  {
    UUID pn = p.getPlayer().getUniqueId();
    boolean check = plugin.getConfig().getBoolean(pn + ".DarkMount");
    if ((!check) && (LcCoinsAPI.hasEnough(p, 10000)))
    {
      LcCoinsAPI.takePoints(p, 10000);
      plugin.getConfig().set(pn + ".DarkMount", Boolean.valueOf(true));
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
        
        entityHorse.getInventory().setArmor(
          new ItemStack(Material.IRON_BARDING));
        horse.setCustomName(ChatColor.AQUA + "" + ChatColor.BOLD + 
          p.getPlayer().getName() + ChatColor.RESET + 
          "'s Horse");
        horse.setCustomNameVisible(true);
        horse.setOwner(p);
        horse.setVariant(Horse.Variant.HORSE);
        horse.setColor(Horse.Color.BLACK);
        horse.setAdult();
        horse.setPassenger(p);
        
        horse.setMetadata("darkrider", new FixedMetadataValue(
          Main.getInstance(), "darkrider"));
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
      p.sendMessage(ChatColor.GREEN  + ""+ ChatColor.BOLD + "Mounts" + ChatColor.RESET + ChatColor.DARK_GRAY + "> " + ChatColor.RED + "Insufficient Funds!");
    }
    return false;
  }
  
  @EventHandler
  public void onPlayerMove(PlayerMoveEvent event)
  {
    if ((event.getFrom().getBlockX() != event.getTo().getBlockX()) || 
      (event.getFrom().getBlockZ() != event.getTo().getBlockZ()))
    {
      Player p = event.getPlayer();
      org.bukkit.entity.Entity e = p.getVehicle();
      if (((e instanceof Horse)) && 
        (e.hasMetadata("darkrider")))
      {
        byte color = 2;
        double r = Math.random();
        if (r > 0.8D) {
          color = 15;
        } else if (r > 0.6D) {
          color = 11;
        } else if (r > 0.2D) {
          color = 12;
        }
        Iterator localIterator = 
          UtilityBlock.getInRadius(
          ((Horse)MountManager.pet.get(p
          .getUniqueId())).getLocation(), 
          2.5D, true).keySet().iterator();
        while (localIterator.hasNext())
        {
          Block block = (Block)localIterator.next();
          if ((UtilityBlock.solid(block)) && 
            (!UtilityBlock.blockToRestore.contains(block))) {
            UtilityBlock.setBlockToRestore(block, 159, 
              color, 1L, true, false, false);
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
 * Qualified Name:     me.mike1665.mount.mounts.DarkRider
 * JD-Core Version:    0.7.0.1
 */