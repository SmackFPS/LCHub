package com.arrayprolc.command;

import java.util.Iterator;
import java.util.Set;

import me.mike1665.Main.Main;
import net.minecraft.server.v1_8_R1.NBTBase;
import net.minecraft.server.v1_8_R1.NBTTagCompound;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import com.arrayprolc.rank.RankManager;
import com.arrayprolc.rank.ServerRank;
import com.arrayprolc.speedways.SeekerSkull;

public class ArrayCommandHandler
{

	static Main plugin;

	public static void setup(Main instance)
	{
		plugin = instance;
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
				me.mike1665.coinapi.LcCoinsAPI.givePoints(p, amount);
				me.mike1665.coinapi.ApiEvent.scoreboard(p);
				return true;
			}
			case "token":
				me.mike1665.coinapi.LcTokensAPI.givePoints(p, amount);
				me.mike1665.coinapi.ApiEvent.scoreboard(p);
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
			Player _p = (Player) sender;
			CraftPlayer _c = (CraftPlayer) _p;
			net.minecraft.server.v1_8_R1.World world = _c.getHandle().getWorld();
			SeekerSkull skull = new SeekerSkull(world, _c.getHandle(), 1, 1, 1);
			world.addEntity(skull, SpawnReason.CUSTOM);
			skull.teleportTo(_c.getLocation().add(0, 5, 0), false);
			NBTTagCompound s = skull.getNBTTag();
			if (s == null)
			{
				Bukkit.broadcastMessage("Null skull");
				s = new NBTTagCompound();
			}
			Bukkit.broadcastMessage(s.isEmpty() + " | " + s);
			Set<NBTBase> b = s.c();
			Iterator<NBTBase> iter = b.iterator();
			while (iter.hasNext())
				Bukkit.broadcastMessage(iter.next() + "");
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

}
