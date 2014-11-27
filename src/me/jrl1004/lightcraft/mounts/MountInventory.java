package me.jrl1004.lightcraft.mounts;

import java.util.Locale;

import me.jrl1004.lightcraft.utils.LcConstants;
import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.SpawnEgg;

public class MountInventory implements Listener {
	private static MountInventory instance;

	private static Inventory inventory;

	private MountInventory() {
		Bukkit.getPluginManager().registerEvents(this, Main.instance);
		if (inventory == null)
			inventory = Bukkit.createInventory(null, MountType.values().length, LcConstants.MOUNT_INVENTORY_TITLE);
	}

	public static MountInventory getInstance() {
		if (instance == null)
			instance = new MountInventory();
		return instance;
	}

	public Inventory getInventory() {
		if (inventory == null)
			inventory = Bukkit.createInventory(null, MountType.values().length, LcConstants.MOUNT_INVENTORY_TITLE);
		stockInventory();
		return inventory;
	}

	private void stockInventory() {
		inventory.clear();
		for (MountType t : MountType.values()) {
			ItemStack stack = new SpawnEgg(EntityType.HORSE).toItemStack(1);
			ItemMeta meta = stack.getItemMeta();
			meta.setDisplayName(t.toString().toLowerCase(Locale.US));
			stack.setItemMeta(meta);
			inventory.addItem(stack);
		}
		updateViewers();
	}

	private void updateViewers() {
		for (HumanEntity ent : inventory.getViewers()) {
			if (ent instanceof Player == false)
				continue;
			((Player) ent).updateInventory();
		}
	}

	// Begin working with events related to this inventory

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (event.getWhoClicked() instanceof Player == false)
			return;
		if (getClicked(event) == null)
			return;

	}

	private ItemStack getClicked(InventoryClickEvent event) {
		ItemStack i = event.getCurrentItem();
		if (i == null)
			i = event.getCursor();
		return i;
	}
}
