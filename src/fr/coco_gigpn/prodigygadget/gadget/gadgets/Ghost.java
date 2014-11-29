package fr.coco_gigpn.prodigygadget.gadget.gadgets;

import java.util.ArrayList;
import java.util.HashMap;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.coco_gigpn.prodigygadget.gadget.GadgetManager;
import fr.coco_gigpn.prodigygadget.gadget.GadgetManager.GadgetType;
import fr.coco_gigpn.prodigygadget.updater.UpdateType;
import fr.coco_gigpn.prodigygadget.updater.event.UpdateEvent;
import fr.coco_gigpn.prodigygadget.util.Configs;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilBlock;
import fr.coco_gigpn.prodigygadget.util.UtilEnt;
import fr.coco_gigpn.prodigygadget.util.UtilGear;
import fr.coco_gigpn.prodigygadget.util.UtilItem;
import fr.coco_gigpn.prodigygadget.util.UtilLag;
import fr.coco_gigpn.prodigygadget.util.UtilParticle;
import fr.coco_gigpn.prodigygadget.util.UtilParticle.Particle;

public class Ghost implements Listener {

	private HashMap<Player, Long> _active = new HashMap<Player, Long>();
	private HashMap<Player, Double> _time = new HashMap<Player, Double>();
	HashMap<Player, BukkitRunnable> _cdRunnable = new HashMap<Player, BukkitRunnable>();

	private static HashMap<Player, ArrayList<Bat>> bat = new HashMap<Player, ArrayList<Bat>>();
	private static HashMap<Player, ArrayList<Zombie>> zomb = new HashMap<Player, ArrayList<Zombie>>();

	@EventHandler
	public void ghostDamage(EntityDamageEvent event) {
		Entity e = event.getEntity();

		if (e instanceof Bat) {

			if (e.hasMetadata("nodamage")) {
				event.setCancelled(true);
			}

		}

		if (e instanceof Zombie) {

			if (e.hasMetadata("nodamage")) {
				event.setCancelled(true);
			}
		}

	}

	@EventHandler
	public void PlayerDamage(EntityDamageByEntityEvent event) {

		if (!(event.getEntityType() == EntityType.PLAYER))
			return;
		if (!(event.getDamager().getType() == EntityType.ZOMBIE))
			return;

		Zombie damager = (Zombie) event.getDamager();

		if (damager.hasMetadata("nodamage")) {

			event.setCancelled(true);

		}

	}

	int step = 1;
	@EventHandler
	public void ParticleAura(UpdateEvent event) {
	
		if (event.getType() == UpdateType.TICK) {

			if (UtilLag.ServerisLag() && Configs.stopParticleOnLag == true) {
				return;
			} else {
				
				for (Player p : zomb.keySet()) {
					ArrayList<Zombie> zombList = zomb.get(p);
					for (Zombie z : zombList) {
				
								UtilParticle.sendParticleToLocation(z.getLocation().add(0 , 1 , 0),
										Particle.CLOUD, 0.3f, 0.3f, 0.3f, 0.1f,
										1);
						

					}
				}
			}
		}

		if (event.getType() == UpdateType.SLOW) {
			
	

			for (Player p : bat.keySet()) {
				ArrayList<Bat> batList = bat.get(p);
				for (Bat b : batList) {

						b.getWorld().playSound(b.getLocation(),
								Sound.WOLF_HOWL, 1, 2);
				
				}

			}
		}
		
		if (event.getType() == UpdateType.SEC) {
			for (Player p : zomb.keySet()) {
				ArrayList<Zombie> zombList = zomb.get(p);
				for (Zombie z : zombList) {
					if ((step % 2) == 0) {
						
						UtilEnt.setHelmet(z, UtilItem.create(Material.JACK_O_LANTERN));

					} else {
		
						UtilEnt.setHelmet(z, UtilItem.create(Material.PUMPKIN));
					}
				}
			}
			step++;
		
		}
		if (event.getType() == UpdateType.FAST) {

			
			for (Player p : bat.keySet()) {
				ArrayList<Bat> batList = bat.get(p);
				for (Bat b : batList) {
					Location l = b.getLocation();
					Location loc = b.getEyeLocation().toVector()
							.add(l.getDirection().multiply(4))
							.toLocation(b.getWorld(), l.getYaw(), l.getPitch());
					Entity e = b.getWorld().spawn(loc, Snowball.class);
				
					e.setMetadata("ghost", new FixedMetadataValue(Main.schedule, "ghost"));
				}
			}
			
		}
	}
	
