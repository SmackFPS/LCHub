package com.arrayprolc.bountifulupdate;


import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.Packet;
import net.minecraft.server.v1_8_R1.PacketDataSerializer;
import net.minecraft.server.v1_8_R1.PacketPlayOutChat;
import net.minecraft.server.v1_8_R1.PlayerConnection;

import org.apache.logging.log4j.core.net.Protocol;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class BUtils {

	public static void sendTitleToPlayer(Player p, String title1, String subtitle1, int timing1, int timing2, int timing3){
	/*	PlayerConnection connection = getConnection(p);
		IChatBaseComponent title = ChatSerializer.a("{'color': '" + "', 'text': '" + title1 + "'}");
		IChatBaseComponent subtitle = ChatSerializer.a("{'color': '" + "', 'text': '" + subtitle1 + "'}");
		connection.sendPacket(new ProtocolInjector.PacketTitle(Action.TITLE, timing1, timing2, timing3));
		connection.sendPacket(new PacketDataSerializer.PacketTitle(Action.TITLE, title));
		connection.sendPacket(new Packet(PacketType.SUBTITLE, subtitle));*/
	}

	public static PlayerConnection getConnection(Player p){
		return ((CraftPlayer)p).getHandle().playerConnection;
	}

	public static void sendActionBar(Player p, String msg){
		try{
				IChatBaseComponent cbc = ChatSerializer.a("{\"text\": \""+ msg +"\"}");
				PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc);
				((CraftPlayer) p).getHandle().playerConnection.sendPacket(ppoc);
		}catch(Exception e){}
	}
	
	public static void sendHeaderAndFooter(Player p, String head, String foot){
	/*	PlayerConnection connection = getConnection(p);
		IChatBaseComponent header = ChatSerializer.a("{'color': '" + "', 'text': '" + head + "'}");
		IChatBaseComponent footer = ChatSerializer.a("{'color': '" + "', 'text': '" + foot + "'}");
		connection.sendPacket(new ProtocolInjector.PacketTabHeader(header, footer));*/
	}
}
