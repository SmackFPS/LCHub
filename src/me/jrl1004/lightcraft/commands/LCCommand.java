package me.jrl1004.lightcraft.commands;

import java.util.Arrays;
import java.util.HashSet;

import me.jrl1004.lightcraft.commands.party.PartyCommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LCCommand implements CommandExecutor {

	private static LCCommand instance;
	private HashSet<LCSubCommand> commands = new HashSet<LCSubCommand>(Arrays.asList(new PartyCommand()));;

	private LCCommand() {
	}

	public static LCCommand getInstance() {
		if (instance == null)
			instance = new LCCommand();
		return instance;
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		LCSubCommand cmd = getByName(arg1.getName());
		if (cmd == null) {
			arg0.sendMessage("An error seems to have occured.");
			return false;
		}
		if (cmd.playerOnly() && arg0 instanceof Player == false) {
			arg0.sendMessage("This is a player-only command.");
			return false;
		}
		cmd.onCommand(arg0, arg3);
		return true;
	}

	private LCSubCommand getByName(String name) {
		for (LCSubCommand c : commands) {
			if (c.getKey().equalsIgnoreCase(name))
				return c;
		}
		return null;
	}

}
