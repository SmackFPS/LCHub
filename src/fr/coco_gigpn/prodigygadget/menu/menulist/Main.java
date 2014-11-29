package fr.coco_gigpn.prodigygadget.menu.menulist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.coco_gigpn.prodigygadget.effect.EffectManager;
import fr.coco_gigpn.prodigygadget.morph.MorphManager;
import fr.coco_gigpn.prodigygadget.mount.MountManager;
import fr.coco_gigpn.prodigygadget.pet.PetManager;
import fr.coco_gigpn.prodigygadget.util.Configs;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilInventory;
import fr.coco_gigpn.prodigygadget.util.UtilItem;

public class Main {
	final public static HashMap<Player, Inventory> gui = new HashMap<Player, Inventory>();
	static List<String> loreStat = new ArrayList<String>();


	public static void openMainGui(Player p) {

		if (!gui.containsKey(p)) {
			
			Inventory inv = Bukkit.createInventory(null, (54), Messages.mainMenuPrefix.replace("&", "§"));
			gui.put(p, inv);

			String statuEffect = "";
			String statuGadget = "";
			String statuPet = "";
	
			if(EffectManager.hasEffect(p) || MorphManager.hasMorph(p)) {
			statuEffect = Messages.isOnName.replace("&", "§");
			} else {
			statuEffect = Messages.isOffName.replace("&", "§");
			}
			
			
			if(UtilInventory.hasCurrentGadget(p)) {
			statuGadget = Messages.isOnName.replace("&", "§");
			} else {
			statuGadget = Messages.isOffName.replace("&", "§");
			}
			
			if(PetManager.HasPet(p) || MountManager.HasPet(p)) {
			statuPet = Messages.isOnName.replace("&", "§");
			} else {
			statuPet = Messages.isOffName.replace("&", "§");
			}
			
			
			ItemStack case1 = UtilItem.create(Material.STAINED_GLASS_PANE, 1 , (byte) 5 , Messages.packName.replace("&", "§"));
			ItemStack case2 = UtilItem.create(Material.STAINED_GLASS_PANE, 1 , (byte) 0 , Messages.packName.replace("&", "§"));
			ItemStack case3 = UtilItem.create(Material.STAINED_GLASS_PANE, 1 , (byte) 2, Messages.packName.replace("&", "§"));
			ItemStack case4 = UtilItem.create(Material.STAINED_GLASS_PANE, 1 , (byte) 14 , Messages.packName.replace("&", "§"));
			ItemStack case5 = UtilItem.create(Material.STAINED_GLASS_PANE, 1 , (byte) 1 , Messages.packName.replace("&", "§"));
			ItemStack case6 = UtilItem.create(Material.STAINED_GLASS_PANE, 1 , (byte) 4 , Messages.packName.replace("&", "§"));
			
			inv.setItem(0, case1);
			inv.setItem(1, case1);
			inv.setItem(2, case1);
			inv.setItem(9, case1);
			inv.setItem(11, case1);
			inv.setItem(18, case1);
			inv.setItem(19, case1);
			inv.setItem(20, case1);
			
			inv.setItem(3, case2);
			inv.setItem(4, case2);
			inv.setItem(5, case2);
			inv.setItem(12, case2);
			inv.setItem(14, case2);
			inv.setItem(21, case2);
			inv.setItem(22, case2);
			inv.setItem(23, case2);
			
			
			inv.setItem(6, case3);
			inv.setItem(7, case3);
			inv.setItem(8, case3);
			inv.setItem(15, case3);
			inv.setItem(17, case3);
			inv.setItem(24, case3);
			inv.setItem(25, case3);
			inv.setItem(26, case3);
			
			inv.setItem(27, case6);
			inv.setItem(28, case6);
			inv.setItem(29, case6);
			inv.setItem(36, case6);
			inv.setItem(38, case6);
			inv.setItem(45, case6);
			inv.setItem(46, case6);
			inv.setItem(47, case6);
			
			inv.setItem(30, case4);
			inv.setItem(32, case4);
			inv.setItem(39, case4);
			inv.setItem(40, case4);
			inv.setItem(41, case4);

			
			inv.setItem(33, case5);
			inv.setItem(34, case5);
			inv.setItem(35, case5);
			inv.setItem(42, case5);
			inv.setItem(44, case5);
			inv.setItem(51, case5);
			inv.setItem(52, case5);
			inv.setItem(53, case5);
			
			
			inv.setItem(10, UtilItem.create(Configs.particleID , 1 , (byte) Configs.particleDATA , Messages.particleName.replace("&", "§")));
			inv.setItem(37, UtilItem.create(Configs.gadgetID , 1 ,(byte) Configs.gadgetDATA , Messages.gadgetName.replace("&", "§")));
			inv.setItem(13, UtilItem.create(Configs.extraID , 1 , (byte) Configs.extraDATA ,Messages.extraName.replace("&", "§")));
			inv.setItem(43, UtilItem.create(Configs.mountID , 1 ,(byte) Configs.mountDATA, Messages.mountName.replace("&", "§")));
			inv.setItem(16, UtilItem.create(Configs.petID , 1 ,(byte) Configs.petDATA, Messages.petMenuName.replace("&", "§")));
			inv.setItem(31, UtilItem.create(Configs.morphID , 1, (byte) Configs.morphDATA, Messages.morphName.replace("&", "§")));
			
			inv.setItem(48, UtilItem.create(Material.FIREBALL, 1 , Messages.removegadgetName.replace("&", "§") , statuGadget));
			inv.setItem(49, UtilItem.create(Material.ENDER_PEARL , 1 , Messages.stopEffectandMorphName.replace("&", "§") , statuEffect));
			inv.setItem(50, UtilItem.create(Material.MAGMA_CREAM , 1 , Messages.removePetAndMountName.replace("&", "§"), statuPet));
			

			
		
	
			for (Player pInv : gui.keySet()) {
				pInv.openInventory(inv);
				gui.remove(p);
			}

		}
	}


}
