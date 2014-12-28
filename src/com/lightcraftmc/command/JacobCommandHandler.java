package com.lightcraftmc.command;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import com.lightcraftmc.fusebox.tools.SelectionTool;
import com.lightcraftmc.fusebox.util.particles18.ParticleLib18.ParticleType;
import com.lightcraftmc.hub.main.Main;
import com.lightcraftmc.lightcraft.gadgets.arcs.ArcDataSaver;
import com.lightcraftmc.lightcraft.gadgets.arcs.ParticleArc;

public class JacobCommandHandler implements Listener {
	private JacobCommandHandler() {
	}

	static Main plugin;
	private static boolean _setup = false;

	public static void setup() {
		plugin = Main.getInstance();
		_setup = true;
	}

	public static boolean command(CommandSender sender, Command cmd,
			String label, String[] a) {
		String name = cmd.getName();
		if (!_setup)
			setup();
		if (name.equalsIgnoreCase("Arc")) {
			if (sender instanceof Player == false) {
				sender.sendMessage("You must be a player to use this command");
				return true;
			}
			final Player player = (Player) sender;
			if (a.length < 3) {
				sender.sendMessage("Usage: /Arc <Particle> <CurveHeight> <Seconds>");
				return true;
			}
			ParticleType type;
			try {
				type = ParticleType.valueOf(a[0].toUpperCase());
			} catch (Exception exc) {
				sender.sendMessage("Invalid particle type");
				return true;
			}
			double h, s;
			try {
				h = Double.parseDouble(a[1]);
				s = Double.parseDouble(a[2]);
			} catch (NumberFormatException exc) {
				sender.sendMessage("Invalid number supplied");
				return true;
			}
			if (!(SelectionTool.getInstance().hasLocation1(player) && SelectionTool
					.getInstance().hasLocation2(player))) {
				player.sendMessage("Make sure you have a valid selection!");
				return true;
			}
			Location l1 = SelectionTool.getInstance().getLocation1(player);
			Location l2 = SelectionTool.getInstance().getLocation2(player);
			ParticleArc arc = new ParticleArc(type, l1, l2, h, s);
			ArcDataSaver.addArcToSaveList(arc);
			arc.beginArcing();
			return true;
		}
		return false;
	}
}
