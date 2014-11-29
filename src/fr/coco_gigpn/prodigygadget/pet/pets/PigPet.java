package fr.coco_gigpn.prodigygadget.pet.pets;

import me.mike1665.Main.Main;

import org.bukkit.Sound;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.metadata.FixedMetadataValue;

import fr.coco_gigpn.prodigygadget.gadget.gadgets.Rocket;
import fr.coco_gigpn.prodigygadget.pet.PetManager;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilPet;

public class PigPet implements Listener {

	public static void playPig(Player p) {
		if(!Rocket.stationnaire.containsKey(p) || !Rocket.effect.containsKey(p)) {
			if (!PetManager.HasPet(p)) {
				Pig pig = p.getWorld().spawn(p.getLocation(), Pig.class);
				pig.setCustomNameVisible(true);
				pig.setCustomName(Messages.petName
						.replace("%ridername", Messages.pigName.replace("&", "§"))
						.replace("%player", p.getName().replace("&", "§"))
						.replace("&", "§"));
				pig.setAgeLock(true);
				pig.setBaby();
				pig.setMetadata("nopetdamage", new FixedMetadataValue(
						Main.schedule, "notpetdamage"));
				UtilPet.makePet(pig, p.getUniqueId());
				PetManager.pet.put(p.getUniqueId(), pig);

			} else {
				p.sendMessage(Messages.alreadypet.replace("&", "§"));
			}
		} else {
			p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1, 1);
		}
	}
}
