package fr.coco_gigpn.prodigygadget.event.events;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import fr.coco_gigpn.prodigygadget.menu.menulist.Main;
import fr.coco_gigpn.prodigygadget.util.Configs;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilItem;

public class PlayerListeners implements Listener {
	
	@EventHandler
	public void OnplayerInteract(PlayerInteractEvent e) {
		
		
		Action a = e.getAction();
		ItemStack is = e.getItem();
		Player p = e.getPlayer();
		
		ItemStack prodigypack = UtilItem.create(Configs.itemID, 1 ,(byte) Configs.itemData , Messages.prodigypackName.replace("&", "§"));
		
		if (a == Action.PHYSICAL || is == null || is.getType() == Material.AIR || prodigypack == null) return;

		if (e.getItem().equals(prodigypack)) {
		if (e.getItem().getItemMeta().equals(prodigypack.getItemMeta())) {
			e.setCancelled(true);
			Main.openMainGui(p);
		}}
	}


	/*@EventHandler
	public void OnPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		if (p.hasPermission("prodigypack.itemjoin") || p.hasPermission("prodigypack.*") || p.isOp()) {
		
		if (Configs.itemonjoin == true) {
		
		ItemStack prodigypack = UtilItem.create(Configs.itemID, 1 ,(byte) Configs.itemData , Messages.prodigypackName.replace("&", "§"));

				if (p.getInventory().contains(prodigypack)) {
					return;
				} else {
					if (Configs.itemOnJoinEnabledPosition == false) {
					
						p.getInventory().addItem(prodigypack);
					} else {
					p.getInventory().setItem((Configs.itemOnJoinPosition - 1), prodigypack);
					}
				}
		}
		} 
				
	}*/
	

	 @EventHandler
	 public void OnPlayerDrop(PlayerDropItemEvent event) {
	
	
		 if (Configs.canDropGadgets == true) return;

		 ItemStack i = new ItemStack(Material.BEACON, 1);
			UtilItem.setName(i, Messages.discoballName.replace("&", "§"));
			ItemStack i2 = new ItemStack(Material.IRON_BARDING, 1);
			UtilItem.setName(i2, Messages.batblasterName.replace("&", "§"));
			ItemStack i3 = new ItemStack(Material.DIAMOND_BARDING, 1);
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
			UtilItem.setName(i18, Messages.tornadogadgetName.replace("&", "§"));
			ItemStack i19 = new ItemStack(Material.ENDER_PORTAL_FRAME, 1);
			UtilItem.setName(i19, Messages.gravitystationName.replace("&", "§"));
			ItemStack i20 = new ItemStack(Material.EYE_OF_ENDER, 1);
			UtilItem.setName(i20, Messages.enderballName.replace("&", "§"));
			ItemStack i21 = new ItemStack(Material.BONE, 1);
			UtilItem.setName(i21, Messages.puppieloverName.replace("&", "§"));
			
			
			 
		if (event.getItemDrop().getItemStack().equals(i)
				|| event.getItemDrop().getItemStack().equals(i2)
				|| event.getItemDrop().getItemStack().equals(i3)
				|| event.getItemDrop().getItemStack().equals(i4)
				|| event.getItemDrop().getItemStack().equals(i5)
				|| event.getItemDrop().getItemStack().equals(i6)
				|| event.getItemDrop().getItemStack().equals(i7)
				|| event.getItemDrop().getItemStack().equals(i8)
				|| event.getItemDrop().getItemStack().equals(i9)
				|| event.getItemDrop().getItemStack().equals(i10)
				|| event.getItemDrop().getItemStack().equals(i12)
				|| event.getItemDrop().getItemStack().equals(i13)
				|| event.getItemDrop().getItemStack().equals(i14)
				|| event.getItemDrop().getItemStack().equals(i15)
				|| event.getItemDrop().getItemStack().equals(i16)
				|| event.getItemDrop().getItemStack().equals(i18)
				|| event.getItemDrop().getItemStack().equals(i17)
				|| event.getItemDrop().getItemStack().equals(i19)
				|| event.getItemDrop().getItemStack().equals(i20)
				|| event.getItemDrop().getItemStack().equals(i21)
				|| event.getItemDrop().getItemStack().equals(i11)) {
			
			event.setCancelled(true);
			
		}
		 
		
}
	 
	 @EventHandler(priority = EventPriority.NORMAL)
	 public void onInventoryMoveItemEvent(final InventoryClickEvent event) {
		 
		 if (event.getWhoClicked().getGameMode() == GameMode.CREATIVE) return;

		 if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR 
					|| !event.getCurrentItem().hasItemMeta() ||  Configs.canDropGadgets == true) {
	
				return;
			} 
		 

		 
		    ItemStack i = new ItemStack(Material.BEACON, 1);
			UtilItem.setName(i, Messages.discoballName.replace("&", "§"));
			ItemStack i2 = new ItemStack(Material.IRON_BARDING, 1);
			UtilItem.setName(i2, Messages.batblasterName.replace("&", "§"));
			ItemStack i3 = new ItemStack(Material.DIAMOND_BARDING, 1);
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
			UtilItem.setName(i18, Messages.tornadogadgetName.replace("&", "§"));
			ItemStack i19 = new ItemStack(Material.ENDER_PORTAL_FRAME, 1);
			UtilItem.setName(i19, Messages.gravitystationName.replace("&", "§"));
			ItemStack i20 = new ItemStack(Material.EYE_OF_ENDER, 1);
			UtilItem.setName(i20, Messages.enderballName.replace("&", "§"));
			ItemStack i21 = new ItemStack(Material.BONE, 1);
			UtilItem.setName(i21, Messages.puppieloverName.replace("&", "§"));

			
			 
		if (event.getCurrentItem().equals(i)
				|| event.getCurrentItem().equals(i2)
				|| event.getCurrentItem().equals(i3)
				|| event.getCurrentItem().equals(i4)
				|| event.getCurrentItem().equals(i5)
				|| event.getCurrentItem().equals(i6)
				|| event.getCurrentItem().equals(i7)
				|| event.getCurrentItem().equals(i8)
				|| event.getCurrentItem().equals(i9)
				|| event.getCurrentItem().equals(i10)
				|| event.getCurrentItem().equals(i12)
				|| event.getCurrentItem().equals(i13)
				|| event.getCurrentItem().equals(i14)
				|| event.getCurrentItem().equals(i15)
				|| event.getCurrentItem().equals(i16)
				|| event.getCurrentItem().equals(i17)
				|| event.getCurrentItem().equals(i18)
				|| event.getCurrentItem().equals(i19)
				|| event.getCurrentItem().equals(i20)
				|| event.getCurrentItem().equals(i21)
				|| event.getCurrentItem().equals(i11)) {
			
			event.setCancelled(true);
			event.getWhoClicked().closeInventory();
			
		} 
		 

	 
	 }
	 
	 @EventHandler
	 public void OnPlayerDrop3(PlayerDropItemEvent event) {
		 if (Configs.canDropProdigypack == true) return;
		 ItemStack prodigypack = UtilItem.create(Configs.itemID, 1 ,(byte) Configs.itemData , Messages.prodigypackName.replace("&", "§"));
		 if (!event.getItemDrop().getItemStack().equals(prodigypack)) return;
		 
		 event.setCancelled(true);
	 }
	  
	     
}
