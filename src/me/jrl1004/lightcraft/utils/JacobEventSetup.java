package me.jrl1004.lightcraft.utils;


public class JacobEventSetup {
	public static void setupEvents(){
		new me.jrl1004.lightcraft.commands.party.PartyManager();
		new ProxiedEconomy();
	}
	public static void disable(){
		
	}
}
