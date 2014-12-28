package me.mike1665.Main;

import java.util.HashMap;
import java.util.UUID;

import mc.jrl1004.lightcraft.utils.JacobCommandHandler;
import me.jrl1004.setup.JacobEventSetup;
import me.mike1665.coinapi.ApiEvent;
import me.mike1665.commands.AmmoTest;
import me.mike1665.commands.GiveAmmo;
import me.mike1665.commands.MikeCommandHandler;
import me.mike1665.commands.MountUnlocked;
import me.mike1665.commands.StatsCommand;
import me.mike1665.commands.UnlockAllArmor;
import me.mike1665.eventhandlers.MikeEventSetup;
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

import com.arrayprolc.command.ArrayCommandHandler;
import com.arrayprolc.event.ArrayEventSetup;

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
