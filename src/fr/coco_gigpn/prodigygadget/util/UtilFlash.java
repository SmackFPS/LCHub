package fr.coco_gigpn.prodigygadget.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
 
public class UtilFlash {
 
    /*
    * Class made by BigTeddy98.
    *
    * RedFactory is a simple class to make players red.
    *
    * 1. No warranty is given or implied.
    * 2. All damage is your own responsibility.
    * 3. If you want to use this in your plugins, a credit would we appreciated.
    */
 
    //everything needed for the reflection
    private static Constructor<?> packetPlayOutAnimation;
    private static Method getHandle;
    private static Field playerConnection;
    private static Method sendPacket;
 
    static {
        try {
            //get the constructor of the packet
            packetPlayOutAnimation = getMCClass("PacketPlayOutAnimation").getConstructor(getMCClass("Entity"), int.class);
            //get method for recieving craftplayer's entityplayer
            getHandle = getCraftClass("entity.CraftPlayer").getMethod("getHandle");
            //get the playerconnection of the entityplayer
            playerConnection = getMCClass("EntityPlayer").getDeclaredField("playerConnection");
            //method to send the packet
            sendPacket = getMCClass("PlayerConnection").getMethod("sendPacket", getMCClass("Packet"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    private Main plugin;
    private static List<String> redPlayers = new ArrayList<String>();
 
    public UtilFlash(Main plugin) {
        this.plugin = plugin;
        start();
    }
 
    /*
    * addRed(String name)
    * String name - the player's name to make red
    */
    public void addRed(String name) {
        redPlayers.add(name);
    }
 
    /*
    * addRed(Player p)
    * Player p - the player to make red
    */
    public static void addRed(Player p) {
        redPlayers.add(p.getName());
    }
 
    /*
    * removeRed(String name)
    * String name - the players name, stop being red
    */
    public void removeRed(String name) {
        if (redPlayers.contains(name)) {
            redPlayers.remove(name);
        }
    }
 
    /*
    * removeRed(Player p)
    * String name - the player, stop being red
    */
    public static void removeRed(Player p) {
        if (redPlayers.contains(p.getName())) {
            redPlayers.remove(p.getName());
        }
    }
 
    /*
    * isRed(Player p)
    * Player p - returns if the player is red or not
    */
    public static boolean isRed(Player p){
        return !redPlayers.contains(p.getName());
    }
 
    /*
    * isRed(String name)
    * String name - returns if the player is red or not
    */
    public static boolean isRed(String name){
        return !redPlayers.contains(name);
    }
 
    //called when initializing the class
    private void start() {
        new BukkitRunnable() {
 
            @Override
            public void run() {
                // loop through the player list
                for (String name : redPlayers) {
                    try {
                        // get the player by name
           
						@SuppressWarnings("deprecation")
						Player p = plugin.getServer().getPlayerExact(name);
                        if (p == null) {
                            // skip player if offline
                            continue;
                        }
                        // get the EntityPlayer
                        Object nms_entity = getHandle.invoke(p);
                        // create a new packet instance
                        Object packet = packetPlayOutAnimation.newInstance(nms_entity, 1);
 
                        // loop through all players in the same world
                        for (Player pl : p.getWorld().getPlayers()) {
                            //skip if packet reciever is the player self
                            if(pl.equals(p)){
                                continue;
                            }
                            // only send if the player is in range
                            if (pl.getLocation().distance(p.getLocation()) <= 50) {
                                // preparing to send packet, getting EntityPlayer.
                                Object nms_player = getHandle.invoke(pl);
                                // get the players connection
                                Object nms_connection = playerConnection.get(nms_player);
                                // send the packet
                                sendPacket.invoke(nms_connection, packet);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.runTaskTimer(this.plugin, 1, 20);
    }
 
    // easy way to get NMS classes
    private static Class<?> getMCClass(String name) throws ClassNotFoundException {
        String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + ".";
        String className = "net.minecraft.server." + version + name;
        return Class.forName(className);
    }
 
    // easy way to get CraftBukkit classes
    private static Class<?> getCraftClass(String name) throws ClassNotFoundException {
        String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + ".";
        String className = "org.bukkit.craftbukkit." + version + name;
        return Class.forName(className);
    }
}
