package com.arrayprolc.event;

import me.mike1665.Main.Main;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import com.arrayprolc.item.ItemTools;
import com.arrayprolc.menu.Menu;
import com.arrayprolc.strings.MessageType;
import com.arrayprolc.strings.StringManager;

public class ClickInventory implements Listener {

	public Main plugin;

	Menu selector = new Menu("§aGame Selector", 9*3);
	public ClickInventory(Main instance){
		plugin = instance;
		selector.addItem(ItemTools.setName(new ItemStack(Material.SAND), "§a§lWalls Tower", "§7§oClick to join!"), 3);
		selector.addItem(ItemTools.setName(new ItemStack(Material.DIAMOND_SWORD), "§9§lGUILD WARS", "§7§oClick to join!"), 4);
		selector.addItem(ItemTools.setName(new ItemStack(Material.DIAMOND_BLOCK), "§a§lCreative Buildoff", new String[] { "§7§oClick to join!", "§7§oRequires Minecraft §a1.8" }), 5);
		selector.addItem(ItemTools.setName(new ItemStack(Material.FIREWORK), "§b§lParty", "§7§oClick to configure!"), 22);
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
	public void join(PlayerJoinEvent e){
		e.getPlayer().getInventory().setItem(0, ItemTools.setName(new ItemStack(Material.BOOK), "§aGame Selector §7(Right-Click)"));
		e.getPlayer().getInventory().setItem(8, ItemTools.setName(new ItemStack(Material.EMERALD), "§aShop §7(Right-Click)"));
	}
	
	@EventHandler
	public void interact(PlayerInteractEvent e){
		if(!e.getAction().toString().contains("CLICK")) return;
		if(e.getPlayer().getItemInHand().getType().equals(Material.BOOK)){
			e.setCancelled(true);
			selector.displayMenu(e.getPlayer());
		}
	}

}
