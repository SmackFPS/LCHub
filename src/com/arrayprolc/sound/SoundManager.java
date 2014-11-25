package com.arrayprolc.sound;

import org.bukkit.entity.Player;

public class SoundManager {

	public void customSound(Player player, String sound, float vol, float pitch){
		Player p = player;
		net.minecraft.server.v1_7_R4.PacketPlayOutNamedSoundEffect packet = new net.minecraft.server.v1_7_R4.PacketPlayOutNamedSoundEffect(sound, p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ(), vol, pitch);
		((org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
	}
	
	public void customSound(Player player, String sound){
		customSound(player, sound, 1.0F, 1.0F);
	}
	
	public void customSound(Player player, String sound, float pitch){
		customSound(player, sound, Float.MAX_VALUE, pitch);
	}
}
