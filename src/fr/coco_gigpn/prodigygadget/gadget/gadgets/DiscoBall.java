package fr.coco_gigpn.prodigygadget.gadget.gadgets;

import java.util.HashMap;
import java.util.Random;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import fr.coco_gigpn.prodigygadget.gadget.GadgetManager;
import fr.coco_gigpn.prodigygadget.gadget.GadgetManager.GadgetType;
import fr.coco_gigpn.prodigygadget.morph.MorphManager;
import fr.coco_gigpn.prodigygadget.util.Configs;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilBlock;
import fr.coco_gigpn.prodigygadget.util.UtilFirework;
import fr.coco_gigpn.prodigygadget.util.UtilFlash;
import fr.coco_gigpn.prodigygadget.util.UtilGear;
import fr.coco_gigpn.prodigygadget.util.UtilInventory;
import fr.coco_gigpn.prodigygadget.util.UtilItem;
import fr.coco_gigpn.prodigygadget.util.UtilLag;
import fr.coco_gigpn.prodigygadget.util.UtilMath;
import fr.coco_gigpn.prodigygadget.util.UtilParticle;
import fr.coco_gigpn.prodigygadget.util.UtilParticle.Particle;

public class DiscoBall implements Listener {

	private HashMap<Player, Long> _active = new HashMap<Player, Long>();
	private HashMap<Player, Double> _time = new HashMap<Player, Double>();
	HashMap<Player, BukkitRunnable> _cdRunnable = new HashMap<Player, BukkitRunnable>();

	@EventHandler
	public void invInteract(InventoryClickEvent e)
	{
		if ((UtilInventory.armor.containsKey(e.getWhoClicked())) && (!e.isCancelled())) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void OnPlayerDrop(PlayerDropItemEvent event) {
		if (!UtilInventory.armor.containsKey(event.getPlayer())) return;


		event.setCancelled(true);
	}

	public void playDiscoBall(final Player p) {
		if(!MorphManager.hasMorph(p)) {

			UtilInventory.saveArmor(p);

			final Location l = new Location(p.getWorld() , p.getLocation().getX() , p.getLocation().getY() + 8 , p.getLocation().getZ());

			final Location l3 = new Location(p.getWorld() , p.getLocation().getX() , p.getLocation().getY() + 8 , p.getLocation().getZ());

			if (p.getWorld().getBlockAt(l).getType() == Material.AIR) {

				UtilFlash.addRed(p);


				final int i = Bukkit.getScheduler()
						.runTaskTimer(Main.schedule, new Runnable() {

							private Random r = new Random();
							PlayerInventory inv = p.getInventory();

							@SuppressWarnings("deprecation")
							public void run() {

								p.getWorld().getBlockAt(l)
								.setType(Material.STAINED_GLASS);
								p.getWorld().getBlockAt(l)
								.setData((byte) UtilMath.randRange(1, 15));

								final Location l2 = new Location(p.getWorld() , l.getX() + UtilMath.randomRangeInt(-6, 6), l.getY() + UtilMath.randomRangeInt(-6, 6), l.getZ() + UtilMath.randomRangeInt(-6, 6));

								if (UtilLag.ServerisLag() && Configs.stopFireworkOnLag == true) {
									return;
								} else {

									UtilFirework.launchRandomFirework(l2);

								}
								Color c = Color.fromRGB(r.nextInt(255), r.nextInt(255), r.nextInt(255));



								inv.setHelmet(UtilInventory.getColorArmor(Material.LEATHER_HELMET, c)); 
								inv.setChestplate(UtilInventory.getColorArmor(Material.LEATHER_CHESTPLATE, c));
								inv.setLeggings(UtilInventory.getColorArmor(Material.LEATHER_LEGGINGS, c));
								inv.setBoots(UtilInventory.getColorArmor(Material.LEATHER_BOOTS, c));




							}
						}, 10L, 10L).getTaskId();

				if (Configs.showLaser == true) {

					if (UtilLag.ServerisLag() && Configs.stopParticleOnLag == true) {
						return;
					} else {

						final int i2 = Bukkit.getScheduler()
								.runTaskTimer(Main.schedule, new Runnable() {


									public float lenght = 10;
									Vector direction = UtilMath.getRandomVectorline();
									int step = 0;

									/**
									 * Turns the line by this angle each iteration around
									 * the x-axis
									 */
									public double angularVelocityX = Math.PI / 170;

									/**
									 * Turns the line by this angle each iteration around
									 * the z-axis
									 */
									public double angularVelocityZ = Math.PI / 170;

									public void run() {



										double xRotation = 0, yRotation = 0, zRotation = 0;

										xRotation = step * angularVelocityX;
										yRotation = step;
										zRotation = step * angularVelocityZ;

										direction.setY(-Math.abs(direction.getY()));

										for (int i = 0; i < 100; i++) {
											float ratio = (float) i * lenght / 100;
											Vector v = direction.clone().multiply(ratio);
											UtilMath.rotateVector(v, xRotation, yRotation,
													zRotation);

											UtilParticle.sendParticleToLocation(l3.add(v),Particle.RED_DUST, 0, 0,
													0, 1, 1);
											l3.subtract(v);
										}
										step++;


									}

								}, 1L, 1L).getTaskId();

						Bukkit.getServer().getScheduler()
						.runTaskLater(Main.schedule, new Runnable() {
							public void run() {
								Bukkit.getServer().getScheduler().cancelTask(i2);
							}
						}, 600L);
					}

				}

				Bukkit.getServer().getScheduler()
				.runTaskLater(Main.schedule, new Runnable() {
					public void run() {
						Bukkit.getServer().getScheduler().cancelTask(i);
						p.getWorld().getBlockAt(l).setType(Material.AIR);
						UtilFlash.removeRed(p);
						UtilItem.clearArmor(p);
						UtilInventory.restoreArmor(p);
						UtilInventory.forsakeArmor(p);

						GadgetManager.removeGadget(p);
					}
				}, 600L);

			}

			if (!GadgetManager.hasGadget(p)){
				GadgetManager.addGadget(p, GadgetType.DISCOBALL);
			}
		}
	}

	public static void activate(Player p) {
		p.getOpenInventory().close();
		if (Configs.gadgetEnabledPosition == true) {
			p.getInventory().setItem((Configs.gadgetPosition - 1), new ItemStack(Material.AIR));
			p.getInventory().setItem((Configs.gadgetPosition - 1), UtilItem.create(Material.BEACON, 1, Messages.discoballName.replace("&", "§")));
		} else {
			p.getInventory().addItem(UtilItem.create(Material.BEACON, 1, Messages.discoballName.replace("&", "§")));
		}

	}


	@EventHandler
	public void Onplayerquit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		if(UtilInventory.armor.containsKey(player)) {
			UtilInventory.restoreArmor(player);
			UtilInventory.forsakeArmor(player);
		}
	}

