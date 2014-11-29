package fr.coco_gigpn.prodigygadget.menu.menulist;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.coco_gigpn.prodigygadget.effect.EffectManager;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilItem;

public class Extra {

	final public static HashMap<Player, Inventory> gui = new HashMap<Player, Inventory>();

	public static void openCloudGui(Player p) {

		if (!gui.containsKey(p)) {

			String statuAccessCloud = "",
					statuAccessCloudlight = "",
					statuAccessCloudSnow = "",
					statuAccessTornado = "",
					statuEffect = "",
					statuAccessShield = "",
					statuAccessFlameLilly = "",
					statuAccessVortex = "",
					statuAccessFountain = "",
					statuAccessHourGlass = "",
					statuAccessHelix = "";


			if (EffectManager.hasEffect(p)) {
				statuEffect = Messages.isOnName.replace("&", "§");
			} else {
				statuEffect = Messages.isOffName.replace("&", "§");
			}




			if (p.hasPermission("propack.extra.cloud")  || p.hasPermission("propack.extra.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessCloud = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");
			} else {
				statuAccessCloud = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}

			if (p.hasPermission("propack.extra.cloudsnow") || p.hasPermission("propack.extra.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessCloudSnow = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");
			} else {
				statuAccessCloudSnow = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}

			if (p.hasPermission("propack.extra.cloudlight") || p.hasPermission("propack.extra.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessCloudlight = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");
			} else {
				statuAccessCloudlight = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}

			if (p.hasPermission("propack.extra.helix") || p.hasPermission("propack.extra.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessHelix = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");
			} else {
				statuAccessHelix = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}

			if (p.hasPermission("propack.extra.tornado") || p.hasPermission("propack.extra.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessTornado = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");
			} else {
				statuAccessTornado = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}
			if (p.hasPermission("propack.extra.shield") || p.hasPermission("propack.extra.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessShield = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");
			} else {
				statuAccessShield = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}
			if (p.hasPermission("propack.extra.hourglass") || p.hasPermission("propack.extra.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessHourGlass = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");
			} else {
				statuAccessHourGlass = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}
			if (p.hasPermission("propack.extra.fountain") || p.hasPermission("propack.extra.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessFountain = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");
			} else {
				statuAccessFountain = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}
			if (p.hasPermission("propack.extra.vortex") || p.hasPermission("propack.extra.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessVortex= Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");
			} else {
				statuAccessVortex = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}
			if (p.hasPermission("propack.extra.flamelilly") || p.hasPermission("propack.extra.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessFlameLilly = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");
			} else {
				statuAccessFlameLilly = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}

			Inventory inv = Bukkit.createInventory(null, (54), Messages.extraMenuPrefix.replace("&", "§"));
			gui.put(p, inv);

			inv.setItem(20,UtilItem.create(Material.QUARTZ, 1, Messages.cloudName.replace("&", "§"), statuAccessCloud));
			inv.setItem(22,UtilItem.create(Material.INK_SACK, 1,(byte) 1, Messages.lightningName.replace("&", "§"), statuAccessCloudlight));
			inv.setItem(21,UtilItem.create(Material.INK_SACK, 1,(byte) 7, Messages.snowCloudName.replace("&", "§"), statuAccessCloudSnow));
			inv.setItem(23,UtilItem.create(Material.REDSTONE, 1, Messages.helixName.replace("&", "§"), statuAccessHelix));
			inv.setItem(24,UtilItem.create(Material.STRING, 1, Messages.tornadoName.replace("&", "§"), statuAccessTornado));
			
			inv.setItem(29,UtilItem.create(Material.RAW_FISH, 1,(byte) 3, Messages.shieldName.replace("&", "§"), statuAccessShield));
			inv.setItem(30,UtilItem.create(Material.EYE_OF_ENDER, 1, Messages.vortexName.replace("&", "§"), statuAccessVortex));
			inv.setItem(31,UtilItem.create(Material.INK_SACK, 1,(byte) 6, Messages.fountainName.replace("&", "§"), statuAccessFountain));
			inv.setItem(32,UtilItem.create(Material.BOOK_AND_QUILL, 1, Messages.hourglassName.replace("&", "§"), statuAccessHourGlass));
			inv.setItem(33,UtilItem.create(Material.WATER_LILY, 1, Messages.flamelillyName.replace("&", "§"), statuAccessFlameLilly));
			
			
			inv.setItem(53,UtilItem.create(Material.ENDER_PEARL, 1 , Messages.stopEffectName.replace("&", "§"), statuEffect));
			inv.setItem(45,UtilItem.create(Material.PISTON_BASE, 1 , Messages.returnMainName.replace("&", "§")));



			for (Player pInv : gui.keySet()) {
				pInv.openInventory(inv);
				gui.remove(p);
			}

		} 
	}


}
