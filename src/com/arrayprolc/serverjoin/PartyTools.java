package com.arrayprolc.serverjoin;

import me.jrl1004.lightcraft.commands.party.Party;
import me.jrl1004.lightcraft.commands.party.PartyManager;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.arrayprolc.bungeehook.BungeeHooks;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class PartyTools {

	public static Party party(Player p){
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
	
	public static boolean sendPartyToServer(Player p, String server){
		if(party(p) == null){
			sendPlayerToServer(server, p);
			return true;
		}
		Party par = party(p);
		if(!isHost(p, par)){
			return false;
		}
		for(OfflinePlayer p2 : getPlayersInParty(par)){
			sendPlayerToServer(server, p2);
		}
		return true;
		
	}
	public static void sendPlayerToServer(String name, OfflinePlayer p){
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("ConnectOther");
		out.writeUTF(p.getName());
		out.writeUTF(name);
		Bukkit.getOnlinePlayers()[0].sendPluginMessage(Bukkit.getServer().getPluginManager().getPlugin("HubPlugin"), "BungeeCord", out.toByteArray());
	}
}
