package com.arrayprolc.event;

import me.mike1665.Main.Main;
import net.minecraft.server.v1_8_R1.PacketPlayOutNamedSoundEffect;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import com.arrayprolc.bungeehook.BungeeHooks;
import com.arrayprolc.bungeehook.Servers;
import com.arrayprolc.menu.Menu;
import com.arrayprolc.serverjoin.PartyTools;
import com.arrayprolc.strings.MessageType;
import com.arrayprolc.strings.StringManager;
import com.arrayprolc.tools.ItemTools;

public class ClickInventory implements Listener {
	Main plugin;

	Menu selector = new Menu("§aGame Selector", 9*3);
	boolean flash = false;
	int ticks = 0;
	public ClickInventory(){
		plugin = Main.getInstance();

		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable(){
			public void run(){
				try{ selector.addItem(ItemTools.setName(new ItemStack(Material.DIAMOND), "§9§lCreative Server", new String[] {"§7§oInvite-Only", "§7" + getTotalPlayers("creative")[0] + " players.", "§a13§7+" }), 22+4); }catch(Exception e){}
				try{ selector.addItem(ItemTools.setName(new ItemStack(Material.SAND), "§a§lWalls Tower", new String[] { getFlashyColour() + "§oClick to join!", "§7" + getTotalPlayers("wt")[0] + " players on " + getTotalPlayers("wt")[1] + " servers." }), 3); }catch(Exception e){}
				try{ selector.addItem(ItemTools.setName(new ItemStack(Material.DIAMOND_SWORD), "§9§lGUILD WARS", new String[] { getFlashyColour() + "§oClick to join!", "§7" + getTotalPlayers("guildwars")[0] + " players." }), 4); }catch(Exception e){}
				try{ selector.addItem(ItemTools.setName(new ItemStack(Material.DIAMOND_BLOCK), "§a§lCreative Buildoff", new String[] { getFlashyColour() + "§oClick to join!", "§7" + getTotalPlayers("cb")[0] + " players on " + getTotalPlayers("cb")[1] + " servers." }), 5); }catch(Exception e){}
				//try{ selector.addItem(ItemTools.setName(new ItemStack(Material.FIREWORK), "§b§lParty", getFlashyColour() + "§oClick to configure!"), 22-4); }catch(Exception e){}
				try{ selector.addItem(ItemTools.setName(new ItemStack(Material.POTION), "§§a§lLightCraft: Adventure.", new String[] { getFlashyColour() + "§oClick to join!", "§7" + getTotalPlayers("cb")[0] + " players on " + getTotalPlayers("lca")[1] + " servers." }), 6); }catch(Exception e){}
				ticks++;
				if(ticks % 5 == 0) flash = !flash;
				if(ticks % 5 == 0) ticks = 0;
			}
		}, 0, 1);

	}

	@EventHandler
	public void click(InventoryClickEvent e){
		if(e.getSlotType().equals(SlotType.ARMOR)){
			e.setCancelled(true);
			e.getWhoClicked().closeInventory();
			((Player) e.getWhoClicked()).sendMessage(StringManager.getMessage("To change armor, use the Hub Gadgets menu.", MessageType.ERROR));
		}
	}
	
	@EventHandler
	public void interact2(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(p.getItemInHand().getType().equals(Material.SLIME_BALL)){
			customSound(e.getPlayer(), "airhorn");
			p.setItemInHand(p.getItemInHand());
		}
	}
	public void customSound(Player player, String sound, float vol, float pitch){
		Player p = player;
		PacketPlayOutNamedSoundEffect packet = new PacketPlayOutNamedSoundEffect(sound, p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ(), vol, pitch);
		((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
	}

	public void customSound(Player player, String sound){
		customSound(player, sound, 1.0F, 1.0F);
	}
	@EventHandler
	public void join(PlayerJoinEvent e){
		e.getPlayer().getInventory().setItem(0, ItemTools.setName(new ItemStack(Material.BOOK), "§aGame Selector §7(Right-Click)"));
		//e.getPlayer().getInventory().setItem(8, ItemTools.setName(new ItemStack(Material.EMERALD), "§aShop §7(Right-Click)"));
	}

	@EventHandler
	public void interact(PlayerInteractEvent e){
		if(!e.getAction().toString().contains("CLICK")) return;
		if(e.getPlayer().getItemInHand().getType().equals(Material.BOOK)){
			e.setCancelled(true);
			Main.requestPlayerList();
			selector.displayMenu(e.getPlayer());
		}
	}

	public int[] getTotalPlayers(String pfx){
		int serverCount = 0;
		int players = 0;
		for(String s : Servers.servers){
			if(s.toLowerCase().startsWith(pfx.toLowerCase())){
				serverCount++;
				players = players + BungeeHooks.players.get(s);
			}
		}
		return new int[] { players, serverCount };
	}



	public String getFlashyColour(){
		if(flash){
			return "§7";
		}
		return "§e";
	}


	@EventHandler
	public void move(PlayerMoveEvent e){
		if(e.getPlayer().getLocation().getY() < 1) e.getPlayer().teleport(e.getPlayer().getWorld().getSpawnLocation());
	}

	@EventHandler
	public void click2(InventoryClickEvent e){
		if(!e.getInventory().getName().equals(selector.getName())) return;
		final Player p = (Player) e.getWhoClicked();
		switch(e.getCurrentItem().getType()){
		default: return;
		case DIAMOND:{
			if(TabHeaderSetup.age.get(e.getWhoClicked().getUniqueId()) < 13){
				((Player) e.getWhoClicked()).sendMessage("§c§lYou are not old enough to join this server!");
				return;
			}
			sendToFirstOpenServer(p, "creative", 20, "Build Team Server");
			return;
		}
		case POTION:{
			if(TabHeaderSetup.age.get(e.getWhoClicked().getUniqueId()) < 13){
				((Player) e.getWhoClicked()).sendMessage("§c§lYou are not old enough to join this server!");
				return;
			}
			sendToFirstOpenServer(p, "lca", 300, "Adventure");
			return;
		}
		case SAND:{
			sendToFirstOpenServer(p, "wt", 5*4, "Walls Tower");
			return;

		}
		case DIAMOND_SWORD:{
			sendToFirstOpenServer(p, "guildwars", 300, "Guild Wars");
			return;

		}
		case DIAMOND_BLOCK:{
			sendToFirstOpenServer(p, "cb", 16, "Creative Buildoff");
			return;
			
		}
		case NAME_TAG:{
			if(!p.isOp()){
				p.sendMessage("§cYou do not have permission to do that.");
				return;
			}
			//TODO make this work
			return;

		}
		}
	}

	static String getFirstOpenServer(Player p, String pfx, int maxPerGame){
		int amountOfPlayers = PartyTools.playersWith(p);
		Main.requestPlayerList();
		for(String s : Servers.servers){
			if(s.toLowerCase().equalsIgnoreCase(pfx.toLowerCase())){
				return s;
			}
			if(s.toLowerCase().startsWith(pfx.toLowerCase())){
				int playersOnline = BungeeHooks.players.get(s);
				if(!(playersOnline >= maxPerGame)){
					int i = (maxPerGame - playersOnline);
					if(i >= amountOfPlayers){
						return s;
					}
				}
			}
		}
		return "none";
	}

	public static void sendToFirstOpenServer(Player p, String pfx, int maxPerGame, String gameDisplay){
		if(!PartyTools.hasControl(p)){
			p.sendMessage(StringManager.getPrefix(MessageType.ERROR) + "You are not the party leader!");
			return;
		}
		String firstOpenServer = getFirstOpenServer(p, pfx, maxPerGame);
		if(firstOpenServer.equalsIgnoreCase("none")){
			p.sendMessage(StringManager.getPrefix(MessageType.ERROR) + "No servers are currently open for that game. Please wait a few minutes and try again!");
			return;
		}
		for(OfflinePlayer p2 : PartyTools.getPartyMembers(p)){
			Bukkit.getPlayerExact(p2.getName()).sendMessage(StringManager.getPrefix(MessageType.SUCCESS) + "Sending you" + and(PartyTools.getPartyMembers(p).length, (" and " + (PartyTools.playersWith(p2)-1) + " other player" + plural((PartyTools.playersWith(p2)-1)))) + " to " + gameDisplay + " #" + getAmount(firstOpenServer, pfx) + ".");
			PartyTools.sendPlayerToServer(firstOpenServer, Bukkit.getPlayerExact(p2.getName()));
		} 

	}
	static String plural(int i){
		if(i > 1){
			return "s";
		}
		return "";
	}

	static int getAmount(String s, String remove){
		s = s.replace(remove, "");
		if(s.equalsIgnoreCase("")){
			return 1;
		}
		return Integer.parseInt(s);
	}
	public static String and(int i, String s){
		if(i > 0){
			return s;
		}
		return "";
	}

}
