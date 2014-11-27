package com.arrayprolc.bungeehook;

import java.util.HashMap;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class BungeeHooks implements PluginMessageListener {
	static Main plugin;
	HashMap<String, Integer> players = new HashMap<String, Integer>();
	static String[] servers;
	@SuppressWarnings("deprecation")
	public BungeeHooks(Main instance){
		plugin = instance;
		Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(instance, "BungeeCord");
		Bukkit.getServer().getMessenger().registerIncomingPluginChannel(instance, "BungeeCord", this);
		Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(plugin, new Runnable(){
			public void run(){
				if(servers != null){
					for(String srv : servers){
						ByteArrayDataOutput out = ByteStreams.newDataOutput();
						out.writeUTF("PlayerList");
						out.writeUTF(srv);
					}
				}
			}
		}, 0, 5);
		
	}

	public static void sendPlayerToServer(String name, Player p){
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(name);
		p.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
	}
	public static void sendPlayerToServer(String name, OfflinePlayer p){
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("ConnectOther");
		out.writeUTF(p.getName());
		out.writeUTF(name);
		Bukkit.getOnlinePlayers()[0].sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
	}

	public int getPlayerCount(String server){
		return 1;

	}

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		if (!channel.equals("BungeeCord")) {
			return;
		}
		ByteArrayDataInput in = ByteStreams.newDataInput(message);
		String subchannel = in.readUTF();
		if (subchannel.equals("PlayerList")) {
			String server = in.readUTF(); 
			String[] playerList = in.readUTF().split(", ");
			players.remove(server);
			players.put(server, playerList.length);
		}
	}
}
