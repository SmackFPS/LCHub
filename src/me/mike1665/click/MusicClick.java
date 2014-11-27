package me.mike1665.click;

import me.mike1665.menu.CosmeticsMenu;
import me.mike1665.menu.MusicMenu;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MusicClick implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		Player p = (Player) event.getWhoClicked();
		if (event.getInventory().getName()
				.equalsIgnoreCase(MusicMenu.name)) {
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
			if (event.getCurrentItem().getItemMeta().getDisplayName()
					.equals("§b§lBlocks Disk")) {
			      p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.RECORD_3, 0);
			      p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
			      p.closeInventory();
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName()
					.equals("§b§lChirp Disk")) {
			      p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.RECORD_4, 0);
			      p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
			      p.closeInventory();
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName()
					.equals("§b§lFar Disk")) {
			      p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.RECORD_5, 0);
			      p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
			      p.closeInventory();
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName()
					.equals("§b§lMall Disk")) {
			      p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.RECORD_6, 0);
			      p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
			      p.closeInventory();
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName()
					.equals("§b§lMellohi Disk")) {
			      p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.RECORD_7, 0);
			      p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
			      p.closeInventory();
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName()
					.equals("§b§lStal Disk")) {
			      p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.RECORD_8, 0);
			      p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
			      p.closeInventory();
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName()
					.equals("§b§lStrad Disk")) {
			      p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.RECORD_9, 0);
			      p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
			      p.closeInventory();
			}

			if (event.getCurrentItem().getItemMeta().getDisplayName()
					.equals("§b§lWard Disk")) {
			      p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.RECORD_10, 0);
			      p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
			      p.closeInventory();
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName()
					.equals("§b§l11 Disk")) {
			      p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.RECORD_11, 0);
			      p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
			      p.closeInventory();
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName()
					.equals("§b§lWait Disk")) {
			      p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.RECORD_12, 0);
			      p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
			      p.closeInventory();
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName()
					.equals("§b§l13 Disk")) {
				 p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.GOLD_RECORD, 0);
			      p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
			      p.closeInventory();
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName()
					.equals("§b§lCat Disk")) {
				p.getWorld().playEffect(p.getLocation(), Effect.RECORD_PLAY, Material.GREEN_RECORD, 0);
			      p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
			      p.closeInventory();
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName()
					.equals("§cGo Back")) {
				p.openInventory(CosmeticsMenu.cosmenu(p));
			}
			if (event.getCurrentItem().getItemMeta().getDisplayName()
					.equals("§4Turn off music")) {
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
			}
		}
	}
}