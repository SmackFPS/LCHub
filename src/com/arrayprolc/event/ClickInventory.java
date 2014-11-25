package com.arrayprolc.event;

import me.mike1665.Main.Main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class ClickInventory implements Listener {
	
	public Main plugin;
	
	public ClickInventory(Main instance){
		plugin = instance;
	}
	
	@EventHandler
	public void click(InventoryClickEvent e){
		if(e.getClickedInventory().getType().equals(InventoryType.CRAFTING) || e.getClickedInventory().getType().equals(InventoryType.CREATIVE)){
			((Player) e.getWhoClicked()).sendMessage(e.getSlot() + "");
		}
	}

}