	@EventHandler
	public void EggFirework(ProjectileHitEvent event) {

		if (event.getEntity().getType() == EntityType.SNOWBALL) {

			Entity e = event.getEntity();

			if (e.hasMetadata("ghost")) {
				Location l = e.getLocation();
				if (UtilLag.ServerisLag() && Configs.stopParticleOnLag == true) {
					return;
				} else {
					
					UtilParticle.sendParticleToLocation(l, Particle.FLAME, 0.4f,0.8f, 0.4f, 0.1f, 10);
						UtilParticle.sendParticleToLocation(l, Particle.WITCH_MAGIC, 0.4f,0.8f, 0.4f, 0.1f, 10);
					
				}
			}

		}
	}

	
	public static void playGhost(final Player p) {
		bat.put(p, new ArrayList<Bat>());
		zomb.put(p, new ArrayList<Zombie>());

		for (int k = 0; k < 8; k++) {
			Bat b = p.getWorld().spawn(p.getLocation().add(0, 2, 0), Bat.class);
			Zombie z = p.getWorld().spawn(p.getLocation().add(0, 2, 0),
					Zombie.class);
			z.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,
					Integer.MAX_VALUE, 1));
			b.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,
					Integer.MAX_VALUE, 1));
			z.setBaby(true);
			b.setPassenger(z);
			UtilEnt.setHelmet(z, UtilItem.create(Material.PUMPKIN));
			UtilItem.EntityToRemove(b, 40, true);
			UtilItem.EntityToRemove(z, 40, true);
			((ArrayList<Bat>) bat.get(p)).add(b);
			((ArrayList<Zombie>) zomb.get(p)).add(z);
			b.setMetadata("nodamage", new FixedMetadataValue(
					Main.schedule, "nodamage"));
			z.setMetadata("nodamage", new FixedMetadataValue(
					Main.schedule, "nodamage"));
		}

		Bukkit.getServer().getScheduler()
				.runTaskLater(Main.schedule, new Runnable() {
					public void run() {

						if (bat.containsKey(p)) {
							bat.remove(p);

						}
						if (zomb.containsKey(p)) {
							zomb.remove(p);

						}
						GadgetManager.removeGadget(p);

					}
				}, 40 * 10L);

		if (!GadgetManager.hasGadget(p)) {
			GadgetManager.addGadget(p, GadgetType.GHOST);
		}
	}

	public static void activate(Player p) {
		p.getOpenInventory().close();
		if (Configs.gadgetEnabledPosition == true) {
			p.getInventory().setItem((Configs.gadgetPosition - 1),
					new ItemStack(Material.AIR));
			p.getInventory().setItem(
					(Configs.gadgetPosition - 1),
					UtilItem.create(Material.FEATHER, 1,
							Messages.ghostName.replace("&", "§")));
		} else {
			p.getInventory().addItem(
					UtilItem.create(Material.FEATHER, 1,
							Messages.ghostName.replace("&", "§")));
		}
	}

	@EventHandler
	public void Activate(final PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		if ((event.getAction() != Action.RIGHT_CLICK_AIR)
				&& (event.getAction() != Action.RIGHT_CLICK_BLOCK)) {
			return;
		}
		if (UtilBlock.usable(event.getClickedBlock())) {
			return;
		}
		if (!UtilGear
				.isMat(event.getPlayer().getItemInHand(), Material.FEATHER)) {
			return;
		}
		ItemStack ghost = new ItemStack(Material.FEATHER);
		UtilItem.setName(ghost, Messages.ghostName.replace("&", "§"));
		if (!player.getItemInHand().getItemMeta().equals(ghost.getItemMeta())) {
			return;
		}
		if (Configs.OnebyOneGadget == true && GadgetManager.hasGadget(player)
				&& !_time.containsKey(player)) {
			player.sendMessage(Messages.onegadgetbygadgetmessage.replace("&", "§"));
		} else {
			event.setCancelled(true);
			if (_time.containsKey(player)) {
				double t = arrondi(_time.get(player), 1);
				String time = Double.toString(t);
				player.sendMessage(Messages.cooldownMessage.replace("&", "§")
						.replace("%gadget", Messages.ghostName.replace("&", "§"))
						.replace("%time", time));
				return;
			}
			

			_time.put(player, Configs.ghostCooldown);
			_cdRunnable.put(player, new BukkitRunnable() {
				public void run() {
					_time.put(player, _time.get(player) - 0.1D);
					if (_time.get(player) < 0.01) {
						_time.remove(player);
						_cdRunnable.remove(player);
						cancel();
					}
				}
			});
			_cdRunnable.get(player).runTaskTimer(Main.schedule, 2, 2);

			this._active.put(player, Long.valueOf(System.currentTimeMillis()));

			playGhost(player);

		}

	}

	public static double arrondi(double A, int B) {
		return (double) ((int) (A * Math.pow(10, B) + .5)) / Math.pow(10, B);
	}

}
