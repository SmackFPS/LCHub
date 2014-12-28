package com.lightcraftmc.event.handlers;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import net.lightcraftmc.fusebox.util.strings.MessageType;
import net.lightcraftmc.fusebox.util.strings.StringManager;

public class MelonBlock implements Listener{
	
	public static ItemStack createItem(Material material, int amount, boolean isEnchanted, String name, String lore) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setAmount(amount);
		ArrayList<String> Lore = new ArrayList<String>();
		Lore.add(lore);
		meta.setLore(Lore);
		if(isEnchanted) {
		meta.addEnchant(Enchantment.OXYGEN, 1, true);
		} else {
			//Do Nothing
		}
		item.setItemMeta(meta);
		
		return item;
		 
		}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerPlaceBlock(PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		  if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
		 if ((disName(player.getItemInHand()) != null) && (disName(player.getItemInHand()).equalsIgnoreCase(ChatColor.RED + "Melon Block"))){
			 	player.playSound(player.getLocation(), Sound.LAVA_POP, 10, 10);
				player.getWorld().dropItemNaturally(player.getLocation().add(new Vector(0, 5, 0)), createItem(Material.MELON, 64, false, ChatColor.GREEN + "" + ChatColor.BOLD + "Super Melon", ChatColor.WHITE + "Eat them to get super speed!"));
				player.sendMessage(StringManager.getPrefix(MessageType.GADGETS) +  "What have you done?!");
				event.setCancelled(true);
				player.setItemInHand(null);
		 		}
		 if ((disName(player.getItemInHand()) != null) && (disName(player.getItemInHand()).equalsIgnoreCase(ChatColor.GREEN + "" +  ChatColor.BOLD + "Super Melon"))){
			 	player.playSound(player.getLocation(), Sound.ORB_PICKUP, 10, 10);
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 150, 5));
				player.sendMessage(StringManager.getPrefix(MessageType.GADGETS) + "* You feel different now...");

				event.setCancelled(true);
				player.setItemInHand(null);
		 		}
			}
		}
	
	public String disName(ItemStack i)
	{
	  if (i == null) {
	    return null;
	  }
	  if (!i.hasItemMeta()) {
	    return null;
	  }
	  if (!i.getItemMeta().hasDisplayName()) {
	    return null;
	  }
	  return i.getItemMeta().getDisplayName();
	}
}