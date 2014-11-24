package me.mike1665.click;

import me.mike1665.coinapi.PixlCoinsAPI;
import me.mike1665.coinapi.PixlPointsAPI;
import me.mike1665.menu.BuyGadgets;
import me.mike1665.menu.CosmeticsMenu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BuyGadgetsClick implements Listener{
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		Player p = (Player) event.getWhoClicked();
		if (event.getInventory().getName().equalsIgnoreCase(BuyGadgets.buygadmenu.getName())) {
			event.setCancelled(true);

			if (event.getCurrentItem() == null) {
				return;
			}

			if (!(event.getCurrentItem().hasItemMeta())) {
				return;
			}

			}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§5Meow Balls")) {
				if(PixlCoinsAPI.hasEnough(p, 60)) {
					PixlCoinsAPI.takePoints(p, 60);
                	ItemStack snow = new ItemStack(Material.SNOW_BALL, 16);
                	ItemMeta sno = snow.getItemMeta();
                	sno.setDisplayName(ChatColor.GREEN + "MeowBall");
                	snow.setItemMeta(sno);
                	p.getInventory().addItem(snow);
                	p.closeInventory();
				} else {
					p.sendMessage(ChatColor.RED + "You dont have enough money!");
				}
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§9Ender Doge")) {
				if(PixlCoinsAPI.hasEnough(p, 50)) {
					PixlCoinsAPI.takePoints(p, 50);
                	ItemStack enderpearl = new ItemStack(Material.ENDER_PEARL, 16);
                	ItemMeta ender = enderpearl.getItemMeta();
                	ender.setDisplayName(ChatColor.BLUE + "EnderDoge");
                	enderpearl.setItemMeta(ender);
                	p.getInventory().addItem(enderpearl);
                	p.closeInventory();
				}else {
					p.sendMessage(ChatColor.RED + "You dont have enough money!");
				}
		}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§5Fireworks")) {
				if(PixlCoinsAPI.hasEnough(p, 100)) {
					PixlCoinsAPI.takePoints(p, 100);
					ItemStack ender = new ItemStack(Material.FIREWORK, 64);
					ItemMeta e = ender.getItemMeta();
					e.setDisplayName(ChatColor.DARK_BLUE + "Firework Launcher");
					ender.setItemMeta(e);
					p.getInventory().addItem(ender);
					p.closeInventory();
				}else {
					p.sendMessage(ChatColor.RED + "You dont have enough money!"); 
				}
			} 
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§1Entity Hook")) {
				if(PixlPointsAPI.hasEnough(p, 50)) {
					PixlPointsAPI.takePoints(p, 50);
					ItemStack ender = new ItemStack(Material.TRIPWIRE_HOOK, 10);
					ItemMeta e = ender.getItemMeta();
            		e.setDisplayName(ChatColor.DARK_BLUE + "Entity Hook");
            		ender.setItemMeta(e);
            		p.getInventory().addItem(ender);
            		p.closeInventory();
				}else {
					p.sendMessage(ChatColor.RED + "You dont have enough money!");
				}
			} 
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cTNT")) {
				if(PixlCoinsAPI.hasEnough(p, 30)) {
					PixlCoinsAPI.takePoints(p, 30);
					ItemStack ender = new ItemStack(Material.TNT, 10);
					ItemMeta e = ender.getItemMeta();
            		e.setDisplayName(ChatColor.RED + "Fun Bomb");
            		ender.setItemMeta(e);
            		p.getInventory().addItem(ender);
            		p.closeInventory();
				}else {
					p.sendMessage(ChatColor.RED + "You dont have enough money!");
				}
			} 
			
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§2Coin Bomb")) {
				if(PixlCoinsAPI.hasEnough(p, 5000)) {
					PixlCoinsAPI.takePoints(p, 5000);
					ItemStack ender = new ItemStack(Material.DOUBLE_PLANT, 1);
					ItemMeta e = ender.getItemMeta();
					e.setDisplayName(ChatColor.DARK_GREEN + "Coin Bomb");
            		ender.setItemMeta(e);
            		p.getInventory().addItem(ender);
            		p.closeInventory();
				}else {
					p.sendMessage(ChatColor.RED + "You dont have enough money!");	
				}
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§dPixel Bomb")) {
				if(PixlPointsAPI.hasEnough(p, 100)) {
					PixlPointsAPI.takePoints(p, 100);
					ItemStack ender = new ItemStack(Material.NETHER_STAR, 1);
					ItemMeta e = ender.getItemMeta();
					e.setDisplayName(ChatColor.LIGHT_PURPLE + "Pixel Bomb");
					ender.setItemMeta(e);
					p.getInventory().addItem(ender);
            		p.closeInventory();
				} else {
					p.sendMessage(ChatColor.RED + "You dont have enough money!");
				}
		}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cGo Back")) {
				p.openInventory(CosmeticsMenu.cosmenu);
				p.playSound(p.getLocation(), Sound.DOOR_CLOSE, 10, 10);
				return;
		}
	}
}
