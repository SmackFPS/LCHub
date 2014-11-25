package com.arrayprolc.command;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;

import me.mike1665.Main.Main;

public class ArrayCommandHandler {

	static Main plugin;
	
	public static void setup(Main instance){
		plugin = instance;
	}
	
	public static boolean command(CommandSender sender, Command cmd, String label, String[] a){
		return false;
	}
	
}
