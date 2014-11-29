package fr.coco_gigpn.prodigygadget.pet;

import java.util.HashMap;
import java.util.UUID;

import me.mike1665.Main.Main;

import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;

import fr.coco_gigpn.prodigygadget.pet.pets.CowPet;
import fr.coco_gigpn.prodigygadget.pet.pets.OcelotPet;
import fr.coco_gigpn.prodigygadget.pet.pets.PigPet;
import fr.coco_gigpn.prodigygadget.pet.pets.SheepPet;
import fr.coco_gigpn.prodigygadget.pet.pets.WolfPet;
import fr.coco_gigpn.prodigygadget.updater.UpdateType;
import fr.coco_gigpn.prodigygadget.updater.event.UpdateEvent;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilServer;

public class PetManager implements Listener {

	public static HashMap<UUID, Entity> pet = new HashMap<UUID, Entity>();
	public static HashMap<UUID, PetType> petType = new HashMap<UUID, PetType>();

	public enum PetType {
		Ocelot, Wolf, Sheep, Pig, Cow
	}

	public static void ActivatePet(Player p, PetType pet) {

		if (pet == PetType.Wolf) {
			WolfPet.playWolf(p);
		}
		if (pet == PetType.Ocelot) {
			OcelotPet.playOcelot(p);
		}
		if (pet == PetType.Sheep) {
			SheepPet.playSheep(p);
		}
		if (pet == PetType.Cow) {
			CowPet.playCow(p);
		}
		if (pet == PetType.Pig) {
			PigPet.playPig(p);
		}

	}

	public static boolean HasPet(Player p) {
		if (pet.containsKey(p.getUniqueId())) {
			return true;
		} else {
			return false;
		}
	}

	public static void removeCurrentPet(Player p, boolean message) {
		if (pet.containsKey(p.getUniqueId())) {
			pet.get(p.getUniqueId()).remove();
			pet.remove(p.getUniqueId());
			PetManager.pet.remove(p.getUniqueId());
		} else {
			if (message == true) {
				p.sendMessage(Messages.alreadypetremoved.replace("&", "§"));
			}

		}
	}

	 @EventHandler
	 public void PlayerChangedWorldEvent(PlayerChangedWorldEvent e) {
		 Player p = e.getPlayer();
		 if(HasPet(p)) {
			 removeCurrentPet(p, false);
		 }
	 }
	
	@EventHandler
	public void OnPlayerLeft(PlayerQuitEvent e) {

		Player p = e.getPlayer();
		if (PetManager.HasPet(p)) {
			PetManager.removeCurrentPet(p, false);
		}
	}

	@EventHandler
	public void onLeave(PlayerKickEvent e) {
		Player p = e.getPlayer();
		if (PetManager.HasPet(p)) {
			PetManager.removeCurrentPet(p, false);
		}

	}

	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		if ((event.getRightClicked() instanceof Wolf)
				|| event.getRightClicked() instanceof Ocelot
				|| event.getRightClicked() instanceof Sheep
				|| event.getRightClicked() instanceof Pig
				|| event.getRightClicked() instanceof Cow) {

			Entity e = event.getRightClicked();

			if (e.hasMetadata("nopetdamage")) {
				try {
					event.setCancelled(true);

				} catch (Exception localException) {
				}
				return;
			}

		}
	}

	@EventHandler()
	public void PetDamageEvent(EntityDamageEvent event) {
		Entity e = event.getEntity();

		if (!(e instanceof Wolf) && !(e instanceof Ocelot)
				&& !(e instanceof Pig) && !(e instanceof Sheep)
				&& !(e instanceof Cow)) {
			return;
		} else {

			if (e.hasMetadata("nopetdamage")) {
				event.setCancelled(true);
			}

		}

	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void petsUpdate(UpdateEvent e){
		if (e.getType() == UpdateType.SEC) {

			for(Player p : UtilServer.getPlayers()){
				if(HasPet(p)){
					Double distanceBtw = p.getLocation().distance(PetManager.pet.get(p.getUniqueId()).getLocation());
					if(distanceBtw > 15D){
						if(p.isOnGround())
							PetManager.pet.get(p.getUniqueId()).teleport(p);
					}
				}
			}
		}
	}

	public static void registerEvents(Main plugin) {

		PluginManager pm = plugin.getServer().getPluginManager();
		pm.registerEvents(new SheepPet(), plugin);
		pm.registerEvents(new WolfPet(), plugin);
		pm.registerEvents(new PetManager(), plugin);

	}

}
