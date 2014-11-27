package me.mike1665.click;

import me.mike1665.menu.VipGadjets;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class VipGadjetsClick implements Listener{
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		Player p = (Player) event.getWhoClicked();
		if (event.getInventory().getName().equalsIgnoreCase(VipGadjets.vipgadmenu.getName())) {
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
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§bMeow Balls")) {
                	ItemStack snow = new ItemStack(Material.SNOW_BALL, 16);
                	ItemMeta sno = snow.getItemMeta();
                	sno.setDisplayName(ChatColor.AQUA + "MeowBall");
                	snow.setItemMeta(sno);
                	p.getInventory().addItem(snow);
                	p.closeInventory();
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName()
					.equals("§5Ender Doge")) {
                	ItemStack enderpearl = new ItemStack(Material.ENDER_PEARL, 16);
                	ItemMeta ender = enderpearl.getItemMeta();
                	ender.setDisplayName(ChatColor.DARK_AQUA + "EnderDoge");
                	enderpearl.setItemMeta(ender);
                	p.getInventory().addItem(enderpearl);
                	p.closeInventory();
		}
			if (event.getCurrentItem().getItemMeta().getDisplayName()
					.equals("§5Cat Works")) {
                	ItemStack categg = new ItemStack(Material.STICK, 1);
                	ItemMeta cat = categg.getItemMeta();
                	cat.setDisplayName(ChatColor.YELLOW + "Cat Works");
                	categg.setItemMeta(cat);
                	p.getInventory().addItem(categg);
                	p.closeInventory();
		}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§1Entity Hook")) {
				ItemStack ender = new ItemStack(Material.TRIPWIRE_HOOK, 1);
            	ItemMeta e = ender.getItemMeta();
            	e.setDisplayName(ChatColor.DARK_BLUE + "Entity Hook");
            	ender.setItemMeta(e);
            	p.getInventory().addItem(ender);
            	p.closeInventory();
			} 
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cTNT")) {
				ItemStack ender = new ItemStack(Material.TRIPWIRE_HOOK, 1);
            	ItemMeta e = ender.getItemMeta();
            	e.setDisplayName(ChatColor.RED + "TNT Fun");
            	ender.setItemMeta(e);
            	p.getInventory().addItem(ender);
            	p.closeInventory();
			} 
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cGo Back")) {
        		p.openInventory(me.mike1665.menu.GadjetsMenu.gadmenu);
      		  	p.playSound(p.getLocation(), Sound.DOOR_CLOSE, 10, 10);

			} 
			
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§dPaintball Gun")) {
				ItemStack ender = new ItemStack(Material.DIAMOND_BARDING, 1);
            	ItemMeta e = ender.getItemMeta();
            	e.setDisplayName(ChatColor.LIGHT_PURPLE + "Paintball Gun");
            	ender.setItemMeta(e);
            	p.getInventory().addItem(ender);
            	p.closeInventory();
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§eMob Gun")) {
				ItemStack ender = new ItemStack(Material.BLAZE_ROD, 1);
            	ItemMeta e = ender.getItemMeta();
            	e.setDisplayName("§6§lMob Gun §f§l- §b§l");
            	ender.setItemMeta(e);
            	p.getInventory().addItem(ender);
            	p.closeInventory();
			}
			
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§7Bat Blaster")) {
				ItemStack ender = new ItemStack(Material.IRON_BARDING, 1);
            	ItemMeta e = ender.getItemMeta();
            	e.setDisplayName(ChatColor.DARK_GRAY + "Bat Blaster");
            	ender.setItemMeta(e);
            	p.getInventory().addItem(ender);
            	p.closeInventory();
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§bGem Bomb")) {
				ItemStack ender = new ItemStack(Material.EMERALD, 1);
            	ItemMeta e = ender.getItemMeta();
            	e.setDisplayName(ChatColor.DARK_GREEN + "Gem Bomb");
            	ender.setItemMeta(e);
            	p.getInventory().addItem(ender);
            	p.closeInventory();
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§1Effetcs")) {
				ItemStack ender = new ItemStack(Material.STICK, 1);
            	ItemMeta e = ender.getItemMeta();
            	e.setDisplayName(ChatColor.GREEN + "Effect");
            	ender.setItemMeta(e);
            	p.getInventory().addItem(ender);
            	p.closeInventory();
			}
	}	
	}
}