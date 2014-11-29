package fr.coco_gigpn.prodigygadget.util;
 
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
 
public class UtilParticle {
 
	private static Constructor<?> constructor;
	private static Field playerConnection;
	private static Method getHandle;
	private static Method sendPacket;
 
	static {
		try {
			Class<?> clazz = getMCClass("PacketPlayOutWorldParticles");
			constructor = clazz.getConstructor(String.class, float.class, float.class, float.class, float.class, float.class, float.class, float.class, int.class);
			getHandle = getCraftClass("entity.CraftPlayer").getMethod("getHandle");
			playerConnection = getMCClass("EntityPlayer").getDeclaredField("playerConnection");
			sendPacket = getMCClass("PlayerConnection").getMethod("sendPacket", getMCClass("Packet"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void sendParticleToLocation(Location loc, Particle particle, float xOffset, float yOffset, float zOffset, float speed, int amount) {
		try {
			Object packet = constructor.newInstance(particle.getName(), (float) loc.getX(), (float) loc.getY(), (float) loc.getZ(), xOffset, yOffset, zOffset, speed, amount);
			for (Player p : UtilServer.getPlayers()) {
				if (loc.getWorld().equals(p.getWorld()) && p.getLocation().distance(loc) <= 50) {
					Object entityPlayer = getHandle.invoke(p);
					Object connection = playerConnection.get(entityPlayer);
					sendPacket.invoke(connection, packet);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	public static void sendPartileToPlayer(Player p, Particle particle, Location loc, float xOffset, float yOffset, float zOffset, float speed, int amount) {
		try {
			Object packet = constructor.newInstance(particle.getName(), (float) loc.getX(), (float) loc.getY(), (float) loc.getZ(), xOffset, yOffset, zOffset, speed, amount);
			Object entityPlayer = getHandle.invoke(p);
			Object connection = playerConnection.get(entityPlayer);
			sendPacket.invoke(connection, packet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	private static Class<?> getMCClass(String name) {
		String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + ".";
		String className = "net.minecraft.server." + version + name;
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return clazz;
	}
 
	private static Class<?> getCraftClass(String name) {
		String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + ".";
		String className = "org.bukkit.craftbukkit." + version + name;
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return clazz;
	}
 
	public enum Particle {
 
		DRIP_WATER("dripWater"), DRIP_LAVA("dripLava"), SNOW_SHOVEL("snowshovel"), MOB_SPELL("mobSpell"), BUBBLE("bubble"), SUSPEND("suspend"), DEPTH_SUSPEND("depthSuspend"), TOWN_AURA("townaura"), CRIT("crit"), SLIME("slime"), HUGE_EXPLOSION("hugeexplosion"), LARGE_EXPLODE("largeexplode"), FIREWORKS_SPARK(
				"fireworksSpark"), HEART("heart"), ANGRY_VILLAGER("angryVillager"), MAGIC_CRIT("magicCrit"), INSTANT_SPELL("instantSpell"), WITCH_MAGIC("witchMagic"), HAPPY_VILLAGER("happerVillager"), NOTE("note"), PORTAL("portal"), ENCHANTMENT_TABLE("enchantmenttable"), EXPLODE("explode"), FLAME(
				"flame"), LAVA("lava"), LARGE_SMOKE("largesmoke"), CLOUD("cloud"), RED_DUST("reddust"), SNOWBALL_POOF("snowballpoof"), MOB_SPELL_AMBIENT("mobSpellAmbient"), SPELL("spell"), FOOTSTEP("footstep"), SPLASH("splash");
 
		private String name;
 
		private Particle(String name) {
			this.name = name;
		}
 
		public String getName() {
			return name;
		}
	}
}