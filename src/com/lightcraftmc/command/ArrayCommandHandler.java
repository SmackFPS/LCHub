package com.lightcraftmc.command;

import java.lang.reflect.Method;

import net.minecraft.server.v1_8_R1.EntityTypes;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Wolf;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import com.lightcraftmc.fusebox.pet.Pet;
import com.lightcraftmc.fusebox.util.UtilEnt;
import com.lightcraftmc.fusebox.util.UtilMath;
import com.lightcraftmc.hub.main.Main;
import com.lightcraftmc.rank.RankManager;
import com.lightcraftmc.rank.ServerRank;
import com.lightcraftmc.speedways.CustomEntityType;

public class ArrayCommandHandler implements Listener
{

	static Main plugin;
	public static ArrayCommandHandler instance2;
	public static void setup()
	{
		plugin = Main.getInstance();
		instance2 = new ArrayCommandHandler();
		Bukkit.getPluginManager().registerEvents(instance2, Main.getInstance());
	}

	public static boolean command(CommandSender sender, Command cmd, String label, String[] a)
	{
		if (label.equalsIgnoreCase("g-reward"))
		{
			if (sender instanceof Player)
				return false;
			Player p = Bukkit.getPlayer(a[0]);
			int amount = Integer.parseInt(a[2]);
			switch (a[1].toLowerCase())
			{

			case "coin":
			{
				com.lightcraftmc.coinapi.LcCoinsAPI.givePoints(p, amount);
				com.lightcraftmc.coinapi.ApiEvent.scoreboard(p);
				return true;
			}
			case "token":
				com.lightcraftmc.coinapi.LcTokensAPI.givePoints(p, amount);
				com.lightcraftmc.coinapi.ApiEvent.scoreboard(p);
				return true;
			}
		}
		if (label.equalsIgnoreCase("sheeplol") && ((Player) sender).isOp())
		{
			try
			{
				((Player) sender).getPassenger().eject();
				((Player) sender).eject();
				// ((Player) sender).spigot().
			} catch (Exception ex)
			{
			}
			for (Entity e : ((Player) sender).getNearbyEntities(5, 5, 5))
			{
				if (e instanceof Sheep)
				{
					((Player) sender).setPassenger(e);
					return false;
				}
			}
		}
		if (label.equalsIgnoreCase("rank"))
		{
			if (sender instanceof Player)
			{
				if (!((Player) sender).isOp())
					return false;
			}
			Player p = Bukkit.getPlayer(a[0]);
			ServerRank r = RankManager.getRankFromString(a[1]);
			RankManager.setRank(p, r);

		}
		if (label.equalsIgnoreCase("test"))
		{
			if(a.length == 1){
				Wolf w = ((Player) sender).getWorld().spawn(((Player) sender).getLocation(), Wolf.class);
				new BukkitRunnable() {
					public void run() {
						UtilEnt.CreatureMove(w, w.getLocation().add(UtilMath.randInt(-5, 5),UtilMath.randInt(-5, 5),UtilMath.randInt(-5, 5)), 2f);
					}
				}.runTaskTimer(Main.getInstance(), 0, 1);
				return true;
			}
			try{
			Pet pet = new Pet(EntityType.WOLF, (Player)sender);	
			}catch(Exception ex){
				for(StackTraceElement s : ex.getStackTrace()){
					
					sender.sendMessage(s.toString());
				}
				
			}
		}
		return false;
	}

	static boolean isOnline(Player p)
	{
		for (Player p2 : Bukkit.getOnlinePlayers())
		{
			if (p2.getName().equalsIgnoreCase(p.getName()))
			{
				return true;
			}
		}
		return false;
	}

	private static void registerEntities()
	{
		for (CustomEntityType entity : CustomEntityType.values())
		{
			try
			{
				Method a = EntityTypes.class.getDeclaredMethod("a", new Class<?>[] { Class.class, String.class, int.class });
				a.setAccessible(true);
				a.invoke(null, entity.getCustomClass(), entity.getName(), entity.getID());
			} catch (Exception e)
			{
				e.printStackTrace();
				Bukkit.broadcastMessage(e.getCause() + "");
			}
		}
	}
}
