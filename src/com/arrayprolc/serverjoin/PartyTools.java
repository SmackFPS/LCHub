package com.arrayprolc.serverjoin;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.lightcraftmc.fusebox.party.Party;
import com.lightcraftmc.fusebox.party.PartyManager;

public class PartyTools {

	public static Party party(Player p){
		if(PartyManager.getInstance().isInParty(p)){
			return PartyManager.getInstance().getByPlayer(p);
		}
		return null;
	}
	public static Party party(OfflinePlayer p){
		if(PartyManager.getInstance().isInParty(p)){
			return PartyManager.getInstance().getByPlayer(p);
		}
		return null;
	}
	
	public static boolean isHost(Player p, Party part){
		return p.getName().equalsIgnoreCase(part.getHost().getName());
	}
	
	public static OfflinePlayer[] getPlayersInParty(Party part){
		return part.getPlayers();
	}
	
	public static OfflinePlayer[] getPartyMembers(OfflinePlayer p){
		if(party(p) == null){
			return new OfflinePlayer[] { p };
		}
		return getPlayersInParty(party(p));
	}
	
	public static boolean sendPartyToServer(Player p, String server){
		if(party(p) == null){
			sendPlayerToServer(server, p);
			return true;
		}
		Party par = party(p);
		if(!isHost(p, par)){
			return false;
		}//
		for(OfflinePlayer p2 : getPlayersInParty(par)){
			sendPlayerToServer(server, p2);
		}
		return true;
		
	}
	public static int playersWith(Player p){
		if(party(p) == null){
			return 1;
		}
		return getPlayersInParty(party(p)).length;
	}
	public static int playersWith(OfflinePlayer p){
		if(party(p) == null){
			return 1;
		}
		return getPlayersInParty(party(p)).length;
	}
	public static boolean hasControl(Player p){
		if(party(p) == null){
			return true;
		}
		return isHost(p, party(p));
	}
	
	public static void sendPlayerToServer(String name, OfflinePlayer p){
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("ConnectOther");
		out.writeUTF(p.getName());
		out.writeUTF(name);
		Bukkit.getOnlinePlayers()[0].sendPluginMessage(Bukkit.getServer().getPluginManager().getPlugin("HubPlugin"), "BungeeCord", out.toByteArray());
	}
	public static void sendPlayerToServer(String name, Player p){
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(name);
		p.sendPluginMessage(Bukkit.getServer().getPluginManager().getPlugin("HubPlugin"), "BungeeCord", out.toByteArray());
	}
}
