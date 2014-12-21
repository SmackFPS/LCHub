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
		Bukkit.getMessenger().registerOutgoingPluginChannel(Main.getInstance(), "BungeeCord");
	}

	public static ProxiedParty getInstance() {
		return instance;
	}

	public void requestPartyList(Player player) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("party:list");
		out.writeUTF(player.getName());
		player.sendPluginMessage(Main.getInstance(), "BungeeCord", out.toByteArray());
	}

	public void requestPartyHost(Player player) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("party:host");
		out.writeUTF(player.getName());
		player.sendPluginMessage(Main.getInstance(), "BungeeCord", out.toByteArray());
	}

	public void requestPartySize(Player player) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("party:size");
		out.writeUTF(player.getName());
		player.sendPluginMessage(Main.getInstance(), "BungeeCord", out.toByteArray());
	}

}
