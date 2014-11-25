package me.mike1665.click;

import me.mike1665.Main.Main;
import me.mike1665.menu.AdminGadgets;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class AdminGadgetsClick implements Listener{
	
	private Main plugin;
	public AdminGadgetsClick(Main main) {
		this.plugin = main;
	}
	
	int num = 1;
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		Player p = (Player) event.getWhoClicked();
		if (event.getInventory().getName().equalsIgnoreCase(AdminGadgets.admingadmenu.getName())) {
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

			}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aTwerking Zombie")) {
					p.sendMessage(ChatColor.GRAY + "[" + ChatColor.YELLOW + "Gadgets" + ChatColor.GRAY + "] " + ChatColor.AQUA + "Coming soon!");
                	p.closeInventory();
                	return;
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cRainbow Sheep")) {
					spawnSHEEP(p);
					p.sendMessage(ChatColor.GRAY + "[" + ChatColor.YELLOW + "Gadgets" + ChatColor.GRAY + "] " + ChatColor.AQUA + "Spawned a Rainbow Sheep");
                	p.closeInventory();
                	return;
		}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§1Wear Rainbow Armor")) {
					Main.activate.add(p.getName());
	        		Main.rainbowarmor.put(p.getName(), "red");
					p.sendMessage(ChatColor.GRAY + "[" + ChatColor.YELLOW + "Gadgets" + ChatColor.GRAY + "] " + ChatColor.AQUA + "Rainbow Armor Active. To Turn off do - /caoff");
                	p.closeInventory();
                	return;
		}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cGo Back")) {
        		p.openInventory(me.mike1665.menu.GadjetsMenu.gadmenu);
      		  	p.playSound(p.getLocation(), Sound.DOOR_CLOSE, 10, 10);
      		  	return;

			} 
	}
	
	@SuppressWarnings("deprecation")
	public void spawnSHEEP(Player player) {
		final org.bukkit.entity.Sheep sheep = (org.bukkit.entity.Sheep) Bukkit.getWorld("world").spawnEntity(player.getLocation(), EntityType.SHEEP);
		sheep.setCustomName(ChatColor.AQUA + "Swag Sheep");
		sheep.setMaxHealth(20);
		sheep.setColor(DyeColor.RED);
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin,
		new Runnable() {			
			@Override
			public void run() {
				if (num == 1){
				sheep.setColor(DyeColor.RED);
				num++;			
			} else if (num == 2){
				sheep.setColor(DyeColor.ORANGE);
				num++;
			} else if (num == 3) {
				sheep.setColor(DyeColor.YELLOW);
				num++;
			} else if (num == 4) {
				sheep.setColor(DyeColor.LIME);
				num++;
			} else if (num == 5) {
				sheep.setColor(DyeColor.BLUE);
				num++;
			} else if (num == 6) {
				sheep.setColor(DyeColor.PURPLE);
				num++;
			}else if (num ==7 ) {
				sheep.setColor(DyeColor.MAGENTA);
				num = 1 ;
				}
			}	
		}, 0 , 1 * 2);
	}
		
}