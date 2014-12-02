package me.mike1665.click;

import java.util.ArrayList;

import me.mike1665.Main.Main;
import me.mike1665.menu.CosmeticsMenu;
import me.mike1665.menu.MusicMenu;
import me.mike1665.particlelib.ParticleEffect;
import me.mike1665.particles18.ParticleLib18;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.arrayprolc.strings.MessageType;
import com.arrayprolc.strings.StringManager;

public class MusicClick implements Listener {
	
	public static Main plugin;
	
	ArrayList<Player> blocks = new ArrayList<Player>();
	ArrayList<Player> chirp = new ArrayList<Player>();
	ArrayList<Player> far = new ArrayList<Player>();
	ArrayList<Player> mall = new ArrayList<Player>();
	ArrayList<Player> mellohi = new ArrayList<Player>();
	ArrayList<Player> stal = new ArrayList<Player>();
	ArrayList<Player> strad = new ArrayList<Player>();
	ArrayList<Player> ward = new ArrayList<Player>();
	ArrayList<Player> eleven = new ArrayList<Player>();
	ArrayList<Location> thirteen = new ArrayList<Location>();
	ArrayList<Player> wait = new ArrayList<Player>();

	public static void initalize(Main plugin){
		MusicClick.plugin = plugin;
	}

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		Player p = (Player) event.getWhoClicked();
		if (event.getInventory().getName()
				.equalsIgnoreCase(MusicMenu.name)) {
			event.setCancelled(true);

			if (event.getCurrentItem() == null) {
				return;
			}

			if (!(event.getCurrentItem().hasItemMeta())) {
				return;
			}
			if (!event.getCurrentItem().hasItemMeta()) {
				return;
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName()
					.equals("§b§lBlocks Disk")) {
			      p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.RECORD_3, 0);
			      p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
			      p.closeInventory();
			      spawnBlocksJuke(p);

			}
			if (event.getCurrentItem().getItemMeta().getDisplayName()
					.equals("§b§lChirp Disk")) {
			      p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.RECORD_4, 0);
			      p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
			      p.closeInventory();
			      spawnChirpJuke(p);

			}
			if (event.getCurrentItem().getItemMeta().getDisplayName()
					.equals("§b§lFar Disk")) {
			      p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.RECORD_5, 0);
			      p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
			      p.closeInventory();
			      spawnFarJuke(p);

			}
			if (event.getCurrentItem().getItemMeta().getDisplayName()
					.equals("§b§lMall Disk")) {
			      p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.RECORD_6, 0);
			      p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
			      p.closeInventory();
			      spawnMallJuke(p);

			}
			if (event.getCurrentItem().getItemMeta().getDisplayName()
					.equals("§b§lMellohi Disk")) {
			      p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.RECORD_7, 0);
			      p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
			      p.closeInventory();
			      spawnMellohiJuke(p);

			}
			if (event.getCurrentItem().getItemMeta().getDisplayName()
					.equals("§b§lStal Disk")) {
			      p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.RECORD_8, 0);
			      p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
			      p.closeInventory();
			      spawnStalJuke(p);

			}
			if (event.getCurrentItem().getItemMeta().getDisplayName()
					.equals("§b§lStrad Disk")) {
			      p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.RECORD_9, 0);
			      p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
			      p.closeInventory();
			      spawnStradJuke(p);

			}

			if (event.getCurrentItem().getItemMeta().getDisplayName()
					.equals("§b§lWard Disk")) {
			      p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.RECORD_10, 0);
			      p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
			      p.closeInventory();
			      spawnWardJuke(p);

			}
			if (event.getCurrentItem().getItemMeta().getDisplayName()
					.equals("§b§l11 Disk")) {
			      p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.RECORD_11, 0);
			      p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
			      p.closeInventory();
			      spawn11Juke(p);

			}
			if (event.getCurrentItem().getItemMeta().getDisplayName()
					.equals("§b§lWait Disk")) {
			      p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.RECORD_12, 0);
			      p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
			      p.closeInventory();
			      spawnWaitJuke(p);

			}
			if (event.getCurrentItem().getItemMeta().getDisplayName()
					.equals("§b§l13 Disk")) {
				 p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.GOLD_RECORD, 0);
			      p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
			      p.closeInventory();
			      spawn13Juke(p);

			}
			if (event.getCurrentItem().getItemMeta().getDisplayName()
					.equals("§b§lCat Disk")) {
				p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.GREEN_RECORD, 0);
			      p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
			      p.closeInventory();
			      spawnCatJuke(p);
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName()
					.equals("§cGo Back")) {
				p.openInventory(CosmeticsMenu.cosmenu(p));
			}
			/*if (event.getCurrentItem().getItemMeta().getDisplayName()
					.equals("§4Turn off music")) {
				p.playSound(p.getLocation(), Sound.ANVIL_LAND, 10.0F, 10.0F);
				p.sendMessage(StringManager.getPrefix(MessageType.ERROR) + "This feature is coming soon!");
			}*/
		}
	}
	
	//Average Spawn Juke - USED IN UNKOWN SITUATIONS
	/* public void spawnjuke(final Player p) {
		 final Location l= p.getEyeLocation();
		 l.add(0, -1, 0).getBlock().setType(Material.JUKEBOX);
		 new BukkitRunnable() {
			 
			int count = 1;
			@Override
			public void run() {
				l.getBlock().setType(Material.AIR);
				runHelix(l);
				count--;
				if (count == 0) cancel();
			}
		}.runTaskTimer(plugin, 20*180, 1);
	 }*/
	 
	 //Blocks Disk
	 public void spawnBlocksJuke(final Player p) {
		 final Location l= p.getEyeLocation();
		 l.add(0, -1, 0).getBlock().setType(Material.JUKEBOX);
		 new BukkitRunnable() {
			 
			int count = 1;
			@Override
			public void run() {
				l.getBlock().setType(Material.AIR);
				runHelix(l);
				count--;
				if (count == 0) cancel();
			}
		}.runTaskTimer(plugin, 20*327, 1);
	 }
	 
	 //Chrip Disk
	 public void spawnChirpJuke(final Player p) {
		 final Location l= p.getEyeLocation();
		 l.add(0, -1, 0).getBlock().setType(Material.JUKEBOX);
		 new BukkitRunnable() {
			 
			int count = 1;
			@Override
			public void run() {
				l.getBlock().setType(Material.AIR);
				runHelix(l);
				count--;
				if (count == 0) cancel();
			}
		}.runTaskTimer(plugin, 20*183, 1);
	 }
	 
	 //Far Disk
	 public void spawnFarJuke(final Player p) {
		 final Location l= p.getEyeLocation();
		 l.add(0, -1, 0).getBlock().setType(Material.JUKEBOX);
		 new BukkitRunnable() {
			 
			int count = 1;
			@Override
			public void run() {
				l.getBlock().setType(Material.AIR);
				runHelix(l);
				count--;
				if (count == 0) cancel();
			}
		}.runTaskTimer(plugin, 3048, 1);
	 }
	 
	 //Mall Disk
	 public void spawnMallJuke(final Player p) {
		 final Location l= p.getEyeLocation();
		 l.add(0, -1, 0).getBlock().setType(Material.JUKEBOX);
		 new BukkitRunnable() {
			 
			int count = 1;
			@Override
			public void run() {
				l.getBlock().setType(Material.AIR);
				runHelix(l);
				count--;
				if (count == 0) cancel();
			}
		}.runTaskTimer(plugin, 3804, 1);
	 }
	 
	 //Mellohi Disk
	 public void spawnMellohiJuke(final Player p) {
		 final Location l= p.getEyeLocation();
		 l.add(0, -1, 0).getBlock().setType(Material.JUKEBOX);
		 new BukkitRunnable() {
			 
			int count = 1;
			@Override
			public void run() {
				l.getBlock().setType(Material.AIR);
				runHelix(l);
				count--;
				if (count == 0) cancel();
			}
		}.runTaskTimer(plugin, 16320, 1);
	 }
	 
	 //Stal Disk
	 public void spawnStalJuke(final Player p) {
		 final Location l= p.getEyeLocation();
		 l.add(0, -1, 0).getBlock().setType(Material.JUKEBOX);
		 new BukkitRunnable() {
			 
			int count = 1;
			@Override
			public void run() {
				l.getBlock().setType(Material.AIR);
				runHelix(l);
				count--;
				if (count == 0) cancel();
			}
		}.runTaskTimer(plugin, 20*138, 1);
	 }
	 
	 //Strad Disk
	 public void spawnStradJuke(final Player p) {
		 final Location l= p.getEyeLocation();
		 l.add(0, -1, 0).getBlock().setType(Material.JUKEBOX);
		 new BukkitRunnable() {
			 
			int count = 1;
			@Override
			public void run() {
				l.getBlock().setType(Material.AIR);
				runHelix(l);
				count--;
				if (count == 0) cancel();
			}
		}.runTaskTimer(plugin, 3696, 1);
	 }
	 
	 //Ward Disk
	 public void spawnWardJuke(final Player p) {
		 final Location l= p.getEyeLocation();
		 l.add(0, -1, 0).getBlock().setType(Material.JUKEBOX);
		 new BukkitRunnable() {
			 
			int count = 1;
			@Override
			public void run() {
				l.getBlock().setType(Material.AIR);
				runHelix(l);
				count--;
				if (count == 0) cancel();
			}
		}.runTaskTimer(plugin, 4932, 1);
	 }
	 
	 //11 Disk
	 public void spawn11Juke(final Player p) {
		 final Location l= p.getEyeLocation();
		 l.add(0, -1, 0).getBlock().setType(Material.JUKEBOX);
		 new BukkitRunnable() {
			 
			int count = 1;
			@Override
			public void run() {
				l.getBlock().setType(Material.AIR);
				runHelix(l);
				count--;
				if (count == 0) cancel();
			}
		}.runTaskTimer(plugin, 1332, 1);
	 }
	 
	 //Wait Disk
	 public void spawnWaitJuke(final Player p) {
		 final Location l= p.getEyeLocation();
		 l.add(0, -1, 0).getBlock().setType(Material.JUKEBOX);
		 new BukkitRunnable() {
			 
			int count = 1;
			@Override
			public void run() {
				l.getBlock().setType(Material.AIR);
				runHelix(l);
				count--;
				if (count == 0) cancel();
			}
		}.runTaskTimer(plugin, 4296, 1);
	 }
	 
	 //13 Disk
	 public void spawn13Juke(final Player p) {
		 final Location l= p.getEyeLocation();
		 l.add(0, -1, 0).getBlock().setType(Material.JUKEBOX);
		 thirteen.add(l);
		 new BukkitRunnable() {
			 
			int count = 1;
			@Override
			public void run() {
				l.getBlock().setType(Material.AIR);
				runHelix(l);
				thirteen.remove(p);
				count--;
				if (count == 0) cancel();
			}
		}.runTaskTimer(plugin, 3096, 1);
	 }
	 
	 //Cat Disk
	 public void spawnCatJuke(final Player p) {
		 final Location l= p.getEyeLocation();
		 l.add(0, -1, 0).getBlock().setType(Material.JUKEBOX);
		 new BukkitRunnable() {
			 
			int count = 1;
			@Override
			public void run() {
				l.getBlock().setType(Material.AIR);
				runHelix(l);
				thirteen.remove(p);
				count--;
				if (count == 0) cancel();
			}
		}.runTaskTimer(plugin, 20*183, 1);
	 }
	 
	 //Remove Disk/JukeBox
	 public void removeJuke(Player p) {
		 Location l = p.getLocation();
		 if (thirteen.contains(l)) {
			 l.getBlock().setType(Material.AIR);
		 }
	 }
	 
	 public static void runHelix(Location loc) {
		   
	        double radius = 5;
	   
	        for (double y = 5; y >= 0; y -= 0.100) {
	            radius = y / 3;
	            double x = radius * Math.cos(3 * y);
	            double z = radius * Math.sin(3 * y);
	       
	            double y2 = 5 - y;
	       
	            ParticleLib18 notes = new ParticleLib18(me.mike1665.particles18.ParticleLib18.ParticleType.NOTE, 0, 1, 5);
	            Location loc2 = new Location(loc.getWorld(), loc.getX() + x, loc.getY() + y2, loc.getZ() + z);
	            //ParticleEffect.NOTE.display(loc2, 0, 0, 0, 0, 1);
	            notes.sendToLocation(loc2);
	        }
	   
	        for (double y = 5; y >= 0; y -= 0.100) {
	            radius = y / 3;
	            double x = -(radius * Math.cos(3 * y));
	            double z = -(radius * Math.sin(3 * y));
	       
	            double y2 = 5 - y;
	       
	            ParticleLib18 notes = new ParticleLib18(me.mike1665.particles18.ParticleLib18.ParticleType.NOTE, 0, 1, 5);
	            Location loc2 = new Location(loc.getWorld(), loc.getX() + x, loc.getY() + y2, loc.getZ() + z);
	            //ParticleEffect.NOTE.display(loc2, 0, 0, 0, 0, 1);
	            notes.sendToLocation(loc2);

	        }
	   
	    }
}