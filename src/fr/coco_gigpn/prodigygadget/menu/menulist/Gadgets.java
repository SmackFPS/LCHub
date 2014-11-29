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

import fr.coco_gigpn.prodigygadget.util.Configs;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilInventory;
import fr.coco_gigpn.prodigygadget.util.UtilItem;

public class Gadgets {
	final public static HashMap<Player, Inventory> gui = new HashMap<Player, Inventory>();
	
	static List<String> _batblaster = new ArrayList<String>();
	static List<String> _paintballgun = new ArrayList<String>();
	static List<String> _quakegun = new ArrayList<String>();
	static List<String> _parachute = new ArrayList<String>();
	static List<String> _fireworkpopper = new ArrayList<String>();
	static List<String>  _discoball = new ArrayList<String>();
	static List<String>  _diamondparty = new ArrayList<String>();
	static List<String>  _ghost = new ArrayList<String>();
	static List<String>  _slimehat = new ArrayList<String>();
	static List<String>  _grenade = new ArrayList<String>();
	static List<String>  _smokebomb= new ArrayList<String>();
	static List<String>  _partypopper = new ArrayList<String>();
	static List<String>  _tntpopper = new ArrayList<String>();
	static List<String>  _humanbomb = new ArrayList<String>();
	static List<String>  _halloweenhead = new ArrayList<String>();
	static List<String>  _axelauncher = new ArrayList<String>();
	static List<String>  _tornado = new ArrayList<String>();
	static List<String>  _dracula = new ArrayList<String>();
	static List<String>  _flowerpopper = new ArrayList<String>();
	static List<String>  _gravitystation = new ArrayList<String>();
	static List<String>  _enderball = new ArrayList<String>();
	static List<String>  _puppielover = new ArrayList<String>();

	

