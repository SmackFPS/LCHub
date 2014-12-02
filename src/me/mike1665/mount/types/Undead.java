package me.mike1665.mount.types;

import me.mike1665.Main.Main;
import me.mike1665.coinapi.LcCoinsAPI;
import me.mike1665.mounts.Mounts;
import me.mike1665.utils.UtilAlg;
import me.mike1665.utils.UtilMath;
import net.minecraft.server.v1_8_R1.EntityCreature;
import net.minecraft.server.v1_8_R1.NavigationAbstract;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftCreature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Undead implements Listener{
	
	Player p;
	Entity ent;
	public Mounts mount;
	private static Main plugin;
	
	public static void initialize(Main plugin){
		Undead.plugin = plugin;
	}
	
	public void spawnUndead(final Player player) {
		String pn = player.getPlayer().getName();
	   boolean check = plugin.getConfig().getBoolean(pn + ".UndeadMount");
	    if (!check && LcCoinsAPI.hasEnough(player, 10000)) {
	    	LcCoinsAPI.takePoints(player, 10000);
			plugin.getConfig().set(pn + ".UndeadMount", true);
			plugin.saveFile();
			player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Mount unlocked!");
			player.sendMessage(ChatColor.AQUA + "Note: Click on your mount again to spawn your new mount! ");
			
	    } else if (check) {
	    	mount.removeMount(player);
    		Entity ent = (org.bukkit.entity.Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
    		final Horse horse = (Horse) ent;
    		horse.setCustomName(ChatColor.AQUA + "" + ChatColor.BOLD + "" + player.getName() + "'s " + ChatColor.WHITE + "Horse");
    		horse.setAdult();
    		horse.setAgeLock(true);
    		horse.setColor(Color.BLACK);
    		horse.setStyle(Style.BLACK_DOTS);
    		horse.setVariant(Variant.SKELETON_HORSE);
    		horse.setOwner(player);
    		horse.setCustomNameVisible(true);
    		horse.setTamed(true);
			horse.setMaxDomestication(1);
			horse.setJumpStrength(0.8D);
			horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
			horse.setOwner(player);
	    	player.sendMessage(ChatColor.GREEN + "Mount Spawned");
	    	mount._active.put(player, horse);
	    	new BukkitRunnable() {
				@Override
				public void run() {
			    	//me.mike1665.particlelib.ParticleEffect.FLAME.display(horse.getLocation(), 0.0F, 1.0F, 0.0F, 0.0F, 2);
			    	//me.mike1665.particlelib.ParticleEffect.LAVA.display(horse.getLocation(), 0.0F, 1.0F, 0.0F, 0.0F, 2);
			    	if (horse.isDead()) {
			    		mount._active.remove(player);
			    		mount.removeMount(player);
			    		cancel();
			    	}
			    	if (!player.getPlayer().isValid()) {
			    		mount._active.remove(player);
			    		mount.removeMount(player);
			    		cancel();
			    	}
			    	if(player.getPlayer().isValid()) {
			    		EntityCreature ec = ((CraftCreature)horse).getHandle();
			    		NavigationAbstract nav = ec.getNavigation();
			    		Location target = player.getLocation().add(UtilAlg.getTrajectory(player, horse).multiply(2));
			    			if (UtilMath.offset(horse.getLocation(), target) > 3.0D) {
			    				target = horse.getLocation();
			    				target.add(UtilAlg.getTrajectory(horse, player).multiply(3));
			    				nav.a(target.getX(), target.getY(), target.getZ(), 1.399999976158142D);
			    			} else if (UtilMath.offset(horse, player) > 3.0D) {
			    				nav.a(target.getX(), target.getY(), target.getZ(), 1.399999976158142D);
			    			}
			    	}
 				}
			}.runTaskTimer(plugin, 6, 1);
	    } else { 
	    	player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Mounts" + ChatColor.RESET + "" + ChatColor.DARK_GRAY + "> " + ChatColor.RED + "Insufficient Funds!");
	    }
	}
}