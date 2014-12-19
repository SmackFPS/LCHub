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
        		p.closeInventory();
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.LEATHER_HELMET)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.LEATHER_HELMET);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Leather Helmet for 50 Tokens!");
        		p.closeInventory();
        	}
        }
        if (event.getCurrentItem().getType() == Material.LEATHER_CHESTPLATE)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.LEATHER_CHESTPLATE)) {
        		WardrobeManager.setChestPlate(p, Material.LEATHER_CHESTPLATE);
        		p.closeInventory();
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.LEATHER_CHESTPLATE)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.LEATHER_CHESTPLATE);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Leather Chestplate for 50 Tokens!");
        		p.closeInventory();
        	}
        }
        if (event.getCurrentItem().getType() == Material.LEATHER_LEGGINGS)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.LEATHER_LEGGINGS)) {
        		WardrobeManager.setLeggings(p, Material.LEATHER_LEGGINGS);
        		p.closeInventory();
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.LEATHER_LEGGINGS)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.LEATHER_LEGGINGS);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Leather Leggings for 50 Tokens!");
        		p.closeInventory();
        	}
        }
        if (event.getCurrentItem().getType() == Material.LEATHER_BOOTS)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.LEATHER_BOOTS)) {
        		WardrobeManager.setBoots(p, Material.LEATHER_BOOTS);
        		p.closeInventory();
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.LEATHER_BOOTS)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.LEATHER_BOOTS);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Leather Boots for 50 Tokens!");
        		p.closeInventory();
        	}
        }
        
        //CHAINMAIL ARMOR
        if (event.getCurrentItem().getType() == Material.CHAINMAIL_HELMET)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.CHAINMAIL_HELMET)) {
        		WardrobeManager.setHelmet(p, Material.CHAINMAIL_HELMET);
        		p.closeInventory();
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.CHAINMAIL_HELMET)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.CHAINMAIL_HELMET);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Chainmail Helmet for 50 Tokens!");
        		p.closeInventory();
        	}
        }
        if (event.getCurrentItem().getType() == Material.CHAINMAIL_CHESTPLATE)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.CHAINMAIL_CHESTPLATE)) {
        		WardrobeManager.setChestPlate(p, Material.CHAINMAIL_CHESTPLATE);
        		p.closeInventory();
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.CHAINMAIL_CHESTPLATE)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.CHAINMAIL_CHESTPLATE);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Chainmail Chestplate for 50 Tokens!");
        		p.closeInventory();
        	}
        }
        if (event.getCurrentItem().getType() == Material.CHAINMAIL_LEGGINGS)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.CHAINMAIL_LEGGINGS)) {
        		WardrobeManager.setLeggings(p, Material.CHAINMAIL_LEGGINGS);
        		p.closeInventory();
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.CHAINMAIL_LEGGINGS)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.CHAINMAIL_LEGGINGS);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Chainmail Leggings for 50 Tokens!");
        		p.closeInventory();
        	}
        }
        if (event.getCurrentItem().getType() == Material.CHAINMAIL_BOOTS)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.CHAINMAIL_BOOTS)) {
        		WardrobeManager.setBoots(p, Material.CHAINMAIL_BOOTS);
        		p.closeInventory();
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.CHAINMAIL_BOOTS)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.CHAINMAIL_BOOTS);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Chainmail Boots for 50 Tokens!");
        		p.closeInventory();
        	}
        }
        
        //IRON ARMOR
        if (event.getCurrentItem().getType() == Material.IRON_HELMET)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.IRON_HELMET)) {
        		WardrobeManager.setHelmet(p, Material.IRON_HELMET);
        		p.closeInventory();
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.IRON_HELMET)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.IRON_HELMET);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Iron Helmet for 50 Tokens!");
        		p.closeInventory();
        	}
        }
        if (event.getCurrentItem().getType() == Material.IRON_CHESTPLATE)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.IRON_CHESTPLATE)) {
        		WardrobeManager.setChestPlate(p, Material.IRON_CHESTPLATE);
        		p.closeInventory();
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.IRON_CHESTPLATE)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.IRON_CHESTPLATE);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Iron Chestplate for 50 Tokens!");
        		p.closeInventory();
        	}
        }
        if (event.getCurrentItem().getType() == Material.IRON_LEGGINGS)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.IRON_LEGGINGS)) {
        		WardrobeManager.setLeggings(p, Material.IRON_LEGGINGS);
        		p.closeInventory();
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.IRON_LEGGINGS)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.IRON_LEGGINGS);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Iron Leggings for 50 Tokens!");
        		p.closeInventory();
        	}
        }
        if (event.getCurrentItem().getType() == Material.IRON_BOOTS)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.IRON_BOOTS)) {
        		WardrobeManager.setBoots(p, Material.IRON_BOOTS);
        		p.closeInventory();
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.IRON_BOOTS)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.IRON_BOOTS);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Iron Boots for 50 Tokens!");
        		p.closeInventory();
        	}
        }
        
        //GOLD ARMOR
        if (event.getCurrentItem().getType() == Material.GOLD_HELMET)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.GOLD_HELMET)) {
        		WardrobeManager.setHelmet(p, Material.GOLD_HELMET);
        		p.closeInventory();
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.GOLD_HELMET)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.GOLD_HELMET);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Gold Helmet for 50 Tokens!");
        		p.closeInventory();
        	}
        }
        if (event.getCurrentItem().getType() == Material.GOLD_CHESTPLATE)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.GOLD_CHESTPLATE)) {
        		WardrobeManager.setChestPlate(p, Material.GOLD_CHESTPLATE);
        		p.closeInventory();
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.GOLD_CHESTPLATE)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.GOLD_CHESTPLATE);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Fold Chestplate for 50 Tokens!");
        		p.closeInventory();
        	}
        }
        if (event.getCurrentItem().getType() == Material.GOLD_LEGGINGS)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.GOLD_LEGGINGS)) {
        		WardrobeManager.setLeggings(p, Material.GOLD_LEGGINGS);
        		p.closeInventory();
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.GOLD_LEGGINGS)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.GOLD_LEGGINGS);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Gold Leggings for 50 Tokens!");
        		p.closeInventory();
        	}
        }
        if (event.getCurrentItem().getType() == Material.GOLD_BOOTS)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.GOLD_BOOTS)) {
        		WardrobeManager.setBoots(p, Material.GOLD_BOOTS);
        		p.closeInventory();
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.GOLD_BOOTS)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.GOLD_BOOTS);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Gold Boots for 50 Tokens!");
        		p.closeInventory();
        	}
        }
        
        //Diamond Armor
        if (event.getCurrentItem().getType() == Material.LEATHER_HELMET)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.LEATHER_HELMET)) {
        		WardrobeManager.setHelmet(p, Material.LEATHER_HELMET);
        		p.closeInventory();
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.LEATHER_HELMET)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.LEATHER_HELMET);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Leather Helmet for 50 Tokens!");
        		p.closeInventory();
        	}
        }
        if (event.getCurrentItem().getType() == Material.LEATHER_CHESTPLATE)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.LEATHER_CHESTPLATE)) {
        		WardrobeManager.setChestPlate(p, Material.LEATHER_CHESTPLATE);
        		p.closeInventory();
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.LEATHER_CHESTPLATE)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.LEATHER_CHESTPLATE);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Leather Chestplate for 50 Tokens!");
        		p.closeInventory();
        	}
        }
        if (event.getCurrentItem().getType() == Material.LEATHER_LEGGINGS)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.LEATHER_LEGGINGS)) {
        		WardrobeManager.setLeggings(p, Material.LEATHER_LEGGINGS);
        		p.closeInventory();
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.LEATHER_LEGGINGS)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.LEATHER_LEGGINGS);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Leather Leggings for 50 Tokens!");
        		p.closeInventory();
        	}
        }
        if (event.getCurrentItem().getType() == Material.LEATHER_BOOTS)
        {
        	if (WardrobeManager.hasUnlockedArmor(p, Material.LEATHER_BOOTS)) {
        		WardrobeManager.setBoots(p, Material.LEATHER_BOOTS);
        		p.closeInventory();
        		return;
        	} else  if (!WardrobeManager.hasUnlockedArmor(p, Material.LEATHER_BOOTS)){
        		LcTokensAPI.takePoints(p, 50);
        		WardrobeManager.unlockArmor(p, Material.LEATHER_BOOTS);
        		p.sendMessage(ChatColor.GREEN + "You unlocked the Leather Boots for 50 Tokens!");
        		p.closeInventory();
        	}
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