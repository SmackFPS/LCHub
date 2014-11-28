package me.mike1665.funstuff;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PvPSword implements Listener{
	
	@EventHandler
	public void onPlayerRespawn(PlayerJoinEvent event) {
	Player p = event.getPlayer();
		ItemStack sword = new ItemStack(Material.CHEST, 1);
		ItemMeta pvp = sword.getItemMeta();
		pvp.setDisplayName(ChatColor.LIGHT_PURPLE + "PvP Sword " + ChatColor.GRAY + "(Right-Click to activate)");
		ArrayList<String> Lore = new ArrayList<String>();
		pvp.setLore(Lore);
		sword.setItemMeta(pvp);
	    p.getInventory().setItem(3, sword);
	}

}
