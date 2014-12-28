package com.lightcraftmc.hub.main;

import java.util.HashMap;
import java.util.UUID;

import me.mike1665.coinapi.ApiEvent;
import me.mike1665.update.Updater;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;

import com.lightcraftmc.command.AmmoTest;
import com.lightcraftmc.command.ArrayCommandHandler;
import com.lightcraftmc.command.GiveAmmo;
import com.lightcraftmc.command.JacobCommandHandler;
import com.lightcraftmc.command.MikeCommandHandler;
import com.lightcraftmc.command.MountUnlocked;
import com.lightcraftmc.command.StatsCommand;
import com.lightcraftmc.command.UnlockAllArmor;
import com.lightcraftmc.event.handlers.MikeEventSetup;
import com.lightcraftmc.event.setup.ArrayEventSetup;
import com.lightcraftmc.event.setup.JacobEventSetup;

public class Main extends JavaPlugin implements Listener, PluginMessageListener {

	public Scoreboard board;
	public String tag = ChatColor.RED + "" + ChatColor.BOLD + "Parkour " + ChatColor.RESET + "" + ChatColor.DARK_GRAY + "> ";
	static Main instance;
	protected HashMap<UUID, Vector> velocities;
	protected HashMap<UUID, Location> positions;
	protected HashMap<UUID, Boolean> onGround;
	public ApiEvent ae = new ApiEvent();

	public void onEnable() {
		instance = this;
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Updater(this), 1L, 1L);
		ArrayEventSetup.setupEvents();
		ArrayCommandHandler.setup();
		JacobEventSetup.setupEvents();
		MikeEventSetup.setupEvents();
	}

	public void onDisable(){
		MikeEventSetup.disable();
		ArrayEventSetup.disable();
		JacobEventSetup.disable();
	}

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		ArrayEventSetup.onPluginMessageReceived(channel, player, message);
	} 

	public void saveFile() {
		this.saveConfig();
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {

		if (ArrayCommandHandler.command(sender, cmd, label, a)) return true;
		if (StatsCommand.command(sender, cmd, label, a)) return true;
		if (MountUnlocked.onCommand(sender, cmd, label, a)) return true;
		if (AmmoTest.onCommand(sender, cmd, label, a)) return true;
		if (GiveAmmo.onCommand(sender, cmd, label, a)) return true;
		if (UnlockAllArmor.onCommand(sender, cmd, label, a)) return true;
		if (MikeCommandHandler.onCommand(sender, cmd, label, a)) return true;
		if (JacobCommandHandler.command(sender, cmd, label, a)) return true;

		return false;

	}
	
	public static Main getInstance() {
		return instance;
	}
}
