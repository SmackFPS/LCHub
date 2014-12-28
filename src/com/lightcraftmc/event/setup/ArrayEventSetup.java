package com.lightcraftmc.event.setup;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.lightcraftmc.bungeehook.BungeeHooks;
import com.lightcraftmc.bungeehook.Servers;
import com.lightcraftmc.event.BuildListener;
import com.lightcraftmc.event.TPSMeter;
import com.lightcraftmc.event.TabHeaderSetup;
import com.lightcraftmc.event.TreasureChestListener;
import com.lightcraftmc.event.VillagerSpawn;
import com.lightcraftmc.event.handlers.menu.ClickInventory;
import com.lightcraftmc.fusebox.build.listener.BuildSettings;
import com.lightcraftmc.hub.main.Main;
import com.lightcraftmc.menu.ParticleMenu;
import com.lightcraftmc.rank.RankManager;
import com.lightcraftmc.speedways.Speedways;

public class ArrayEventSetup {

	static Main plugin;
	
	public static void setupEvents(){
		PluginManager pm = Bukkit.getServer().getPluginManager();
		plugin = Main.getInstance();
		pm.registerEvents(new ClickInventory(), plugin);
		pm.registerEvents(new TabHeaderSetup(), plugin);
		pm.registerEvents(new BuildListener(), plugin);
		pm.registerEvents(new TreasureChestListener(), plugin);
		pm.registerEvents(new Speedways(), plugin);
		pm.registerEvents(new VillagerSpawn(), plugin);
		for(World w : Bukkit.getWorlds()) w.setGameRuleValue("reducedDebugInfo", "true");
		for(World w : Bukkit.getWorlds()) w.setGameRuleValue("doDaylightCycle", "false");
		TPSMeter.setupTPSMeter();
		RankManager.init();
		pm.registerEvents(new ParticleMenu(), plugin);
		bungee();
		BuildSettings.disableServerHunger();
		BuildSettings.revokeBlockBreak(Bukkit.getWorlds().get(0).getName());
		removeEntities();
		
	}
	private static void removeEntities(){
		for(World w : Bukkit.getWorlds()) for(Entity e : w.getEntities()) if(!(e instanceof Player)) e.remove();
	}
	public static void bungee(){

		Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(Main.getInstance(), "BungeeCord");
		Bukkit.getServer().getMessenger().registerIncomingPluginChannel(Main.getInstance(), "BungeeCord", Main.getInstance());
		System.out.println("Initializing Bungee Hooks");
		BungeeHooks.players.put("lobby", Bukkit.getOnlinePlayers().length);

	}
	
	@SuppressWarnings("deprecation")
	public static void requestPlayerList(){
		if(Bukkit.getOnlinePlayers().length > 0){
		for(String srv : Servers.servers){
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("PlayerList");  
			out.writeUTF(srv);
			Bukkit.getOnlinePlayers()[0].sendPluginMessage(Bukkit.getServer().getPluginManager().getPlugin("HubPlugin"), "BungeeCord", out.toByteArray());
		}
		}
	}
	
	public static void onPluginMessageReceived(String channel, Player player, byte[] message) {
		try{
		ByteArrayDataInput in = ByteStreams.newDataInput(message);
		if (!channel.equals("BungeeCord")) {
			return;
		}
		String build = "";
		if(!in.readUTF().equalsIgnoreCase("PlayerList")) return;
		String server = in.readUTF();
		String builder = in.readUTF();
		boolean removeOne = false;
		for(String s : builder.split(", ")){
			if(s.equalsIgnoreCase("")){
				removeOne = true;
			}
		}
		int r = builder.split(", ").length;
		BungeeHooks.players.remove(server);
		if(removeOne) r = r - 1;
		BungeeHooks.players.put(server, r);
		}catch(Exception ex){
		}
	} 
	
	public static void disable(){
		
	}
}
//
