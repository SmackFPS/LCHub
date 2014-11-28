package me.jrl1004.lightcraft.utils;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class ProxiedParty {
	private static ProxiedParty instance;

	public ProxiedParty() {
		instance = this;
		Bukkit.getMessenger().registerOutgoingPluginChannel(Main.instance, "BungeeCord");
	}

	public static ProxiedParty getInstance() {
		return instance;
	}
	
	public void requestPartyList(String player) {
		@SuppressWarnings("deprecation")
		Player sender = Bukkit.getPlayer(player);
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("party:list");
		sender.sendPluginMessage(Main.instance, "BungeeCord", out.toByteArray());
	}
	
	public void requestPartyHost(String player) {
		@SuppressWarnings("deprecation")
		Player sender = Bukkit.getOnlinePlayers()[0];
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("party:host");
		sender.sendPluginMessage(Main.instance, "BungeeCord", out.toByteArray());
	}
	
	public void requestPartySize(String player) {
		@SuppressWarnings("deprecation")
		Player sender = Bukkit.getOnlinePlayers()[0];
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("party:size");
		sender.sendPluginMessage(Main.instance, "BungeeCord", out.toByteArray());
	}
	
	
}
