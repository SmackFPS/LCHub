package com.lightcraftmc.event;

import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.material.Dye;

import com.lightcraftmc.fusebox.menu.Menu;
import com.lightcraftmc.fusebox.util.item.ItemTools;

public class ColouredWardrobe implements Listener {

	public static void openColorWardrobe(Player p, WardrobeType a){
		Menu m = new Menu("Wardrobe: " + a.toString(), getClosestTo(16, 9));
		int end = 0;
		for(int i = 0; i < 16; i++){
			byte dyeColour = (byte)i;
			int slot = (DyeColor.values().length-1) - i;
			String colour = WordUtils.capitalize(DyeColor.values()[slot].toString().toLowerCase().replace("_", " "));
			ItemStack dye = ItemTools.setName(new ItemStack(Material.INK_SACK, 1, dyeColour),  ChatColor.RESET + "" + colour);
			m.addItem(dye, i);
			end++;
		}
		end = end + 2;
		m.addItem(ItemTools.setName(new ItemStack(Material.ARROW), "§a<§a§m-- §f§aGo Back"), end);
		m.displayMenu(p);
	}
	@EventHandler
	public void onInteract(InventoryClickEvent e){
		if(!e.getInventory().getTitle().contains("Wardrobe:")){
			return;
		}
		String type = e.getInventory().getTitle().split("Wardrobe: ")[1];
		Material t = getType(type);
		if(e.getCurrentItem().getType().equals(Material.ARROW)){
			//send the player back a level in the menu
		}
		if(!(e.getCurrentItem().getData() instanceof Dye)) return;
		Dye dye =  (Dye) e.getCurrentItem().getData();
		ItemStack lhelmet = new ItemStack(t, 1);
		LeatherArmorMeta lam = (LeatherArmorMeta)lhelmet.getItemMeta();
		lam.setColor(dye.getColor().getColor());
		lhelmet.setItemMeta(lam);
		switch(t){
		case LEATHER_HELMET: e.getWhoClicked().getInventory().setHelmet(lhelmet); break;
		case LEATHER_CHESTPLATE: e.getWhoClicked().getInventory().setChestplate(lhelmet);break;
		case LEATHER_LEGGINGS: e.getWhoClicked().getInventory().setLeggings(lhelmet);break;
		case LEATHER_BOOTS: e.getWhoClicked().getInventory().setBoots(lhelmet);break;
		default: return;
		}
	}
	
	
	public static Material getType(String s){
		s = s.toLowerCase();
		for(Material m : Material.values()){
			if(m.toString().toLowerCase().contains("LEATHER") && m.toString().toLowerCase().contains(s)){
				return m;
			}
		}
		return Material.LEATHER_CHESTPLATE;
	}

	public static int getClosestTo(int j, int rejex){
		int working = rejex; for(int i = 0; i < 9*7; i++){
			if(j > working){
				working = working + rejex; 
			}
		}
		return working;
	}
	
}
