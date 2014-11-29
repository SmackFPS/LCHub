package fr.coco_gigpn.prodigygadget.pet.pets;

import java.util.ArrayList;
import java.util.Random;

import me.mike1665.Main.Main;

import org.bukkit.DyeColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.metadata.FixedMetadataValue;

import fr.coco_gigpn.prodigygadget.gadget.gadgets.Rocket;
import fr.coco_gigpn.prodigygadget.pet.PetManager;
import fr.coco_gigpn.prodigygadget.updater.UpdateType;
import fr.coco_gigpn.prodigygadget.updater.event.UpdateEvent;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilPet;

public class WolfPet implements Listener {
	
	public static ArrayList<Wolf> rainbowWolf = new ArrayList<>();

	public static void playWolf(Player p) {
		if(!Rocket.stationnaire.containsKey(p) || !Rocket.effect.containsKey(p)) {
			if (!PetManager.HasPet(p)) {
				Wolf wolf = p.getWorld().spawn(p.getLocation(), Wolf.class);
				wolf.setCustomNameVisible(true);
				wolf.setCustomName(Messages.petName
						.replace("%ridername", Messages.wolfName.replace("&", "§"))
						.replace("%player", p.getName().replace("&", "§"))
						.replace("&", "§"));
				wolf.setOwner(p);
				wolf.setAdult();
				wolf.setAgeLock(true);
				wolf.setMetadata("nopetdamage", new FixedMetadataValue(
						Main.schedule, "nopetdamage"));
				UtilPet.makePet(wolf, p.getUniqueId());
				PetManager.pet.put(p.getUniqueId(), wolf);
				rainbowWolf.add(wolf);

			} else {
				p.sendMessage(Messages.alreadypet.replace("&", "§"));
			}
		} else {
			p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1, 1);
		}
	}
	
	@EventHandler
	public void ColorRandomiser(UpdateEvent event) {
		if (event.getType() == UpdateType.FASTER) {
			
			Random r = new Random();
			DyeColor c = DyeColor.values()[r.nextInt(5)];
		

				for (Wolf w: rainbowWolf) {

					w.setCollarColor(c);
				
			}
		}
	}
}
