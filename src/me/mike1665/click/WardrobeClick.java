package me.mike1665.click;

import me.mike1665.coinapi.LcTokensAPI;
import me.mike1665.menu.CosmeticsMenu;
import me.mike1665.menu.WardrobeMenu;
import me.mike1665.mount.MountManager;
import me.mike1665.mount.mounts.DarkRider;
import me.mike1665.mount.mounts.GhostRider;
import me.mike1665.mount.mounts.NyanRider;
import me.mike1665.mount.mounts.PoseidonRider;
import me.mike1665.wardrobe.WardrobeManager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class WardrobeClick
  implements Listener
{
  @EventHandler
  public void onClick(InventoryClickEvent event)
  {
    Player p = (Player)event.getWhoClicked();
    try
    {
      if (event.getInventory().getName().equalsIgnoreCase("Wardrobe Menu"))
      {
        event.setCancelled(true);
        if (event.getCurrentItem() == null) {
          return;
        }
        if (!event.getCurrentItem().hasItemMeta()) {
          return;
        }
        if (!event.getCurrentItem().hasItemMeta()) {
          return;
        }
        if (!event.getCurrentItem().hasItemMeta()) {
          return;
        }
        
        //LEATHER ARMOR
        if (event.getCurrentItem().getType() == Material.LEATHER_HELMET)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.LEATHER_HELMET)) {
        		WardrobeManager.setHelmet(p, Material.LEATHER_HELMET);
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.LEATHER_HELMET)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.LEATHER_HELMET);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Leather Helmet for 50 Tokens!");
        	}
        }
        if (event.getCurrentItem().getType() == Material.LEATHER_CHESTPLATE)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.LEATHER_CHESTPLATE)) {
        		WardrobeManager.setChestPlate(p, Material.LEATHER_CHESTPLATE);
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.LEATHER_CHESTPLATE)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.LEATHER_CHESTPLATE);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Leather Chestplate for 50 Tokens!");
        	}
        }
        if (event.getCurrentItem().getType() == Material.LEATHER_LEGGINGS)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.LEATHER_LEGGINGS)) {
        		WardrobeManager.setLeggings(p, Material.LEATHER_LEGGINGS);
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.LEATHER_LEGGINGS)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.LEATHER_LEGGINGS);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Leather Leggings for 50 Tokens!");
        	}
        }
        if (event.getCurrentItem().getType() == Material.LEATHER_BOOTS)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.LEATHER_BOOTS)) {
        		WardrobeManager.setBoots(p, Material.LEATHER_BOOTS);
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.LEATHER_BOOTS)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.LEATHER_BOOTS);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Leather Boots for 50 Tokens!");
        	}
        }
        
        //CHAINMAIL ARMOR
        if (event.getCurrentItem().getType() == Material.CHAINMAIL_HELMET)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.CHAINMAIL_HELMET)) {
        		WardrobeManager.setHelmet(p, Material.CHAINMAIL_HELMET);
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.CHAINMAIL_HELMET)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.CHAINMAIL_HELMET);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Chainmail Helmet for 50 Tokens!");
        	}
        }
        if (event.getCurrentItem().getType() == Material.CHAINMAIL_CHESTPLATE)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.CHAINMAIL_CHESTPLATE)) {
        		WardrobeManager.setChestPlate(p, Material.CHAINMAIL_CHESTPLATE);
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.CHAINMAIL_CHESTPLATE)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.CHAINMAIL_CHESTPLATE);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Chainmail Chestplate for 50 Tokens!");
        	}
        }
        if (event.getCurrentItem().getType() == Material.CHAINMAIL_LEGGINGS)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.CHAINMAIL_LEGGINGS)) {
        		WardrobeManager.setLeggings(p, Material.CHAINMAIL_LEGGINGS);
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.CHAINMAIL_LEGGINGS)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.CHAINMAIL_LEGGINGS);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Chainmail Leggings for 50 Tokens!");
        	}
        }
        if (event.getCurrentItem().getType() == Material.CHAINMAIL_BOOTS)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.CHAINMAIL_BOOTS)) {
        		WardrobeManager.setBoots(p, Material.CHAINMAIL_BOOTS);
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.CHAINMAIL_BOOTS)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.CHAINMAIL_BOOTS);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Chainmail Boots for 50 Tokens!");
        	}
        }
        
        //IRON ARMOR
        if (event.getCurrentItem().getType() == Material.IRON_HELMET)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.IRON_HELMET)) {
        		WardrobeManager.setHelmet(p, Material.IRON_HELMET);
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.IRON_HELMET)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.IRON_HELMET);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Iron Helmet for 50 Tokens!");
        	}
        }
        if (event.getCurrentItem().getType() == Material.IRON_CHESTPLATE)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.IRON_CHESTPLATE)) {
        		WardrobeManager.setChestPlate(p, Material.IRON_CHESTPLATE);
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.IRON_CHESTPLATE)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.IRON_CHESTPLATE);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Iron Chestplate for 50 Tokens!");
        	}
        }
        if (event.getCurrentItem().getType() == Material.IRON_LEGGINGS)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.IRON_LEGGINGS)) {
        		WardrobeManager.setLeggings(p, Material.IRON_LEGGINGS);
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.IRON_LEGGINGS)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.IRON_LEGGINGS);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Iron Leggings for 50 Tokens!");
        	}
        }
        if (event.getCurrentItem().getType() == Material.IRON_BOOTS)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.IRON_BOOTS)) {
        		WardrobeManager.setBoots(p, Material.IRON_BOOTS);
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.IRON_BOOTS)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.IRON_BOOTS);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Iron Boots for 50 Tokens!");
        	}
        }
        
        //GOLD ARMOR
        if (event.getCurrentItem().getType() == Material.GOLD_HELMET)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.GOLD_HELMET)) {
        		WardrobeManager.setHelmet(p, Material.GOLD_HELMET);
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.GOLD_HELMET)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.GOLD_HELMET);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Gold Helmet for 50 Tokens!");
        	}
        }
        if (event.getCurrentItem().getType() == Material.GOLD_CHESTPLATE)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.GOLD_CHESTPLATE)) {
        		WardrobeManager.setChestPlate(p, Material.GOLD_CHESTPLATE);
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.GOLD_CHESTPLATE)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.GOLD_CHESTPLATE);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Fold Chestplate for 50 Tokens!");
        	}
        }
        if (event.getCurrentItem().getType() == Material.GOLD_LEGGINGS)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.GOLD_LEGGINGS)) {
        		WardrobeManager.setLeggings(p, Material.GOLD_LEGGINGS);
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.GOLD_LEGGINGS)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.GOLD_LEGGINGS);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Gold Leggings for 50 Tokens!");
        	}
        }
        if (event.getCurrentItem().getType() == Material.GOLD_BOOTS)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.GOLD_BOOTS)) {
        		WardrobeManager.setBoots(p, Material.GOLD_BOOTS);
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.GOLD_BOOTS)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.GOLD_BOOTS);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Gold Boots for 50 Tokens!");
        	}
        }
        
        //Diamond Armor
        if (event.getCurrentItem().getType() == Material.DIAMOND_HELMET)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.DIAMOND_HELMET)) {
        		WardrobeManager.setHelmet(p, Material.DIAMOND_HELMET);
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.DIAMOND_HELMET)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.DIAMOND_HELMET);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Diamond Helmet for 50 Tokens!");
        	}
        }
        if (event.getCurrentItem().getType() == Material.DIAMOND_CHESTPLATE)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.DIAMOND_CHESTPLATE)) {
        		WardrobeManager.setChestPlate(p, Material.DIAMOND_CHESTPLATE);
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.DIAMOND_CHESTPLATE)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.DIAMOND_CHESTPLATE);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Diamond Chestplate for 50 Tokens!");
        	}
        }
        if (event.getCurrentItem().getType() == Material.DIAMOND_LEGGINGS)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.DIAMOND_LEGGINGS)) {
        		WardrobeManager.setLeggings(p, Material.DIAMOND_LEGGINGS);
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.DIAMOND_LEGGINGS)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.DIAMOND_LEGGINGS);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Diamond Leggings for 50 Tokens!");
        	}
        }
        if (event.getCurrentItem().getType() == Material.DIAMOND_BOOTS)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.DIAMOND_BOOTS)) {
        		WardrobeManager.setBoots(p, Material.DIAMOND_BOOTS);
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.DIAMOND_BOOTS)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.DIAMOND_BOOTS);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Leather Boots for 50 Tokens!");
        	}
        }
        
        //Remove Stuff
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains("§a§lRemove Helmet"))
        {
        	p.getInventory().setHelmet(null);
        	p.sendMessage(ChatColor.RED + "Helmet Removed");
        }
        
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains("§a§lRemove Chestplate"))
        {
        	p.getInventory().setChestplate(null);
        	p.sendMessage(ChatColor.RED + "Chestplate Removed");
        }
        
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains("§a§lRemove Leggings"))
        {
        	p.getInventory().setLeggings(null);
        	p.sendMessage(ChatColor.RED + "Leggings Removed");
        }
        
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains("§a§lRemove Boots"))
        {
        	p.getInventory().setBoots(null);
        	p.sendMessage(ChatColor.RED + "Boots Removed");
        }
        
        if (event.getCurrentItem().getType() == Material.REDSTONE_BLOCK)
        {
        	p.getInventory().setHelmet(null);
        	p.getInventory().setChestplate(null);
        	p.getInventory().setLeggings(null);
        	p.getInventory().setBoots(null);
        	p.closeInventory();
        	p.playSound(p.getPlayer().getLocation(), Sound.ANVIL_LAND, 5, 5);
        	p.sendMessage(ChatColor.RED + "Armor Contents Remove");
        }
        
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Go Back"))
        {
          p.closeInventory();
          p.openInventory(CosmeticsMenu.cosmenu(p));
          return;
        }
      }
    }
    catch (Exception localException) {}
  }
}