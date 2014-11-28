package me.jrl1004.lightcraft.utils;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scheduler.BukkitRunnable;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class ProxiedParty implements PluginMessageListener {
	private static ProxiedParty instance;

	public ProxiedParty() {
		instance = this;
		Bukkit.getMessenger().registerIncomingPluginChannel(Main.instance, "BungeeCord", this);
		Bukkit.getMessenger().registerOutgoingPluginChannel(Main.instance, "BungeeCord");
	}

	public static ProxiedParty getInstance() {
		return instance;
	}

	@Override
	public void onPluginMessageReceived(String arg0, Player arg1, byte[] arg2) {
		if (!arg0.equals("BungeeCord")) {
			return;
		}
		ByteArrayDataInput input = ByteStreams.newDataInput(arg2);
		if (!input.readUTF().equalsIgnoreCase("party"))
			return;
		String target = input.readUTF();
		String line = input.readUTF();
		@SuppressWarnings("deprecation")
		OfflinePlayer player = Bukkit.getOfflinePlayer(target);
		if (player.isOnline())
			player.getPlayer().sendMessage(line);
	}

	public synchronized void sendPlayerPartyCommand(final Player player, final String... args) {
		if (args.length == 0)
			return;
		if (!args[0].equalsIgnoreCase("party"))
			return;
		new BukkitRunnable() {
			@Override
			public void run() {
				ByteArrayDataOutput out = ByteStreams.newDataOutput();
				for (String s : args)
					out.writeUTF(s);
				player.sendPluginMessage(Main.instance, "BungeeCord", out.toByteArray());
			}
		}.runTaskAsynchronously(Main.instance);
	}
}
