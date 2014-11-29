package fr.coco_gigpn.prodigygadget.gadget.gadgets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
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
import fr.coco_gigpn.prodigygadget.util.UtilGear;
import fr.coco_gigpn.prodigygadget.util.UtilItem;
import fr.coco_gigpn.prodigygadget.util.UtilLag;
import fr.coco_gigpn.prodigygadget.util.UtilMath;
import fr.coco_gigpn.prodigygadget.util.UtilMovement;
import fr.coco_gigpn.prodigygadget.util.UtilParticle;
import fr.coco_gigpn.prodigygadget.util.UtilParticle.Particle;

public class PuppieLover implements Listener {

	private HashMap<Player, Long> _active = new HashMap<Player, Long>();
	private HashMap<Player, Double> _time = new HashMap<Player, Double>();
	HashMap<Player, BukkitRunnable> _cdRunnable = new HashMap<Player, BukkitRunnable>();

	private static HashMap<UUID, ArrayList<Wolf>> wolf = new HashMap<UUID, ArrayList<Wolf>>();
	
	@EventHandler
	 public void Onplayerquit(PlayerQuitEvent event) {
		 Player p = event.getPlayer();
		if(wolf.containsKey(p.getUniqueId())) {
			for(Wolf w : wolf.get(p.getUniqueId())) {
				if(w.isValid())
				w.remove();
			}
			wolf.remove(p.getUniqueId());
		}
		
		 
	 }
	 
	 @EventHandler
	  public void onLeave(PlayerKickEvent e)
	  {
		 Player p = e.getPlayer();
		 if(wolf.containsKey(p.getUniqueId())) {
				for(Wolf w : wolf.get(p.getUniqueId())) {
					if(w.isValid())
					w.remove();
				}
				wolf.remove(p.getUniqueId());
			}
	  }

	@EventHandler
	public void wolfDamage(EntityDamageEvent event) {
		Entity e = event.getEntity();

		if (e instanceof Wolf) {

			if (e.hasMetadata("nodamage")) {
				event.setCancelled(true);
			}

		}

	}

	@EventHandler
	public void PlayerDamage(EntityDamageByEntityEvent event) {

		if (!(event.getEntityType() == EntityType.PLAYER))
			return;
		if (!(event.getDamager().getType() == EntityType.WOLF))
			return;

		Wolf damager = (Wolf) event.getDamager();

		if (damager.hasMetadata("nodamage")) {

			event.setCancelled(true);

		}

	}

	int step = 1;
	@EventHandler
	public void ParticleAura(UpdateEvent event) {

		if (event.getType() == UpdateType.SEC) {

			if (UtilLag.ServerisLag() && Configs.stopParticleOnLag == true) {
				return;
			} else {

				for (UUID p : wolf.keySet()) {
					ArrayList<Wolf> wolfList = wolf.get(p);
					for (Wolf w : wolfList) {

						UtilParticle.sendParticleToLocation(w.getLocation().add(0 , 0.3f , 0),
								Particle.HEART, 0.3f, 0.3f, 0.3f, 0.1f,
								5);


					}
				}
			}
		}

	}
	
	


	public static void playPuppies(final Player p) {

		wolf.put(p.getUniqueId(), new ArrayList<Wolf>());

		for (int k = 0; k < 8; k++) {
			int x = UtilMath.randomRangeInt(-3, 3);
			int z = UtilMath.randomRangeInt(-3, 3);
			Wolf w = p.getWorld().spawn(p.getLocation().add(x , 0 , z),Wolf.class);
			w.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,Integer.MAX_VALUE, 1));
			w.setBaby();
			UtilMovement.makePet(w, p.getUniqueId());
			UtilItem.EntityToRemove(w, 40, true);
			((ArrayList<Wolf>) wolf.get(p.getUniqueId())).add(w);
			w.setMetadata("nodamage", new FixedMetadataValue(
					Main.schedule, "nodamage"));
		}

		Bukkit.getServer().getScheduler()
		.runTaskLater(Main.schedule, new Runnable() {
			public void run() {

				if (wolf.containsKey(p.getUniqueId())) {
					wolf.remove(p.getUniqueId());

				}
				GadgetManager.removeGadget(p);

			}
		}, 40 * 10L);

		if (!GadgetManager.hasGadget(p)) {
			GadgetManager.addGadget(p, GadgetType.PUPPIELOVER);
		}
	}

	public static void activate(Player p) {
		p.getOpenInventory().close();
		if (Configs.gadgetEnabledPosition == true) {
			p.getInventory().setItem((Configs.gadgetPosition - 1),
					new ItemStack(Material.AIR));
			p.getInventory().setItem(
					(Configs.gadgetPosition - 1),
					UtilItem.create(Material.BONE, 1,
							Messages.puppieloverName.replace("&", "§")));
		} else {
			p.getInventory().addItem(
					UtilItem.create(Material.BONE, 1,
							Messages.puppieloverName.replace("&", "§")));
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
				.isMat(event.getPlayer().getItemInHand(), Material.BONE)) {
			return;
		}
		ItemStack puppielover = new ItemStack(Material.BONE);
		UtilItem.setName(puppielover, Messages.puppieloverName.replace("&", "§"));
		if (!player.getItemInHand().getItemMeta().equals(puppielover.getItemMeta())) {
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
						.replace("%gadget", Messages.puppieloverName.replace("&", "§"))
						.replace("%time", time));
				return;
			}


			_time.put(player, Configs.puppieloverCooldown);
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

			playPuppies(player);

		}

	}

	public static double arrondi(double A, int B) {
		return (double) ((int) (A * Math.pow(10, B) + .5)) / Math.pow(10, B);
	}

}