	public static void openGadgetGui(Player p) {

		if (!gui.containsKey(p)) {

			Inventory inv = Bukkit.createInventory(null, (54), Messages.gadgetMenuPrefix.replace("&", "§"));
			gui.put(p, inv);
			
			_batblaster.clear();
			_paintballgun.clear();
			_diamondparty.clear();
			_discoball.clear();
			_ghost.clear();
			_grenade.clear();
			_fireworkpopper.clear();
			_flowerpopper.clear();
			_parachute.clear();
			_halloweenhead.clear();
			_quakegun.clear();
			_slimehat.clear();
			_smokebomb.clear();
			_humanbomb.clear();
			_tntpopper.clear();
			_partypopper.clear();
			_axelauncher.clear();
			_tornado.clear();
			_dracula.clear();
			_gravitystation.clear();
			_enderball.clear();
			_puppielover.clear();
		

			ItemStack batblaster = new ItemStack(Material.IRON_BARDING);
			ItemMeta batblasterMeta = batblaster.getItemMeta();
			batblasterMeta.setDisplayName(Messages.batblasterName.replace("&", "§"));
			_batblaster.add(Messages.cooldownDesc.replace("%cooldown", Double.toString(Configs.batblasterCooldown)).replace("&", "§"));
			 if (p.hasPermission("propack.gadget.batblaster")|| p.hasPermission("prodigypack.use.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				 _batblaster.add(Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§"));
			 } else {
				 _batblaster.add(Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§"));
			 }
			_batblaster.add(Messages.batblasterDescription.replace("&", "§"));
			batblasterMeta.setLore(_batblaster);
			batblaster.setItemMeta(batblasterMeta);
			inv.setItem(11, batblaster);
			
			ItemStack paintballgun = new ItemStack(Material.GOLD_BARDING);
			ItemMeta paintballgunMeta = paintballgun.getItemMeta();
			paintballgunMeta.setDisplayName(Messages.paintballgunName.replace("&", "§"));
			_paintballgun.add(Messages.cooldownDesc.replace("%cooldown", "1").replace("&", "§"));
			if (p.hasPermission("propack.gadget.paintballgun")|| p.hasPermission("prodigypack.use.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				 _paintballgun.add(Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§"));
			 } else {
				 _paintballgun.add(Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§"));
			 }
			_paintballgun.add(Messages.paintballgunDescription.replace("&", "§"));
			paintballgunMeta.setLore(_paintballgun);
			paintballgun.setItemMeta(paintballgunMeta);
			inv.setItem(12, paintballgun);
			
			
			ItemStack quakegun = new ItemStack(Material.DIAMOND_HOE);
			ItemMeta quakegunMeta = quakegun.getItemMeta();
			quakegunMeta.setDisplayName(Messages.quakegunName.replace("&", "§"));
			_quakegun.add(Messages.cooldownDesc.replace("%cooldown", Double.toString(Configs.quakegunCooldown)).replace("&", "§"));
			if (p.hasPermission("propack.gadget.quakegun")|| p.hasPermission("prodigypack.use.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				 _quakegun.add(Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§"));
			 } else {
				 _quakegun.add(Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§"));
			 }
			_quakegun.add(Messages.quakegunDescription.replace("&", "§"));
			quakegunMeta.setLore(_quakegun);
			quakegun.setItemMeta(quakegunMeta);
			inv.setItem(13, quakegun);
			
			ItemStack parachute = new ItemStack(Material.REDSTONE_TORCH_ON);
			ItemMeta parachuteMeta = parachute.getItemMeta();
			parachuteMeta.setDisplayName(Messages.rocketName.replace("&", "§"));
			_parachute.add(Messages.cooldownDesc.replace("%cooldown", Double.toString(Configs.rocketCooldown)).replace("&", "§"));
			if (p.hasPermission("propack.gadget.rocket")|| p.hasPermission("prodigypack.use.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				 _parachute .add(Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§"));
			 } else {
				 _parachute .add(Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§"));
			 }
			_parachute.add(Messages.rocketDescription.replace("&", "§"));
			parachuteMeta.setLore(_parachute);
			parachute.setItemMeta(parachuteMeta);
			inv.setItem(15, parachute);
			
			ItemStack fireworkpopper = new ItemStack(Material.FIREWORK);
			ItemMeta fireworkpopperMeta = fireworkpopper.getItemMeta();
			fireworkpopperMeta.setDisplayName(Messages.fireworkpopperName.replace("&", "§"));
			_fireworkpopper.add(Messages.cooldownDesc.replace("%cooldown", Double.toString(Configs.fireworkpopperCooldown)).replace("&", "§"));
			if (p.hasPermission("propack.gadget.fireworkpopper")|| p.hasPermission("prodigypack.use.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				 _fireworkpopper.add(Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§"));
			 } else {
				 _fireworkpopper.add(Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§"));
			 }
			_fireworkpopper.add(Messages.fireworkpopperDescription.replace("&", "§"));
			fireworkpopperMeta.setLore(_fireworkpopper);
			fireworkpopper.setItemMeta(fireworkpopperMeta);
			inv.setItem(14, fireworkpopper);
			
			ItemStack discoball = new ItemStack(Material.BEACON);
			ItemMeta discoballMeta = discoball.getItemMeta();
			discoballMeta.setDisplayName(Messages.discoballName.replace("&", "§"));
			_discoball.add(Messages.cooldownDesc.replace("%cooldown", Double.toString(Configs.discoballCooldown)).replace("&", "§"));
			if (p.hasPermission("propack.gadget.discoball")|| p.hasPermission("prodigypack.use.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				 _discoball.add(Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§"));
			 } else {
				 _discoball.add(Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§"));
			 }
			_discoball.add(Messages.discoballDescription.replace("&", "§"));
			discoballMeta.setLore(_discoball);
			discoball.setItemMeta(discoballMeta);
			inv.setItem(22, discoball);
			
			ItemStack diamondparty = new ItemStack(Material.DIAMOND);
			ItemMeta diamondpartyMeta = diamondparty.getItemMeta();
			diamondpartyMeta.setDisplayName(Messages.diamondpartyName.replace("&", "§"));
			_diamondparty.add(Messages.cooldownDesc.replace("%cooldown", Double.toString(Configs.diamondPartyCooldown)).replace("&", "§"));
			if (p.hasPermission("propack.gadget.diamondparty")|| p.hasPermission("prodigypack.use.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				 _diamondparty.add(Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§"));
			 } else {
				 _diamondparty.add(Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§"));
			 }
			_diamondparty.add(Messages.diamondpartyDescription.replace("&", "§"));
			diamondpartyMeta.setLore(_diamondparty);
			diamondparty.setItemMeta(diamondpartyMeta);
			inv.setItem(23, diamondparty);
			
			ItemStack humanbomb = new ItemStack(Material.TNT);
			ItemMeta humanbombMeta = humanbomb.getItemMeta();
			humanbombMeta.setDisplayName(Messages.humanbombName.replace("&", "§"));
			_humanbomb.add(Messages.cooldownDesc.replace("%cooldown", Double.toString(Configs.humanbombCooldown)).replace("&", "§"));
			if (p.hasPermission("propack.gadget.humanbomb")|| p.hasPermission("prodigypack.use.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				 _humanbomb.add(Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§"));
			 } else {
				 _humanbomb.add(Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§"));
			 }
			_humanbomb.add(Messages.humanbombDescription.replace("&", "§"));
			humanbombMeta.setLore(_humanbomb);
			humanbomb.setItemMeta(humanbombMeta);
			inv.setItem(20, humanbomb);
			
			ItemStack slimehat = new ItemStack(Material.SLIME_BALL);
			ItemMeta slimehatMeta = slimehat.getItemMeta();
			slimehatMeta.setDisplayName(Messages.slimehatName.replace("&", "§"));
			_slimehat.add(Messages.cooldownDesc.replace("%cooldown", Double.toString(Configs.slimehatCooldown)).replace("&", "§"));
			if (p.hasPermission("propack.gadget.slimehat")|| p.hasPermission("prodigypack.use.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				 _slimehat.add(Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§"));
			 } else {
				 _slimehat.add(Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§"));
			 }
			_slimehat.add(Messages.slimehatDescription.replace("&", "§"));
			slimehatMeta.setLore(_slimehat);
			slimehat.setItemMeta(slimehatMeta);
			inv.setItem(24, slimehat);
			
			ItemStack grenade = new ItemStack(Material.ENCHANTED_BOOK);
			ItemMeta grenadeMeta = grenade.getItemMeta();
			grenadeMeta.setDisplayName(Messages.grenadeName.replace("&", "§"));
			_grenade.add(Messages.cooldownDesc.replace("%cooldown", Double.toString(Configs.grenadeCooldown)).replace("&", "§"));
			if (p.hasPermission("propack.gadget.grenade")|| p.hasPermission("prodigypack.use.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				 _grenade.add(Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§"));
			 } else {
				 _grenade.add(Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§"));
			 }
			_grenade.add(Messages.grenadeDescription.replace("&", "§"));
			grenadeMeta.setLore(_grenade);
			grenade.setItemMeta(grenadeMeta);
			inv.setItem(29, grenade);
			
			ItemStack smokebomb = new ItemStack(Material.SUGAR);
			ItemMeta sugarMeta = smokebomb.getItemMeta();
			sugarMeta.setDisplayName(Messages.smokebombName.replace("&", "§"));
			_smokebomb.add(Messages.cooldownDesc.replace("%cooldown", Double.toString(Configs.smokebombCooldown)).replace("&", "§"));
			if (p.hasPermission("propack.gadget.smokebomb ")|| p.hasPermission("prodigypack.use.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				 _smokebomb .add(Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§"));
			 } else {
				 _smokebomb .add(Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§"));
			 }
			_smokebomb.add(Messages.smokebombDescription.replace("&", "§"));
			sugarMeta.setLore(_smokebomb );
			smokebomb.setItemMeta(sugarMeta);
			inv.setItem(30, smokebomb);
			
			ItemStack halloweenhead = new ItemStack(Material.PUMPKIN);
			ItemMeta halloweenheadMeta = halloweenhead.getItemMeta();
			halloweenheadMeta.setDisplayName(Messages.halloweenheadName.replace("&", "§"));
			_halloweenhead.add(Messages.cooldownDesc.replace("%cooldown", Double.toString(Configs.halloweenheadCooldown)).replace("&", "§"));
			if (p.hasPermission("propack.gadget.halloweenhead")|| p.hasPermission("prodigypack.use.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				 _halloweenhead.add(Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§"));
			 } else {
				 _halloweenhead.add(Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§"));
			 }
			_halloweenhead.add(Messages.halloweenheadDescription.replace("&", "§"));
			halloweenheadMeta.setLore(_halloweenhead);
			halloweenhead.setItemMeta(halloweenheadMeta);
			inv.setItem(21, halloweenhead);
			
			
			ItemStack partypopper = new ItemStack(Material.DOUBLE_PLANT);
			ItemMeta partypopperMeta = partypopper.getItemMeta();
			partypopperMeta.setDisplayName(Messages.partypopperName.replace("&", "§"));
			_partypopper.add(Messages.cooldownDesc.replace("%cooldown", Double.toString(Configs.partypopperCooldown)).replace("&", "§"));
			if (p.hasPermission("propack.gadget.partypopper")|| p.hasPermission("prodigypack.use.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				 _partypopper.add(Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§"));
			 } else {
				 _partypopper.add(Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§"));
			 }
			_partypopper.add(Messages.partypopperDescription.replace("&", "§"));
			partypopperMeta.setLore(_partypopper);
			partypopper.setItemMeta(partypopperMeta);
			inv.setItem(31, partypopper);
			
			ItemStack tntpopper = new ItemStack(Material.SULPHUR);
			ItemMeta tntpopperMeta = tntpopper.getItemMeta();
			tntpopperMeta.setDisplayName(Messages.tntpopperName.replace("&", "§"));
			_tntpopper.add(Messages.cooldownDesc.replace("%cooldown", Double.toString(Configs.tntpopperCooldown)).replace("&", "§"));
			if (p.hasPermission("propack.gadget.tntpopper")|| p.hasPermission("prodigypack.use.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				 _tntpopper.add(Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§"));
			 } else {
				 _tntpopper.add(Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§"));
			 }
			_tntpopper.add(Messages.tntpopperDescription.replace("&", "§"));
			tntpopperMeta.setLore(_tntpopper);
			tntpopper.setItemMeta(tntpopperMeta);
			inv.setItem(32, tntpopper);
			
			
			ItemStack ghost = new ItemStack(Material.FEATHER);
			ItemMeta ghostMeta = ghost.getItemMeta();
			ghostMeta.setDisplayName(Messages.ghostName.replace("&", "§"));
			_ghost.add(Messages.cooldownDesc.replace("%cooldown", Double.toString(Configs.ghostCooldown)).replace("&", "§"));
			if (p.hasPermission("propack.gadget.ghost")|| p.hasPermission("prodigypack.use.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				 _ghost.add(Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§"));
			 } else {
				 _ghost.add(Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§"));
			 }
			_ghost.add(Messages.ghostDescription.replace("&", "§"));
			ghostMeta.setLore(_ghost);
			ghost.setItemMeta(ghostMeta);
			inv.setItem(33, ghost);
			
			ItemStack axelauncher = new ItemStack(Material.IRON_AXE);
			ItemMeta axelauncherMeta = ghost.getItemMeta();
			axelauncherMeta.setDisplayName(Messages.axelauncherName.replace("&", "§"));
			_axelauncher.add(Messages.cooldownDesc.replace("%cooldown", Double.toString(Configs.axelauncherCooldown)).replace("&", "§"));
			if (p.hasPermission("propack.gadget.axelauncher")|| p.hasPermission("prodigypack.use.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				 _axelauncher.add(Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§"));
			 } else {
				 _axelauncher.add(Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§"));
			 }
			_axelauncher.add(Messages.axelauncherDescription.replace("&", "§"));
			axelauncherMeta.setLore(_axelauncher);
			axelauncher.setItemMeta(axelauncherMeta);
			inv.setItem(38, axelauncher);
			
			ItemStack tornado = new ItemStack(Material.WEB);
			ItemMeta tornadoMeta = tornado.getItemMeta();
			 tornadoMeta.setDisplayName(Messages.tornadogadgetName.replace("&", "§"));
			_tornado.add(Messages.cooldownDesc.replace("%cooldown", Double.toString(Configs.tornadogadgetCooldown)).replace("&", "§"));
			if (p.hasPermission("propack.gadget.tornado")|| p.hasPermission("prodigypack.use.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				 _tornado.add(Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§"));
			 } else {
				 _tornado.add(Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§"));
			 }
			_tornado.add(Messages.tornadoDescription.replace("&", "§"));
			tornadoMeta.setLore(_tornado);
			tornado.setItemMeta(tornadoMeta);
			inv.setItem(39, tornado);
			
			ItemStack flowerpopper = new ItemStack(Material.RED_ROSE);
			ItemMeta flowerpopperMeta = flowerpopper.getItemMeta();
			flowerpopperMeta.setDisplayName(Messages.flowerpopperName.replace("&", "§"));
			_flowerpopper.add(Messages.cooldownDesc.replace("%cooldown", Double.toString(Configs.flowerpopperCooldown)).replace("&", "§"));
			if (p.hasPermission("propack.gadget.flowerpopper")|| p.hasPermission("prodigypack.use.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				 _flowerpopper.add(Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§"));
			 } else {
				 _flowerpopper.add(Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§"));
			 }
			_flowerpopper.add(Messages.flowerpopperDescription.replace("&", "§"));
			flowerpopperMeta.setLore(_flowerpopper);
			flowerpopper.setItemMeta(flowerpopperMeta);
			inv.setItem(40, flowerpopper);
			
			
			ItemStack gravitystation = new ItemStack(Material.ENDER_PORTAL_FRAME);
			ItemMeta gravitystationMeta = gravitystation.getItemMeta();
			gravitystationMeta.setDisplayName(Messages.gravitystationName.replace("&", "§"));
			_gravitystation.add(Messages.cooldownDesc.replace("%cooldown", Double.toString(Configs.gravitystationCooldown)).replace("&", "§"));
			if (p.hasPermission("propack.gadget.gravitystation")|| p.hasPermission("prodigypack.use.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				 _gravitystation.add(Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§"));
			 } else {
				 _gravitystation.add(Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§"));
			 }
			_gravitystation.add(Messages.gravitystationDescription.replace("&", "§"));
			gravitystationMeta.setLore(_gravitystation);
			gravitystation.setItemMeta(gravitystationMeta);
			inv.setItem(40, gravitystation);
			
			ItemStack enderball = new ItemStack(Material.EYE_OF_ENDER);
			ItemMeta enderballMeta = enderball.getItemMeta();
			enderballMeta.setDisplayName(Messages.enderballName.replace("&", "§"));
			_enderball.add(Messages.cooldownDesc.replace("%cooldown", Double.toString(Configs.enderballCooldown)).replace("&", "§"));
			if (p.hasPermission("propack.gadget.enderball")|| p.hasPermission("prodigypack.use.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				 _enderball.add(Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§"));
			 } else {
				 _enderball.add(Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§"));
			 }
			_enderball.add(Messages.enderballDescription.replace("&", "§"));
			enderballMeta.setLore(_enderball);
			enderball.setItemMeta(enderballMeta);
			inv.setItem(41, enderball);
			
			ItemStack puppielover = new ItemStack(Material.BONE);
			ItemMeta puppieloverMeta = puppielover.getItemMeta();
			puppieloverMeta.setDisplayName(Messages.puppieloverName.replace("&", "§"));
			_puppielover.add(Messages.cooldownDesc.replace("%cooldown", Double.toString(Configs.puppieloverCooldown)).replace("&", "§"));
			if (p.hasPermission("propack.gadget.puppielover")|| p.hasPermission("prodigypack.use.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
				 _puppielover.add(Messages.AccessDesc.replace("%access", Messages.accessOn.replace("&", "§")).replace("&", "§"));
			 } else {
				 _puppielover.add(Messages.AccessDesc.replace("%access", Messages.accessDeny.replace("&", "§")).replace("&", "§"));
			 }
			_puppielover.add(Messages.puppieloverDescription.replace("&", "§"));
			puppieloverMeta.setLore(_puppielover);
			puppielover.setItemMeta(puppieloverMeta);
			inv.setItem(42, puppielover);
			
			
			
			
	
			String statuGadget = "";
			
			if(UtilInventory.hasCurrentGadget(p)) {
				statuGadget = Messages.isOnName.replace("&", "§");
				} else {
				statuGadget = Messages.isOffName.replace("&", "§");
				}
			
			inv.setItem(53, UtilItem.create(Material.FIREBALL, 1 , Messages.removegadgetName.replace("&", "§") , statuGadget));
			inv.setItem(45, UtilItem.create(Material.PISTON_BASE, 1 , Messages.returnMainName.replace("&", "§")));
		
	
			for (Player pInv : gui.keySet()) {
				pInv.openInventory(inv);
				gui.remove(p);
			}

		}
	}


}
