package com.arrayprolc.command;
import java.util.UUID;

import me.jrl1004.lightcraft.utils.ProxiedParty;
import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;

import com.arrayprolc.friend.FriendManager;
import com.arrayprolc.rank.RankManager;
import com.arrayprolc.rank.ServerRank;
import com.arrayprolc.strings.MessageType;
import com.arrayprolc.strings.StringManager;

public class ArrayCommandHandler {

	static Main plugin;
	public static void setup(Main instance){
		plugin = instance;
	}
	
	public static boolean command(CommandSender sender, Command cmd, String label, String[] a){
		if(label.equalsIgnoreCase("g-reward")){
			if(sender instanceof Player) return false;
			Player p = Bukkit.getPlayer(a[0]);
			int amount = Integer.parseInt(a[2]);
			switch(a[1].toLowerCase()){
			case "coin": {
				me.mike1665.coinapi.LcCoinsAPI.givePoints(p, amount);
				me.mike1665.coinapi.ApiEvent.scoreboard(p);
				return true;
			}
			case "token":
				me.mike1665.coinapi.LcTokensAPI.givePoints(p, amount);
				me.mike1665.coinapi.ApiEvent.scoreboard(p);
				return true;
			}
		}
		if(label.equalsIgnoreCase("sheeplol") && ((Player)sender).isOp()){
			try{((Player)sender).getPassenger().eject();
			((Player)sender).eject(); }catch(Exception ex){}
			for(Entity e :  ((Player)sender).getNearbyEntities(5,  5,  5)){
				if(e instanceof Sheep){
					 ((Player)sender).setPassenger(e);
					 return false;
				}
			}
		}
		if(label.equalsIgnoreCase("rank")){
			if(sender instanceof Player){
				if(!((Player)sender).isOp()) return false;
			}
			Player p = Bukkit.getPlayer(a[0]);
			ServerRank r = RankManager.getRankFromString(a[1]);
			RankManager.setRank(p, r);
			
		}
		if(label.equalsIgnoreCase("test")){
			ProxiedParty.getInstance().sendPlayerPartyCommand((Player)sender, a);
			
		}
		if(label.equalsIgnoreCase("friend")){
			if(a.length != 1){
				sender.sendMessage(StringManager.getPrefix(MessageType.ERROR) + "/friend [name/list]");
				return true;
			}
			

			if(!(sender instanceof Player)){
				return true;
			}
			Player p1 = (Player)sender;
			if(a[0].equalsIgnoreCase("list")){
				String s1 = "";
				boolean start = true;
				for(String s : FriendManager.getFriends(p1)){
					Player f = Bukkit.getPlayer(UUID.fromString(s));
					if(start){
						if(isOnline(f)){
							s1 = s1 + "§a" + f.getName();
						}else{
							s1 = s1 + "§7" + f.getName();
						}
						start = false;
					}else{
						if(isOnline(f)){
							s1 = s1 + "§7, §a" + f.getName();
						}else{
							s1 = s1 + "§7, " + f.getName();
						}
					}
				}
				p1.sendMessage("§6Your Friends: " + s1);
				return true;
			}
			try{
				Player p2 = Bukkit.getPlayerExact(a[0]);
				FriendManager.sendFriendRequest(p1, p2);
			}catch(Exception ex){
				sender.sendMessage(StringManager.getPrefix(MessageType.ERROR) + "Could not find that player. Make sure caps are correct.");
			}
			
		}
		if(label.equalsIgnoreCase("delfriend")){
			if(a.length != 1){
				sender.sendMessage(StringManager.getPrefix(MessageType.ERROR) + "/delfriend [name]");
				return true;
			}
			

			if(!(sender instanceof Player)){
				return true;
			}//
			Player p1 = (Player)sender;
			try{
				Player p2 = Bukkit.getPlayerExact(a[0]);
				if(FriendManager.areFriends(p1, p2) || FriendManager.areFriends(p2, p1)){
					FriendManager.removeFriend(p1, p2);
					p1.sendMessage(StringManager.getPrefix(MessageType.SUCCESS) + "You are no longer friends with " + p2.getName());
				}
			}catch(Exception ex){
				sender.sendMessage(StringManager.getPrefix(MessageType.ERROR) + "Could not find that player. Make sure caps are correct.");
			}
		}
		return false;
	}
	
	static boolean isOnline(Player p){
		for(Player p2 : Bukkit.getOnlinePlayers()){
			if(p2.getName().equalsIgnoreCase(p.getName())){
				return true;
			}
		}
		return false;
	}
	
	

	
}
