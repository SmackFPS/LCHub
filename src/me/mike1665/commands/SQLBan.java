package me.mike1665.commands;

import me.mike1665.Main.Main;
import me.mike1665.mysql.MySQL;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SQLBan {

	public static Main plugin;
	
	public static void setup() {
		SQLBan.plugin = Main.getInstance();
	}
	
    public static boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player p = (Player) sender;
            /*if (cmd.getName().equalsIgnoreCase("sqlban")) {
                    if (args.length < 2) {
                            sender.sendMessage(ChatColor.RED + "/sqlban <username> <reason>");
                            return true;
                    }
                   
                    Player player = Bukkit.getServer().getPlayer(args[0]);
                   
                    if (player == null) {
                            sender.sendMessage(ChatColor.RED + "That player doesn't exist!");
                            return true;
                    }
                   
                    StringBuilder reasonBuilder = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                            reasonBuilder.append(args[i]).append(" ");
                    }
                   
                    String reason = reasonBuilder.toString();
                   
                    player.kickPlayer(reason);
                    me.mike1665.mysql.MySQL.banPlayer(player, reason);
            }*/
			return true;
    }
}
