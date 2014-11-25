package com.arrayprolc.rank;

import me.mike1665.Main.Main;

import org.bukkit.entity.Player;

public class RankManager {

	static Main plugin;

	public static void init(Main instance){
		plugin = instance;
	}

	/**
	 * @deprecated "Do not use, use ServerRank instead."
	 */
	@Deprecated
	public static String getRankString(Player p){
		return plugin.getConfig().getString(p.getUniqueId() + ".Rank");
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

	/**
	 * @deprecated "Do not use, use ServerRank instead."
	 */
	@Deprecated
	public static void setRankString(Player p, String s){
		plugin.getConfig().set(p.getUniqueId() + ".Rank", s);
		plugin.reloadConfig();
	}

	public static void setRank(Player p, ServerRank r){
		setRankString(p, r.toString());
	}
	
	public String getFormat(ServerRank r){
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
	}

}
