package me.mike1665.utils;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class UtilServer {
	
	public static Collection<? extends Player> getPlayers() {
		return getServer().getOnlinePlayers();
	}
	
	public static Server getServer() {
		return Bukkit.getServer();
	}

}
