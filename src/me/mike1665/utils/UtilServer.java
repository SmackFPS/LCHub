package me.mike1665.utils;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class UtilServer {
	
	public static Player[] getPlayers() {
		return getServer().getOnlinePlayers();
	}
	
	public static Server getServer() {
		return Bukkit.getServer();
	}

}
