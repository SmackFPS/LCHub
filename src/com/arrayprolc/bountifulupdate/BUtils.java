package com.arrayprolc.bountifulupdate;

import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import net.minecraft.server.v1_7_R4.PacketPlayOutChat;
import net.minecraft.server.v1_7_R4.PlayerConnection;

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.spigotmc.ProtocolInjector;
import org.spigotmc.ProtocolInjector.PacketTitle.Action;

public class BUtils {

	public static void sendTitleToPlayer(Player p, String title1, String subtitle1, int timing1, int timing2, int timing3){
		if(!isPlayerRightVersion(p)) return;
		PlayerConnection connection = getConnection(p);
		IChatBaseComponent title = ChatSerializer.a("{'color': '" + "', 'text': '" + title1 + "'}");
		IChatBaseComponent subtitle = ChatSerializer.a("{'color': '" + "', 'text': '" + subtitle1 + "'}");
		connection.sendPacket(new ProtocolInjector.PacketTitle(Action.TITLE, timing1, timing2, timing3));
		connection.sendPacket(new ProtocolInjector.PacketTitle(Action.TITLE, title));
		connection.sendPacket(new ProtocolInjector.PacketTitle(Action.SUBTITLE, subtitle));
	}

	public static boolean isPlayerRightVersion(Player player)
	{
		return ((CraftPlayer)player).getHandle().playerConnection.networkManager.getVersion() >= 47;
	}

	public static PlayerConnection getConnection(Player p){
		return ((CraftPlayer)p).getHandle().playerConnection;

	}


	public static void sendActionBar(Player p, String msg){
		try{
			if(((CraftPlayer) p).getHandle().playerConnection.networkManager.getVersion() >= 47){
				//only 1.8 clients
				IChatBaseComponent cbc = ChatSerializer.a("{\"text\": \""+msg+"\"}");
				PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc,2);
				((CraftPlayer) p).getHandle().playerConnection.sendPacket(ppoc);
			}
		}catch(Exception e){}
	}
	
	public static void sendHeaderAndFooter(Player p, String head, String foot){
		if(!isPlayerRightVersion(p)) return;
		PlayerConnection connection = getConnection(p);
		IChatBaseComponent header = ChatSerializer.a("{'color': '" + "', 'text': '" + head + "'}");
		IChatBaseComponent footer = ChatSerializer.a("{'color': '" + "', 'text': '" + foot + "'}");
		connection.sendPacket(new ProtocolInjector.PacketTabHeader(header, footer));
	}
}
