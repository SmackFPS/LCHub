package com.arrayprolc.event;

import me.mike1665.Main.Main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;

import com.arrayprolc.strings.MessageType;
import com.arrayprolc.strings.StringManager;

public class ClickInventory implements Listener {

	public Main plugin;

	public ClickInventory(Main instance){
		plugin = instance;
	}

	@EventHandler
	public void click(InventoryClickEvent e){
		if(e.getSlotType().equals(SlotType.ARMOR)){
			e.setCancelled(true);
			e.getWhoClicked().closeInventory();
			((Player) e.getWhoClicked()).sendMessage(StringManager.getMessage("To change armor, use the Hub Gadgets menu.", MessageType.ERROR));
		}
	}

}
