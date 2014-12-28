package com.arrayprolc.rank;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.lightcraftmc.hub.main.Main;

public class RankManager {

	static Main plugin;

	public static void init(){
		plugin = Main.getInstance();
	}

	/**
	 * @deprecated "Do not use, use ServerRank instead."
	 */
	@Deprecated
	public static String getRankString(Player p){
		String get = p.getUniqueId().toString() + ".rank";
		return (String) plugin.getConfig().getString(get);
	}

	public static ServerRank getRank(Player p){
		try{
			for(ServerRank r : ServerRank.values()){
				if(r.toString().equalsIgnoreCase(getRankString(p))){
					return r;
				}
			}
		}catch(Exception ex){}
		setRank(p, ServerRank.PLAYER);
		return ServerRank.PLAYER;
		
	}
	
	public static boolean hasRank(Player p, ServerRank r){
		if(getRank(p).toString().equalsIgnoreCase(r.toString())) return true;
		for(ServerRank r5 : ServerRank.values()){
			if(r5.toString().equalsIgnoreCase(getRank(p).toString())) return false;
			if(r5.toString().equalsIgnoreCase(r.toString())) return true;
		}
		return false;
	}
	
	public static ServerRank getRankFromString(String s){
		try{
			for(ServerRank r : ServerRank.values()){
				if(r.toString().equalsIgnoreCase(s)){
					return r;
				}
			}
		}catch(Exception ex){}
		//setRank(p, ServerRank.PLAYER);
		return ServerRank.PLAYER;
		
	}

	/**
	 * @deprecated "Do not use, use ServerRank instead."
	 */
	@Deprecated
	public static void setRankString(Player p, String s){
		try{
			String s2 = p.getUniqueId().toString() + ".rank";
		plugin.getConfig().set(s2, s);
		plugin.saveConfig();
		
		}catch(Exception ex){ ex.printStackTrace(); }
		
	}

	public static void setRank(Player p, ServerRank r){
		setRankString(p, r.toString());
	}
	
	public static String getFormat(ServerRank r){
		try{
		switch(r){
		case ADMIN: return "§7[§cAdmin§7] §c[name]§7:§f [message]";
		case DEVELOPER: return "§7[§9Dev§7] §9[name]§7:§f [message]";
		case HELPER: return "§7[§2Helper§7] §d[name]§7:§f [message]";
		case MODERATOR: return "§7[§dMod§7] §d[name]§7:§f [message]";
		case MVP: return "§7[§eMVP§7] §e[name]§7:§f [message]";
		case OWNER: return "§7[§3Owner§7] §c[name]§7:§f [message]";
		case VIP: return "§7[§aVIP§7] §a[name]§7:§f [message]";
		case PLAYER: return "§7[name]: [message]";
		default: return "§7[name]: [message]";
		
		}
		}catch(Exception ex){return "§7[name]: [message]";}
	}
	public static String getColor(ServerRank r){
		try{
		switch(r){
		case ADMIN: return "§c";
		case DEVELOPER: return "§9";
		case HELPER: return "§d";
		case MODERATOR: return "§d";
		case MVP: return "§e";
		case OWNER: return "§c";
		case VIP: return "§a";
		case PLAYER: return "§7";
		default: return "§7";
		
		}
		}catch(Exception ex){return "§7";}
	}

}
