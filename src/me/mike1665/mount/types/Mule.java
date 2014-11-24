package me.mike1665.mount.types;

import me.mike1665.Main.Main;
import me.mike1665.coinapi.PixlCoinsAPI;
import me.mike1665.coinapi.PixlPointsAPI;
import me.mike1665.utils.UtilAlg;
import me.mike1665.utils.UtilMath;
import net.minecraft.server.v1_7_R4.EntityCreature;
import net.minecraft.server.v1_7_R4.Navigation;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftCreature;
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

public class Mule implements Listener{
	
	Player p;
	Entity ent;
	private static Main plugin;	
	public static void initialize(Main plugin){
		Mule.plugin = plugin;
	}
	
	public void spawnMule(final Player player) {
		String pn = player.getPlayer().getName();
	   boolean check = plugin.getConfig().getBoolean(pn + ".MuleMount");
	    if (!check && PixlPointsAPI.hasEnough(player, 100)) {
	    	PixlCoinsAPI.takePoints(player, 100);
			plugin.getConfig().set(pn + ".MuleMount", true);
			plugin.saveFile();
			player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Mounts" + ChatColor.RESET + "" + ChatColor.DARK_GRAY + "> " + ChatColor.YELLOW + "" + ChatColor.BOLD + "Mount unlocked!");
			player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Mounts" + ChatColor.RESET + "" + ChatColor.DARK_GRAY + "> " + ChatColor.AQUA + "Note: Click on your mount again to spawn your new mount! ");
	    } else if (check) {
    		Entity ent = (org.bukkit.entity.Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
    		final Horse horse = (Horse) ent;
    		horse.setCustomName(ChatColor.AQUA + "" + ChatColor.BOLD + "" + player.getName() + "'s " + ChatColor.WHITE + "Mule");
    		horse.setAdult();
    		horse.setAgeLock(true);
    		horse.setColor(Color.BLACK);
    		horse.setStyle(Style.BLACK_DOTS);
    		horse.setVariant(Variant.MULE);
    		horse.setOwner(player);
    		horse.setCustomNameVisible(true);
    		horse.setTamed(true);
			horse.setMaxDomestication(1);
			horse.setJumpStrength(1.0D);
			horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
			horse.setCarryingChest(true);
			horse.setOwner(player);
	    	player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Mounts" + ChatColor.RESET + "" + ChatColor.DARK_GRAY + "> " + ChatColor.LIGHT_PURPLE + "Mount Spawned");
	    	new BukkitRunnable() {
				@Override
				public void run() {
			    	if (horse.isDead()) {
			    		cancel();
			    	}
			    	if (!player.getPlayer().isValid()) {
			    		horse.remove();
			    		cancel();
			    	}
			    	if(player.getPlayer().isValid()) {
			    		EntityCreature ec = ((CraftCreature)horse).getHandle();
			    		Navigation nav = ec.getNavigation();
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
	
	public void removeMule(Entity horse) {
		Entity h = ent;
		h.remove();
	}
	
}