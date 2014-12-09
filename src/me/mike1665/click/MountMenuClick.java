package me.mike1665.click;

import me.mike1665.menu.CosmeticsMenu;
import me.mike1665.menu.MountMenu;
import me.mike1665.mount.MountManager;
import me.mike1665.mount.mounts.AngelRider;
import me.mike1665.mount.mounts.DarkRider;
import me.mike1665.mount.mounts.GhostRider;
import me.mike1665.mount.mounts.NyanRider;
import me.mike1665.mount.mounts.PoseidonRider;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MountMenuClick implements Listener{
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		Player p = (Player) event.getWhoClicked();
		try{
		if (event.getInventory().getName().equalsIgnoreCase(MountMenu.INV_NAME)) {
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
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§b§lAngel Mount")) {
				AngelRider.playAngelRider(p);
				p.closeInventory();
				return;
			}
                
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§5§lNyan Mount")) {
				NyanRider.playNyanRider(p);
				p.closeInventory();
				return;

			}
			
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§4§lGhost Mount")) {
				GhostRider.playGhostRider(p);
				p.closeInventory();
				return;

			} 
			
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§f§lDark Mount")) {
				DarkRider.playDarkRider(p);
				p.closeInventory();
				return;

			} 
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§9§lPoseidon Mount")) {
				PoseidonRider.playPoseidonRider(p);
				p.closeInventory();
				return;

			} if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§4Remove Mount")) {
				MountManager.removeCurrentPet(p, false);
				p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Mounts" + ChatColor.RESET + "" + ChatColor.DARK_GRAY + "> " + ChatColor.RED + "Mount removed.");
				return;

			} 
		}
		}catch(Exception ex){} //causing random nullpointers
	}
}
