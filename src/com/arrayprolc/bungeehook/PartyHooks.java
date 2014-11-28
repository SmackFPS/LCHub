package com.arrayprolc.bungeehook;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

public class PartyHooks implements PluginMessageListener {
	private static PartyHooks instance;

	public PartyHooks() {
		instance = this;
		Bukkit.getMessenger().registerOutgoingPluginChannel(Main.instance, "BungeeCord");
		Bukkit.getServer().getMessenger().registerIncomingPluginChannel(Main.instance, "BungeeCord", this);
	}

	public static PartyHooks getInstance() {
		return instance;
	}

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		Bukkit.broadcastMessage("incoming request");
		try{
		ByteArrayDataInput in = ByteStreams.newDataInput(message);
		if (!channel.equals("BungeeCord")) {
			return;
		}
		String build = in.readUTF();
		Bukkit.broadcastMessage(build);
		if(!build.equalsIgnoreCase("party:list")) return;
		String key = in.readUTF();
		String builder = in.readUTF();
		//Bukkit.broadcastMessage(server + " " + builder);
		boolean removeOne = false;
		for(String s : builder.split(", ")){
			if(s.equalsIgnoreCase("")){
				removeOne = true;
			}
		}
		Bukkit.broadcastMessage(key);
		int r = builder.split(", ").length;
		Bukkit.broadcastMessage(r + "");
		}catch(Exception ex){
			//Don't print the stack trace.
		}
	}
	

	
	
}
