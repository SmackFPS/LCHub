package fr.coco_gigpn.prodigygadget.menu.menulist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.coco_gigpn.prodigygadget.morph.MorphManager;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilItem;

public class Morphs {
	final public static HashMap<Player, Inventory> gui = new HashMap<Player, Inventory>();
	static List<String> loreStat = new ArrayList<String>();


	public static void openMorphGui(Player p) {

		if (!gui.containsKey(p)) {
			
			Inventory inv = Bukkit.createInventory(null, (54), Messages.morphMenuPrefix.replace("&", "§"));
			gui.put(p, inv);

			String statuMorph = "";
			String statuAccessVampire = "",
					statuAccessAstronot = "",
					statuAccessWitch = "",
					statuAccessBlaze = "",
					statuAccessGhast = "",
					statuAccessEnderman = "",
					statuAccessCactus = "";
			
			if (p.hasPermission("propack.morph.vampire")|| p.hasPermission("propack.morph.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessVampire = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");

			} else {
				statuAccessVampire = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}
			
			if (p.hasPermission("propack.morph.astronot")|| p.hasPermission("propack.morph.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessAstronot = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");

			} else {
				statuAccessAstronot = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}
	
			if (p.hasPermission("propack.morph.witch")|| p.hasPermission("propack.morph.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessWitch = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");

			} else {
				statuAccessWitch = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}
			if (p.hasPermission("propack.morph.blaze")|| p.hasPermission("propack.morph.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessBlaze= Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");

			} else {
				statuAccessBlaze = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}
			if (p.hasPermission("propack.morph.cactus")|| p.hasPermission("propack.morph.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessCactus = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");

			} else {
				statuAccessCactus = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}
			if (p.hasPermission("propack.morph.enderman")|| p.hasPermission("propack.morph.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessEnderman = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");

			} else {
				statuAccessEnderman = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}
			
			if (p.hasPermission("propack.morph.ghast")|| p.hasPermission("propack.morph.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessGhast = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");

			} else {
				statuAccessGhast = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}
			
			if(MorphManager.hasMorph(p)) {
			statuMorph = Messages.isOnName.replace("&", "§");
			} else {
			statuMorph = Messages.isOffName.replace("&", "§");
			}
			
			inv.setItem(19, UtilItem.create(Material.GHAST_TEAR, 1 ,(byte) 0 , Messages.ghastmorphName.replace("&", "§") , statuAccessGhast , Messages.ghastDescription.replace("&", "§")));
			inv.setItem(20, UtilItem.create(Material.STAINED_GLASS , 1 ,(byte) 0 , Messages.astronotName.replace("&", "§") , statuAccessAstronot , Messages.astronotDescription.replace("&", "§")));
			inv.setItem(21, UtilItem.create(Material.CACTUS , 1 , (byte) 0 , Messages.cactusName.replace("&", "§") , statuAccessCactus , Messages.cactusDescription.replace("&", "§")));
			inv.setItem(22, UtilItem.create(Material.SKULL_ITEM , 1 , (byte) 1  ,Messages.vampireName.replace("&", "§"), statuAccessVampire ,Messages.vampireDescription.replace("&", "§")));
			inv.setItem(23, UtilItem.create(Material.BLAZE_POWDER , 1 , Messages.blazeName.replace("&", "§"), statuAccessBlaze , Messages.blazeDescription.replace("&", "§")));
			inv.setItem(24, UtilItem.create(Material.SPIDER_EYE, 1 ,(byte) 0, Messages.witchmorphName.replace("&", "§"), statuAccessWitch , Messages.witchDescription.replace("&", "§")));
			inv.setItem(25, UtilItem.create(Material.EYE_OF_ENDER, 1 ,(byte) 0, Messages.endermanmorphName.replace("&", "§"), statuAccessEnderman , Messages.endermanDescription.replace("&", "§")));
			
			inv.setItem(45, UtilItem.create(Material.PISTON_BASE, 1 , Messages.returnMainName.replace("&", "§")));
			inv.setItem(53, UtilItem.create(Material.ENDER_PEARL , 1 , Messages.stopMorphName.replace("&", "§"), statuMorph));
	
			for (Player pInv : gui.keySet()) {
				pInv.openInventory(inv);
				gui.remove(p);
			}

		}
	}


}
