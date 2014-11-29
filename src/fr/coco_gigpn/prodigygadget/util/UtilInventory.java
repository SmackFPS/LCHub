package fr.coco_gigpn.prodigygadget.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class UtilInventory {

	/**
	 * 
	 * @param Changer
	 *            la couleur d'une armure
	 * 
	 * @return
	 */

	public static ItemStack getColorArmor(Material m, Color c) {
		ItemStack i = new ItemStack(m, 1);
		LeatherArmorMeta meta = (LeatherArmorMeta) i.getItemMeta();
		meta.setColor(c);
		i.setItemMeta(meta);
		return i;
	}

	public static Map<String, ItemStack[]> playerInventories = new HashMap<String, ItemStack[]>();
	public static HashMap<Player, ItemStack[]> armor = new HashMap<Player, ItemStack[]>();

	/**
	 * 
	 * @param Sauvegarder
	 *            l'inventaire
	 * 
	 * @return
	 */

	public static void saveInventory(Player p) {

		PlayerInventory pInv = p.getInventory();
		Collection<ItemStack> items = new ArrayList<ItemStack>();
		items.addAll(Arrays.asList(pInv.getContents()));
		items.addAll(Arrays.asList(pInv.getArmorContents()));

		ItemStack[] inventoryItems = items.toArray(new ItemStack[] {});
		playerInventories.put(p.getName(), inventoryItems);
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
	}

	/**
	 * 
	 * @param Supprimer
	 *            l'inventaire et le restaurer
	 * 
	 * @return
	 * 
	 */

	@SuppressWarnings("deprecation")
	public static void resetInventory(Player p) {
		if (!playerInventories.containsKey(p.getName()))
			return;
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		ItemStack[] inventoryItems = playerInventories.get(p.getName());
		ItemStack[] playerItems = new ItemStack[inventoryItems.length - 4];
		ItemStack[] playerArmour = new ItemStack[4];
		int armourI = 0;
		for (int i = 0; i < inventoryItems.length; i++) {
			if (i < inventoryItems.length - 4) {
				playerItems[i] = inventoryItems[i];
			} else {
				playerArmour[armourI] = inventoryItems[i];
				armourI++;
			}
		}
		p.getInventory().setContents(playerItems);
		p.getInventory().setArmorContents(playerArmour);
		p.updateInventory();
	}

	/**
	 * 
	 * @param Sauvegarder
	 *            l'armure
	 * 
	 * @return
	 * 
	 */
	public static void saveArmor(Player p) {
		p.getInventory().setHelmet(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setLeggings(null);
		p.getInventory().setBoots(null);

		armor.put(p, p.getEquipment().getArmorContents());

	}

	/**
	 * 
	 * @param Restorer
	 *            l'armure
	 * 
	 * @return
	 * 
	 */
	public static void restoreArmor(Player p) {

		if (armor.containsKey(p)) {
			p.getInventory().setArmorContents(armor.get(p));

		}
	}

	/**
	 * 
	 * @param Supprimer
	 *            le joueur de la restauration
	 * 
	 */
	public static void forsakeArmor(Player p) {
		armor.remove(p);
	}

	/**
	 * 
	 * @param Supprimer
	 *            un item
	 * 
	 */
	public static int removeItem(Inventory inventory, Material id, int quantity) {
		int rest = quantity;
		for (int i = 0; i < inventory.getSize(); i++) {
			ItemStack stack = inventory.getItem(i);
			if (stack == null || stack.getType() != id)
				continue;

			if (rest >= stack.getAmount()) {
				rest -= stack.getAmount();
				inventory.clear(i);
			} else if (rest > 0) {
				stack.setAmount(stack.getAmount() - rest);
				rest = 0;
			} else {
				break;
			}
		}
		return quantity - rest;
	}

	public static void removeCurrentGadget(Player p) {
		ItemStack i = new ItemStack(Material.BEACON, 1);
		UtilItem.setName(i, Messages.discoballName.replace("&", "§"));
		ItemStack i2 = new ItemStack(Material.IRON_BARDING, 1);
		UtilItem.setName(i2, Messages.batblasterName.replace("&", "§"));
		ItemStack i3 = new ItemStack(Material.GOLD_BARDING, 1);
		UtilItem.setName(i3, Messages.paintballgunName.replace("&", "§"));
		ItemStack i4 = new ItemStack(Material.FIREWORK, 1);
		UtilItem.setName(i4, Messages.fireworkpopperName.replace("&", "§"));
		ItemStack i5 = new ItemStack(Material.TNT, 1);
		UtilItem.setName(i5, Messages.humanbombName.replace("&", "§"));
		ItemStack i6 = new ItemStack(Material.REDSTONE_TORCH_ON, 1);
		UtilItem.setName(i6, Messages.rocketName.replace("&", "§"));
		ItemStack i7 = new ItemStack(Material.DIAMOND, 1);
		UtilItem.setName(i7, Messages.diamondpartyName.replace("&", "§"));
		ItemStack i8 = new ItemStack(Material.SLIME_BALL, 1);
		UtilItem.setName(i8, Messages.slimehatName.replace("&", "§"));
		ItemStack i9 = new ItemStack(Material.DIAMOND_HOE, 1);
		UtilItem.setName(i9, Messages.quakegunName.replace("&", "§"));
		ItemStack i10 = new ItemStack(Material.PUMPKIN, 1);
		UtilItem.setName(i10, Messages.halloweenheadName.replace("&", "§"));
		ItemStack i11 = new ItemStack(Material.ENCHANTED_BOOK, 1);
		UtilItem.setName(i11, Messages.grenadeName.replace("&", "§"));
		ItemStack i12 = new ItemStack(Material.DOUBLE_PLANT, 1);
		UtilItem.setName(i12, Messages.partypopperName.replace("&", "§"));
		ItemStack i13 = new ItemStack(Material.SULPHUR, 1);
		UtilItem.setName(i13, Messages.tntpopperName.replace("&", "§"));
		ItemStack i14 = new ItemStack(Material.FEATHER, 1);
		UtilItem.setName(i14, Messages.ghostName.replace("&", "§"));
		ItemStack i15 = new ItemStack(Material.SUGAR, 1);
		UtilItem.setName(i15, Messages.smokebombName.replace("&", "§"));
		ItemStack i16 = new ItemStack(Material.IRON_AXE, 1);
		UtilItem.setName(i16, Messages.axelauncherName.replace("&", "§"));
		ItemStack i17 = new ItemStack(Material.WEB, 1);
		UtilItem.setName(i17, Messages.tornadogadgetName.replace("&", "§"));
		ItemStack i18 = new ItemStack(Material.RED_ROSE, 1);
		UtilItem.setName(i18, Messages.flowerpopperName.replace("&", "§"));
		ItemStack i19 = new ItemStack(Material.ENDER_PORTAL_FRAME, 1);
		UtilItem.setName(i19, Messages.gravitystationName.replace("&", "§"));
		ItemStack i20 = new ItemStack(Material.EYE_OF_ENDER, 1);
		UtilItem.setName(i20, Messages.enderballName.replace("&", "§"));
		ItemStack i21 = new ItemStack(Material.BONE, 1);
		UtilItem.setName(i21, Messages.puppieloverName.replace("&", "§"));



		if (p.getInventory().contains(i)) {
			p.getInventory().remove(i);
		}
		if (p.getInventory().contains(i2)) {
			p.getInventory().remove(i2);
		}
		if (p.getInventory().contains(i3)) {
			p.getInventory().remove(i3);
		}
		if (p.getInventory().contains(i4)) {
			p.getInventory().remove(i4);
		}
		if (p.getInventory().contains(i5)) {
			p.getInventory().remove(i5);

		}
		if (p.getInventory().contains(i6)) {
			p.getInventory().remove(i6);

		}
		if (p.getInventory().contains(i7)) {
			p.getInventory().remove(i7);

		}
		if (p.getInventory().contains(i8)) {
			p.getInventory().remove(i8);

		}
		if (p.getInventory().contains(i9)) {
			p.getInventory().remove(i9);

		}
		if (p.getInventory().contains(i10)) {
			p.getInventory().remove(i10);

		}
		if (p.getInventory().contains(i11)) {
			p.getInventory().remove(i11);

		}
		if (p.getInventory().contains(i12)) {
			p.getInventory().remove(i12);

		}
		if (p.getInventory().contains(i13)) {
			p.getInventory().remove(i13);

		}
		if (p.getInventory().contains(i14)) {
			p.getInventory().remove(i14);

		}
		if (p.getInventory().contains(i15)) {
			p.getInventory().remove(i15);

		}
		if (p.getInventory().contains(i16)) {
			p.getInventory().remove(i16);

		}
		if (p.getInventory().contains(i17)) {
			p.getInventory().remove(i17);

		}
		if (p.getInventory().contains(i18)) {
			p.getInventory().remove(i18);

		}
		if (p.getInventory().contains(i19)) {
			p.getInventory().remove(i19);

		}
		if (p.getInventory().contains(i20)) {
			p.getInventory().remove(i20);

		}
		if (p.getInventory().contains(i21)) {
			p.getInventory().remove(i21);

		}

	}

	public static boolean hasCurrentGadget(Player p) {
		ItemStack i = new ItemStack(Material.BEACON, 1);
		UtilItem.setName(i, Messages.discoballName.replace("&", "§"));
		ItemStack i2 = new ItemStack(Material.IRON_BARDING, 1);
		UtilItem.setName(i2, Messages.batblasterName.replace("&", "§"));
		ItemStack i3 = new ItemStack(Material.GOLD_BARDING, 1);
		UtilItem.setName(i3, Messages.paintballgunName.replace("&", "§"));
		ItemStack i4 = new ItemStack(Material.FIREWORK, 1);
		UtilItem.setName(i4, Messages.fireworkpopperName.replace("&", "§"));
		ItemStack i5 = new ItemStack(Material.TNT, 1);
		UtilItem.setName(i5, Messages.humanbombName.replace("&", "§"));
		ItemStack i6 = new ItemStack(Material.REDSTONE_TORCH_ON, 1);
		UtilItem.setName(i6, Messages.rocketName.replace("&", "§"));
		ItemStack i7 = new ItemStack(Material.DIAMOND, 1);
		UtilItem.setName(i7, Messages.diamondpartyName.replace("&", "§"));
		ItemStack i8 = new ItemStack(Material.SLIME_BALL, 1);
		UtilItem.setName(i8, Messages.slimehatName.replace("&", "§"));
		ItemStack i9 = new ItemStack(Material.DIAMOND_HOE, 1);
		UtilItem.setName(i9, Messages.quakegunName.replace("&", "§"));
		ItemStack i10 = new ItemStack(Material.PUMPKIN, 1);
		UtilItem.setName(i10, Messages.halloweenheadName.replace("&", "§"));
		ItemStack i11 = new ItemStack(Material.ENCHANTED_BOOK, 1);
		UtilItem.setName(i11, Messages.grenadeName.replace("&", "§"));
		ItemStack i12 = new ItemStack(Material.DOUBLE_PLANT, 1);
		UtilItem.setName(i12, Messages.partypopperName.replace("&", "§"));
		ItemStack i13 = new ItemStack(Material.SULPHUR, 1);
		UtilItem.setName(i13, Messages.tntpopperName.replace("&", "§"));
		ItemStack i14 = new ItemStack(Material.FEATHER, 1);
		UtilItem.setName(i14, Messages.ghostName.replace("&", "§"));
		ItemStack i15 = new ItemStack(Material.SUGAR, 1);
		UtilItem.setName(i15, Messages.smokebombName.replace("&", "§"));
		ItemStack i16 = new ItemStack(Material.IRON_AXE, 1);
		UtilItem.setName(i16, Messages.axelauncherName.replace("&", "§"));
		ItemStack i17 = new ItemStack(Material.WEB, 1);
		UtilItem.setName(i17, Messages.tornadogadgetName.replace("&", "§"));
		ItemStack i18 = new ItemStack(Material.RED_ROSE, 1);
		UtilItem.setName(i18, Messages.flowerpopperName.replace("&", "§"));
		ItemStack i19 = new ItemStack(Material.ENDER_PORTAL_FRAME, 1);
		UtilItem.setName(i19, Messages.gravitystationName.replace("&", "§"));
		ItemStack i20 = new ItemStack(Material.EYE_OF_ENDER, 1);
		UtilItem.setName(i20, Messages.enderballName.replace("&", "§"));
		ItemStack i21 = new ItemStack(Material.BONE, 1);
		UtilItem.setName(i21, Messages.puppieloverName.replace("&", "§"));

		if (p.getInventory().contains(i) 
				|| p.getInventory().contains(i2)
				|| p.getInventory().contains(i3)
				|| p.getInventory().contains(i4)
				|| p.getInventory().contains(i5)
				|| p.getInventory().contains(i6)
				|| p.getInventory().contains(i7)
				|| p.getInventory().contains(i8)
				|| p.getInventory().contains(i9)
				|| p.getInventory().contains(i10)
				|| p.getInventory().contains(i11)
				|| p.getInventory().contains(i12)
				|| p.getInventory().contains(i13)
				|| p.getInventory().contains(i14)
				|| p.getInventory().contains(i15)
				|| p.getInventory().contains(i16)
				|| p.getInventory().contains(i17)
				|| p.getInventory().contains(i18)
				|| p.getInventory().contains(i19)
				|| p.getInventory().contains(i20)
				|| p.getInventory().contains(i21)
				|| p.getInventory().contains(i2)) {

			return true;

		} else {
			return false;
		}

	}


}