	@EventHandler
	public void onLeave(PlayerKickEvent e)
	{
		Player player = e.getPlayer();
		if(UtilInventory.armor.containsKey(player)) {
			UtilInventory.restoreArmor(player);
			UtilInventory.forsakeArmor(player);
		}
	}


	@SuppressWarnings("deprecation")
	@EventHandler
	public void Activate(final PlayerInteractEvent event)
	{
		final Player player = event.getPlayer();
		if ((event.getAction() != Action.RIGHT_CLICK_AIR) && (event.getAction() != Action.RIGHT_CLICK_BLOCK)) {
			return;
		}
		if (UtilBlock.usable(event.getClickedBlock())) {
			return;
		}
		if (!UtilGear.isMat(event.getPlayer().getItemInHand(), Material.BEACON)) {
			return;
		}
		ItemStack discoball = new ItemStack(Material.BEACON);
		UtilItem.setName(discoball, Messages.discoballName.replace("&", "§"));
		if (!player.getItemInHand().getItemMeta().equals(discoball.getItemMeta())) {
			return;
		}
		if(Configs.OnebyOneGadget == true && GadgetManager.hasGadget(player) && !_time.containsKey(player)) {
			player.sendMessage(Messages.onegadgetbygadgetmessage.replace("&", "§"));
		} else {

			event.setCancelled(true);
			player.updateInventory();
			if(_time.containsKey(player)){
				double t = arrondi(_time.get(player), 1);
				String time = Double.toString(t);
				player.sendMessage(Messages.cooldownMessage.replace("&", "§").replace("%gadget", Messages.discoballName.replace("&", "§")).replace("%time", time));
				return;
			}

			_time.put(player, Configs.discoballCooldown);
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

			playDiscoBall(player);
		}

	}

	public static double arrondi(double A, int B) {
		return (double) ( (int) (A * Math.pow(10, B) + .5)) / Math.pow(10, B);
	}


}
