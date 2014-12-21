package me.jrl1004.lightcraft.utils;

import java.util.Random;

import me.jrl1004.lightcraft.gadgets.seekers.SeekerBeam;
import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class JacobCommandHandler implements Listener {

	private static Random random;
	static Main plugin;
	public static JacobCommandHandler instance2;
	private static boolean _setup = false;

	public static void setup() {
		plugin = Main.getInstance();
		instance2 = new JacobCommandHandler();
		random = new Random();
		_setup = true;
	}

	public static boolean command(CommandSender sender, Command cmd,
			String label, String[] a) {
		String name = cmd.getName();
		if (!_setup)
			setup();
		if (name.equalsIgnoreCase("Seeker")) {
			if (sender instanceof Player == false)
				return false;
			Player s = (Player) sender;
			if (a.length == 0) {
				s.sendMessage("/Seeker <target>");
				return true;
			}
			Player t = Bukkit.getPlayer(a[0]);
			if (t == null) {
				s.sendMessage("Player is offline");
				return true;
			}
			System.out.println("Begin Launch");
			SeekerBeam.launchBeam(s.getEyeLocation(), t.getEyeLocation(), 50,
					Math.sin(random.nextInt(4)), Math.sin(random.nextInt(4)),
					Math.sin(random.nextInt(4)));
			return true;
		}

		return false;
	}
}
