package com.arrayprolc.event;

import java.util.HashMap;
import java.util.UUID;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import com.arrayprolc.bountifulupdate.BUtils;
import com.arrayprolc.coin.Multiplier;
import com.arrayprolc.menu.Menu;
import com.arrayprolc.tools.ItemTools;

public class TabHeaderSetup implements Listener {

	public Main plugin;
	public static HashMap<UUID, Integer> age = new HashMap<UUID, Integer>();
	String ageInventoryName = "§c§lPlease select your age.";
	Menu m;
	public TabHeaderSetup(Main instance){
		plugin = instance;
		m = new Menu(ageInventoryName, 9);
		m.addItem(ItemTools.setName(new ItemStack(Material.NAME_TAG, 1), "§9Under 13", "§7§oAll ages are allowed!"), 0);
		m.addItem(ItemTools.setName(new ItemStack(Material.NAME_TAG, 13), "§913-16", "§7§oAll ages are allowed!"), 4);
		m.addItem(ItemTools.setName(new ItemStack(Material.NAME_TAG, 16), "§916+", "§7§oAll ages are allowed!"), 8);
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(instance, new Runnable(){
			public void run(){
				for(Player p : Bukkit.getOnlinePlayers()){
					if(!age.containsKey(p.getUniqueId())){
						if(!p.getOpenInventory().getTitle().equals(m.getName())){
							m.displayMenu(p);
						}
					}else{
						if(p.getInventory().getName().equalsIgnoreCase(m.getName())){
							p.closeInventory();
						}
					}
				}
			}
		}, 0, 1);


	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		BUtils.sendHeaderAndFooter(e.getPlayer(), "§9Light§bCraft §3Network", "§7Buy Ranks, Gadgets and more at store.lightcraftmc.com");
		String sub = "";
		if(Multiplier.coin(e.getPlayer()) > 1){
			sub = sub + "§a" + Multiplier.coin(e.getPlayer()) + "x §aCoins";
		}
		if(Multiplier.token(e.getPlayer()) > 1){
			if(sub.equals("")){
			sub = sub + "§a" + Multiplier.coin(e.getPlayer()) + "x §dTokens";
			}else{
				sub = sub + "§7 ▪ §a" + Multiplier.coin(e.getPlayer()) + "x §dTokens";
			}
		}
		BUtils.sendTitle(e.getPlayer(), "", sub, 5, 5, 5);
		e.getPlayer().teleport(e.getPlayer().getWorld().getSpawnLocation());
		e.getPlayer().setGameMode(GameMode.ADVENTURE);
		e.setJoinMessage(null);
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e){
		e.setQuitMessage(null);
	}
	
	@EventHandler
	public void click(final InventoryClickEvent e){
		if(!e.getInventory().getName().equalsIgnoreCase(m.getInventory().getName())){
			return;
		}
		
		if(e.getSlot() == 0){
			age.put(e.getWhoClicked().getUniqueId(), 11);
		}
		if(e.getSlot() == 4){
			age.put(e.getWhoClicked().getUniqueId(), 13);
		}
		if(e.getSlot() == 8){
			age.put(e.getWhoClicked().getUniqueId(), 17);
		}
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
			public void run(){
				e.getWhoClicked().closeInventory();
				((Player) e.getWhoClicked()).sendMessage("§c§lThank you! You will be asked again in one month.");
			}
		}, 3);
		
	}

}
