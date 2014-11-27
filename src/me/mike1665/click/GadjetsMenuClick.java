package me.mike1665.click;


import me.mike1665.menu.GadjetsMenu;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GadjetsMenuClick implements Listener{
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		Player p = (Player) event.getWhoClicked();
		if (event.getInventory().getName().equalsIgnoreCase(GadjetsMenu.gadmenu.getName())) {
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

			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Vip Gadgets")) {
                if (p.hasPermission("vip.gad") || p.isOp()) {
                		p.openInventory(me.mike1665.menu.VipGadjets.vipgadmenu);
              		  	p.playSound(p.getLocation(), Sound.DOOR_OPEN, 10, 10);

				} else {
					p.sendMessage(ChatColor.GRAY + "[" + ChatColor.YELLOW + "Gadgets" + ChatColor.GRAY + "] " + ChatColor.AQUA + "Please purchase VIP at pixlpunch.com!");
					p.closeInventory();
				}
			}
                
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§4Admin Gadgets")) {
                if (p.hasPermission("admin.gad") || p.isOp()) {
                		p.openInventory(me.mike1665.menu.AdminGadgets.admingadmenu);
                		p.playSound(p.getLocation(), Sound.DOOR_OPEN, 10, 10);

                } else {
					p.sendMessage(ChatColor.GRAY + "[" + ChatColor.YELLOW + "Gadgets" + ChatColor.GRAY + "] " + ChatColor.AQUA + "Apply for admin @ pixlpunch.com!");
					p.closeInventory();
                }
			}
			
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aRegular Gadgets")) {
        		p.openInventory(me.mike1665.menu.PlayerGadjets.plrgadmenu);
      		  	p.playSound(p.getLocation(), Sound.DOOR_OPEN, 10, 10);

			} 
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§bBack to Hub Menu")) {
				p.closeInventory();
        		p.openInventory(me.mike1665.menu.CosmeticsMenu.cosmenu((Player) event.getWhoClicked()));
      		  	p.playSound(p.getLocation(), Sound.DOOR_CLOSE, 10, 10);
			} 
		}
	}
	}
		