package com.arrayprolc.friend;

import java.util.ArrayList;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import com.arrayprolc.strings.MessageType;
import com.arrayprolc.strings.StringManager;

public class FriendManager {

	static Main plugin;
	static ArrayList<String> pendingFriendRequest = new ArrayList<String>();
	public static void init(Main instance){
		plugin = instance;
	}

	public static void sendFriendRequest(OfflinePlayer from, OfflinePlayer to){
		String str = formatString(from, to);
		
		if(areFriends(from, to) || areFriends(to, from)){
			sendMessage(from, StringManager.getPrefix(MessageType.ERROR) + "You are already friends! (Use /delfriend to remove)");
			return;
		}
		if(from.getName().equalsIgnoreCase(to.getName())){
			sendMessage(from, StringManager.getPrefix(MessageType.ERROR) + "Do you need this? §bhttp://bit.ly/1FvRqwG");
			return;
		}
		if(pendingFriendRequest.contains(formatString(to, from))) {
			acceptFriendRequest(to, from);
			pendingFriendRequest.remove(formatString(to, from));
			pendingFriendRequest.remove(formatString(from, to));
			return; //
		}
		if(pendingFriendRequest.contains(str)) {
			sendMessage(from, StringManager.getPrefix(MessageType.ERROR) + "You have already sent " + to.getName() + " a friend request. Please wait for them to accept.");
			return;
		}
		pendingFriendRequest.add(formatString(from, to));
		sendMessage(from, StringManager.getPrefix(MessageType.INFO) + "You have sent " + to.getName() + " a friend request. Please wait for them to accept.");
		sendMessage(to, StringManager.getPrefix(MessageType.INFO) + from.getName() + " would like to be friends with you. Type /friend " + from.getName() + " to accept.");
	}

	public static String formatString(OfflinePlayer p, OfflinePlayer p2){
		return p.getUniqueId() + " | " + p2.getUniqueId();
	}
	public static void removeFriend(OfflinePlayer to, OfflinePlayer from){
		ArrayList<String> friends1 = getFriendsList(from);
		friends1.remove(to.getUniqueId().toString());
		setFriendsList(from, friends1);
		
		ArrayList<String> friends2 = getFriendsList(to);
		friends2.remove(from.getUniqueId().toString());
		setFriendsList(to, friends2);
		
	}
	protected static void acceptFriendRequest(OfflinePlayer to, OfflinePlayer from){
		sendMessage(from, StringManager.getPrefix(MessageType.SUCCESS) + "You are now friends with " + to.getName() + "! (Use /delfriend to remove)");
		sendMessage(to, StringManager.getPrefix(MessageType.SUCCESS) + "You are now friends with " + from.getName() + "! (Use /delfriend to remove)");
		ArrayList<String> friends1 = getFriendsList(from);
		friends1.add(to.getUniqueId().toString());
		setFriendsList(from, friends1);
		
		ArrayList<String> friends2 = getFriendsList(to);
		friends2.add(from.getUniqueId().toString());
		setFriendsList(to, friends2);
	}

	protected static void sendMessage(OfflinePlayer p, String msg){
		Bukkit.getPlayerExact(p.getName()).sendMessage(msg);
	}
	
	public static String[] getFriends(OfflinePlayer p){
		try{
		if(plugin.getConfig().contains(p.getUniqueId() + ".Friends")){
			ArrayList<String> s = (ArrayList<String>) plugin.getConfig().get(p.getUniqueId() + ".Friends");
			return s.toArray(new String[s.size()]);
		}
		}catch(Exception ex){
			if(plugin.getConfig().contains(p.getUniqueId() + ".Friends")){
				return (String[]) plugin.getConfig().get(p.getUniqueId() + ".Friends");
			}
		}
		return new String[] { };
	}
	
	protected static void setFriends(OfflinePlayer p, String[] friends){
		plugin.getConfig().set(p.getUniqueId() + ".Friends", friends);
	}
	
	public static ArrayList<String> getFriendsList(OfflinePlayer p){
		ArrayList<String> f = new ArrayList<String>();
		for(String s : getFriends(p)) f.add(s);
		return f;
	}
	
	protected static void setFriendsList(OfflinePlayer p, ArrayList<String> friends){
		plugin.getConfig().set(p.getUniqueId() + ".Friends", friends.toArray(new String[friends.size()]));
	}
	
	public static boolean areFriends(OfflinePlayer p1, OfflinePlayer p2){
		if(contains(getFriends(p1), p2.getUniqueId().toString()) || contains(getFriends(p1), p2.getUniqueId().toString())){
			return true;
		}
		return false;
	}
	
	protected static boolean contains(String[] s, String r){
		for(String s2 : s){
			if(s2.equalsIgnoreCase(r)){
				return true;
			}
		}
		return false;
	}



}
