package me.mike1665.funstuff;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MagicClock implements Listener{
	
    ArrayList<String> clock = new ArrayList<String>();

	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerRespawn(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		ItemStack sword = new ItemStack(Material.WATCH, 1);
		ItemMeta pvp = sword.getItemMeta();
		pvp.setDisplayName(ChatColor.GREEN + "Players " + ChatColor.YELLOW + "" + ChatColor.BOLD + ">> " + ChatColor.RESET + "" + ChatColor.GREEN + "Activated");
		ArrayList<String> Lore = new ArrayList<String>();
		Lore.add(ChatColor.RESET + "" + ChatColor.BLUE + "The Magic Clock!");
		pvp.setLore(Lore);
		sword.setItemMeta(pvp);
		p.getInventory().setItem(1, sword);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerPlaceBlock(PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if ((disName(player.getItemInHand()) != null)&& (disName(player.getItemInHand()).equalsIgnoreCase(ChatColor.GREEN + "Players " + ChatColor.YELLOW + "" + ChatColor.BOLD + ">> " + ChatColor.RESET + "" + ChatColor.GREEN + "Activated"))) {
				player.getItemInHand().getItemMeta().setDisplayName(ChatColor.GREEN + "Players " + ChatColor.YELLOW + "" + ChatColor.BOLD + ">> " + ChatColor.RESET + "" + ChatColor.RED + "Deactivated");
				player.getInventory().getItem(1).getItemMeta().setDisplayName(ChatColor.GREEN + "Players " + ChatColor.YELLOW + "" + ChatColor.BOLD + ">> " + ChatColor.RESET + "" + ChatColor.RED + "Deactivated");
				for (Player pl : Bukkit.getOnlinePlayers()) {
					player.hidePlayer(pl);
				}
			} else if ((disName(player.getItemInHand()) != null)&& (disName(player.getItemInHand()).equalsIgnoreCase(ChatColor.GREEN + "Players " + ChatColor.YELLOW + "" + ChatColor.BOLD + ">> " + ChatColor.RESET + "" + ChatColor.RED + "Deactivated"))) {
				player.getItemInHand().getItemMeta().setDisplayName(ChatColor.GREEN + "Players " + ChatColor.YELLOW + "" + ChatColor.BOLD + ">> " + ChatColor.RESET + "" + ChatColor.GREEN + "Activated");
				player.getInventory().getItem(1).getItemMeta().setDisplayName(ChatColor.GREEN + "Players " + ChatColor.YELLOW + "" + ChatColor.BOLD + ">> " + ChatColor.RESET + "" + ChatColor.GREEN + "Activated");
				for (Player pl : Bukkit.getOnlinePlayers()) {
					player.showPlayer(pl);
				}
			}
		}
	}
	
	public String disName(ItemStack i) {
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
