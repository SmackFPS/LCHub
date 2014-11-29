package fr.coco_gigpn.prodigygadget.pet.pets;

import me.mike1665.Main.Main;

import org.bukkit.Sound;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.metadata.FixedMetadataValue;

import fr.coco_gigpn.prodigygadget.gadget.gadgets.Rocket;
import fr.coco_gigpn.prodigygadget.pet.PetManager;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilPet;

public class CowPet implements Listener {

	public static void playCow(Player p) {
		if(!Rocket.stationnaire.containsKey(p) || !Rocket.effect.containsKey(p)) {
			if (!PetManager.HasPet(p)) {
				Cow cow = p.getWorld().spawn(p.getLocation(), Cow.class);
				cow.setCustomNameVisible(true);
				cow.setCustomName(Messages.petName
						.replace("%ridername", Messages.cowName.replace("&", "§"))
						.replace("%player", p.getName().replace("&", "§"))
						.replace("&", "§"));
				cow.setAgeLock(true);
				cow.setTarget(p);
				cow.setBaby();
				cow.setMetadata("nopetdamage", new FixedMetadataValue(
						Main.schedule, "notpetdamage"));
				UtilPet.makePet(cow, p.getUniqueId());
				PetManager.pet.put(p.getUniqueId(), cow);

			} else {
				p.sendMessage(Messages.alreadypet.replace("&", "§"));
			}
		} else {
			p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1, 1);
		}
	}
}
