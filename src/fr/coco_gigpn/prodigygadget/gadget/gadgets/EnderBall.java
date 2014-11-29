package fr.coco_gigpn.prodigygadget.gadget.gadgets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

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

public class EnderBall implements Listener {

	private static HashMap<Player, ArrayList<Entity>> enderball = new HashMap<Player, ArrayList<Entity>>();
	private static HashMap<Player, ArrayList<Entity>> item = new HashMap<Player, ArrayList<Entity>>();
	private static ArrayList<UUID> victime = new ArrayList<>();

	private HashMap<Player, Long> _active = new HashMap<Player, Long>();
	private HashMap<Player, Double> _time = new HashMap<Player, Double>();
	HashMap<Player, BukkitRunnable> _cdRunnable = new HashMap<Player, BukkitRunnable>();

	public static void playEnderBall(final Player p) {
		enderball.put(p, new ArrayList<Entity>());
		item.put(p, new ArrayList<Entity>());
		@SuppressWarnings("deprecation")
		final WitherSkull skull = (WitherSkull) p.getWorld().spawn(
				p.getTargetBlock(null, 1).getLocation(), WitherSkull.class);
		skull.setDirection(new Vector(0, 0, 0));
		skull.setVelocity(new Vector(0, 0, 0));
		((ArrayList<Entity>) enderball.get(p)).add(skull);
		final Entity e = p.getWorld().dropItem(p.getLocation().add(0, 1, 0),
				UtilItem.create(Material.EYE_OF_ENDER));
		skull.setPassenger(e);
		((ArrayList<Entity>) enderball.get(p)).add(e);
		e.setMetadata("unpickable", new FixedMetadataValue(
				Main.schedule, "unpickable"));
		skull.setMetadata("notakedamage", new FixedMetadataValue(
				Main.schedule, "notakedamage"));


		Bukkit.getServer().getScheduler()
		.runTaskLater(Main.schedule, new Runnable() {
			public void run() {
				if (e.isValid()) {
					e.remove();
				}
				if (skull.isValid()) {
					skull.remove();
				}
				enderball.remove(p);
				GadgetManager.removeGadget(p);

			}
		}, 20 * 15);

		if (!GadgetManager.hasGadget(p)) {
			GadgetManager.addGadget(p, GadgetType.ENDERBALL);
		}

	}

	public static void playVelocityToRemove(final UUID p) {
		victime.add(p);

		Bukkit.getServer().getScheduler()
		.runTaskLater(Main.schedule, new Runnable() {
			public void run() {

				victime.remove(p);

			}
		}, 20 * 4);

	}

	@EventHandler
	public void skullDamage(EntityDamageEvent event) {
		Entity e = event.getEntity();

		if (e instanceof WitherSkull || e instanceof Entity) {

			if (e.hasMetadata("notakedamage")) {
				event.setCancelled(true);
			}

		}

	}

	@EventHandler
	public void PlayerChangedWorldEvent(PlayerChangedWorldEvent e) {
		Player p = e.getPlayer();
		if(victime.contains(p.getUniqueId())) {
			victime.remove(p.getUniqueId());
		}
	}

	@EventHandler
	public void ParticleAura(UpdateEvent event) {

		if (event.getType() == UpdateType.TICK) {

			if (UtilLag.ServerisLag() && Configs.stopParticleOnLag == true) {
				return;
			} else {


				for(Player p : enderball.keySet()) {
					for(Entity e : enderball.get(p)) {

						for(Entity victim : UtilEnt. getNearbyEntities(e.getLocation(), 10)) {

							if(victim instanceof Player)  {

								if(!enderball.containsKey(victim) && victim.isValid()) {
									playVelocityToRemove(victim.getUniqueId());
									if(victime.contains(victim.getUniqueId())) {
									victim.setVelocity(e.getLocation().toVector().subtract(victim.getLocation().toVector()).normalize());
									}
								}
							}
						}


						UtilParticle.sendParticleToLocation(e.getLocation(), Particle.ENCHANTMENT_TABLE, 0.5f, 0.5f ,0.5f, 0.5f, 2);
						UtilParticle.sendParticleToLocation(e.getLocation(), Particle.PORTAL, 0.5f, 0.5f ,0.5f, 0.5f, 2);

					}
				}



			}
		}
		if (event.getType() == UpdateType.SEC) {
			for(Player p : enderball.keySet()) {
				for(Entity e : enderball.get(p)) {
					e.getLocation().getWorld().playSound(e.getLocation(),Sound.PORTAL_TRIGGER,0.3f, 2);
				}
			}
		}

	}

	public static void activate(Player p) {
		p.getOpenInventory().close();
		if (Configs.gadgetEnabledPosition == true) {
			p.getInventory().setItem((Configs.gadgetPosition - 1),
					new ItemStack(Material.AIR));
			p.getInventory().setItem(
					(Configs.gadgetPosition - 1),
					UtilItem.create(Material.EYE_OF_ENDER, 1,
							Messages.enderballName.replace("&", "§")));
		} else {
			p.getInventory().addItem(
					UtilItem.create(Material.EYE_OF_ENDER, 1,
							Messages.enderballName.replace("&", "§")));
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
				.isMat(event.getPlayer().getItemInHand(), Material.EYE_OF_ENDER)) {
			return;
		}
		ItemStack enderball= new ItemStack(Material.EYE_OF_ENDER);
		UtilItem.setName(enderball, Messages.enderballName.replace("&", "§"));
		if (!player.getItemInHand().getItemMeta().equals(enderball.getItemMeta())) {
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
						.replace("%gadget", Messages.enderballName.replace("&", "§"))
						.replace("%time", time));
				return;
			}

			event.setCancelled(true);
			_time.put(player, Configs.enderballCooldown);
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


			playEnderBall(player);

		}

	}

	public static double arrondi(double A, int B) {
		return (double) ((int) (A * Math.pow(10, B) + .5)) / Math.pow(10, B);
	}
}
