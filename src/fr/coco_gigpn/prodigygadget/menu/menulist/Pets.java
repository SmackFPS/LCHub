package fr.coco_gigpn.prodigygadget.menu.menulist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.coco_gigpn.prodigygadget.pet.PetManager;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilItem;

public class Pets {
	final public static HashMap<Player, Inventory> gui = new HashMap<Player, Inventory>();
	static List<String> loreStat = new ArrayList<String>();


	public static void openMainGui(Player p) {

		if (!gui.containsKey(p)) {
			
			Inventory inv = Bukkit.createInventory(null, (54), Messages.petMenuPrefix.replace("&", "§"));
			gui.put(p, inv);

			String statuPet = "";
			String statuAccessWolf = "",
					statuAccessOcelot = "",
					statuAccessSheep = "",
					statuAccessPig = "",
					statuAccessCow = "";
			
			if (p.hasPermission("propack.pet.wolf")|| p.hasPermission("propack.pet.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessWolf = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");

			} else {
				statuAccessWolf = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}
			
			if (p.hasPermission("propack.pet.ocelot")|| p.hasPermission("propack.pet.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessOcelot = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");

			} else {
				statuAccessOcelot = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}
	
			if (p.hasPermission("propack.pet.sheep")|| p.hasPermission("propack.pet.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessSheep = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");

			} else {
				statuAccessSheep = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}
			if (p.hasPermission("propack.pet.pig")|| p.hasPermission("propack.pet.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessPig = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");

			} else {
				statuAccessPig = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}
			if (p.hasPermission("propack.pet.cow")|| p.hasPermission("propack.pet.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessCow = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");

			} else {
				statuAccessCow = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}
			
			if(PetManager.HasPet(p)) {
			statuPet = Messages.isOnName.replace("&", "§");
			} else {
			statuPet = Messages.isOffName.replace("&", "§");
			}
			
			inv.setItem(20, UtilItem.create(Material.BONE , 1 , Messages.wolfName.replace("&", "§") , statuAccessWolf));
			inv.setItem(21, UtilItem.create(Material.INK_SACK , 1 , (byte) 3 , Messages.ocelotName.replace("&", "§") , statuAccessOcelot));
			inv.setItem(22, UtilItem.create(Material.CLAY_BALL , 1 , Messages.sheepName.replace("&", "§"), statuAccessSheep));
			inv.setItem(23, UtilItem.create(Material.RAW_BEEF , 1 , Messages.pigName.replace("&", "§"), statuAccessPig));
			inv.setItem(24, UtilItem.create(Material.WHEAT, 1 , Messages.cowName.replace("&", "§"), statuAccessCow));
			
			inv.setItem(45, UtilItem.create(Material.PISTON_BASE, 1 , Messages.returnMainName.replace("&", "§")));
			inv.setItem(53, UtilItem.create(Material.MAGMA_CREAM , 1 , Messages.removePetName.replace("&", "§"), statuPet));
	
			for (Player pInv : gui.keySet()) {
				pInv.openInventory(inv);
				gui.remove(p);
			}

		}
	} 


}
