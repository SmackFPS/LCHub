package fr.coco_gigpn.prodigygadget.util;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class UtilServer
{


	public static Player[] getPlayers()
	{
		return getServer().getOnlinePlayers();
	}

	@SuppressWarnings("deprecation")
	public static Player getPlayer(String name)
	{
		return Bukkit.getPlayer(name);
	}

	public static Server getServer()
	{
		return Bukkit.getServer();
	}

	public static double getFilledPercent()
	{
		return getPlayers().length / getServer().getMaxPlayers();
	}
}
