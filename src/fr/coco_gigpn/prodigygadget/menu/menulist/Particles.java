package fr.coco_gigpn.prodigygadget.menu.menulist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.coco_gigpn.prodigygadget.particle.ParticleManager;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilItem;

public class Particles {
	final public static HashMap<Player, Inventory> gui = new HashMap<Player, Inventory>();
	static List<String> loreStat = new ArrayList<String>();

	public static void openParticleGui(Player p) {

		if (!gui.containsKey(p)) {
		

			String statuAccessHeart = "",
					statuAccessMusic = "",
					statuAccessFlame = "",
					statuAccessWater = "",
					statuAccessLava = "",
					statuAccessWitch = "",
					statuAccessSpark = "",
					statuAccessEnchant = "",
					statuAccessPortal = "",
					statuAccessSnow = "",
					statuAccessSlime = "",
					statuAccessAngryvillager = "",
					statuAccessMagic = "",
					statuAccessSnowshovel = "",
					statuAccessReddust = "";
			
			String statuEffect = "";
					

			Inventory inv = Bukkit.createInventory(null, (54),
					Messages.particleMenuPrefix.replace("&", "§"));
			gui.put(p, inv);
			
			if (ParticleManager.hasCircleEffect(p)) {
				statuEffect = Messages.isOnName.replace("&", "§");
			} else {
				statuEffect = Messages.isOffName.replace("&", "§");
			}

			if (p.hasPermission("propack.particle.heart")|| p.hasPermission("propack.particle.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessHeart = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");
			} else {
				statuAccessHeart = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}
			if (p.hasPermission("propack.particle.music")|| p.hasPermission("propack.particle.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessMusic = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");
			} else {
				statuAccessMusic = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}
			if (p.hasPermission("propack.particle.flame")|| p.hasPermission("propack.particle.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessFlame = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");
			} else {
				statuAccessFlame = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}
			if (p.hasPermission("propack.particle.lava")|| p.hasPermission("propack.particle.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessLava = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");
			} else {
				statuAccessLava = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}
			if (p.hasPermission("propack.particle.witch")|| p.hasPermission("propack.particle.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessWitch = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");
			} else {
				statuAccessWitch = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}
			if (p.hasPermission("propack.particle.water")|| p.hasPermission("propack.particle.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessWater = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");
			} else {
				statuAccessWater= Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}
			if (p.hasPermission("propack.particle.spark")|| p.hasPermission("propack.particle.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessSpark = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");
			} else {
				statuAccessSpark = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}
			if (p.hasPermission("propack.particle.enchant")|| p.hasPermission("propack.particle.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessEnchant = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");
			} else {
				statuAccessEnchant = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}
			if (p.hasPermission("propack.particle.portal")|| p.hasPermission("propack.particle.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessPortal = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");
			} else {
				statuAccessPortal = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}
			if (p.hasPermission("propack.particle.slime")|| p.hasPermission("propack.particle.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessSlime = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");
			} else {
				statuAccessSlime = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}
			if (p.hasPermission("propack.particle.angryvillager")|| p.hasPermission("prodigypack.use.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessAngryvillager = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");
			} else {
				statuAccessAngryvillager = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}
			if (p.hasPermission("propack.particle.magic")|| p.hasPermission("prodigypack.use.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessMagic = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");
			} else {
				statuAccessMagic = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}
			if (p.hasPermission("propack.particle.snow")|| p.hasPermission("prodigypack.use.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessSnow = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");
			} else {
				statuAccessSnow = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}
			if (p.hasPermission("propack.particle.snowshovel")|| p.hasPermission("propack.particle.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessSnowshovel = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");
			} else {
				statuAccessSnowshovel = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}
			if (p.hasPermission("propack.particle.rainbow")|| p.hasPermission("propack.particle.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				statuAccessReddust = Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§");
			} else {
				statuAccessReddust = Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§");
			}
			
			
			inv.setItem(11,UtilItem.create(Material.REDSTONE, 1, Messages.heartName.replace("&", "§"), statuAccessHeart));
			inv.setItem(14,UtilItem.create(Material. BLAZE_POWDER, 1, Messages.flameName.replace("&", "§"), statuAccessFlame));
			inv.setItem(24,UtilItem.create(Material.NOTE_BLOCK, 1, Messages.musicName.replace("&", "§"), statuAccessMusic));
			inv.setItem(12,UtilItem.create(Material.WATER_BUCKET, 1, Messages.waterName.replace("&", "§"), statuAccessWater));
			inv.setItem(13,UtilItem.create(Material.LAVA_BUCKET, 1, Messages.lavaName.replace("&", "§"), statuAccessLava));
			inv.setItem(32,UtilItem.create(Material.SPIDER_EYE, 1, Messages.witchName.replace("&", "§"), statuAccessWitch));
			inv.setItem(31,UtilItem.create(Material.NETHER_STAR, 1, Messages.sparkName.replace("&", "§"), statuAccessSpark));
			inv.setItem(15,UtilItem.create(Material.ENCHANTMENT_TABLE, 1, Messages.enchantName.replace("&", "§"), statuAccessEnchant));
			inv.setItem(20,UtilItem.create(Material.EYE_OF_ENDER, 1, Messages.portalName.replace("&", "§"), statuAccessPortal));
			inv.setItem(21,UtilItem.create(Material.SNOW_BALL, 1, Messages.snowName.replace("&", "§"), statuAccessSnow));
			inv.setItem(22,UtilItem.create(Material.SLIME_BALL, 1, Messages.slimeName.replace("&", "§"), statuAccessSlime));
			inv.setItem(30,UtilItem.create(Material.QUARTZ, 1, Messages.snowshovelName.replace("&", "§"), statuAccessSnowshovel));
			inv.setItem(23,UtilItem.create(Material.FIREBALL, 1, Messages.angryvillagerName.replace("&", "§"), statuAccessAngryvillager));
			inv.setItem(29,UtilItem.create(Material.DIAMOND_SWORD, 1, Messages.magicName.replace("&", "§"), statuAccessMagic));
			inv.setItem(33,UtilItem.create(Material.MONSTER_EGG, 1 , (byte) 66, Messages.rainbowName.replace("&", "§"), statuAccessReddust));
			
			inv.setItem(53,UtilItem.create(Material.ENDER_PEARL, 1 , Messages.stopEffectName.replace("&", "§"), statuEffect));
			inv.setItem(45,UtilItem.create(Material.PISTON_BASE, 1 , Messages.returnMainName.replace("&", "§")));

		
			for (Player pInv : gui.keySet()) {
				pInv.openInventory(inv);
				gui.remove(p);
			}

		
		}
	}

}
