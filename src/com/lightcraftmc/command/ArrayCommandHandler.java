package com.lightcraftmc.command;

import java.lang.reflect.Method;
import java.util.HashMap;

import net.minecraft.server.v1_8_R1.EntityTypes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import com.lightcraftmc.effects.EffectManager;
import com.lightcraftmc.extra.ExtraManager;
import com.lightcraftmc.fusebox.menu.Menu;
import com.lightcraftmc.fusebox.menu.UtilMenu;
import com.lightcraftmc.fusebox.util.item.ItemTools;
import com.lightcraftmc.fusebox.util.strings.UtilString;
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
		if(label.equalsIgnoreCase("extrafx")){
			Player p = (Player)sender;
			if(!p.isOp()) return false;
			if(a.length == 0){
				ExtraManager.removeExtraEffect(p);
				sender.sendMessage("§cExtra Effects disabled.");
				return true;
			}else{
				for(EffectManager.EffectType e : EffectManager.EffectType.values()){
					if(e.toString().equalsIgnoreCase(a[0])){
						ExtraManager.removeExtraEffect(p);
						ExtraManager.Activate(p, e);
						sender.sendMessage("§aSuccess!");
					}
				}
				sender.sendMessage("§a/extrafx §7CircleEffect, FlameRing, GreenSpiral, Cloud, CloudSnow, CloudLight, Helix, Tornado, Vortex, FlameLilly, HourGlass, Shield, Fountain, GreenRing");

			}
		}

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
			/*try{
				EntityType en = EntityType.PIG;
				for(EntityType e2 : EntityType.values()){
					try{
						if(a[0].equalsIgnoreCase(e2.toString())){
							en = e2;
						}
					}catch(Exception ex){}
				}
			Pet pet = new Pet(en, (Player)sender);	
			}catch(Exception ex){
				for(StackTraceElement s : ex.getStackTrace()){

					sender.sendMessage(s.toString());
				}

			}*/
		}
		return false;
	}

	public static boolean isExclusion(EntityType e, EntityType[] types){
		for(EntityType e2 : types){
			if(e == e2){
				return true;
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
