package com.arrayprolc.rank;

import me.mike1665.Main.Main;

public class RankManager {
	
	static Main plugin;
	
	public static void init(Main instance){
		plugin = instance;
	}
	
	public static String getRank(Player p){
		plugin.getConfig().set(p,..getName() + ".Administrator", true);
		plugin.saveFile();
	}

}
