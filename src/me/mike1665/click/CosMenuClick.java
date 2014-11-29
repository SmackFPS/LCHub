package me.mike1665.click;

import me.mike1665.menu.BuyGadgets;
import me.mike1665.menu.CosmeticsMenu;
import me.mike1665.menu.GadjetsMenu;
import me.mike1665.menu.MountMenu;
import me.mike1665.menu.MusicMenu;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CosMenuClick implements Listener{
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		Player p = (Player) event.getWhoClicked();
		if (event.getInventory().getName().equalsIgnoreCase(CosmeticsMenu.name)) {
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
			
			if (!event.getCurrentItem().hasItemMeta()) {
				return;
			}

			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aGadgets")) {
      		  	p.playSound(p.getLocation(), Sound.DOOR_OPEN, 10, 10);
      		  	p.openInventory(GadjetsMenu.gadmenu);
				return;
			}
                
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cMounts")) {
      		  	p.playSound(p.getLocation(), Sound.DOOR_OPEN, 10, 10);
				p.openInventory(MountMenu.getMountShop(p));
				return;

			}
			
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§8Hub Purchaseables")) {
      		  	p.playSound(p.getLocation(), Sound.DOOR_OPEN, 10, 10);
				fr.coco_gigpn.prodigygadget.menu.menulist.Gadgets.openGadgetGui(p);
				return;

			} 
			
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Pets")) {
				p.sendMessage(ChatColor.GRAY + "[" + ChatColor.LIGHT_PURPLE + "Hub" + ChatColor.GRAY + "] " + ChatColor.RED + "COMING SOON");
      		  	p.playSound(p.getLocation(), Sound.DOOR_CLOSE, 10, 10);
				p.closeInventory();
				return;

			} 
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§3JukeBox")) {
      		  	p.playSound(p.getLocation(), Sound.DOOR_OPEN, 10, 10);
				p.openInventory(MusicMenu.musicmenu(p));
				return;
			}
		}
	}
	}