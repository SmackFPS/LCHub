package com.arrayprolc.bungeehook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.lightcraftmc.hub.main.Main;

public class PartyHooks implements PluginMessageListener {
	private static PartyHooks instance;
	public static HashMap<UUID, Integer> players = new HashMap<UUID, Integer>();

	public PartyHooks() {
		instance = this;
		Bukkit.getMessenger().registerOutgoingPluginChannel(Main.getInstance(), "BungeeCord");
		Bukkit.getServer().getMessenger().registerIncomingPluginChannel(Main.getInstance(), "BungeeCord", this);
		Bukkit.getServer().getMessenger().registerIncomingPluginChannel(Main.getInstance(), "partylist", this);
		Bukkit.getServer().getMessenger().registerIncomingPluginChannel(Main.getInstance(), "partyhost", this);
		Bukkit.getServer().getMessenger().registerIncomingPluginChannel(Main.getInstance(), "partysize", this);
		Bukkit.getServer().getMessenger().registerIncomingPluginChannel(Main.getInstance(), "party", this);
		//Bukkit.getServer().getMessenger().registerIncomingPluginChannel(Main.getInstance(), "partylist", this);
	}

	public static PartyHooks getInstance() {
		return instance;
	}

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		try{
			ByteArrayDataInput in = ByteStreams.newDataInput(message);
			if(channel.equals("partylist")){ partyList(channel, player, message, in); return; }
			if(channel.equals("partyhost")){ partyHost(channel, player, message, in); return; }
			if(channel.equals("partysize")){ partySize(channel, player, message, in); return; }
		}catch(Exception ex){
			//Don't print the stack trace.
		}
	}

	public void partyList(String channel, Player player, byte[] message, ByteArrayDataInput in){
		String temp = "";
		ArrayList<String> s = new ArrayList<String>();
		boolean removeOne = false;
		while(temp != null){
			try{
				temp = in.readUTF();

			}catch(Exception e){
				temp = null;
			}
			if(temp != null){
				s.add(temp);
			}
		}
		players.remove(player.getUniqueId());
		players.put(player.getUniqueId(), s.size());
	}

	public void partyHost(String channel, Player player, byte[] message, ByteArrayDataInput in){

	}

	public void partySize(String channel, Player player, byte[] message, ByteArrayDataInput in){

	}




}
