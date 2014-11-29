package fr.coco_gigpn.prodigygadget.menu.menulist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.coco_gigpn.prodigygadget.mount.MountManager;
import fr.coco_gigpn.prodigygadget.util.Messages;

public class Mounts {
	final public static HashMap<Player, Inventory> gui = new HashMap<Player, Inventory>();
	static List<String> loreStatpet = new ArrayList<String>();
	
	static List<String> _angelrider = new ArrayList<String>();
	static List<String> _ghostrider = new ArrayList<String>();
	static List<String> _darkrider = new ArrayList<String>();
	static List<String> _nyanrider = new ArrayList<String>();
	static List<String> _poseidonrider = new ArrayList<String>();

	public static void openPetGui(Player p) {

		if (!gui.containsKey(p)) {
			
			_angelrider.clear();
			_ghostrider.clear();
			_darkrider.clear();
			_nyanrider.clear();
			_poseidonrider.clear();

			Inventory inv = Bukkit.createInventory(null, (54), Messages.mountMenuPrefix.replace("&", "§"));
			gui.put(p, inv);
		
			ItemStack angelrider = new ItemStack(Material.MONSTER_EGG , 1 ,(byte) 56);
			ItemMeta angelriderMeta = angelrider.getItemMeta();
			angelriderMeta.setDisplayName(Messages.angelriderName.replace("&", "§"));
			 if (p.hasPermission("propack.mount.angelrider")|| p.hasPermission("prodigypack.use.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
			 _angelrider.add(Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§"));
			 } else {
			 _angelrider.add(Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§"));
			 }
			 _angelrider.add(Messages.angelriderDescription.replace("&", "§"));
			 angelriderMeta.setLore(_angelrider);
			angelrider.setItemMeta(angelriderMeta);
			inv.setItem(20, angelrider);
			
			ItemStack ghostrider = new ItemStack(Material.MONSTER_EGG , 1 ,(byte) 61);
			ItemMeta ghostriderMeta = ghostrider.getItemMeta();
			ghostriderMeta.setDisplayName(Messages.ghostriderName.replace("&", "§"));
			if (p.hasPermission("propack.mount.ghostrider")|| p.hasPermission("propack.mount.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				 _ghostrider.add(Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§"));
				 } else {
				 _ghostrider.add(Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§"));
				 }
			_ghostrider.add(Messages.ghostriderDescription.replace("&", "§"));
			ghostriderMeta.setLore(_ghostrider);
			ghostrider.setItemMeta(ghostriderMeta);
			inv.setItem(21, ghostrider);
			
			
			ItemStack darkrider = new ItemStack(Material.MONSTER_EGG , 1 ,(byte) 58);
			ItemMeta darkriderMeta = darkrider.getItemMeta();
			darkriderMeta.setDisplayName(Messages.darkriderName.replace("&", "§"));
			if (p.hasPermission("propack.mount.darkrider")|| p.hasPermission("propack.mount.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				 _darkrider.add(Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§"));
				 } else {
				 _darkrider.add(Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§"));
				 }
			_darkrider.add(Messages.darkriderDescription.replace("&", "§"));
			darkriderMeta.setLore(_darkrider);
			darkrider.setItemMeta(darkriderMeta);
			inv.setItem(22, darkrider);
			
			ItemStack poseidonrider = new ItemStack(Material.MONSTER_EGG , 1 ,(byte) 94);
			ItemMeta poseidonriderMeta = poseidonrider.getItemMeta();
			poseidonriderMeta.setDisplayName(Messages.poseidonriderName.replace("&", "§"));
			if (p.hasPermission("propack.mount.poseidonrider")|| p.hasPermission("propack.mount.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				 _poseidonrider.add(Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§"));
				 } else {
				 _poseidonrider.add(Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§"));
				 }
			_poseidonrider.add(Messages.poseidonriderDescription.replace("&", "§"));
			poseidonriderMeta.setLore(_poseidonrider);
			poseidonrider.setItemMeta(poseidonriderMeta);
			inv.setItem(23, poseidonrider);
			
			ItemStack nyanrider = new ItemStack(Material.MONSTER_EGG , 1 ,(byte) 90);
			ItemMeta nyanriderMeta = nyanrider.getItemMeta();
			nyanriderMeta.setDisplayName(Messages.nyanriderName.replace("&", "§"));
			if (p.hasPermission("propack.mount.nyanrider")|| p.hasPermission("propack.mount.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				 _nyanrider.add(Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§"));
				 } else {
				 _nyanrider.add(Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§"));
				 }
			_nyanrider.add(Messages.nyanriderDescription.replace("&", "§"));
			nyanriderMeta.setLore(_nyanrider);
			nyanrider.setItemMeta(nyanriderMeta);
			inv.setItem(24, nyanrider);
			
			
			ItemStack returnmain = new ItemStack(Material.PISTON_BASE);
			ItemMeta returnmainMeta = returnmain.getItemMeta();
			returnmainMeta.setDisplayName(Messages.returnMainName.replace("&", "§"));
			returnmain.setItemMeta(returnmainMeta);
			inv.setItem(45, returnmain);
			
			ItemStack removepet = new ItemStack(Material.MAGMA_CREAM);
			ItemMeta removepetMeta = removepet.getItemMeta();
			removepetMeta.setDisplayName(Messages.removeMountName.replace("&", "§"));
			loreStatpet.clear();
			if(MountManager.HasPet(p)) {
			loreStatpet.add(Messages.isOnName.replace("&", "§"));
			} else {
			loreStatpet.add(Messages.isOffName.replace("&", "§"));
			}
			removepetMeta.setLore(loreStatpet);
			removepet.setItemMeta(removepetMeta);
			inv.setItem(53, removepet);
	
			for (Player pInv : gui.keySet()) {
				pInv.openInventory(inv);
				gui.remove(p);
			}

		}
	}


}
