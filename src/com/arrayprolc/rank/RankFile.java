package com.arrayprolc.rank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;


public class RankFile {

	public static void saveToFile(String[] s){
		PrintWriter writer;
		try {
			BufferedWriter out;
            out = new BufferedWriter(new FileWriter("C:!Users!Justin!Desktop!test.txt".replace("!", File.separator),true));
           for(String s2 : s) out.write(s2);
            out.newLine();
            out.close();
        }catch(IOException e){
            System.out.println("There was a problem:" + e);
        }

	}
	
	public void generateRankString(){
		String s = "";
		for(Player p : Bukkit.getOnlinePlayers()){
			s = s + p.getUniqueId() + "|" + RankManager.getRank(p).toString() + ",";
		}
	}
	
}
