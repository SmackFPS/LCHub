package fr.coco_gigpn.prodigygadget.pet.pets;

import me.mike1665.Main.Main;

import org.bukkit.Sound;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.metadata.FixedMetadataValue;

import fr.coco_gigpn.prodigygadget.gadget.gadgets.Rocket;
import fr.coco_gigpn.prodigygadget.pet.PetManager;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilPet;

public class OcelotPet implements Listener {

	public static void playOcelot(Player p) {
		if(!Rocket.stationnaire.containsKey(p) || !Rocket.effect.containsKey(p)) {
			if (!PetManager.HasPet(p)) {
				Ocelot ocelot = p.getWorld().spawn(p.getLocation(),
						Ocelot.class);
				ocelot.setCustomNameVisible(true);
				ocelot.setCustomName(Messages.petName
						.replace("%ridername", Messages.ocelotName.replace("&", "§"))
						.replace("%player", p.getName().replace("&", "§"))
						.replace("&", "§"));
				ocelot.setOwner(p);
				ocelot.setAgeLock(true);
				ocelot.setAdult();
				ocelot.setMetadata("nopetdamage", new FixedMetadataValue(
						Main.schedule, "nopetdamage"));
				UtilPet.makePet(ocelot, p.getUniqueId());
				PetManager.pet.put(p.getUniqueId(), ocelot);

			} else {
				p.sendMessage(Messages.alreadypet.replace("&", "§"));
			}
		} else {
			p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1, 1);
		}
	}
}
