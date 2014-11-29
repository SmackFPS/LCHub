package fr.coco_gigpn.prodigygadget.gadget;

import java.util.HashMap;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.coco_gigpn.prodigygadget.gadget.gadgets.AxeLauncher;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.BatBlaster;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.DiamondParty;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.DiscoBall;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.EnderBall;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.FireworksPopper;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.FlowerPopper;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.Ghost;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.GravityStation;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.Grenade;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.HalloweenHead;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.HumanBomb;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.PaintballGun;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.PartyPopper;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.PuppieLover;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.QuakeGun;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.Rocket;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.SlimeHat;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.SmokeBomb;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.TntPopper;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.Tornado;
import fr.coco_gigpn.prodigygadget.util.Configs;
import fr.coco_gigpn.prodigygadget.util.UtilInventory;
import fr.coco_gigpn.prodigygadget.util.UtilItem;
import fr.coco_gigpn.prodigygadget.util.UtilLag;
import fr.coco_gigpn.prodigygadget.util.UtilParticle;
import fr.coco_gigpn.prodigygadget.util.UtilParticle.Particle;

public class GadgetManager implements Listener {
	
	public static HashMap<Player, GadgetType> gadget = new HashMap<Player, GadgetType>();
	
	public static enum GadgetType {
		BATBLASTER,
		DIAMONDPARTY,
		DISCOBALL,
		FIREWORKPOPPER,
		GRENADE,
		HALLOWEENHEAD,
		HUMANBOMB,
		PAINTBALLGUN,
		QUAKEGUN,
		SLIMEHAT,
		PARTYPOPPER,
		TNTPOPPER,
		SMOKEBOMB,
		GHOST,
		AXELAUNCHER,
		ROCKET,
		TORNADO,
		GRAVITYSTATION,
		ENDERBALL,
		FLOWERPOPPER,
		PUPPIELOVER
	}
	
	public static void addGadget(Player p , GadgetType type) {
		gadget.put(p, type);
	}
	
	public static void removeGadget(Player p) {
		gadget.remove(p);
	}
	
	public static boolean hasGadget(Player p) {
		if (gadget.containsKey(p)) {
			return true;
		} else {
			return false;
		}
	}
	
	 @EventHandler
	 public void OnPlayerLeft(PlayerQuitEvent e) {
		 
		 Player p = e.getPlayer();
		 if (Configs.removeGadgetOnleft == true) {
		UtilInventory.removeCurrentGadget(p);		
		 }	
		 
	 }
	 
	 @EventHandler
	  public void onLeave(PlayerKickEvent e)
	  {
		 Player p = e.getPlayer();
		 
		 if (Configs.removeGadgetOnleft == true) {
		 
		UtilInventory.removeCurrentGadget(p);
					
		 }	
	  }
	
	 @EventHandler
	    public void OnPlayerPickup(PlayerPickupItemEvent event) {
			
			if (event.getItem().hasMetadata("diamond")) {
				event.setCancelled(true);
				event.getItem().remove();
				if (UtilLag.ServerisLag() && Configs.stopParticleOnLag == true) {
					 return;
				 } else {
				UtilParticle.sendParticleToLocation(event.getItem().getLocation(), Particle.FIREWORKS_SPARK, 0, 0, 0, 0.3f, 5);
				 }
				
			}
			
			if (event.getItem().hasMetadata("slimeball")) {
				if (UtilLag.ServerisLag() && Configs.stopParticleOnLag == true) {
					 return;
				 } else {
				UtilParticle.sendParticleToLocation(event.getItem().getLocation(), Particle.SLIME, 0, 0, 0, 0.3f, 5);
				 }
				event.getItem().remove();
				event.setCancelled(true);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.EAT, 1, 1);
			}
			
			if (event.getItem().hasMetadata("flower")) {
				if (UtilLag.ServerisLag() && Configs.stopParticleOnLag == true) {
					 return;
				 } else {
				UtilParticle.sendPartileToPlayer(event.getPlayer(), Particle.MOB_SPELL, event.getItem().getLocation(), 0.3F, 0.3F, 0.3F, 1, 2);
				 }
				event.getItem().remove();
				event.setCancelled(true);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.EAT, 1, 1);
				event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 4  , 2));
			}
	    
	    
		if (event.getItem().hasMetadata("unpickable")) {
		event.setCancelled(true);
		}
		
}
	 
	 public static void clearAllGadget(Player p) {
		 if(TntPopper.party.containsKey(p)) {
			  Bukkit.getServer().getScheduler().cancelTask(TntPopper.party.get(p));
			 }
		 if(PartyPopper.party.containsKey(p)) {
			  Bukkit.getServer().getScheduler().cancelTask(PartyPopper.party.get(p));
			 }
		 if (Rocket.stationnaire.containsKey(p)) {
			 UtilItem.clearArmor(p);
				UtilInventory.restoreArmor(p);
				UtilInventory.forsakeArmor(p);
				Rocket.stationnaire.remove(p);
			 if(p.isInsideVehicle()) {
				 p.eject();
			 }
			 Rocket.arrowToRemove.get(p).remove();
		 }
		 if (Rocket.effect.containsKey(p)) {
			 UtilItem.clearArmor(p);
				UtilInventory.restoreArmor(p);
				UtilInventory.forsakeArmor(p);
				Rocket.effect.remove(p);
			 if(p.isInsideVehicle()) {
				 p.eject();
			 }
			 for(Entity entity : Rocket.fallingblocktoremove.get(p)) {
				 entity.remove();
				 
			 }
		 }
		 if(HalloweenHead.halloweenhead.containsKey(p)) {
		 Bukkit.getServer().getScheduler().cancelTask(HalloweenHead.halloweenhead.get(p));
		 HalloweenHead.halloweenhead.remove(p);
			UtilInventory.restoreArmor(p);
			UtilInventory.forsakeArmor(p);
		 }
		 
		 removeGadget(p);
		 
		 
	 }
	
	public static void registerEvents(Main plugin) {

		PluginManager pm = plugin.getServer().getPluginManager();
	    pm.registerEvents(new BatBlaster() , plugin);
	    pm.registerEvents(new PaintballGun() , plugin);
	    pm.registerEvents(new FireworksPopper() , plugin);
	    pm.registerEvents(new QuakeGun() , plugin);
	    pm.registerEvents(new DiscoBall() , plugin);
	    pm.registerEvents(new DiamondParty() , plugin);
	    pm.registerEvents(new HumanBomb() , plugin);
	    pm.registerEvents(new SlimeHat() , plugin);
	    pm.registerEvents(new HalloweenHead() , plugin);
	    pm.registerEvents(new PartyPopper() , plugin);
	    pm.registerEvents(new TntPopper() , plugin);
	    pm.registerEvents(new Grenade() , plugin);
	    pm.registerEvents(new SmokeBomb() , plugin);
	    pm.registerEvents(new Ghost() , plugin);
	    pm.registerEvents(new Rocket() , plugin);
	    pm.registerEvents(new AxeLauncher() , plugin);
	    pm.registerEvents(new Tornado() , plugin);
	    pm.registerEvents(new FlowerPopper() , plugin);
	    pm.registerEvents(new GravityStation() , plugin);
	    pm.registerEvents(new EnderBall() , plugin);
	    pm.registerEvents(new PuppieLover() , plugin);
	    pm.registerEvents(new GadgetManager() , plugin);
		
	}

}
