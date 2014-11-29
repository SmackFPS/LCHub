package fr.coco_gigpn.prodigygadget.pet.pets;

import java.util.ArrayList;
import java.util.Random;

import me.mike1665.Main.Main;

import org.bukkit.DyeColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.metadata.FixedMetadataValue;

import fr.coco_gigpn.prodigygadget.gadget.gadgets.Rocket;
import fr.coco_gigpn.prodigygadget.pet.PetManager;
import fr.coco_gigpn.prodigygadget.updater.UpdateType;
import fr.coco_gigpn.prodigygadget.updater.event.UpdateEvent;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilPet;

public class SheepPet implements Listener {
	
	public static ArrayList<Sheep> rainbowSheep = new ArrayList<>();

	public static void playSheep(Player p) {
		if(!Rocket.stationnaire.containsKey(p) || !Rocket.effect.containsKey(p)) {
			if (!PetManager.HasPet(p)) {
				Sheep sheep = p.getWorld().spawn(p.getLocation(), Sheep.class);
				sheep.setCustomNameVisible(true);
				sheep.setCustomName(Messages.petName
						.replace("%ridername", Messages.sheepName.replace("&", "§"))
						.replace("%player", p.getName().replace("&", "§"))
						.replace("&", "§"));
				sheep.setAgeLock(true);
				sheep.setBaby();
				sheep.setMetadata("nopetdamage", new FixedMetadataValue(
						Main.schedule, "notpetdamage"));
				UtilPet.makePet(sheep, p.getUniqueId());
				PetManager.pet.put(p.getUniqueId(), sheep);
				rainbowSheep.add(sheep);

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
		

				for (Sheep s : rainbowSheep) {

					s.setColor(c);
				
			}
		}
	}
}
