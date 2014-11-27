package me.mike1665.click;

import me.mike1665.menu.CosmeticsMenu;
import me.mike1665.menu.MountMenu;
import me.mike1665.mount.types.Frust;
import me.mike1665.mount.types.Mule;
import me.mike1665.mount.types.Undead;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MountMenuClick implements Listener{
	
	public Mule m = new Mule();
	public Undead und = new Undead();
	public Frust fro = new Frust();
	
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
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aFrosty")) {
				this.fro.spawnFrost(p);
				p.closeInventory();
				return;
			}
                
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§4Mule")) {
				this.m.spawnMule(p);
				p.closeInventory();
				return;

			}
			
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§4Undead")) {
				this.und.spawnUndead(p);
				p.closeInventory();
				return;

			} 
			
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§c<- Go Back")) {
				p.openInventory(CosmeticsMenu.cosmenu(p));
      		  	p.playSound(p.getLocation(), Sound.DOOR_CLOSE, 10, 10);
				return;

			} 
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§2Dragon")) {
				event.setCancelled(true);
				p.sendMessage(ChatColor.RED + "This feature is in development!");
				return;

			} if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§4Remove")) {
				this.m.removeMule(p);
				p.sendMessage(ChatColor.RED + "Should remove YOUR HORSE");
				return;

			} 
		}
		}catch(Exception ex){} //causing random nullpointers
	}
}
