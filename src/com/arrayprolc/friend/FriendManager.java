package com.arrayprolc.friend;

import java.util.ArrayList;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.arrayprolc.strings.MessageType;
import com.arrayprolc.strings.StringManager;

public class FriendManager {

	static Main plugin;
	ArrayList<String> pendingFriendRequest = new ArrayList<String>();
	public static void init(Main instance){
		plugin = instance;
	}

	public void sendFriendRequest(OfflinePlayer from, OfflinePlayer to){
		String str = formatString(from, to);
		if(pendingFriendRequest.contains(formatString(to, from))) {
			acceptFriendRequest(to, from);
			pendingFriendRequest.remove(formatString(to, from));
			pendingFriendRequest.remove(formatString(from, to));
			return;
		}
		if(pendingFriendRequest.contains(str)) {
			sendMessage(from, StringManager.getFormatAndColor(MessageType.ERROR) + " You have already sent " + to.getName() + " a friend request. Please wait for them to accept.");
			return;
		}
		pendingFriendRequest.add(formatString(from, to));
		sendMessage(from, StringManager.getFormatAndColor(MessageType.ERROR) + " You have sent " + to.getName() + " a friend request. Please wait for them to accept.");
		sendMessage(to, StringManager.getFormatAndColor(MessageType.ERROR) + from.getName() + " would like to be friends with you. Type /friend " + from.getName() + " to accept.");
	}

	public String formatString(OfflinePlayer p, OfflinePlayer p2){
		return p.getUniqueId() + " | " + p2.getUniqueId();
	}

	public void acceptFriendRequest(OfflinePlayer to, OfflinePlayer from){
		sendMessage(from, StringManager.getFormatAndColor(MessageType.ERROR) + " You are now friends with " + to.getName() + "!");
		sendMessage(to, StringManager.getFormatAndColor(MessageType.ERROR) + " You are now friends with " + from.getName() + "!");
		ArrayList<String> friends1 = getFriendsList(from);
		friends1.add(to.getUniqueId().toString());
		setFriendsList(from, friends1);
		
		ArrayList<String> friends2 = getFriendsList(to);
		friends2.add(from.getUniqueId().toString());
		setFriendsList(to, friends2);
	}

	public void sendMessage(OfflinePlayer p, String msg){
		Bukkit.getPlayerExact(p.getName()).sendMessage(msg);
	}
	
	public String[] getFriends(OfflinePlayer p){
		if(plugin.getConfig().contains(p.getUniqueId() + ".Friends")){
			return (String[]) plugin.getConfig().get(p.getUniqueId() + ".Friends");
		}
		return new String[] { };
	}
	
	public void setFriends(OfflinePlayer p, String[] friends){
		plugin.getConfig().set(p.getUniqueId() + ".Friends", friends);
	}
	
	public ArrayList<String> getFriendsList(OfflinePlayer p){
		ArrayList<String> f = new ArrayList<String>();
		for(String s : getFriends(p)) f.add(s);
		return f;
	}
	
	public void setFriendsList(OfflinePlayer p, ArrayList<String> friends){
		plugin.getConfig().set(p.getUniqueId() + ".Friends", friends.toArray(new String[friends.size()]));
	}
	
	public boolean areFriends(OfflinePlayer p1, OfflinePlayer p2){
		if(getFriendsList(p1).contains(p2.getUniqueId().toString()) || getFriendsList(p2).contains(p1.getUniqueId().toString())){
			return true;
		}
		return false;
	}



}
