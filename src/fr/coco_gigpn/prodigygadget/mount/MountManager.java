package fr.coco_gigpn.prodigygadget.mount;

import java.util.HashMap;
import java.util.UUID;

import me.mike1665.Main.Main;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;

import fr.coco_gigpn.prodigygadget.mount.mounts.AngelRider;
import fr.coco_gigpn.prodigygadget.mount.mounts.DarkRider;
import fr.coco_gigpn.prodigygadget.mount.mounts.GhostRider;
import fr.coco_gigpn.prodigygadget.mount.mounts.NyanRider;
import fr.coco_gigpn.prodigygadget.mount.mounts.PoseidonRider;
import fr.coco_gigpn.prodigygadget.updater.UpdateType;
import fr.coco_gigpn.prodigygadget.updater.event.UpdateEvent;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilParticle;
import fr.coco_gigpn.prodigygadget.util.UtilParticle.Particle;
import fr.coco_gigpn.prodigygadget.util.UtilServer;

public class MountManager implements Listener {

	public static HashMap<UUID, Horse> pet = new HashMap<UUID, Horse>();
	
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
			MountManager.pet.remove(p.getUniqueId());
		} else {
			if(message == true) {
			p.sendMessage(Messages.alreadypetremoved.replace("&", "§"));
			}
		}
	}

	public static boolean isPetOwner(Player p, Horse h) {
		final Horse horse = pet.get(p.getUniqueId());
		return horse != null && horse.equals(h);
	}
	
	 @EventHandler
	 public void OnPlayerLeft(PlayerQuitEvent e) {
		 
		 Player p = e.getPlayer();
		 if (MountManager.HasPet(p)) {
			 MountManager.removeCurrentPet(p, false);
		 }
	 }
	 
	 @EventHandler
	  public void onLeave(PlayerKickEvent e)
	  {
		 Player p = e.getPlayer();
		 if (MountManager.HasPet(p)) {
			 MountManager.removeCurrentPet(p, false);
		 }
	
	  }
	
	
	@EventHandler
    public void InventoryOpenHorse (InventoryOpenEvent event) {
		Player p = (Player) event.getPlayer();
		if (!p.isInsideVehicle()) return;
		
		Entity e = p.getVehicle();
		if (e.hasMetadata("poseidonrider") 
				|| e.hasMetadata("darkrider")
				|| e.hasMetadata("nyanrider")
				|| e.hasMetadata("angelrider")
				|| e.hasMetadata("ghostrider")) {
			event.setCancelled(true);
		}
		
	}
	
	 @EventHandler
	  public void onPlayerInteractEntity(PlayerInteractEntityEvent event)
	  {
	    if ((event.getRightClicked() instanceof Horse))
	    {
			Player damager = event.getPlayer();
			Horse h = (Horse) event.getRightClicked();
			
			if (h.hasMetadata("poseidonrider") 
					|| h.hasMetadata("darkrider")
					|| h.hasMetadata("nyanrider")
					|| h.hasMetadata("angelrider")
					|| h.hasMetadata("ghostrider")) {
			
			 if (!MountManager.isPetOwner(damager, h))
		        {
		          try
		          {
		        	  event.setCancelled(true);
		        	  damager.sendMessage(Messages.notYourPet.replace("&", "§"));
		          }
		          catch (Exception localException) {}
		          return;
		        } 
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
	public void HorseDamageEvent(EntityDamageEvent event) {
		Entity e = event.getEntity();

		if (!(e instanceof Horse)) {
			return;
		} else {

			if (e.hasMetadata("poseidonrider") || e.hasMetadata("darkrider")
					|| e.hasMetadata("nyanrider")
					|| e.hasMetadata("angelrider")
					|| e.hasMetadata("ghostrider")) {
				event.setCancelled(true);
			}
			
		}

	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void ParticleAura(UpdateEvent event) {
		if (event.getType() == UpdateType.TICK) {
		

				for (UUID p : MountManager.pet.keySet()) {
					
					if(MountManager.pet.get(p).isValid()) {
						

					if (MountManager.pet.get(p).hasMetadata("angelrider")) {

						UtilParticle.sendParticleToLocation(MountManager.pet.get(p).getLocation().add(0, 1, 0),
								Particle.SNOW_SHOVEL, 0.25F, 0.25F, 0.25F,0.1F, 5);
					}

					if (MountManager.pet.get(p).hasMetadata("darkrider")) {
						UtilParticle.sendParticleToLocation(MountManager.pet.get(p).getLocation().add(0, 1, 0),
								Particle.WITCH_MAGIC, 0.25F, 0.25F, 0.25F,0.1F, 5);
					}

					if (MountManager.pet.get(p).hasMetadata("ghostrider")) {
						UtilParticle.sendParticleToLocation(MountManager.pet.get(p).getLocation().add(0, 1, 0),
								Particle.FLAME, 0.25F, 0.25F, 0.25F,0.05F, 2);
					}
					
					if (MountManager.pet.get(p).hasMetadata("nyanrider")) {
						UtilParticle.sendParticleToLocation(MountManager.pet.get(p).getLocation().add(0, 1, 0),
								Particle.MOB_SPELL, 0.25F, 0.25F, 0.25F, 1, 2);
					}
					
					if (MountManager.pet.get(p).hasMetadata("poseidonrider")) {
						UtilParticle.sendParticleToLocation(MountManager.pet.get(p).getLocation().add(0, 1, 0),
								Particle.DRIP_WATER, 0.25F, 0.25F, 0.25F,0.1F, 2);
					}
					

				
			}
				
				
		}
	}
		
		if (event.getType() == UpdateType.SEC) {

			for(Player p : UtilServer.getPlayers()){
				if(HasPet(p)){
					Double distanceBtw = p.getLocation().distance(MountManager.pet.get(p.getUniqueId()).getLocation());
					if(distanceBtw > 15D){
						if(p.isOnGround())
							MountManager.pet.get(p.getUniqueId()).teleport(p);
					}
				}
			}
		}
				}
	
	public static void registerEvents(Main plugin) {

		PluginManager pm = plugin.getServer().getPluginManager();
		pm.registerEvents(new AngelRider(), plugin);
		pm.registerEvents(new DarkRider(), plugin);
		pm.registerEvents(new GhostRider(), plugin);
		pm.registerEvents(new NyanRider(), plugin);
		pm.registerEvents(new PoseidonRider(), plugin);
		pm.registerEvents(new MountManager(), plugin);
		
	}

}
